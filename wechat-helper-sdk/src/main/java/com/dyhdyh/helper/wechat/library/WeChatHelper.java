package com.dyhdyh.helper.wechat.library;

import com.dyhdyh.helper.wechat.library.interfaces.WeChatMessageListener;
import com.dyhdyh.helper.wechat.library.interfaces.WeChatMessageTransform;

/**
 * @author dengyuhan
 * @created 2017/11/7 11:47
 */
public class WeChatHelper {
    private static WeChatHelper mInstance;
    private WeChatMessageListener mMessageListener;
    private WeChatMessageTransform mMessageTransform;

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

    public WeChatHelper registerMessageListener(WeChatMessageListener messageListener) {
        this.mMessageListener = messageListener;
        return this;
    }


    public WeChatHelper setMessageTransform(WeChatMessageTransform messageTransform) {
        this.mMessageTransform = messageTransform;
        return this;
    }

    public WeChatMessageListener getMessageListener() {
        return mMessageListener;
    }

    public WeChatMessageTransform getMessageTransform() {
        return mMessageTransform;
    }
}
