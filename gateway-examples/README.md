# Gateway Examples

### Start Up
需启动springmvc-examples微服务配合测试，由/example09/接收处理网关微服务请求  

spring-cloud-gateway默认使用支持异步非阻塞的Netty框架而非Tomcat服务器实现多微服务数据的聚合。  

### Design Principles
微服务应为独立开发/测试/部署的项目，因此允许各自包含名称相同，但实现目的不同的实体类。  
例如，用户服务/订单服务/驾驶员服务等等均需提供，包含相同信息(主键)以及不同侧重数据的用户信息。
因此，所有微服务均应定义自己的User实体类。  

### Routes
配置cloud.gateway.routes路由转发规则。  
路由转发不经网关的controller处理，直接按配置转发请求到其他微服务并返回结果。  

### GatewayFilterFactory
支持过滤自定义指定路由转发规则的局部过滤器。  
命名规则，局部过滤器类SpringmvcGatewayFilterFactory，
在配置中去掉过滤器类名后缀GatewayFilterFactory，直接声明为Springmvc  

### GlobalFilter
支持过滤所有转发请求的全局过滤器，GlobalFilter接口。  
仅过滤转发请求，controller应使用Interceptor拦截器。  

### @LoadBalanced
@LoadBalanced注解，注入支持负载均衡(需基于nacos等注册发现服务)的同步/异步请求组件。  
支持Mono/Flux的异步请求组件需注入WebClient.Builder而不是WebClient对象。  

The retrieve() method directly performs the HTTP request and retrieve the response body.  
The exchange() method returns ClientResponse having the response status and headers.
We can get the response body from ClientResponse instance.

