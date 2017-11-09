package com.dyhdyh.helper.wechat.library.helper;

import android.view.accessibility.AccessibilityNodeInfo;

import com.dyhdyh.helper.wechat.library.constants.WeChatConstants;

import java.util.List;

/**
 * @author dengyuhan
 * @created 2017/11/8 11:02
 */
public class WeChatAccessibilityViewHelper {

    public static final String VIEW_PATH_HOME_VIEWPAGER = String.format("%s.ui.mogic.WxViewPager", WeChatConstants.PACKAGE_NAME_WECHAT);

    //聊天页面的消息列表
    public static final String VIEW_ID_CHAT_LISTVIEW = "a64";
    //首页viewpager
    public static final String VIEW_ID_HOME_VIEWPAGER = "awg";
    //首页-发现-FrameLayout
    public static final String VIEW_ID_HOME_FIND_ROOT = "bx5";
    //首页-发现-ListView
    public static final String VIEW_ID_HOME_FIND_LISTVIEW = "list";
    //首页消息列表条目
    public static final String VIEW_ID_HOME_LIST_ITEM = "ajz";
    //聊天页面 - 标题(昵称)
    public static final String VIEW_ID_CHAT_TITLE = "h2";
    //页面根节点
    public static final String VIEW_ID_PAGE_ROOT = "d2d";
    //页面根节点
    public static final String VIEW_ID_STATUS_BAR = "android:id/statusBarBackground";
    //返回按钮
    public static final String VIEW_DESCRIPTION_BACK = "[返回]";

    //用来判断是否发现页面的关键词
    public static final String KEYWORD_HOME_FIND = "朋友圈";

    /**
     * 把View的id名称转换成微信View的id
     *
     * @param idName
     * @return
     */
    public static String getViewId(String idName) {
        return WeChatConstants.PACKAGE_NAME_WECHAT + ":id/" + idName;
    }


    /**
     * 根据id查找出所有对应id的view
     *
     * @param info
     * @param id
     * @return
     */
    public static List<AccessibilityNodeInfo> findViewsById(AccessibilityNodeInfo info, String id) {
        return info.findAccessibilityNodeInfosByViewId(id);
    }


    public static List<AccessibilityNodeInfo> findViewsByIdName(AccessibilityNodeInfo info, String idName) {
        return findViewsById(info, getViewId(idName));
    }

    /**
     * 根据id找出对应id的第一个view
     *
     * @param info
     * @param id
     * @return
     */
    public static AccessibilityNodeInfo findViewById(AccessibilityNodeInfo info, String id) {
        List<AccessibilityNodeInfo> infoList = findViewsById(info, id);
        if (infoList != null && !infoList.isEmpty()) {
            return infoList.get(0);
        }
        return null;
    }


    public static AccessibilityNodeInfo findViewByIdName(AccessibilityNodeInfo info, String idName) {
        return findViewById(info, getViewId(idName));
    }


    public static List<AccessibilityNodeInfo> findViewsByText(AccessibilityNodeInfo info, String text) {
        return info.findAccessibilityNodeInfosByText(text);
    }


    public static AccessibilityNodeInfo findViewByText(AccessibilityNodeInfo info, String text) {
        List<AccessibilityNodeInfo> infoList = findViewsByText(info, text);
        if (infoList != null && !infoList.isEmpty()) {
            return infoList.get(0);
        }
        return null;
    }


}
