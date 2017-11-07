package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:44
 */
public class WeChatText implements WeChatContent{
    private String text;

    public WeChatText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.TEXT;
    }

    @Override
    public String toString() {
        return text;
    }
}
