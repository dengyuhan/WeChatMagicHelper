package com.dyhdyh.helper.wechat.library.helper;

import android.util.Log;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatWindowState;

/**
 * 聊天窗口
 *
 * @author dengyuhan
 * @created 2017/11/8 16:08
 */
public class WeChatDialogueWindowController {
    private final String TAG = "WeChatDialogueWindow";

    private static WeChatDialogueWindowController mInstance;
    private boolean mMayStartAction;
    private String mTitle;
    private WeChatWindowState mState;

    private WeChatDialogueWindowController() {
        mState = WeChatWindowState.CLOSE;
    }

    public static WeChatDialogueWindowController getInstance() {
        synchronized (WeChatDialogueWindowController.class) {
            if (mInstance == null) {
                mInstance = new WeChatDialogueWindowController();
            }
        }
        return mInstance;
    }

    /**
     * 执行了下一步可能会打开聊天窗口的动作(例如 通知栏消息被删除,点击微信首页消息列表)
     */
    public void mayStartAction() {
        mMayStartAction = true;

        Log.d(TAG, "mayStartAction----->" + mMayStartAction);
    }


    public void start(String title) {
        if (isClose() && mMayStartAction) {
            mTitle = title;
            mMayStartAction = false;
            mState = WeChatWindowState.OPEN;

            //通知监听器
            WeChatListenerController.notifyDialogueWindowLifecycle(mTitle, mState);

            Log.d(TAG, "start----->" + mTitle);
        }
    }

    @Deprecated
    private void resume() {
        //if (isClose() || mState == WeChatWindowState.RESUME) {
        if (isClose()) {
            return;
        }
        //mState = WeChatWindowState.RESUME;

        //通知监听器
        //WeChatListenerController.notifyDialogueWindowLifecycle(mTitle, mState);

        //Log.d(TAG, "resume----->" + mTitle);
    }

    @Deprecated
    private void pause() {
        //if (isClose() || mState == WeChatWindowState.PAUSE) {
        if (isClose()) {
            return;
        }
        //mState = WeChatWindowState.PAUSE;

        //通知监听器
        //WeChatListenerController.notifyDialogueWindowLifecycle(mTitle, mState);

        //Log.d(TAG, "pause----->" + mTitle);
    }


    public void close() {
        if (isClose()) {
            return;
        }
        mState = WeChatWindowState.CLOSE;

        //通知监听器
        WeChatListenerController.notifyDialogueWindowLifecycle(mTitle, mState);

        Log.d(TAG, "close----->" + mTitle);
    }


    public boolean isClose() {
        return mState == WeChatWindowState.CLOSE;
    }

    public String getTitle() {
        return mTitle;
    }
}
