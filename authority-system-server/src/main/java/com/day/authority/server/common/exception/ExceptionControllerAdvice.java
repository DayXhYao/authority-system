package com.day.authority.server.common.exception;

import com.day.authority.common.dto.Result;
import com.day.authority.common.enums.ResponseCode;
import com.day.authority.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:23
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("class.*", "Class.*", "*.class.*", "*.Class.*");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage(), e);

        //自定义异常
        if (e instanceof ServiceException) {
            return Result.fail(e.getMessage());
        }

        if (e instanceof MethodArgumentTypeMismatchException || e instanceof MissingServletRequestParameterException || e instanceof HttpMessageNotReadableException) {
            return Result.fail(ResponseCode.PARAM_ERROR);
        }

        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.fail("请求方法错误:" + e.getMessage());
        }

        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return Result.fail(fieldError.getDefaultMessage());
            } else {
                return Result.fail("处理接口参数异常");
            }
        }

        return Result.fail(ResponseCode.FAIL);
    }


}
