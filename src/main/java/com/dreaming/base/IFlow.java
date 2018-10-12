package com.dreaming.base;

import com.dreaming.service.IBaseService;

/**
 * Message:  IBaseService  分同步服务、异步服务，同步服务执行两段式事务，异步执行三段式事务，
 *           失败通知，从上到下，事务回滚，从上到下
 * <p>
 * Content:
 *
 * @author lucky
 * create on 12/10/2018
 */
public interface IFlow {
    //服务注册接口
    void addStep(IBaseService service,String serviceId);
    //服务治理接口
    void addFlow(String fromServiceId,String nextServiceId,ServerReturn serverReturn);

}
