package com.dingtalk;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
/**
 * 实现了机器人的简单问答功能
 */
public class RobotsController {

    private static final String Hello = "你好";

    @RequestMapping(value = "/robots", method = RequestMethod.POST)
    public String helloRobots(@RequestBody(required = false) JSONObject json) {
        System.out.println(json);
        String content = json.getJSONObject("text").get("content").toString().replaceAll(" ", "");
        // 获取用户手机号，用于发送@消息
        // String mobile = getUserMobile(json.getString("senderStaffId"));
        String sessionWebhook = json.getString("sessionWebhook");
        DingTalkClient client = new DefaultDingTalkClient(sessionWebhook);

        if (Hello.contains(content)) {
            hello(client);
        } else {
            learning(client);
        }
        return null;
    }

    /**
     * 回答其他
     */
    private void learning(DingTalkClient client) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent("我是问好机器人 ~");
            request.setText(text);
            // OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            // at.setAtMobiles(Arrays.asList(mobile));
            // isAtAll类型如果不为Boolean，请升级至最新SDK
            // at.setIsAtAll(false);
            // request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回答你好
     */
    public void hello(DingTalkClient client) {
        try {
            OapiRobotSendRequest request = new OapiRobotSendRequest();
            request.setMsgtype("text");
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent("你也好 ~");
            request.setText(text);
            // OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            // at.setAtMobiles(Arrays.asList(mobile));
            // isAtAll类型如果不为Boolean，请升级至最新SDK
            // at.setIsAtAll(false);
            // request.setAt(at);
            OapiRobotSendResponse response = client.execute(request);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}