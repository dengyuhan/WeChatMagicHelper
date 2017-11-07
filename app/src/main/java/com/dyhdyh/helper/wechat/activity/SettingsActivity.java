package com.dyhdyh.helper.wechat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dyhdyh.helper.wechat.R;
import com.dyhdyh.helper.wechat.library.WeChatHelper;
import com.dyhdyh.helper.wechat.library.listener.WeChatMessageListener;
import com.dyhdyh.helper.wechat.library.model.WeChatContent;
import com.dyhdyh.helper.wechat.library.model.WeChatCustomEmoji;
import com.dyhdyh.helper.wechat.library.model.WeChatImage;
import com.dyhdyh.helper.wechat.library.model.WeChatMessage;
import com.dyhdyh.helper.wechat.library.model.WeChatShare;
import com.dyhdyh.helper.wechat.library.model.WeChatText;
import com.dyhdyh.helper.wechat.library.model.WeChatUndefined;
import com.dyhdyh.helper.wechat.library.model.WeChatVideo;
import com.dyhdyh.helper.wechat.library.service.WeChatAccessibilityService;
import com.dyhdyh.helper.wechat.library.util.AccessibilityServiceUtil;
import com.dyhdyh.helper.wechat.library.util.NotificationServiceUtil;

/**
 * @author dengyuhan
 * @created 2017/11/3 10:25
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!AccessibilityServiceUtil.isServiceEnabled(this, WeChatAccessibilityService.class)) {
            AccessibilityServiceUtil.startAccessibilitySetting(this);
        } else if (!NotificationServiceUtil.isServiceEnabled(this)) {
            NotificationServiceUtil.startNotificationSetting(this);
        }

        WeChatHelper.getInstance().setMessageListener(new WeChatMessageListener() {
            @Override
            public void onReceiveMessage(WeChatMessage message) {
                WeChatContent content = message.getContent();
                Log.i("接收到" + content.getType().value() + "消息--------------->", content + "");

                if (content instanceof WeChatText) {

                } else if (content instanceof WeChatImage) {

                } else if (content instanceof WeChatVideo) {

                } else if (content instanceof WeChatCustomEmoji) {

                } else if (content instanceof WeChatShare) {

                } else if (content instanceof WeChatUndefined) {

                }
            }
        });

    }
}
