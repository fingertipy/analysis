package analysis.server;

import analysis.entity.UserInfoEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Login Service
 */
public interface LoginService {
    /**
     * 登录
     * @param entity
     */
    Boolean login(HttpServletRequest request, UserInfoEntity entity);

    /**
     * 退出
     */
    Boolean logout(HttpServletRequest request);

    /**
     * 注册
     * @param entity
     * @return
     */
    Boolean register(UserInfoEntity entity);
}
