package com.dreaming.dao;

import com.dreaming.base.ErrorCode;
import com.dreaming.dao.pool.BaseSysConPool;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserBaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lucky
 * create on 2017/12/9
 */
public class LoginDao extends BaseDao {
    private static Logger logger = LoggerFactory.getLogger(LoginDao.class);
    private static LoginDao loginDao = new LoginDao();
    private static Connection conn  ;
    private static String gid;
    private LoginDao(){}
    public static LoginDao getInstance(String id){
        gid = id;
        conn = BaseSysConPool.getConnection(gid);
        return loginDao;
    }

    /**
     * query all Users
     * @param userEntity
     * @return
     * @throws DreamingSysException
     */
    public List<UserBaseEntity> queryAllUserBase(UserBaseEntity userEntity) throws DreamingSysException{

        List<UserBaseEntity> list = null;
        try {
            list = queryAll(conn,userEntity);
        } catch (SQLException|InstantiationException|IllegalAccessException e) {
            logger.error("queryAllUserBase failed:{}", e);
            throw new DreamingSysException(ErrorCode.BASE_SQL_ERROR, "queryAllUserBase failed");
        }  finally {
            BaseSysConPool.release(conn);
        }
        return list;
    }

    /**
     * query one user
     * @param queryEntity
     * @return
     * @throws DreamingSysException
     */
    public UserBaseEntity queryUserBase(UserBaseEntity queryEntity) throws DreamingSysException {
        UserBaseEntity entity;
        try {
            entity = queryOne(conn,queryEntity);
        } catch (SQLException|InstantiationException|IllegalAccessException e) {
            logger.error("queryUserBase failed:{}", e);
            throw new DreamingSysException(ErrorCode.BASE_SQL_ERROR,"queryUserBase failed");
        }  finally {
            BaseSysConPool.release(conn);
        }
        return entity;
    }

    public UserBaseEntity queryByPhone(String phone) throws DreamingSysException{

        UserBaseEntity entity;
        try {
            UserBaseEntity queryEntity = new UserBaseEntity();
            queryEntity.setPhone(phone);
            entity = queryOne(conn,queryEntity);
        } catch (SQLException|InstantiationException|IllegalAccessException e) {
            logger.error("queryUserBase failed:{}", e);
            throw new DreamingSysException(ErrorCode.BASE_SQL_ERROR,"queryUserBase failed");
        }  finally {
            BaseSysConPool.release(conn);
        }
        return entity;
    }

    public void createUser(UserBaseEntity userEntity) throws DreamingSysException {
        try {
            create(conn,userEntity);
        } catch (SQLException e) {
            logger.error("queryUserBase failed:{}", e.getMessage());
            throw new DreamingSysException(ErrorCode.BASE_SQL_ERROR,"createUser failed");
        }

    }

}
