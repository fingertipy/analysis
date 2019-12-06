package analysis.server;

import analysis.entity.UserInfoEntity;

/**
 * Login Service
 */
public interface LoginService {
    /**
     * 登录
     * @param entity
     */
    Boolean login(UserInfoEntity entity);

    /**
     * 注册
     * @param entity
     * @return
     */
    String register(UserInfoEntity entity);
}
