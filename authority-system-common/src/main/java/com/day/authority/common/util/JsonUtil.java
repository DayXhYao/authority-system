package com.day.authority.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * @author DayXhYao
 * @date 2023/3/11 10:49
 */
public class JsonUtil {


    /**
     * 校验json格式是否正确
     *
     * @param json
     * @return
     */
    public static boolean isJson(String json) {
        JSONValidator.Type type = JSONValidator.from(json).getType();
        return JSONValidator.Type.Object == type || JSONValidator.Type.Array == type;
    }

    public static boolean isNotJson(String json) {
        return !isJson(json);
    }


    /**
     * bean转json
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> String toJson(T bean) {
        return JSON.toJSONString(bean, SerializerFeature.IgnoreErrorGetter);
    }

    public static <T> JSONObject toJsonObject(String json) {
        return JSONObject.parseObject(json);
    }


    public static <T> JSONObject toJsonObject(T bean) {
        return JSONObject.parseObject(toJson(bean));
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static <T> T fromJson(Object data, Class<T> clazz) {
        return JSON.parseObject(toJson(data), clazz);
    }

    public static <T> T fromJson(Object data, TypeReference<T> clazz) {
        return JSON.parseObject(toJson(data), clazz);
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static <T> List<T> fromJsonArray(Object data, Class<T> clazz) {
        return fromJsonArray(toJson(data), clazz);
    }


    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }


}
