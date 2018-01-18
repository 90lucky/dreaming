package com.dreaming.model.entity;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author lucky
 * create on 2017/12/11
 */
public class AbstractEntity extends HashMap<String,Object> {
    protected static final String KEY_TABLE = "TABLE_NAME";

    protected  <T extends AbstractEntity> void  initEntity(T childEntity)
    {
        Field[] fields = childEntity.getClass().getDeclaredFields();
        for(Field field:fields)
        {
            if(field.getName().startsWith("KEY"))
            {
                try {
                    this.put((String)field.get(childEntity),null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
