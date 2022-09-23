package com.sing.dubbo.provider;


import com.sing.dubbo.api.DemoApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

@DubboService//(version = "1.0.0",timeout = 10000,interfaceClass = IDemoService.class)
public class DemoApiImpl implements DemoApi {

    @Override
    public String sayHello(String name) {
        System.out.println("Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
         return "Hello " + name;
    }
}
