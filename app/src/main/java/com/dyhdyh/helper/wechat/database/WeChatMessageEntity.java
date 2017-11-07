package com.dyhdyh.helper.wechat.database;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author dengyuhan
 * @created 2017/11/3 18:11
 */
@Entity(nameInDb = "wechat_message")
public class WeChatMessageEntity extends WeChatMessage {
    @Id(autoincrement = true)
    private long id;
    private String messageJson;


    @Generated(hash = 1859845671)
    public WeChatMessageEntity(long id, String messageJson) {
        this.id = id;
        this.messageJson = messageJson;
    }

    @Generated(hash = 317551440)
    public WeChatMessageEntity() {
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(String messageJson) {
        this.messageJson = messageJson;
    }
}
