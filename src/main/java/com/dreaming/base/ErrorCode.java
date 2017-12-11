package com.dreaming.base;

/**
 * Message: all is constant about all error code which will happened in the project
 *
 * Content: all of the code need to be String,and different business can defined the unique code
 *
 * @author lucky
 * create on 2017/12/9
 */
public class ErrorCode {
    public static final String SYS_SUCCESS = "200";
    public static final String SYS_UNKNOW_ERROR = "500";

    private static final String BASE_CODE = "00000";

    private static final String USER_CODE = "00001";

    public static final String BASE_CREATE_EMPTY = BASE_CODE + "01";

    public static final String USER_NOT_EXIST = USER_CODE + "01";
}
