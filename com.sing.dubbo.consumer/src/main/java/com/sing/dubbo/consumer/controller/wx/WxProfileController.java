package com.sing.dubbo.consumer.controller.wx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import weixin.popular.bean.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 展示用户微信信息
 *
 * @author songbo
 * @since 2022-09-19
 */
@Controller
@RequestMapping("/wxProfile")
public class WxProfileController {
    @RequestMapping("/user")
    public String user(Model model, HttpServletRequest request) {
        return "/wx/wxProfile/user";
    }

}
