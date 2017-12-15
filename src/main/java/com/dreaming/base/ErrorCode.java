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

    private static final String LOGIN_CODE = "00001";

    private static final String USER_CODE = "00002";

    /**
     * param is null
     */
    public static final String BASE_PARAM_NULL = BASE_CODE + "01";
    /**
     * request bean is null
     */
    public static final String BASE_BEAN_NULL = BASE_CODE + "02";

    /**
     * sql execute error
     */
    public static final String BASE_SQL_ERROR = BASE_CODE + "03";

    /**
     * phone num is illegal
     */
    public static final String LOGIN_PHONE_ILLEGAL = LOGIN_CODE + "01";

    /**
     * login user not exist
     */
    public static final String LOGIN_NOT_EXIST = LOGIN_CODE + "02";

    /**
     * login user already exist
     */
    public static final String LOGIN_ALREADY_EXIST = LOGIN_CODE + "03";
}
