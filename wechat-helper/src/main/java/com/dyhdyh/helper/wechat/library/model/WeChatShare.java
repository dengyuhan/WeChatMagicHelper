package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:11
 */
public class WeChatShare implements WeChatContent {
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.SHARE;
    }

    @Override
    public String toString() {
        return getType().value() + ": " + title;
    }
}
