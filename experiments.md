# Experiments
### 实验四 MyBatis持久化实验
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


### 实验五 MyBatis关联查询实验
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
