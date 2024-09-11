package com.fqishappy.utils;

import lombok.val;
import org.springframework.beans.BeanUtils;

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
}
