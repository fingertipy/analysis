package analysis.exception;

import analysis.controller.ApiController;
import analysis.entity.Response;
import analysis.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description API异常处理器
 * @Author      dayu
 * @Date        2019/12/6 16:05
 * @Version     v1.0
 */
@RestControllerAdvice(basePackageClasses = {ApiController.class})
public class ApiExceptionHandler {

    @ExceptionHandler(CustomeException.class)
    public Response customeExceptionHandler(HttpServletRequest request, CustomeException ex) throws Exception{
        //打印异常日志
        LogUtils.info("自定义异常: {}", ex.getMsg());
        //返回异常响应
        return Response.ERROR(ex);
    }

    @ExceptionHandler(Exception.class)
    public Response defaultExceptionHandler(HttpServletRequest request, Exception ex) throws Exception{
        //打印异常日志
        LogUtils.error(ex, "哎呀! 异常了: " + request.getRequestURL());
        //返回异常响应
        return Response.ERROR();
    }
}
