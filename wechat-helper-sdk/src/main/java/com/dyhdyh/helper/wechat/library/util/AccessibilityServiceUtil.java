package com.dyhdyh.helper.wechat.library.util;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

/**
 * @author dengyuhan
 * @created 2017/11/3 10:33
 */
public class AccessibilityServiceUtil {

    /**
     * 无障碍服务是否开启
     *
     * @param context
     * @param cls
     * @return
     */
    public static boolean isServiceEnabled(Context context, Class<? extends AccessibilityService> cls) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);

        List<AccessibilityServiceInfo> accessibilityServices =
                accessibilityManager.getEnabledAccessibilityServiceList(
                        AccessibilityServiceInfo.FEEDBACK_ALL_MASK);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            String infoId = info.getId();
            if (infoId.startsWith(context.getPackageName()) && infoId.contains(cls.getSimpleName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 打开系统无障碍设置界面
     *
     * @param context
     */
    public static void startAccessibilitySetting(Context context) {
        Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        context.startActivity(accessibleIntent);
    }
}
