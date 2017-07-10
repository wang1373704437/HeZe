package com.linkct.hzdlzcgl.utils;

import java.util.UUID;

/**
 * Created by wlh on 2017/6/29.
 */

public class CommonUtils {


    public static String getUUID() {

        String uuid = UUID.randomUUID().toString();

        return uuid.substring(0, 8) + uuid.substring(9, 13)
                + uuid.substring(14, 18) + uuid.substring(19, 23)
                + uuid.substring(24);

    }
}
