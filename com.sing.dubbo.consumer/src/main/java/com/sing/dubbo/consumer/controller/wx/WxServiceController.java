package com.sing.dubbo.consumer.controller.wx;

import com.sing.dubbo.consumer.conf.WxConfig;
import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weixin.popular.api.MenuAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.TokenManager;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author songbo
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/wxService")
public class WxServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxServiceController.class);
    // 重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();
    @Autowired
    WxConfig wxConfig;

    @RequestMapping("createMenu")
    public BaseResult createMenu() {
        String menuJson = " {" +
                "     \"button\":[" +
                "     {" +
                "          \"type\":\"click\"," +
                "          \"name\":\"今日歌曲\"," +
                "          \"key\":\"V1001_TODAY_MUSIC\"" +
                "      }," +
                "      {" +
                "           \"name\":\"菜单\"," +
                "           \"sub_button\":[" +
                "           {" +
                "               \"type\":\"view\"," +
                "               \"name\":\"搜索\"," +
                "               \"url\":\"http://www.soso.com/\"" +
                "            }," +
                "            {" +
                "               \"type\":\"click\"," +
                "               \"name\":\"赞一下我们\"," +
                "               \"key\":\"V1001_GOOD\"" +
                "            }]" +
                "       }]" +
                " }";
        return MenuAPI.menuCreate(TokenManager.getDefaultToken(), menuJson);
    }

    @RequestMapping("verifyToken")
    public String verifyToken(@RequestParam Map<String, String> param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = param.get("signature");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");

        String echostr = param.get("echostr");

        String token = wxConfig.getToken();
        if (StringUtils.isBlank(signature)) {
            return "fail";
        }

        //验证请求签名
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(token, timestamp, nonce))) {
            return "fail";
        }
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            String key = eventMessage.getFromUserName() + "_" + eventMessage.getToUserName() + "_" +
                    eventMessage.getMsgId() + "_" + eventMessage.getCreateTime();
            LOGGER.info("eventMessage:{}",key);
            if(expireKey.exists(key)){
                LOGGER.info("通知重复不做处理");
            }else{
                expireKey.add(key);
            }
            OutputStream outputStream = response.getOutputStream();
            String content = "Hi,reply eventMessage";
            XMLMessage xmlMessage = new XMLTextMessage(eventMessage.getFromUserName(),eventMessage.getToUserName(),content);
            xmlMessage.outputStreamWrite(outputStream);
            return null;
        }

        return echostr;
    }
}
