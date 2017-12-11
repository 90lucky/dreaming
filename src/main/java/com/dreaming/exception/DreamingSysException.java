package com.dreaming.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Message:this exception is defined for System handle exception,others not allowed which like service exception
 *         jdbc exception or transaction exception ... if those happened, please find another
 * Content:Here is three kinds of exception,which no param,or String exceptionMsg,
 *         or exceptionMsg with {} and param
 * @author lucky
 * create on 2017/12/8
 */
public class DreamingSysException extends Exception {

    private String errorCode;
    private String errorMsg;
    private Exception exception;

    public DreamingSysException() {
        super();
    }

    public DreamingSysException(String errorCode,String message) {
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public DreamingSysException(String errorCode,String message,String... params) {
        this.errorCode = errorCode;
        this.errorMsg = replaceWithParams(message,params);
    }

    public DreamingSysException(String errorCode,String message,Exception e,String... params) {
        this.errorCode = errorCode;
        this.errorMsg = replaceWithParams(message,params);
        this.exception = e;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * To replace exception message contains {} with exist params
     *  example: "this is the {} exception: {}" , first, systemException
     *          this is the first exception: systemException
     * @param exceptionStr the string message
     * @param params exception params
     * @return String
     */
    private static String replaceWithParams(String exceptionStr,String... params ) {
        String strRegex = "\\{\\}";
        int i = 0;
        Matcher m= Pattern.compile(strRegex).matcher(exceptionStr);
        while(m.find()){
            if(i+1 <= params.length)
                exceptionStr=exceptionStr.replaceFirst(strRegex,params[i]);
            i++;
        }
        return exceptionStr;
    }
}
