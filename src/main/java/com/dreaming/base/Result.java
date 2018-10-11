package com.dreaming.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private static final String CODE = "code";
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "500";
    private static final String MESSAGE = "msg";
    private static final String RESPONSE = "response";
    private static final String DATA = "date";
    private static final String PAGE = "page";

    private Result(){}


    public static Result error(String msg) {
        return error(ERROR_CODE,msg);
    }
    public static Result error(String code,String msg) {
        return getResultReturn(code,msg,null,null);
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(String msg) {
        return success(msg,null);
    }

    public static Result success(String msg,Object data) {
        return success(msg,null,data);
    }

    public static Result success(String msg, Page page, Object data) {
        return getResultReturn(SUCCESS_CODE,msg,page,data);
    }


    private static Result getResultReturn(String code, String msg, Page page,Object data) {
        Result resultReturn = new Result();
        resultReturn.put(CODE,code);
        resultReturn.put(MESSAGE,msg);
        resultReturn.put(RESPONSE,getResponse(page, data));
        return resultReturn;
    }

    private static Map<String,Object> getResponse(Page page, Object data){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put(PAGE,page);
        response.put(DATA,data);
        return response;
    }

}
