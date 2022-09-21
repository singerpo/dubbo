package com.sing.dubbo.consumer.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author songbo
 * @since 2022-09-21
 */
@Api(tags = "权限管理")
public interface IPermissionManagerRestController {
    @ApiOperation("添加权限")
    String say(@ApiParam(value = "权限id",required = true) int id, @ApiParam("权限名称")String name);
}
