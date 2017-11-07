package com.dyhdyh.helper.wechat.database;

import com.dyhdyh.helper.wechat.library.model.WeChatMessage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author dengyuhan
 * @created 2017/11/3 18:11
 */
@Entity
public class WeChatMessageEntity extends WeChatMessage {
    @Id
    private long id;

    @Generated(hash = 958878816)
    public WeChatMessageEntity(long id) {
        this.id = id;
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

}
