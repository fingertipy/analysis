package analysis.server.impl;

import analysis.entity.UserInfoEntity;
import analysis.exception.CustomeException;
import analysis.exception.ExceptionEnum;
import analysis.server.LoginService;
import analysis.server.UserInfoService;
import analysis.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Login service imp
 */
@Service
public class LoginServiceImpl implements LoginService {

    //首页
    private static final String INDEX_PAGE = "/index";

    //首页
    private static final String LOGIN_PAGE = "/login";

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Boolean login(UserInfoEntity entity) throws CustomeException{
        //参数校验
        if (entity == null || StringUtils.isBlank(entity.getUsername()) || StringUtils.isBlank(entity.getPassword()))
            throw new CustomeException(ExceptionEnum.PARAME_ERROR);
        //获取用户信息
        UserInfoEntity userInfo = userInfoService.selectUserInfo(entity.getUsername());
        //用户不存在
        if (userInfo == null)
            throw new CustomeException(ExceptionEnum.USER_NOT_FOUND);
        //密码错误
        if (!MD5Utils.MD5Code(entity.getPassword()).equals(MD5Utils.MD5Code(userInfo.getPassword())))
            throw new CustomeException(ExceptionEnum.PASSWORD_ERROR);
        return true;
    }

    @Override
    public String register(UserInfoEntity entity) {
        return LOGIN_PAGE;
    }
}
