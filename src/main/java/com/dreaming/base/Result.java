package com.dreaming.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Message:About the result of all controllers, the request return should be clearly and enough
 *         to explain
 *
 * Content:result must contain the result code : success or error ,some types should be given
 *          some description
 *
 * @author lucky
 * create on 2017/12/9
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", ErrorCode.SYS_SUCCESS);
    }

    public static Result error() {
        return error(ErrorCode.SYS_UNKNOW_ERROR, "undefine error");
    }

    public static Result error(String msg) {
        return error(ErrorCode.SYS_UNKNOW_ERROR, msg);
    }

    public static Result error(String code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result success(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result success(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result success() {
        return new Result();
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
