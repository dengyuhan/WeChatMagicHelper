package com.dyhdyh.helper.wechat.library.interfaces;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

/**
 * @author dengyuhan
 * @created 2017/11/3 11:28
 */
public interface WeChatMessageListener<T> {

    void onReceiveMessage(WeChatMessage source, T transform);
}
