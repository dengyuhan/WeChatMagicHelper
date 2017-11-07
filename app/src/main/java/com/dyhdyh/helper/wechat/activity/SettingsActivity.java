package com.dyhdyh.helper.wechat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dyhdyh.helper.wechat.R;
import com.dyhdyh.helper.wechat.client.GreenDaoClient;
import com.dyhdyh.helper.wechat.database.WeChatMessageEntity;
import com.dyhdyh.helper.wechat.library.WeChatHelper;
import com.dyhdyh.helper.wechat.library.interfaces.WeChatMessageListener;
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
import com.google.gson.Gson;

/**
 * @author dengyuhan
 * @created 2017/11/3 10:25
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!NotificationServiceUtil.isServiceEnabled(this)) {
            NotificationServiceUtil.startNotificationSetting(this);
        } else if (!AccessibilityServiceUtil.isServiceEnabled(this, WeChatAccessibilityService.class)) {
            AccessibilityServiceUtil.startAccessibilitySetting(this);
        }

        WeChatHelper.getInstance()
                .setMessageTransform(message -> {
                    WeChatMessageEntity entity = new WeChatMessageEntity();
                    entity.setMessageJson(new Gson().toJson(message));
                    return entity;
                })
                .registerMessageListener(new WeChatMessageListener<WeChatMessageEntity>() {
                    @Override
                    public void onReceiveMessage(WeChatMessage source, WeChatMessageEntity transform) {
                        long insert = GreenDaoClient.get(SettingsActivity.this).getSession()
                                .getWeChatMessageEntityDao()
                                .insert(transform);

                        Log.i("插入数据库---------->", insert + "");

                        WeChatContent content = source.getContent();
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
