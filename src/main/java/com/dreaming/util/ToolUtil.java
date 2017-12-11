package com.dreaming.util;

import org.springframework.util.ObjectUtils;

/**
 * create by lucky on 2017/12/8
 */
public final class ToolUtil {

    private ToolUtil() {
    }

    public static boolean checkArrayNotBlank(Object object) {
        return ObjectUtils.isArray(object);
    }

    public static boolean strIsBlanck(String str) {
        return null == str || 0 == str.trim().length();
    }
}
