package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:11
 */
public class WeChatVideo implements WeChatContent {
    private String cover;
    private String video;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.VIDEO;
    }

    @Override
    public String toString() {
        return getType().value() + ": 封面=" + cover + ", 视频=" + video;
    }
}
