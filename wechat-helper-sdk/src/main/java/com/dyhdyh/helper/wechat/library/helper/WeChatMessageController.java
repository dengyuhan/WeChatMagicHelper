package com.dyhdyh.helper.wechat.library.helper;

import com.dyhdyh.helper.wechat.library.WeChatHelper;
import com.dyhdyh.helper.wechat.library.interfaces.WeChatMessageListener;
import com.dyhdyh.helper.wechat.library.interfaces.WeChatMessageTransform;
import com.dyhdyh.helper.wechat.library.model.WeChatContent;
import com.dyhdyh.helper.wechat.library.model.WeChatMessage;
import com.dyhdyh.helper.wechat.library.model.WeChatWithdraw;

/**
 * 消息 控制
 *
 * @author dengyuhan
 * @created 2017/11/7 17:46
 */
public class WeChatMessageController {

    /**
     * 通知消息监听
     *
     * @param title
     * @param text
     */
    public static void notifyListener(String title, String text) {
        WeChatHelper helper = WeChatHelper.getInstance();
        WeChatMessageListener listener = helper.getMessageListener();
        if (listener != null) {
            WeChatMessage message = new WeChatMessage();
            message.setUuid();
            message.setNickname(title);
            message.setTimestamp(System.currentTimeMillis());
            WeChatContent content = WeChatMessageProcessor.process(message.getNickname(), text);
            if (content instanceof WeChatWithdraw) {
                //是撤回消息就忽略
                return;
            }
            message.setContent(content);
            //transform
            Object transformMessage = null;
            WeChatMessageTransform transform = helper.getMessageTransform();
            if (transform != null) {
                transformMessage = transform.transform(message);
            }
            //listener
            listener.onReceiveMessage(message, transformMessage);
        }
    }
}
