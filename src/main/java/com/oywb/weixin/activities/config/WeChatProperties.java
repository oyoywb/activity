package com.oywb.weixin.activities.config;

import com.oywb.weixin.activities.entity.MiniProgram;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sai
 * @since 2020/12/2
 */
@ConfigurationProperties(prefix = "sai.wechat")
public class WeChatProperties {

    private MiniProgram miniProgram;

    public MiniProgram getMiniProgram() {
        return miniProgram;
    }

    public void setMiniProgram(MiniProgram miniProgram) {
        this.miniProgram = miniProgram;
    }

    public WeChatProperties(MiniProgram miniProgram) {
        this.miniProgram = miniProgram;
    }

    public WeChatProperties() {
    }
}
