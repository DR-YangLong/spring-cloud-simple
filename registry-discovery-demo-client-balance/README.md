# 使用说明

## eureka服务器：eureka-server
1. 必须首先启动。
2. 使用域名的方式下，需要修改3个项目所在的系统hosts文件，将eureka-first映射到eureka服务器所在IP。
3. 如果不使用域名，那么在3个项目zone配置时使用IP进行配置。

> 扩展

```txt
如果要使用eureka聚群，在配置zone时，使用
http://{ip/域名}:{端口}/eureka/,http://{ip/域名}:{端口}/eureka/,...
的形式
```

## 服务端：eureka-server
1. zone配置和扩展配置参照eureka服务器。zone配置与eureka服务器保持一致。
2. eureka服务器启动后，先启动service服务，在yml配置文件中配有3个profile，仅端口不同，使用不同profile启动3个服务。
 

## 客户端：eureka-client
1. zone配置和扩展配置参照eureka服务器。zone配置与eureka服务器保持一致。
2. 最后启动。启动后访问 http://{某客户端IP}:{配置端口} 或者  http://{某客户端IP}:{配置端口}/hello 测试。