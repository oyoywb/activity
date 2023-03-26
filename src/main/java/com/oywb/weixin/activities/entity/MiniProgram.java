package com.oywb.weixin.activities.entity;

import lombok.Data;

public class MiniProgram {
    private String code2SessionApi = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";
    private String accessTokenApi = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}";
    private String uniformMessageSendApi = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token={ACCESS_TOKEN}";

    /**
     * 小程序AppId
     */
    private String AppId;

    /**
     * 小程序AppSecret
     */
    private String AppSecret;

    public String getCode2SessionApi() {
        return code2SessionApi;
    }

    public void setCode2SessionApi(String code2SessionApi) {
        this.code2SessionApi = code2SessionApi;
    }

    public String getAccessTokenApi() {
        return accessTokenApi;
    }

    public void setAccessTokenApi(String accessTokenApi) {
        this.accessTokenApi = accessTokenApi;
    }

    public String getUniformMessageSendApi() {
        return uniformMessageSendApi;
    }

    public void setUniformMessageSendApi(String uniformMessageSendApi) {
        this.uniformMessageSendApi = uniformMessageSendApi;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getAppSecret() {
        return AppSecret;
    }

    public void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public MiniProgram() {
    }

    public MiniProgram(String code2SessionApi, String accessTokenApi, String uniformMessageSendApi, String appId, String appSecret) {
        this.code2SessionApi = code2SessionApi;
        this.accessTokenApi = accessTokenApi;
        this.uniformMessageSendApi = uniformMessageSendApi;
        AppId = appId;
        AppSecret = appSecret;
    }
}
