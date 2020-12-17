### motang 基于微服务构建的书城
![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square](https://img.shields.io/badge/springcloud-Hoxton.RELEASE-yellow.svg?style=flat-square)
![https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square](https://img.shields.io/badge/SpringCloudAlibaba-2.1.1.RELEASE-blueviolet.svg?style=flat-square)
![https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.0.RELEASE-brightgreen.svg?style=flat-square)



### 主要使用技术                                                                       
名称  | 描述                                      
---|---                                          
springboot2.2.7| 基本骨架                                       
springcloud Hoxton| 基本骨架
nacos| 网关
oauth2.0 | 权限处理
redis| 缓存
mybatis-plus | 持久层
swagger-2.9.2| 接口文档
seata | 分布式事务
rabbitMq| 消息队列
elasticsearch 6.8.8| 全文检索
quartz| 定时任务
canal 1.1.5| 数据同步
Actuator| 服务监控
SkyWalking | 分布式追踪系统
docker| 服务部署


### 服务模块


服务名称 | 端口 | 描述
---|---|---
book-auth| 8881| 微服务认证授权
book-gateway| 8882 |微服务网关
book-server-system| 8883 | 系统资源微服务
book-bus| 8884 |消息微服务
book-search| 8885 |全文检索微服务
book-apm| 8886 |监控微服务
book-upload| 8887 |文件上传微服务
book-generator| 8888 |逆向工程-代码生成器
book-crawl| 8889 |爬虫服务
book-portal| 8889 |门户网站





