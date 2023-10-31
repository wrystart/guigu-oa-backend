package com.atguigu.common.config.exception;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * @Description 全局异常处理
 * @Author wrystart
 * @Version
 * @Date 2023/10/18 20:55
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理...");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    //特定异常处理
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("执行了特定异常处理...");

    }

    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    //自定义异常处理
    public Result error(GuiguException e){
        e.printStackTrace();
        return Result.fail().message("执行了自定义异常处理...");

    }

    /**
     * spring security异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public Result error(AccessDeniedException e) throws AccessDeniedException {
        return Result.fail().code(205).message("没有操作权限");
    }

}