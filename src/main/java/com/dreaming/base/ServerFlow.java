package com.dreaming.base;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.IBaseService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lucky
 * create on 2017/12/22
 */
public class ServerFlow {

    private static final  Map<String,LinkedList<IBaseService>> FLOW_STEP = new HashMap<>();
    private static final  Map<String,Object> FLOW_CONTEXT = new HashMap<>();

    public static void addStep(String id,IBaseService service)
    {
        if(FLOW_STEP.containsKey(id))
        {
            FLOW_STEP.get(id).add(service);
        }
        else
        {
            LinkedList<IBaseService> list = new LinkedList<>();
            list.add(service);
            FLOW_STEP.put(id,  list);
        }
    }

    public static LinkedList<IBaseService> getFlow(String id){
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
            LinkedList<IBaseService> list = FLOW_STEP.get(id);
            for (IBaseService service:list)
            {
                ServerReturn result = service.run(id);
                if(result==ServerReturn.FAILED)
                {
                    JDBCManagers.rollback(id);
                    break;
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
