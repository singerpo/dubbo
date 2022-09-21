package com.sing.dubbo.consumer.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songbo
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/api/v1/manager/permission")
public class PermissionManagerRestController implements IPermissionManagerRestController {

    @GetMapping("say")
    public String say(int id, String name) {
        return "consumer success";
    }

}
