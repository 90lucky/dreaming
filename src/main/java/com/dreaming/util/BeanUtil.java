package com.dreaming.util;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.AbstractEntity;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Message:used to convert bean to another
 *
 * Content:bean convert from ones to others flowing the rule that we define
 *
 * create by lucky on 2017/12/8
 */
public class BeanUtil {
    private final static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    private BeanUtil(){}

    /**
     * to convert bean of request to the entity that is consistent with db parameters
     * @param sourceBean
     * @param entity
     * @param <T>
     * @return
     * @throws DreamingSysException
     */
    public static <T extends AbstractEntity> T convertBean2Entity(Object sourceBean, T entity) {

        Method[] methods = sourceBean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("is")) {
                methodName = methodName.replaceFirst("get||is", "set");
                try {
                    Method desMethod = entity.getClass().getMethod(methodName, method.getReturnType());
                    desMethod.invoke(entity, method.invoke(sourceBean));
                } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
//                        e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }
        return entity;
    }

    /**
     * convert bean to map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"",beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * Convert map to bean
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }
}
