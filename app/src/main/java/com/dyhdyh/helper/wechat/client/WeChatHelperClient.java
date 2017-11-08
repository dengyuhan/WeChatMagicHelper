package com.dyhdyh.helper.wechat.client;

import android.content.Context;
import android.util.Log;

import com.dyhdyh.helper.wechat.control.WeChatMessageDatabaseController;
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
import com.google.gson.Gson;

/**
 * @author dengyuhan
 * @created 2017/11/8 09:40
 */
public class WeChatHelperClient {

    public static void register(Context context) {
        WeChatHelper.getInstance()
                .setMessageTransform(message -> {
                    WeChatMessageEntity entity = new WeChatMessageEntity();
                    entity.setMessageJson(new Gson().toJson(message));
                    entity.setTimestamp(message.getTimestamp());
                    return entity;
                })
                .registerMessageListener(new WeChatMessageListener<WeChatMessageEntity>() {
                    @Override
                    public void onReceiveMessage(WeChatMessage source, WeChatMessageEntity transform) {
                        if (transform != null) {
                            WeChatMessageDatabaseController.insertMessageAsync(context, transform)
                                    .subscribe(id -> {
                                        Log.i("新消息插入数据库---------->", id + "");
                                    });
                        }

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
