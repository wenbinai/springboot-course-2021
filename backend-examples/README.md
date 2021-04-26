# backend-examples

后端单微服务整合示例，为前端项目提供支持

### API & Swagger3
http://114.116.213.241:8080/swagger-ui/index.html  
超管账号初始化用户名密码：admin/admin  

修改vue.config.js文件，添加请求代理设置，在开发环境下由vue实现跨域请求，生产环境下无效。  
注释掉main.ts中的mock启动代码，或在mock过滤/api/请求前缀    
vue example中创建组件，完成基本的前后端联调  
通过swagger查看API接口的请求类型/参数等  
例如，/api/welcome，为无需登录的请求  
登录成功后，会在header返回token；admin有权限添加教师

### Initialization Data
通过application.yml数据源配置创建数据库，通过schema.sql初始化数据表  
实现InitializingBean接口的组件，重写afterPropertiesSet()方法，在spring容器启动后，回调。  
可添加需要通过业务逻辑方法执行的初始化行为。例如，初始化超级管理员账号，需要通过组件编码密码存入数据库。  

### Login
登录成功后，后端仅在header返回token/加密的role(约定)，不返回首页组件加载数据。
前端登录成功，获取token/role保存在SessionStorage，路由到后台首页组件，在首组件发出数据请求。  

为什么首页组件数据不在登陆后直接返回，而是通过第二次请求获取？  

当用户在登录后刷新后台首页时，store数据对象会清空，首页组件数据需要渲染填充，但是数据却是执行登录后返回的，则将无法获取渲染数据。  
因此，在加载后台首页组件时请求首页数据返回渲染

规则，组件需要的远程数据尽可能由组件自己请求，保证组件的内聚独立性，便于维护测试。

### Interceptor
后端在LoginInterceptor中解密出token中的uid/role数据，置于request attribute作用域。由于每次请求创建一个请求对象，
且请求对象一直持续到本次互交结束。因此，对象可在后续拦截器/控制层方法中获取并使用。  
在controller方法直接注入请求对象，或通过@RequestAttribute注解注入作用域中指定属性数据  

### Service
业务逻辑处理是系统的核心

分析系统功能，会发现功能均是围绕几个核心实体展开，则以此若干实体为中心创建service层，粗粒度的用例设计。

设计业务层时，可以与权限无关。  
例如，所有角色均有修改密码功能，创建CommonController组件接收角色通用请求，创建UserService组件处理用户CURD，以及用户自己的个人信息维护等操作

例如，学生/教师功能角色分明，但均包含作业相关功能。基于角色设计TeacherController/StudentController组件接收教师操作请求，也便于统一路径拦截请求鉴权。
设计HomeworkService组件处理教师对课程的CURD ，以及学生的查询/提交等操作。

请求操作角色权限(粗粒度)由Interceptor负责。是否能够执行由controller负责(教师角色均由删除课程的权限，但仅能删除自己创建的课程，细粒度)，
controller验证后调用service执行。service关注点要比角色高，比角色抽象。否则如果系统有大量角色，且角色功能有大量覆盖，按角色划分service会很难维护。  
当然，如果用户角色功能明显，基于角色创建service更适合。

### Others
引入caffeine缓存。应缓存所有用户均可能使用的必要数据，例如最新的新闻。  
应缓存不会/较少修改的数据。例如应缓存今天的每条最新新闻，而不应缓存最新的5条新闻集合。因为最新的5条集合是随时变化的。
单用户数据由前端缓存处理，例如用户自己的订单。

因此，仅在查询所有学生数据使用缓存(其实本系统用不上，仅用于说明问题)，当添加学生时删除此缓存，再次请求时再次置于缓存。

## Deployment
服务部署在openjdk11+MySQL8 2个容器。通过docker compose编排管理。