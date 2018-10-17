package com.dreaming.base;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.IBaseService;
import com.dreaming.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Message:服务注册池
 * <p>
 * Content:
 *
 * @author lucky
 * create on 16/10/2018
 */
public class ServicePool {
    private final static Logger logger = LoggerFactory.getLogger(ServicePool.class);
    private static Map<String, IBaseService> serviceMap = new ConcurrentHashMap<>();
    private static Map<String, LinkedBlockingQueue<IBaseService>> serviceQueueMap = new ConcurrentHashMap<>();

    /**
     * 服务注册
     *
     * @param serviceId
     * @param service
     * @throws DreamingSysException
     */
    public static void addStep(String serviceId, IBaseService service) throws DreamingSysException {
        if (ToolUtil.strIsBlanck(serviceId)) {
            throw new DreamingSysException("");
        }
        serviceMap.put(serviceId, service);
    }

    /**
     * 服务编排
     *
     * @param flowId
     * @param service1
     * @param service2
     */
    public static void addFlow(String flowId, String service1, String service2) throws InterruptedException {
        if (serviceQueueMap.containsKey(flowId)) {
            Queue<IBaseService> queue = serviceQueueMap.get(flowId);
            if (queue.contains(serviceMap.get(service1))) {
                queue.offer(serviceMap.get(service2));
            } else {
                queue.offer(serviceMap.get(service1));
                queue.offer(serviceMap.get(service2));
            }
        }
    }

    /**
     * 服务执行器
     *
     * @param id
     * @throws DreamingSysException
     */
    public static void run(String id) throws DreamingSysException {
        try {
            Queue<IBaseService> serviceQueue = serviceQueueMap.get(id);
            while (!serviceQueue.isEmpty()) {
                IBaseService service = serviceQueue.poll();
                ServerReturn result = service.run(id);
                if (result == ServerReturn.FAILED) {
                    JDBCManagers.rollback(id);
                    removeKey(id);
                    break;
                } else if (result == ServerReturn.NEXT) {
                    serviceQueue.offer(service);
                }
            }
        } catch (DreamingSysException e) {
            JDBCManagers.rollback(id);
            throw e;
        }

        JDBCManagers.commit(id);
    }

    /**
     * 工作流执行失败后，将该流中与当前id相关的队列清空
     * @param flowId
     */
    private static void removeKey(String flowId){
        Queue<IBaseService> serviceQueue = serviceQueueMap.get(flowId);
        serviceQueue.clear();
        serviceQueueMap.remove(flowId);
    }

}
