package com.dyhdyh.helper.wechat.library.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.dyhdyh.helper.wechat.library.helper.WeChatMessageControlHelper;
import com.dyhdyh.helper.wechat.library.util.Constants;

/**
 * @author dengyuhan
 * @created 2017/11/7 11:22
 */
@SuppressLint("OverrideAbstract")
public class WeChatNotificationService extends NotificationListenerService {

    /**
     * 在收到消息时触发
     *
     * @param sbn
     */
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        // 获取接收消息APP的包名
        String notificationPkg = sbn.getPackageName();

        Log.i("onNotificationPosted", notificationPkg);
        if (Constants.PACKAGE_NAME_WECHAT.equals(notificationPkg)) {
            // 获取接收消息的抬头
            String title = extras.getString(Notification.EXTRA_TITLE);
            // 获取接收消息的内容
            String text = extras.getString(Notification.EXTRA_TEXT);

            Log.i("onNotificationPosted", title + "---->" + text);

            WeChatMessageControlHelper.notifyListener(title, text);
        }
    }


}
