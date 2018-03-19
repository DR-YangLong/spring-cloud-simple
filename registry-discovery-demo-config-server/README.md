# 高可用配置中心
结构
> git-->config-server cluster-->eureka cluster-->clients

配置文件中使用了少量配置，并未优化。配置见各自项目中配置文件。
修改hosts，在同一主机使用eureka集群
```
127.0.0.1      	eureka-first
127.0.0.1       eureka-second
127.0.0.1       eureka-third
```
使用不同的profile启动多个config-server和eureka server实例。
启动顺序：eureka server-->config server-->config client
