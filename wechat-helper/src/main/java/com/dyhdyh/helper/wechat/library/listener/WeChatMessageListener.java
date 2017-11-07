package com.dyhdyh.helper.wechat.library.listener;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

/**
 * @author dengyuhan
 * @created 2017/11/3 11:28
 */
public interface WeChatMessageListener{

    void onReceiveMessage(WeChatMessage message);
}
