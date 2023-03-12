package com.day.authority.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.WeakConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DayXhYao
 * @date 2023/3/11 10:48
 */
@Slf4j
public class BeanCopierUtil {

    /**
     * BeanCopier的缓存
     */
    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new WeakConcurrentMap<>();

    /**
     * BeanCopier的copy
     *
     * @param source 源文件的
     * @param target 目标文件
     */
    public static <T> T copy(Object source, T target) {
        String key = getKey(source.getClass(), target.getClass());
        BeanCopier beanCopier;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            beanCopier = BEAN_COPIER_CACHE.get(key);
        } else {
            beanCopier = BeanCopier.create(source.getClass(), target.getClass(), Boolean.FALSE);
            BEAN_COPIER_CACHE.put(key, beanCopier);
        }
        beanCopier.copy(source, target, null);
        return target;
    }


    public static <T> List<T> copy(List<?> source, Class<T> clazz) {
        if (CollectionUtil.isEmpty(source)) {
            return CollectionUtil.newArrayList();
        }

        ArrayList<T> response = new ArrayList<>();

        try {
            for (Object sourceObj : source) {
                T copy = copy(sourceObj, clazz.getDeclaredConstructor().newInstance());
                response.add(copy);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }


    /**
     * 生成key
     *
     * @param srcClazz 源文件的class
     * @param tgtClazz 目标文件的class
     * @return string
     */
    private static String getKey(Class<?> srcClazz, Class<?> tgtClazz) {
        return srcClazz.getName() + tgtClazz.getName();
    }

}
