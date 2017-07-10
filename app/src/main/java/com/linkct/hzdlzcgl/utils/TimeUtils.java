package com.linkct.hzdlzcgl.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wlh on 2017/7/3.
 */

public class TimeUtils {


    /**
     * 格式化时间
     *
     * @param timeStamp
     * @return
     */
    public static String formatDate(String timeStamp) {
        SimpleDateFormat standard = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(Long.parseLong(timeStamp));
        String time = standard.format(date);
        return time;
    }
}
