package com.dreaming.service.login;

import com.dreaming.base.ServerFlow;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.bean.UserBean;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.util.RedisUtil;
import com.dreaming.util.ToolUtil;
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class LoginService{
    @Autowired
    private ILoginQuery loginQuery;

    @Autowired
    private ILoginUpdate loginUpdate;

    @Autowired
    private ILoginCreate loginCreate;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private JedisCluster jedis;

//    @Autowired
//    private RedisUtil redisUtil;

//    @Autowired
//    private BoneCPDataSource dataSource;
//    @Autowired
//    private UserBean user;
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
//        System.out.println(dataSource);
//        System.out.println(user);

        //添加流的内容
        String flowId = ToolUtil.getRandomUUID();
//        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.putString("flowId",flowId);
//        jedisCluster.set("flowId",flowId);
        jedis.set("flowId",flowId);
        ServerFlow.setContxt(flowId,userBaseEntity);
        ServerFlow.addStep(flowId,loginQuery);

        System.out.println(jedisCluster.get("flowId"));

        //执行流
        ServerFlow.run(flowId);

        return (UserBaseEntity) ServerFlow.getContxt(flowId);
    }
}
