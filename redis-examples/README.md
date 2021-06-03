# Redis
确定已启动redis服务

### Spring Cache Redis
可通过spring-boot-starter-actuator监控缓存命中率等数据  
/spring-examples，有基于自定义注解/AOP/redis，模拟spring-cache-redis的实现  

### Redis Hash Data type  & HashOperations
redisTemplate.opsForHash()方法，获取操作hash数据类型的对象HashOperations<K, HK, HV>  

|命令 |HashOperations<K, HK, HV>中方法 |说明 |
|---|---|---|
|hset key field value |void put(K, HK, HV) |设置指定hash的字段值，有则覆盖|
|hsetnx key field value |Boolean putIfAbsent(K, HK, HV) |字段不存在则存入；存在则忽略|
|hget key field |HV get(K, HK) |获取指定hash中指定字段的值|
|hincrby key field increment |long increment(K, HK, L) |指定hash中指定字段增加/减少指定字段值，increment为负数则减.<br>指定字段不存在，则等价于执行put(K, HK, L)赋值 |
|hgetall key|Map<HK, HV> entries(K)|获取hash的全部字段值|
|hexists key field|hasKey(K, HK)|指定hash是否存在指定字段|

### Limited-Time Offers
秒杀抢购3件套。限流/内存单线程数据库/消息队列

示例仅演示基于hash数据类型的基本redis的使用。不讨论入口限流/RocketMQ

OrderService，向redis添加抢购商品数据；抢购方法  
OrderServiceTest，模拟20K抢购100商品  