package com.mall.product.config;

import com.mall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther zhz
 * @Date 2020-11-07 04:08
 */
@Slf4j
@RestControllerAdvice
public class ProductExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{},异常类型：{}", StringUtils.substring(e.getMessage(), 0, 200), e.getClass());
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        return R.error(HttpStatus.SC_BAD_REQUEST, "数据检验异常").put("data", errorMap);
    }

    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable throwable) {
        return R.error();
    }
}
