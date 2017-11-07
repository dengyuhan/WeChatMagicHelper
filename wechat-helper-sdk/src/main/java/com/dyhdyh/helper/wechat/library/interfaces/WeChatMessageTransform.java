package com.dyhdyh.helper.wechat.library.interfaces;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

/**
 * @author dengyuhan
 * @created 2017/11/7 16:44
 */
public interface WeChatMessageTransform<T> {

    T transform(WeChatMessage message);
}
