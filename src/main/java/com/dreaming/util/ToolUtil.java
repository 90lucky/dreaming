package com.dreaming.util;

import com.dreaming.exception.DreamingSysException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Message：utils for usual checked about different content, all methods are static which do not need
 *          new an instance
 *
 * Content：
 *
 * create by lucky on 2017/12/8
 */
public final class ToolUtil {

    private ToolUtil() {
    }

    /**
     * 正则表达式：验证手机号
     */
    private static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 日期格式
     */
    private static final String REGEX_NOW_DATE = "yyyyMMdd HH:mm:ss";

    /**
     *
     */
    private static final String REGEX_LINE = "-";

    /**
     *
     */
    private static final String REGEX_SPACE = "";
    /**
     *
     * @param str
     * @return
     */
    public static boolean strIsBlanck(String str) {
        return null == str || 0 == str.trim().length();
    }

    /**
     * check phone number is true or not
     * @param phoneStr
     * @return
     */
    public static boolean checkIsMobile(String phoneStr) throws DreamingSysException {
        Pattern regex = Pattern.compile(REGEX_MOBILE);
        Matcher matcher = regex.matcher(phoneStr);
        return matcher.matches();
    }

    /**
     * check email number is true or not
     * @param emailStr
     * @return
     */
    public static boolean checkEmail(String emailStr) throws DreamingSysException {
        Pattern regex = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = regex.matcher(emailStr);
        return matcher.matches();
    }

    /**
     * date to string which format yyyyMMdd HH:mm:ss
     * @return yyyyMMdd HH:mm:ss
     */
    public static String date2Str(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(REGEX_NOW_DATE);
        return simpleDateFormat.format(date);
    }

    /**
     * UUID to str
     * @return
     */
    public static String getRandomUUID()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace(REGEX_LINE,REGEX_SPACE);
    }
}
