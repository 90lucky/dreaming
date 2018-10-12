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

    private Result(){}


    public static Result error(String msg) {
        return error(ErrorCode.SYS_UNKNOW_ERROR,msg);
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
        return getResultReturn(ErrorCode.SYS_SUCCESS,msg,page,data);
    }


    private static Result getResultReturn(String code, String msg, Page page,Object data) {
        Result resultReturn = new Result();
        resultReturn.put(Constants.CODE,code);
        resultReturn.put(Constants.MESSAGE,msg);
        if (null != page || null != data)
            resultReturn.put(Constants.RESPONSE, getResponse(page, data));
        return resultReturn;
    }

    private static Map<String,Object> getResponse(Page page, Object data){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put(Constants.PAGE,page);
        response.put(Constants.DATA,data);
        return response;
    }

}
