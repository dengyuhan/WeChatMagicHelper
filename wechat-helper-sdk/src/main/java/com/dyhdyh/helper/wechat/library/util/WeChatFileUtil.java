package com.dyhdyh.helper.wechat.library.util;

import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/**
 * @author dengyuhan
 * @created 2017/11/7 13:40
 */
public class WeChatFileUtil {
    private static final String PATH_WECHAT_ROOT = "tencent/MicroMsg/";
    private static final List<String> FILTER_FILE_NAME = Arrays.asList(
            "CheckResUpdate", "wallet", "card", "newyear", "Download", "xlog", "WebviewCache", "WeiXin", "vusericon", "Game", "CDNTemp", "FailMsgFileCache", "Cache", "sns_ad_landingpages", "wxacache", "wxafiles", "Handler", "recovery", "crash", "SQLTrace"
    );
    public static final String IMAGE_CACHE_NAME = "image2";
    public static final String EMOJI_CACHE_NAME = "emoji";
    public static final String VIDEO_CACHE_NAME = "video";
    private static File mWechatCacheDirectory;

    public static File getWeChatDirectory() {
        File directory = new File(Environment.getExternalStorageDirectory(), PATH_WECHAT_ROOT);
        return directory;
    }

    public static File getWeChatCacheDirectory() {
        if (mWechatCacheDirectory == null) {
            File directory = getWeChatDirectory();
            if (directory.exists()) {
                File[] listFiles = directory.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return !FILTER_FILE_NAME.contains(name);
                    }
                });
                mWechatCacheDirectory = FileUtil.getLastModifiedFile(listFiles);
            }
        }
        return mWechatCacheDirectory;
    }


    public static File getWeChatImageCacheDirectory(String cacheChildName) {
        File cacheDirectory = getWeChatCacheDirectory();
        if (cacheDirectory != null) {
            File file = new File(cacheDirectory, cacheChildName);
            return file;
        }
        return null;
    }
}
