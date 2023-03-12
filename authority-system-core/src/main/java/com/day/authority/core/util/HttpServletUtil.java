package com.day.authority.core.util;

import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:02
 */
public class HttpServletUtil {

    public static HttpServletRequest getRequest(Boolean requireNonNull) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes)) {
            if (requireNonNull) {
                throw new RuntimeException("null");
            } else {
                return null;
            }
        } else {
            return requestAttributes.getRequest();
        }
    }

    public static HttpServletRequest getRequest() {
        return getRequest(false);
    }


    public static HttpMethod getRequestMethod(String name) {
        for (HttpMethod value : HttpMethod.values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }


    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }

}
