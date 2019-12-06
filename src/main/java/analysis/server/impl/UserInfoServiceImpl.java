package analysis.server.impl;

import analysis.entity.UserInfoEntity;
import analysis.mapper.UserInfoMapper;
import analysis.server.UserInfoService;
import analysis.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Description user info service impl
 * @Author      dayu
 * @Date        2019/12/6 18:05
 * @Version     v1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Integer saveUserInfo(UserInfoEntity userInfoEntity) {
        try {
            return userInfoMapper.saveUserInfo(userInfoEntity);
        } catch (Exception e) {
            LogUtils.error(e, "新增用户信息异常");
        }
        return 0;
    }

    @Override
    public Integer updateUserInfo(UserInfoEntity userInfoEntity) {
        try {
            return userInfoMapper.updateUserInfo(userInfoEntity);
        } catch (Exception e) {
            LogUtils.error(e, "更新用户信息异常");
        }
        return 0;
    }

    @Override
    public UserInfoEntity selectUserInfo(String username) {
        try {
            if (StringUtils.isBlank(username)) return new UserInfoEntity(username);
            return userInfoMapper.selectUserInfoByUsername(username);
        } catch (Exception e) {
            LogUtils.error(e, "查询用户信息异常");
        }
        return new UserInfoEntity(username);
    }
}
