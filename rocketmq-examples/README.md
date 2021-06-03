# RocketMQ Examples
ali转给apache社区的开源消息中间件，实现了JMS规范  
可用于微服务间异步处理消息，和实现分布式事务

### RocketMQ Server & Broker
rocketmq，本地运行的脚本基于java8，修改脚本使其基于java11运行一直不好使  
反正最终部署也是docker，因此在本地使用虚拟机+docker开发测试  

rocketmq-docker官方提供基于指定版本创建docker rocketmq镜像的脚本。
但在docker-hub提供的官方最新版为4.6.0，而当前最新版4.8.0  

docker挂载的目录必须有rocketmq server(uid3000)进程操作权限  
broker容器需要暴露10909/10911/10912端口，服务器内部部署的微服务间调用时不应暴露  

broker对外网暴露时，必须显式指定broker.conf中brokerIP1为外网IP。否则获取的是docker容器IP。
和nacos很像，果然是ali一家亲。

创建broker容器时可以声明挂载目录的uid权限。如何动态设置宿主IP？    

rocketmq-docker很不友好

### rocketmq-spring-boot-starter
rocketmq.producer.group属性必须声明，否则无法启动  
RocketMQAutoConfiguration上有一个@ConditionalOnBean注解，注入以上配置值，没有无法启动  
一个没用的破配置排查了一晚上  

消息体是普通字符串，转字节数组，发出  
消息体是对象，转json字符串，转字节数组，发送  

支持同步/异步的sendAndReceive()方法，直接获取返回的消息结果  

### RocketMQListener & @RocketMQMessageListener
@RocketMQMessageListener，声明监听的topic  
RocketMQListener，声明泛型的消息体类型，当监听的topic有消息时自动回调onMessage()方法  

服务启动过程：
- 与RockerMQ Server服务器建立连接，服务器返回分配的Broker服务器地址
- 与Broker服务器建立连接
- 与Broker互交，发送/接收消息

因此，项目刚启动时，首次接收消息会有较长的延迟

### Distributed Transactions
例如，通过微信交电费  
用户银行账户转账100至微信账户  
微信与电力公司收款服务异步互交，发送付款100请求  
成功，微信账户转账100至电力公司账户  
失败，电力公司收款服务系统维护/临时网络中断/电力中断等等，一段时间内(1小时)多次尝试无效取消交易  
微信转账100至用户银行账户  
银行流水2条记录，转出微信100，微信转回100，账户余额不变  

单服务同步事务，付款，同步等待电力公司结果，转账业务失败数据直接回滚，账户余额不变。但账户流水无任何交易信息记录  

同步单服务事务/异步分布式事务最终结果相同，但意义与实现手段完全不同

基于异步消息处理的分布式事务，实现最终一致性而非强一致性，需手动实现失败的业务补偿。其他优缺点自己查

### RocketMQ Transaction Messages
事务消息的特点是，先发送消息至消息服务器，但禁止消费此消息  
发送源将消息状态置为提交状态，允许消费消息；置于回滚状态，服务器删除消息；  
置于未知状态，服务器继续禁止此消息消费，并持续回查发送源此消息状态(可设置)

### RocketMQTransactionListener
基于RocketMQ RocketMQTransactionListener接口的事务消息执行流程
- RocketMQTemplate.sendMessageInTransaction()方法，发送事务消息至消息服务器
- 成功后，回调executeLocalTransaction()方法确定此消息状态
- COMMIT状态，消息服务器允许消费消息
- ROLLBACK状态，消息服务器删除消息
- UNKNOWN状态，消息服务器禁止消费消息，并持续回调checkLocalTransaction()方法回查消息状态


项目中只能有一个RocketMQTransactionListener，全局监听事务消息  
天，如果一个微服务项目包含多个topic的事务消息，事务消息逻辑处理要写在一起？通过判断消息头数据执行那个？？？

回调executeLocalTransaction()中注入的消息对象中的消息体，竟然是字节数组？？而最终监听消费的消息体是反序列化的对象

### Finally
用户转账微信成功，微信发送付款请求至电力公司成功，电力公司最终交易操作失败。  
电力公司如何转账回微信，微信再转账回用户？  
通过异步消息服务设计  
