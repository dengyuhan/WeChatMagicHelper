package com.dyhdyh.helper.wechat;

import android.app.Application;

import com.dyhdyh.helper.wechat.client.WeChatHelperClient;

/**
 * @author dengyuhan
 * @created 2017/11/8 09:40
 */
public class MainApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        WeChatHelperClient.register(this);
    }
}
