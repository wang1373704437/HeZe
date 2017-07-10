package com.linkct.hzdlzcgl.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by wlh on 2017/6/29.
 */

public class FileUtils {
    public static String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/CXMobile/";


    public static String IMAGE_PATH = FILE_PATH + "images/";//
    public static String DB_PATH = FILE_PATH + "db/";//
    public static String CACHE_PATH = FILE_PATH + "cache/";//
    public static void initPath() {
        File dbFile = new File(DB_PATH);
        File imageFile = new File(IMAGE_PATH);
        File cacheFile = new File(CACHE_PATH);
        if (!imageFile.exists()) {
            imageFile.mkdirs();
        }
        if (!dbFile.exists()) {
            dbFile.mkdirs();
        }
        if (!cacheFile.exists()) {
            dbFile.mkdirs();
        }
    }
}
