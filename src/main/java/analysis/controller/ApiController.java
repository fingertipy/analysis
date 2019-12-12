package analysis.controller;

import analysis.constatns.GlobalConstants;
import analysis.entity.Response;
import analysis.entity.UserInfoEntity;
import analysis.server.LoginService;
import analysis.server.TransferService;
import analysis.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * API Controller
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private LoginService loginService;

    @GetMapping("/invok")
    public void invok(String args){
        Response response = transferService.invok(Response.class, GlobalConstants.ANALYSIS_MODEL_1, args);
        LogUtils.info(response.toString());
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) throws Exception{
        String path = transferService.saveFile(file);
        LogUtils.info("file path: {}", path);
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
