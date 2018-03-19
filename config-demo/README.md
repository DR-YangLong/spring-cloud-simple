# spring cloud 配置中心
配置中心由server和client构成。
server从配置源地址获取配置信息并维护管理，client从server获取配置信息，用于自身spring boot应用。
配置源地址可以是版本控制服务器或是本地目录，或者自定义实现配置获取方式。在已经集成的方式中，可以使用加解密。
可以通过@RefreshScope对@Bean注解的bean进行重新初始化。
