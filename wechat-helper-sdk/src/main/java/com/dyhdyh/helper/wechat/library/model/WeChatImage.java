package com.dyhdyh.helper.wechat.library.model;

import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:11
 */
public class WeChatImage implements WeChatContent {
    private String thumbnail;
    private String thumbnailHD;
    private String original;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailHD() {
        return thumbnailHD;
    }

    public void setThumbnailHD(String thumbnailHD) {
        this.thumbnailHD = thumbnailHD;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public String toString() {
        return getType().value() + "缩略图='" + thumbnail + '\'' +
                ", 缩略图HD='" + thumbnailHD + '\'' +
                ", 原图='" + original + '\'' +
                '}';
    }

    @Override
    public WeChatMessageType getType() {
        return WeChatMessageType.IMAGE;
    }
}
