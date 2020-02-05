package analysis.server.impl;

import analysis.constatns.ModelConstants;
import analysis.entity.*;
import analysis.exception.CustomeException;
import analysis.exception.ExceptionEnum;
import analysis.server.LoginService;
import analysis.server.UserInfoService;
import analysis.utils.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Boolean login(HttpServletRequest request, UserInfoEntity entity) throws CustomeException{
        //参数校验
        if (entity == null || StringUtils.isBlank(entity.getUsername()) || StringUtils.isBlank(entity.getPassword()))
            throw new CustomeException(ExceptionEnum.PARAME_ERROR);
        //获取用户信息
        UserInfoEntity userInfo = userInfoService.selectUserInfo(entity.getUsername());
        //用户不存在
        if (userInfo == null)
            throw new CustomeException(ExceptionEnum.USER_NOT_FOUND);
        //密码错误
        if (!MD5Utils.MD5Code(entity.getPassword()).equals(userInfo.getPassword()))
            throw new CustomeException(ExceptionEnum.PASSWORD_ERROR);
        //添加至缓存
        CacheUtils.put(request.getSession().getId(), userInfo);
        return true;
    }

    @Override
    public Boolean logout(HttpServletRequest request) throws CustomeException{
        CacheUtils.remove(request.getSession().getId());
        return true;
    }

    @Override
    public Boolean register(UserInfoEntity entity) throws CustomeException{
        //参数校验
        if (entity == null || StringUtils.isBlank(entity.getUsername()) || StringUtils.isBlank(entity.getPassword()))
            throw new CustomeException(ExceptionEnum.PARAME_ERROR);
        //获取用户
        UserInfoEntity userInfo = userInfoService.selectUserInfo(entity.getUsername());
        if (userInfo != null) throw new CustomeException(ExceptionEnum.USER_EXSIST);
        //密码MD5
        entity.setPassword(MD5Utils.MD5Code(entity.getPassword()));
        //注册
        Integer row = userInfoService.saveUserInfo(entity);
        //注册失败
        if (row == 0) throw new CustomeException(ExceptionEnum.ERROR);
        return true;
    }

    /**
     * 注册
     * @param userInfo    用户信息
     * @param coordinates 签名坐标
     * @param image       签名图片地址
     * @return
     */
    public boolean register(UserInfo userInfo, List<Coordinate> coordinates, MultipartFile multipartFile){
        //注册响应值
        boolean result = false;
        try {
            //保存用户信息至数据库
            result = saveUserInfo(userInfo);
            //保存用户签名坐标
            result = saveSignatureCoordinate(coordinates);
            //保存用户签名图片
            result = saveSignatureImage(FileUtils.saveFile(multipartFile));
            //返回注册结果
            return result;
        } catch (Exception e) {
            LogUtils.error(e, "用户注册异常,userInfo:{},coordinates:{}", userInfo, coordinates);
        }
        return result;
    }

    private boolean saveUserInfo(UserInfo userInfo){
        return true;
    }

    private boolean saveSignatureCoordinate(List<Coordinate> coordinates){
        return true;
    }

    private boolean saveSignatureImage(String image){
        return true;
    }

    /**
     * 签名认证
     * @param userInfo
     * @param coordinates 签名坐标
     * @param image       签名图片地址
     * @return
     */
    public boolean signatureVerification(Long account, List<Coordinate> coordinates, String image){
        //登录响应值
        boolean result = false;
        try {
            //用户信息非空校验
            if (account == null || CollectionUtils.isEmpty(coordinates) || StringUtils.isBlank(image)) return false;
            //构建坐标模型参数
            ModelData cModelData = ModelData.buidle(ModelConstants.MODEL_TYPE_COORDINATE, account, coordinates);
            //调用模型验证用户签名坐标
            ModelResult coordinateResult = PythonUtils.exec(VERIFICATION_COORDINATE_MODEL_PATH, JSONObject.toJSONString(cModelData));
            //构建图片模型参数
            ModelData iModelData = ModelData.buidle(ModelConstants.MODEL_TYPE_IAMGE, account, image);
            //调用模型验证用户签名图片
            ModelResult imageResult = PythonUtils.exec(VERIFICATION_IMAGE_MODEL_PATH, JSONObject.toJSONString(iModelData));
            //计算并返回模型验证结果
            return calcModelResult(coordinateResult, imageResult);
        } catch (Exception e) {
            LogUtils.error(e, "用户签名认证异常,account:{},coordinates:{},image:{}", account, coordinates, image);
        }
        return result;
    }

    private boolean calcModelResult(ModelResult coordinateResult, ModelResult imageResult){
        return true;
    }

    private static final String VERIFICATION_COORDINATE_MODEL_PATH = null;
    private static final String VERIFICATION_IMAGE_MODEL_PATH = null;
    private static final String VERIFICATION_TRAIN_MODEL_PATH = null;

    /**
     * 验证用户签名
     * @param account     用户账号
     * @param coordinates 签名坐标
     * @param image       签名图片地址
     * @return
     */
    private boolean verificationSignature(Long account, List<Coordinate> coordinates, String image){
        //参数非空校验
        if (account == null || CollectionUtils.isEmpty(coordinates) || StringUtils.isBlank(image)) return false;
        //构建坐标模型参数
        ModelData cModelData = ModelData.buidle(ModelConstants.MODEL_TYPE_COORDINATE, account, coordinates);
        //调用模型验证用户签名坐标
        ModelResult coordinateResult = PythonUtils.exec(VERIFICATION_COORDINATE_MODEL_PATH, JSONObject.toJSONString(cModelData));
        //构建图片模型参数
        ModelData iModelData = ModelData.buidle(ModelConstants.MODEL_TYPE_IAMGE, account, image);
        //调用模型验证用户签名图片
        ModelResult imageResult = PythonUtils.exec(VERIFICATION_IMAGE_MODEL_PATH, JSONObject.toJSONString(iModelData));
        //返回模型验证结果
        return imageResult.getVerification() && coordinateResult.getVerification();
    }

    /**
     * 训练模型
     * @param modelData
     */
    public void train(ModelData modelData){
        //设置类型为训练模型
        modelData.setType(ModelConstants.MODEL_TYPE_TRAIN);
        //调用模型完成训练
        PythonUtils.exec(VERIFICATION_TRAIN_MODEL_PATH, JSONObject.toJSONString(modelData));
    }
}
