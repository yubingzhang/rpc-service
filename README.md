# rpc-service
RPC服务提供方和消费方示例


# 目录结构说明：
rpc-service: 服务提供方

rpc-consumer: 服务消费方


# 具体实现： 
rpc-service将服务地址和端口信息注册到zk，通过 netty开启对服务地址的监听，处理消费方的连接请求；

rpc-consumer启动时获取注册在zk的提供方地址，连接到服务器；将api接口动态注册到容器，对api接口的调用通过jdk动态代理处理；
