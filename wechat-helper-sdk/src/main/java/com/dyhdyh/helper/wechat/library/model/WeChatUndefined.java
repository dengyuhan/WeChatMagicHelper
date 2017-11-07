package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:53
 */
public class WeChatUndefined implements WeChatContent{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return getType().value() + ": " + text;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.UNDEFINED;
    }
}
