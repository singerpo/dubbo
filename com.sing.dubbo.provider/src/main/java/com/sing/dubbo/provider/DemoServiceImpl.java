package com.sing.dubbo.provider;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService//(version = "1.0.0",timeout = 10000,interfaceClass = IDemoService.class)
public class DemoServiceImpl implements IDemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name;
    }
}
