package analysis.controller;

import analysis.entity.SampleEntity;
import analysis.entity.UserInfoEntity;
import analysis.server.SampleService;
import analysis.server.UserInfoService;
import analysis.utils.PythonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * API Controller
 */
@RestController
@RequestMapping("/api")
public class ApiController {
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
        PythonUtils.exec(pypath1, "4", "6");
    }
}
