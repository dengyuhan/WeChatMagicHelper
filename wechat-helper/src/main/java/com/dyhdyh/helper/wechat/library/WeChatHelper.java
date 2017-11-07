package com.dyhdyh.helper.wechat.library;

import com.dyhdyh.helper.wechat.library.listener.WeChatMessageListener;

/**
 * @author dengyuhan
 * @created 2017/11/7 11:47
 */
public class WeChatHelper {
    private static WeChatHelper mInstance;
    private WeChatMessageListener mMessageListener;

    private WeChatHelper() {

    }

    public static WeChatHelper getInstance() {
        synchronized (WeChatHelper.class) {
            if (mInstance == null) {
                mInstance = new WeChatHelper();
            }
        }
        return mInstance;
    }

    public void setMessageListener(WeChatMessageListener messageListener) {
        this.mMessageListener = messageListener;
    }

    public WeChatMessageListener getMessageListener() {
        return mMessageListener;
    }
}
