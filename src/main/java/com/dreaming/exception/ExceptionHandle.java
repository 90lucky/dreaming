package com.dreaming.exception;

import com.dreaming.base.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Message:exception handle class
 * <p>
 * Content:
 *         DreamingSysException :
 *            HttpStatus:400
 *            return error code and error message about self;
 *         Exception :
 *            HttpStatus:500
 *            return error code :500 and error message <p>System error,please contact administrator later!</p>
 *
 * @author lucky
 * create on 11/10/2018
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 已知异常返回400
     * @param e
     * @param httpServletResponse
     * @return
     */
    @ExceptionHandler(DreamingSysException.class)
    @ResponseBody
    public Result catchDreamingException(DreamingSysException e, HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return Result.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 系统异常返回500
     * @param e
     * @param httpServletResponse
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result catchSysException(Exception e, HttpServletResponse httpServletResponse){
        httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return Result.error("System error,please contact administrator later!");
    }
}
