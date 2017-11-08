package com.dyhdyh.helper.wechat.library.helper;

import android.text.TextUtils;

import com.dyhdyh.helper.wechat.library.model.WeChatContent;
import com.dyhdyh.helper.wechat.library.model.WeChatCustomEmoji;
import com.dyhdyh.helper.wechat.library.model.WeChatImage;
import com.dyhdyh.helper.wechat.library.model.WeChatShare;
import com.dyhdyh.helper.wechat.library.model.WeChatText;
import com.dyhdyh.helper.wechat.library.model.WeChatUndefined;
import com.dyhdyh.helper.wechat.library.model.WeChatVideo;
import com.dyhdyh.helper.wechat.library.model.WeChatWithdraw;
import com.dyhdyh.helper.wechat.library.model.fixed.WeChatMessageType;
import com.dyhdyh.helper.wechat.library.util.FileUtil;
import com.dyhdyh.helper.wechat.library.util.WeChatFileUtil;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 消息加工厂
 *
 * @author dengyuhan
 * @created 2017/11/7 12:19
 */
public class WeChatMessageProcessor {

    public static WeChatContent process(String nickName, String text) {
        String rawText = TextUtils.isEmpty(text) ? "" : text.replaceFirst(String.format("%s: ", nickName), "");
        if (rawText.startsWith("[") && rawText.contains("条]")) {
            rawText = rawText.substring(rawText.indexOf("]") + 1, rawText.length());
        }
        //非文本消息
        if (rawText.startsWith("[") && rawText.contains("]")) {
            if (rawText.startsWith(WeChatMessageType.IMAGE.getTag())) {
                return processImageMessage();
            } else if (rawText.startsWith(WeChatMessageType.GIF.getTag())) {
                return processCustomEmojiMessage();
            } else if (rawText.startsWith(WeChatMessageType.VIDEO.getTag())) {
                return processVideoMessage();
            } else if (rawText.startsWith(WeChatMessageType.SHARE.getTag())) {
                return new WeChatShare();
            } else if (rawText.startsWith(WeChatMessageType.WITHDRAW.getTag())) {
                return new WeChatWithdraw();
            } else {
                WeChatUndefined undefined = new WeChatUndefined();
                undefined.setText(rawText);
                return undefined;
            }
        } else {
            //文本消息
            return new WeChatText(rawText);
        }
    }

    public static WeChatVideo processVideoMessage() {
        File lastModifiedFile = FileUtil.getLastModifiedFile(WeChatFileUtil.getWeChatImageCacheDirectory(WeChatFileUtil.VIDEO_CACHE_NAME), new FileUtil.HideFilenameFilter());
        WeChatVideo weChatVideo = new WeChatVideo();
        if (lastModifiedFile != null) {
            String fileNameNoEx = FileUtil.getFileNameNoEx(lastModifiedFile.getName());
            weChatVideo.setCover(String.format("%s.jpg", fileNameNoEx));
            weChatVideo.setVideo(String.format("%s.mp4", fileNameNoEx));
        }
        return weChatVideo;
    }


    /**
     * 加工自定义表情消息
     *
     * @return
     */
    public static WeChatCustomEmoji processCustomEmojiMessage() {
        WeChatCustomEmoji customEmoji = new WeChatCustomEmoji();
        File lastModifiedFile = FileUtil.getLastModifiedFile(WeChatFileUtil.getWeChatImageCacheDirectory(WeChatFileUtil.EMOJI_CACHE_NAME), new FileUtil.HideFilenameFilter());
        if (lastModifiedFile != null) {
            customEmoji.setImage(lastModifiedFile.getAbsolutePath());
        }
        return customEmoji;
    }

    /**
     * 加工图片消息
     *
     * @return
     */
    public static WeChatImage processImageMessage() {
        WeChatImage weChatImage = new WeChatImage();
        File lastModifiedFile = FileUtil.getLastModifiedFile(WeChatFileUtil.getWeChatImageCacheDirectory(WeChatFileUtil.IMAGE_CACHE_NAME), new FileUtil.HideFilenameFilter());
        if (lastModifiedFile != null) {
            File parentFile = lastModifiedFile.getParentFile();
            final String fileName = lastModifiedFile.getName();
            File[] files = parentFile.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    //包含这个文件名的文件都加进来
                    return name.contains(FileUtil.getFileNameNoEx(fileName));
                }
            });
            //根据文件名规则筛选出各个规格的图
            for (int i = 0; i < files.length; i++) {
                if (files[i].getName().startsWith("th_")) {
                    weChatImage.setThumbnail(files[i].getAbsolutePath());
                } else if (files[i].getName().endsWith("hd")) {
                    weChatImage.setThumbnailHD(files[i].getAbsolutePath());
                } else if (files[i].getName().contains(".")) {
                    weChatImage.setOriginal(files[i].getAbsolutePath());
                }
            }
        }
        return weChatImage;
    }

}
