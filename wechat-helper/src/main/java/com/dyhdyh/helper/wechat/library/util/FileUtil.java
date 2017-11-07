package com.dyhdyh.helper.wechat.library.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dengyuhan
 * @created 2017/11/7 14:02
 */
public class FileUtil {

    /**
     * 获取最后修改的文件或文件夹
     *
     * @param listFiles
     * @return
     */
    public static File getLastModifiedFile(File[] listFiles) {
        File lastFile = null;
        for (int i = 0; i < listFiles.length; i++) {
            if (lastFile == null || listFiles[i].lastModified() > lastFile.lastModified()) {
                //筛选最后修改的文件夹
                lastFile = listFiles[i];
            }
        }
        return lastFile;
    }


    /**
     * 获取最后修改的文件
     *
     * @param file
     * @return
     */
    public static File getLastModifiedFile(File file, FilenameFilter filenameFilter) {
        File[] listFiles = file.listFiles(filenameFilter);
        File lastModifiedFile = getLastModifiedFile(listFiles);
        if (lastModifiedFile.isDirectory()) {
            return getLastModifiedFile(lastModifiedFile, filenameFilter);
        } else {
            return lastModifiedFile;
        }
    }

    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static class HideFilenameFilter implements FilenameFilter{

        @Override
        public boolean accept(File dir, String name) {
            return !name.startsWith(".");
        }
    }
}
