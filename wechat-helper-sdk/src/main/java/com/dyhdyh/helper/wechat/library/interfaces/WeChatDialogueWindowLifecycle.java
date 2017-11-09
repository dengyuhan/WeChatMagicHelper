package com.dyhdyh.helper.wechat.library.interfaces;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatWindowState;

/**
 * 聊天窗口的生命周期
 *
 * @author dengyuhan
 * @created 2017/11/8 16:54
 */
public interface WeChatDialogueWindowLifecycle {

    void onDialogueWindowStateChanged(String title, WeChatWindowState state);

}
