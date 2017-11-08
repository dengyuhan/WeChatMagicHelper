package com.dyhdyh.helper.wechat.library.model.fixed;

/**
 * @author dengyuhan
 * @created 2017/11/7 12:21
 */
public enum WeChatMessageType {
    TEXT("文本"), IMAGE("图片"), VIDEO("视频"), GIF("动画表情"), SHARE("链接"),WITHDRAW("撤回了一条信息"), UNDEFINED("未定义");

    String value;

    WeChatMessageType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String getTag() {
        return String.format("[%s]", value);
    }
}
