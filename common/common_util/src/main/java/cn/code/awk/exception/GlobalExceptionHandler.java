package cn.code.awk.exception;

import cn.code.awk.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author anwenkang
 * @version 1.0.0
 * @ClassName GlobalExceptionHandler.java
 * @Description TODO
 * @createTime 2021年09月12日 10:44:00
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<T> error(Exception e){
        log.error(e.getMessage(),Exception.class);
        return R.fail();
    }

    @ExceptionHandler(ZhylException.class)
    public R<T> error(ZhylException e){
        log.error(e.getMessage(),ZhylException.class);
        return R.fail();
    }
}