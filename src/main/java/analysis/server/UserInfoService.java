package analysis.server;

import analysis.entity.UserInfoEntity;

import java.util.List;

/**
 * @Description User info service
 * @Author      dayu
 * @Date        2019/12/6 18:04
 * @Version     v1.0
 */
public interface UserInfoService {
    /**
     * @Description 新增用户信息
     * @Author      dayu
     * @Date        2019/12/6 18:01
     * @Param       userInfoEntity
     * @Return      java.lang.Integer
     */
    Integer saveUserInfo(UserInfoEntity userInfoEntity);

    /**
     * @Description 更新用户信息
     * @Author      dayu
     * @Date        2019/12/6 18:02
     * @Param       userInfoEntity
     * @Return      java.lang.Integer
     */
    Integer updateUserInfo(UserInfoEntity userInfoEntity);

    /**
     * @Description 根据用户名查询用户信息
     * @Author      dayu
     * @Date        2019/12/6 18:03
     * @Param       username
     * @Return      java.util.List<analysis.entity.UserInfoEntity>
     */
    UserInfoEntity selectUserInfo(String username);
}
