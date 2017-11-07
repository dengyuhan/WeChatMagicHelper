package com.dyhdyh.helper.wechat.library.util;

/**
 * @author dengyuhan
 * @created 2017/11/3 14:40
 */
public class Constants {
    public static final String PACKAGE_NAME_WECHAT = "com.tencent.mm";

    public static final String VIEW_ID_CHAT_LISTVIEW = "a64";

    public static final String getViewId(String id) {
        return PACKAGE_NAME_WECHAT + ":id/" + id;
    }
}
