package com.dyhdyh.helper.wechat.database;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author dengyuhan
 * @created 2017/11/3 18:11
 */
@Entity(nameInDb = "wechat_message")
public class WeChatMessageEntity extends WeChatMessage {
    @Property(nameInDb = "_id")
    @Id(autoincrement = true)
    private Long id;
    private String messageJson;
    private long timestamp;


    @Generated(hash = 1062630867)
    public WeChatMessageEntity(Long id, String messageJson, long timestamp) {
        this.id = id;
        this.messageJson = messageJson;
        this.timestamp = timestamp;
    }

    @Generated(hash = 317551440)
    public WeChatMessageEntity() {
    }


    public String getMessageJson() {
        return messageJson;
    }

    public void setMessageJson(String messageJson) {
        this.messageJson = messageJson;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
