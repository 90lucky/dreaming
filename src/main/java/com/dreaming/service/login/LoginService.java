package com.dreaming.service.login;

import com.dreaming.base.ServerFlow;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService{
    @Autowired
    private ILoginQuery loginQuery;

    @Autowired
    private ILoginUpdate loginUpdate;

    @Autowired
    private ILoginCreate loginCreate;

    public void createLogin(UserBaseEntity userBaseEntity) throws DreamingSysException {
        //添加流的内容
        String flowId = ToolUtil.getRandomUUID();
        ServerFlow.setContxt(flowId,userBaseEntity);
        ServerFlow.addStep(flowId,loginCreate);
//        ServerFlow.addStep(flowId,loginUpdate);

        //执行流
        ServerFlow.run(flowId);
    }

    public UserBaseEntity queryLogin(UserBaseEntity userBaseEntity) throws DreamingSysException {
        //添加流的内容
        String flowId = ToolUtil.getRandomUUID();
        ServerFlow.setContxt(flowId,userBaseEntity);
        ServerFlow.addStep(flowId,loginQuery);

        //执行流
        ServerFlow.run(flowId);

        return (UserBaseEntity) ServerFlow.getContxt(flowId);
    }
}
