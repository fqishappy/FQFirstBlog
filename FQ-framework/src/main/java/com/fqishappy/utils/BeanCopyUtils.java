package com.fqishappy.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fqishappy
 * @date 2024/9/11 15:57
 */
public class BeanCopyUtils {
    private BeanCopyUtils() {}
    public static <T>T copyBean(Object source, Class<T> clazz) {
        //创建目标对象
        T target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    public static <V,T> List<T> copyBeanList(List<V> list,Class<T> clazz ) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
