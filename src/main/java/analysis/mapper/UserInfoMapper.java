package analysis.mapper;

import analysis.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description User info Mapper
 * @Author      dayu
 * @Date        2019/12/6 17:52
 * @Version     v1.0
 */
@Mapper
public interface UserInfoMapper {
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
    UserInfoEntity selectUserInfoByUsername(@Param("username") String username);
}
