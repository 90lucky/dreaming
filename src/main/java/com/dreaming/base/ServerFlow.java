package com.dreaming.base;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.IBaseService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lucky
 * create on 2017/12/22
 */
public class ServerFlow {

    private static final  Map<String,List<IBaseService>> FLOW_STEP = new HashMap<>();
    private static final  Map<String,Object> FLOW_CONTEXT = new ConcurrentHashMap<>();

    public static void addStep(String id,IBaseService service)
    {
        if(FLOW_STEP.containsKey(id))
        {
            FLOW_STEP.get(id).add(service);
        }
        else
        {
            List<IBaseService> list = new LinkedList<>();
            list.add(service);
            FLOW_STEP.put(id,  list);
        }
    }

    public static List<IBaseService> getFlow(String id){
        return FLOW_STEP.get(id);
    }

    public static void setContxt(String id,Object o)
    {
        FLOW_CONTEXT.put(id,o);
    }

    public static Object getContxt(String id)
    {
       return FLOW_CONTEXT.get(id);
    }

    public static void run(String id) throws DreamingSysException {
        try {
            List<IBaseService> list = FLOW_STEP.get(id);
            for (IBaseService service:list)
            {
                ServerReturn result = service.run(id);
                if (result == ServerReturn.FAILED) {
                    JDBCManagers.rollback(id);
                    break;
                } else if (result == ServerReturn.NEXT) {
                    service.run(id);
                }
            }
        }catch (DreamingSysException e)
        {
            JDBCManagers.rollback(id);
            throw e;
        }

        JDBCManagers.commit(id);
    }

}
