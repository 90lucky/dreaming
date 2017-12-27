package com.dreaming.base;

import com.dreaming.service.BaseService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lucky
 * create on 2017/12/22
 */
public class ServerFlow {

    private static final  Map<String,LinkedList<BaseService>> FLOW_STEP = new HashMap<>();
    private static final  Map<String,Object> FLOW_CONTEXT = new HashMap<>();

    public static void addStep(BaseService service, String id)
    {
        if(FLOW_STEP.containsKey(id))
        {
            FLOW_STEP.get(id).add(service);
        }
        else
        {
            LinkedList<BaseService> list = new LinkedList<>();
            list.add(service);
            FLOW_STEP.put(id,  list);
        }
    }

    public static LinkedList<BaseService> getFlow(String id){
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

//    public static void run(String id)
//    {
//        LinkedList<BaseService> list = FLOW.get(id);
//        for (BaseService service:list)
//        {
//            ServerReturn result = service.run(id);
//            if(result==ServerReturn.FAILED)
//            {
//                //事务回滚
//                break;
//            }
//        }
//        //事务提交
//    }

}
