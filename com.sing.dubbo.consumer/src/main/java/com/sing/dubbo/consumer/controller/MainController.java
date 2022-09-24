package com.sing.dubbo.consumer.controller;

import com.sing.dubbo.api.DemoApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songbo
 * @since 2022-09-14
 */
@RestController
@RequestMapping("/main")
public class MainController {
    @DubboReference
    DemoApi demoService;

    @RequestMapping("say")
    public String say(){
        return demoService.sayHello("consumer success");
    }
}
