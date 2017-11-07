package com.dyhdyh.helper.wechat.library.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.dyhdyh.helper.wechat.library.util.Constants;

import java.util.List;

/**
 * 微信无障碍服务
 *
 * @author dengyuhan
 * @created 2017/11/3 10:27
 */
public class WeChatAccessibilityService extends AccessibilityService {
    private final String TAG = "WeChatAccessibility";
    private String mCurrentActivity;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        //Log.i(TAG, AccessibilityEvent.eventTypeToString(event.getEventType()));

        dispatchWindowEvent(event);

    }

    /**
     * 分发窗口事件
     *
     * @param event
     */
    public void dispatchWindowEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                onNotificationStateChanged(event);
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                //窗口状态变化
                onWindowStateChanged(event);
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                //窗口内容变化
                onWindowContentChanged(event);
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                //点击操作
                onViewClicked(event);
                break;
        }

    }

    /**
     * 通知状态发生变化
     *
     * @param event
     */
    protected void onNotificationStateChanged(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (texts == null || texts.isEmpty()) {
            return;
        }
        Log.i(TAG, "Notification: " + texts);
    }


    /**
     * 窗口状态发生变化
     *
     * @param event
     */
    protected void onWindowStateChanged(AccessibilityEvent event) {
        String className = event.getClassName().toString();
        //Log.i(TAG, "onWindowStateChanged---->" + className);
        mCurrentActivity = className;
        //Activity跳转会使窗口状态发生变化
    }


    /**
     * 窗口内容发生变化
     *
     * @param event
     */
    protected void onWindowContentChanged(AccessibilityEvent event) {
        String className = event.getClassName().toString();
        //Log.i(TAG, "onWindowContentChanged---->" + className);
        AccessibilityNodeInfo info = getRootInActiveWindow();
        if (info == null) {
            return;
        }
        //消息列表
        List<AccessibilityNodeInfo> messageViews = info.findAccessibilityNodeInfosByViewId(Constants.getViewId(Constants.VIEW_ID_CHAT_LISTVIEW));
        if (messageViews != null && !messageViews.isEmpty()) {
            AccessibilityNodeInfo item = messageViews.get(0);
            Log.i(TAG, "" + item.getChildCount());
        }
    }


    /**
     * view被点击
     *
     * @param event
     */
    protected void onViewClicked(AccessibilityEvent event) {
        String packageName = event.getPackageName().toString();
        Log.i(TAG, "onViewClicked---->" + packageName);
        /*if (Constants.PACKAGE_NAME_WECHAT.equals(packageName)) {
            AccessibilityNodeInfo info = getRootInActiveWindow();
            int childCount = info.getChildCount();
            for (int i = 0; i < childCount; i++) {
                Log.i(TAG, "" + info.getChild(i).getViewIdResourceName());
            }
        }*/
    }

    @Override
    public void onInterrupt() {

    }
}
