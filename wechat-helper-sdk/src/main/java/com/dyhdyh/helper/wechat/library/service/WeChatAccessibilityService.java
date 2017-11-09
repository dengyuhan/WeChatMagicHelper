package com.dyhdyh.helper.wechat.library.service;

import android.accessibilityservice.AccessibilityService;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

import com.dyhdyh.helper.wechat.library.helper.WeChatAccessibilityViewHelper;
import com.dyhdyh.helper.wechat.library.helper.WeChatDialogueWindowController;

import java.util.List;

/**
 * 微信无障碍服务
 *
 * @author dengyuhan
 * @created 2017/11/3 10:27
 */
public class WeChatAccessibilityService extends AccessibilityService {
    private final String TAG = "WeChatAccessibility";
    private final String BACK_SYSTEM_KEY = "accessibility_back";
    private String mCurrentActivity;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //系统按键
        if ("com.android.systemui".equals(event.getPackageName())) {
            if (AccessibilityEvent.TYPE_VIEW_CLICKED == event.getEventType()) {
                String description = TextUtils.isEmpty(event.getContentDescription()) ? "" : event.getContentDescription().toString();
                if (isWeChatDialogueWindow() && description.equals(getSystemEventKey(BACK_SYSTEM_KEY))) {
                    onWeChatBackEvent();
                }
            }
            return;
        }

        if (event.getEventType() != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            Log.i(TAG, AccessibilityEvent.eventTypeToString(event.getEventType()));
        }

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
        Log.i(TAG, "onWindowContentChanged---->" + className + " " + event.getAction());
        AccessibilityNodeInfo info = getRootInActiveWindow();
        if (info == null) {
            return;
        }
        //首页
        AccessibilityNodeInfo findView = WeChatAccessibilityViewHelper.findViewByIdName(info, WeChatAccessibilityViewHelper.VIEW_ID_CHAT_TITLE);
        if (findView != null && !TextUtils.isEmpty(findView.getText())) {
            if (WeChatDialogueWindowController.getInstance().isClose()) {
                WeChatDialogueWindowController.getInstance().start(findView.getText().toString());
            }
        }
        //消息列表
        /*List<AccessibilityNodeInfo> messageViews = WeChatAccessibilityViewHelper.findViewsByIdName(info, WeChatAccessibilityViewHelper.VIEW_ID_CHAT_LISTVIEW);
        if (messageViews != null && !messageViews.isEmpty()) {
            AccessibilityNodeInfo item = messageViews.get(0);
            Log.i(TAG, "" + item.getChildCount());
        }*/
    }


    /**
     * view被点击
     *
     * @param event
     */
    protected void onViewClicked(AccessibilityEvent event) {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (WeChatAccessibilityViewHelper.VIEW_DESCRIPTION_BACK.equals(event.getText().toString())) {
            //点击了返回按钮
            onWeChatBackEvent();
        } else if (LinearLayout.class.getName().equals(event.getClassName())) {
            List<AccessibilityNodeInfo> infoList = WeChatAccessibilityViewHelper.findViewsByIdName(root, WeChatAccessibilityViewHelper.VIEW_ID_HOME_LIST_ITEM);
            if (infoList != null && !infoList.isEmpty() && WeChatDialogueWindowController.getInstance().isClose()) {
                WeChatDialogueWindowController.getInstance().mayStartAction();
            }
        }
        String packageName = event.getPackageName().toString();
        Log.i(TAG, "onViewClicked---->" + packageName + " " + event.getEventType() + " " + event.getClassName() + " " + event.getAction() + " " + event.getFromIndex() + " " + event.getText() + " " + event.getToIndex());
        /*AccessibilityNodeInfo info = getRootInActiveWindow();
        int childCount = info.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = info.getChild(i);
            if (child == null) {
                continue;
            }
            Log.i(TAG, child.getText() + " " + child.getExtras() + " " + info.getChild(i).getClassName() + " " + info.getChild(i).getViewIdResourceName());
        }*/
    }


    /**
     * 微信返回事件
     */
    protected void onWeChatBackEvent() {
        WeChatDialogueWindowController controller = WeChatDialogueWindowController.getInstance();
        if (!controller.isClose()) {
            AccessibilityNodeInfo findView = WeChatAccessibilityViewHelper.findViewByIdName(getRootInActiveWindow(), WeChatAccessibilityViewHelper.VIEW_ID_CHAT_TITLE);
            if (findView!=null){
                Log.d("WeChatDialogueWindow-->",findView.getText()+"");
            }
            if (findView != null && !TextUtils.isEmpty(findView.getText()) && controller.getTitle().equals(findView.getText())) {
                //如果是在聊天窗点的返回
                controller.close();
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    public String getSystemEventKey(String name) {
        try {
            String packageName = "com.android.systemui";
            Resources packageManager = getPackageManager().getResourcesForApplication(packageName);
            String key = packageManager.getString(packageManager.getIdentifier(name, "string", packageName));
            return key;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    private boolean isWeChatDialogueWindow() {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        if (root == null) {
            return false;
        }
        List<AccessibilityNodeInfo> charTitle = WeChatAccessibilityViewHelper.findViewsByIdName(root, WeChatAccessibilityViewHelper.VIEW_ID_CHAT_TITLE);
        if (charTitle != null && !charTitle.isEmpty()) {
            return true;
        }
        List<AccessibilityNodeInfo> infoList = WeChatAccessibilityViewHelper.findViewsByText(root, "微信");
        if (infoList != null && !infoList.isEmpty()) {
            return true;
        }
        return false;
    }
}
