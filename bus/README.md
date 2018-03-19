# 刷新配置
1. 安装RabbitMQ，创建springcloud用户并赋予远程连接权限。
2. 启动eureka-server：通过不同profile启动集群。访问http://127.0.0.1:9001/查看eureka监控。
3. 启动config-server：通过不同profile启动集群。访问http://127.0.0.1:8001(或8002、8003)/application-production(或develop).yml查看配置信息。
4. fork config-client中bootstrap.yml里指定的git项目，然后将原配置的git地址换为你自己的地址。
5. 启动config-client，使用profile启动2个。
6. 访问http://127.0.0.1:8888和http://127.0.0.1:9999查看当前client生效的配置信息。
7. 修改git上的配置文件中的hello属性。
8. 用POST方式访问http://127.0.0.1:8001/bus/refresh。
9. 访问http://127.0.0.1:8888和http://127.0.0.1:9999查看当前client生效的配置信息是否是新的。