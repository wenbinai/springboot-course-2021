# Experiments
### 实验4 MyBatis持久化实验
**实验原理** 
理解面向对象持久化技术的意义及作用，掌握MyBatis/MyBatis-Plus持久化框架的基本使用方法

**实验目的**  
掌握基于idea的基本springboot工程项目的创建方法  
理解maven项目结构，依赖配置声明管理  
掌握DO类与数据表的映射规则  
掌握基本的属性映射规则  
理解雪花算法主键生成策略与特点  
掌握基于MP框架的基本CURD操作  

**实验内容**   
创建springboot项目，添加MP框架相关依赖  
创建springboot项目配置文件，添加数据源/logging等相关配置  
创建数据表生成脚本，编写user表结构  
打开idea database视图，添加连接远程数据库  
执行脚本生成测试数据表  
编写user表对应的DO类，按映射规则声明属性  
编写操作DO类的mapper组件接口  
接口中编写insert()/update()/remove()/get()/list()等CURD方法  
通过注解声明方法执行的SQL语句  

编写测试类，启动事务及回滚等设置  
注入Mapper组件，执行测试编写的持久化方法


### 实验5 MyBatis关联查询实验
**实验原理** 
基于MyBatis持久化框架的复杂关系数据的关联查询

**实验目的**  
理解仅索引非外键关系的作用  
掌握MyBatis xml映射配置的编写方法  
掌握编写关联查询语句的方法  
掌握编写结果集映射的方法  
理解Mapper接口中“不支持方法重载”的原因  
  

**实验内容**   
结合MyBatis基本实验内容，添加address数据表  
其中在address中添加user表主键作为字段并设置索引，user一对多address，address一对一user  
创建封装user/address集合信息的UserDTO类  
编写address对应的Mapper接口  
创建Mapper对应的xml映射规则文件  
编写基于user主键同时查询所有addresses的关联查询语句  
编写结果集映射封装规则  
在Mapper接口添加执行方法  

编写测试类，启动事务及回滚等设置  
注入Mapper组件，执行测试编写的持久化方法

### 实验6 Spring AOP实验
**实验原理**  
通过Spring容器创建组件对象，基于组件间的依赖关系，由Spring容器自动注入组件对象供调用者使用，从而降低代码的耦合性。  
基于自定义注解以及AOP切面，通过对指定类型/方法/方法参数的拦截，实现非侵入式的业务修改，降低业务逻辑之间的耦合。  

**实验目的**  
理解依赖注入原理及程序设计思想  
理解并掌握Spring的配置方法  
理解并掌握Spring容器的启动方法  
理解并掌握基于Spring容器获取各种类型对象的方法  
理解并掌握基于注解的Spring组件声明方法  
理解并掌握基于注解的依赖注入的方法  
理解AOP设计思想及原理  
理解并掌握Spring AOP的配置方法  
理解并掌握Spring AOP切面的定义方法  
理解并掌握Spring AOP切入点表达式  
理解并掌握自定义注解的声明、使用方法  
理解并掌握Spring AOP对自定义注解的拦截表达式  
理解并掌握Around类型通知的定义方法  
理解并掌握ProceedingJoinPoint接口类型对象获取方法  
理解并掌握目标对象、拦截的方法、方法中的参数的获取方法以及替换方法  
理解并掌握被拦截业务逻辑继续执行的方法  

**实验内容**  
基创建一个支持AOP的springboot项目  
修改application配置，添加log等基本配置  

需求0  
在service下，创建UserService组件，添加buyCar()方法  
在aspect下，创建MyAspect切面实现类，编写一个切入所有以buy前缀的方法，计算方法的执行时间  
创建测试类，注入UserService组件，测试buyCar()方法的执行是否被切入  

需求+1  
在aspect下，创建支持运行时，类型及方法级，自定义注解MyInterceptor  
在MyInterceptor注解中，创建权限枚举类型AuthorityType，声明若干权限
在MyAspect切面类中，编写切入所有类型级方法级修饰的MyInterceptor注解，打印显示被切入方法的权限类型  
为UserService添加自定义权限注解，在UserSerivce编写添加方法addUser()，添加自定义权限注解  

在测试类中，测试addUser()方法的执行是否被权限切面切入  

### 实验7 SpringMVC实验
**实验原理**  
基于SpringMVC实现对HTTP请求的映射与处理。  

**实验目的**  
理解并掌握REST API的设计及实现方法  
理解并掌握controller组件的声明方法  
理解并掌握HTTP GET/POST/PATCH请求的处理  
理解并掌握JSON数据结构   
理解并掌握基本基于Jackson的数据响应   
理解并掌握基于json请求数据的封装  
理解并掌握基于idea的HTTP REST API模拟请求脚本  

**实验内容**  
创建一个支持SpringMVC的springboot项目  
添加log/jackson忽略空属性等基本配置  

需求0  
创建ResultVO类，统一处理响应数据  
在controller下，创建UserController控制组件，添加REST注解，声明/api根路径  
创建处理/index，请求方法，返回ResultVO对象，封装普通字符串即可  

需求+1  
在dto下，创建User类，添加用户名/密码，为密码添加序列化忽略注解，当序列化user对象时，将忽略密码属性  
```shell
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
```
在控制组件中模拟一个users集合，模拟封装若干user对象  
创建处理/users，get请求方法，封装users集合   
创建处理/users/{uid}，get请求方法，获取请求地址user ID参数，基于参数从users集合中获取对象，封装到返回。注意Map.of()不能添加null，因此需处理如果集合中没有指定ID对象的实现  
创建处理/users，post请求方法，将请求数据封装到User对象，作为参数注入方法，将对象添加到users集合  

在test下，创建http文件夹，创建user.http测试脚本测试请求  

### 实验8 SpringMVC拦截器实验
**实验原理**  
通过定义SpringMVC拦截器对指定请求的拦截，实现对用户请求权限的验证。

**实验目的**  
理解拦截器的作用与意义
掌握拦截器的声明配置方法
掌握拦截器过滤指定请求的配置方法
掌握拦截器的实现方法
了解拦截器回调方法的区别和使用场景
掌握在拦截器中请求权限验证 

**实验内容**  
创建一个支持SpringMVC的springboot项目

需求0  
添加Spring Security框架中的加密解密依赖。注意：不是在构建工程时添加Spring Security整体框架，仅需其中的crypto依赖
在application中添加log/jackson忽略空属性配置，以及自定义加密密钥与盐值  

在component下，创建整合Jackson的加密/解密组件EncryptorComponent，注入密钥与盐值，实现对给定Map对象的加密/解密，对无法解密异常，转抛为自定义异常  
创建SecurityConfiguration配置类，创建基于BCryptPasswordEncoder算法的PasswordEncoder对象，注入spring容器  
在dto下创建User类，添加userName/password属性，getter/setter注解等；添加password的序列化忽略注解  
在controller下，创建LoginController组件，注入密码编码组件，注入加密/解密组件。组件内创建一个Map，模拟基于用户名保存用户对象  
创建处理/register post请求方法，将注册用户密码编码，将user对象保存在Map对象中(模拟数据库)  
创建处理/login post请求方法，判断登录用户是否存在，用户密码是否正确；并将用户名加密保存在响应header中  
在interceptor下，创建LoginInterceptor拦截器，从请求header中获取token数据，解密并将解密出的用户名置于requestattribute中  

创建实现WebMvcConfigurer接口的配置实现类WebMvcConfiguration，重写addInterceptors()方法，注册拦截器，设置拦截规则过滤  

在LoginController组件中，创建处理/index get请求，在方法中注入requestattribute中的用户名，并将用户名返回

测试  
在test下，创建http目录，创建login.http测试脚本  
编写注册测试脚本  
编写登录测试脚本测试，在响应中获取token数据  
编写/index请求测试脚本，在header中携带token发起请求  
