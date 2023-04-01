package com.day.authority.common.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.WeakConcurrentMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     * @param source 源对象
     * @param clazz 目标对象
     * @return 目标对象必须要有无参构造器，否则返回为空
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        try {
            return copy(source, clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
        if (CollectionUtil.isEmpty(source)) {
            return CollectionUtil.newArrayList();
        }

        List<T> response = new ArrayList<>();

        for (Object sourceObj : source) {
            T copy = copy(sourceObj, clazz);
            if (Objects.isNull(copy)) {
                return response;
            }

            response.add(copy);
        }
        return response;
    }



    /**
     * BeanCopier的copy
     *
     * @param source 源对象
     * @param target 目标对象
     */
    private static <T> T copy(Object source, T target) {
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
