package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:11
 */
public class WeChatCustomEmoji implements WeChatContent {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.GIF;
    }


    @Override
    public String toString() {
        return getType().value() + ": " + image;
    }
}
