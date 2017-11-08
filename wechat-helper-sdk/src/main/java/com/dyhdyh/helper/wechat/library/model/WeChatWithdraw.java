package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:53
 */
public class WeChatWithdraw implements WeChatContent{

    @Override
    public String toString() {
        return getType().value() + ": " ;
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.WITHDRAW;
    }
}
