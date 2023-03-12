package com.day.authority.common.dto;

import com.day.authority.common.constants.CommonConstant;
import com.day.authority.common.enums.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author DayXhYao
 * @date 2023/3/11 10:45
 */
@Data
@ApiModel("接口统一返回格式")
public class Result<T> implements Serializable {

    @ApiModelProperty("返回code: 200成功")
    private int code;
    @ApiModelProperty("返回信息")
    private String message;
    @ApiModelProperty("返回数据条数")
    private Integer count;
    @ApiModelProperty("返回数据")
    private T data;


    public static boolean isSuccess(Result dto) {
        return null != dto && ResponseCode.SUCCESS.getCode().equals(dto.getCode());
    }

    public static boolean isFail(Result dto) {
        return !isSuccess(dto);
    }

    public static <T> Result<T> success(T data) {
        Result<T> response = new Result<T>();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(ResponseCode.SUCCESS.getMessage());
        response.setData(data);

        if (data instanceof List) {
            response.setCount(((List) data).size());
        } else {
            response.setCount(CommonConstant.INT_ONE);
        }
        return response;
    }

    public static Result success(String message) {
        Result response = new Result();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(message);
        return response;
    }

    public static <T> Result<T> success(String message, T data) {
        Result response = new Result();
        response.setCode(ResponseCode.SUCCESS.getCode());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Result fail(String message) {
        return fail(message, null);
    }

    public static <T> Result<T> fail(String message, T data) {
        return fail(ResponseCode.FAIL.getCode(), message, data);
    }

    public static Result fail(ResponseCode resCode) {
        return fail(resCode.getCode(), resCode.getMessage(), null);
    }


    public static <T> Result<T> fail(Integer code, String message, T data) {
        Result response = new Result();
        response.setCode(code);
        response.setMessage(message);
        response.setCount(CommonConstant.INT_ZERO);
        response.setData(data);
        return response;
    }


    public static <T> Result<T> fail(ResponseCode resCode, T data) {
        Result response = new Result();
        response.setCode(resCode.getCode());
        response.setMessage(resCode.getMessage());
        response.setData(data);
        return response;
    }


}
