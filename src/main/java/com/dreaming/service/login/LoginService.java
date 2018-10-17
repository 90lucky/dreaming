package com.dreaming.service.login;

import com.dreaming.base.ServerFlow;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.service.user.IUserCreate;
import com.dreaming.util.RedisUtil;
import com.dreaming.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ILoginQuery loginQuery;

    @Autowired
    private ILoginCreate loginCreate;

    @Autowired
    private IUserCreate userCreate;

    public void createLogin(UserBaseEntity userBaseEntity) throws DreamingSysException {
        //添加流的内容
        String flowId = ToolUtil.getRandomUUID();
        ServerFlow.setContxt(flowId, userBaseEntity);
        ServerFlow.addStep(flowId, loginCreate);

        //执行流
        ServerFlow.run(flowId);
    }

    public UserBaseEntity queryLogin(UserBaseEntity userBaseEntity) throws DreamingSysException {

        //添加流
        String flowId = ToolUtil.getRandomUUID();
        RedisUtil.put("flowId", flowId);
        ServerFlow.setContxt(flowId, userBaseEntity);
        ServerFlow.addStep(flowId, loginQuery);
        ServerFlow.addStep(flowId, userCreate);

        //执行流
        ServerFlow.run(flowId);

        return (UserBaseEntity) ServerFlow.getContxt(flowId);
    }
}
