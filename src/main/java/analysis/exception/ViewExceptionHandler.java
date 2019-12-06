package analysis.exception;

import analysis.constatns.GlobalConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description VIEW异常处理器
 * @Author      dayu
 * @Date        2019/12/6 16:47
 * @Version     v1.0
 */
@Controller
public class ViewExceptionHandler implements ErrorController {
    //请求响应码属性
    private static final String REQUEST_STATUS = "javax.servlet.error.status_code";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取响应码
        Integer code = (Integer)request.getAttribute(REQUEST_STATUS);
        //错误转发路径
        String  path = "/404";
        //匹配转发路径
        switch (code){
            case GlobalConstants.FAILURE:
                path = "/404";
                break;
            case GlobalConstants.PARAME_ERROR:
                path = "/404";
                break;
            case GlobalConstants.SERVER_ERROR:
                path = "/500";
                break;
            case GlobalConstants.REQUEST_NOT_FOUND:
                path = "/404";
                break;
        }
        return path;
    }

    @Override
    public String getErrorPath() {
        return "/index";
    }
}
