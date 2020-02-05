package analysis.controller;

import analysis.entity.SampleEntity;
import analysis.entity.UserInfoEntity;
import analysis.server.LoginService;
import analysis.server.SampleService;
import analysis.server.UserInfoService;
import analysis.utils.CacheUtils;
import analysis.utils.PythonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * API Controller
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SampleService sampleService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/sample")
    public List<SampleEntity> sample(){
        return sampleService.selectAllSample();
    }

    @GetMapping("/selectuser")
    public UserInfoEntity selectUserInfo(String username){
        return userInfoService.selectUserInfo(username);
    }

    @GetMapping("/saveuser")
    public Integer saveUserInfo(UserInfoEntity entity){
        return userInfoService.saveUserInfo(entity);
    }

    @GetMapping("/updateuser")
    public Integer udpateUserInfo(UserInfoEntity entity){
        return userInfoService.updateUserInfo(entity);
    }

    @GetMapping("/exec")
    public void exec(){
        String pypath = "D:\\repository\\analysis_python\\analysis\\model.py";
        PythonUtils.exec(pypath);
        String pypath1 = "D:\\repository\\analysis_python\\analysis\\parametor.py";
        PythonUtils.exec(pypath1, "6");
    }

    @GetMapping("/login")
    public Boolean login(HttpServletRequest request, UserInfoEntity entity){
        return loginService.login(request, entity);
    }

    @GetMapping("/logout")
    public Boolean logtout(HttpServletRequest request){
        return loginService.logout(request);
    }

    @GetMapping("/register")
    public Boolean register(UserInfoEntity entity){
        return loginService.register(entity);
    }
}
