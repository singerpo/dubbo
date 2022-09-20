package com.sing.dubbo.consumer.controller.wx;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.api.MessageAPI;
import weixin.popular.api.QrcodeAPI;
import weixin.popular.bean.message.templatemessage.TemplateMessage;
import weixin.popular.bean.message.templatemessage.TemplateMessageItem;
import weixin.popular.bean.message.templatemessage.TemplateMessageResult;
import weixin.popular.bean.qrcode.QrcodeTicket;
import weixin.popular.support.TokenManager;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * 发送模板消息
 * 生成带参数推广二维码
 *
 * @author songbo
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/wxTemplateMsg")
public class WxTemplateMsgController {
    @Autowired
    WxConfig wxConfig;

    /**
     * 发送模板消息
     * @return
     */
    @RequestMapping
    public TemplateMessageResult sendMsg() {
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser("oXVi_6qpOwpyfl-bTePxzVO7rxaw");
        templateMessage.setTemplate_id("xDKDUv47rGCoBe_Jp-72FdYxlT4Ec9iRa1GnA_HRkA4");
        templateMessage.setUrl(wxConfig.getDomain() + "/wxProfile/user");
        LinkedHashMap<String, TemplateMessageItem> data = new LinkedHashMap<>();
        data.put("orderNum", new TemplateMessageItem("666888", "#173177"));
        templateMessage.setData(data);
        return MessageAPI.messageTemplateSend(TokenManager.getDefaultToken(), templateMessage);
    }

    /**
     * 生成带参数推广二维码
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("qrcode")
    public String qrcode(HttpServletResponse response) throws IOException {
        QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateTemp(TokenManager.getDefaultToken(),604800,"1");
        BufferedImage bufferedImage = QrcodeAPI.showqrcode(qrcodeTicket.getTicket());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        response.getOutputStream().write(bytes);
        return null;

    }
}
