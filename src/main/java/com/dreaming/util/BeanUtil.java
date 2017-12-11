package com.dreaming.util;

import com.dreaming.exception.DreamingSysException;
import org.springframework.util.ObjectUtils;

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

    private BeanUtil(){}

    /**
     * to convert bean of request to the entity that is consistent with db parameters
     * @param sourceBean
     * @param entity
     * @param <T>
     * @return
     * @throws DreamingSysException
     */
    public static <T> T convertBean2Entity(Object sourceBean, T entity) throws DreamingSysException {

        if (ObjectUtils.isEmpty(sourceBean)) {
            throw new DreamingSysException("convertBean2Entity sourceBean not allow Empty:", sourceBean.getClass().getName());
        }
        Method[] methods = sourceBean.getClass().getMethods();

        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set") || methodName.startsWith("is")) {
                methodName = methodName.replaceFirst("set||is", "get");
                try {
                    Method desMethod = method.getClass().getMethod(methodName, method.getReturnType());
                    desMethod.invoke(entity, method.invoke(sourceBean));
                } catch (NoSuchMethodException e) {
//                    throw new DreamingSysException("NoSuchMethodException: method:{},source bean:{},des bean:{}",
//                            methodName, sourceBean.toString(), entity.toString());
                } catch (IllegalAccessException e) {
                        System.out.println("");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return entity;
    }

    /**
     *
     * @param bean
     * @param map
     * @param <T>
     */
    public static <T> void convertBeanToMap(T bean, Map map)
    {
    }
}
