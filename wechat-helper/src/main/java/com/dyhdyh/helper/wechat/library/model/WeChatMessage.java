package com.dyhdyh.helper.wechat.library.model;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author dengyuhan
 * @created 2017/11/3 14:34
 */
public class WeChatMessage {
    private String uuid;
    private String nickname;
    private WeChatContent content;
    private long timestamp;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public WeChatContent getContent() {
        return content;
    }

    public void setContent(WeChatContent content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "微信消息{" +
                "昵称='" + nickname + '\'' +
                ", 内容='" + content + '\'' +
                ", 时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp) +
                '}';
    }
}
