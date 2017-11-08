package com.dyhdyh.helper.wechat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dyhdyh.helper.wechat.R;
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
        if (!NotificationServiceUtil.isServiceEnabled(this)) {
            NotificationServiceUtil.startNotificationSetting(this);
        } else if (!AccessibilityServiceUtil.isServiceEnabled(this, WeChatAccessibilityService.class)) {
            AccessibilityServiceUtil.startAccessibilitySetting(this);
        }



    }
}
