package analysis.advice;

import analysis.entity.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description 全局返回结果处理器
 * @Author      dayu
 * @Date        2019/12/6 14:38
 * @Version     v1.0
 */
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Value("${filter.class}")
    private String filterClass;

    /**
     * @Description 是否处理返回数据
     * @Author      dayu
     * @Date        2019/12/6 14:40
     * @Return      boolean
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return filter(methodParameter);
    }

    /**
     * @Description 处理返回数据
     * @Author      dayu
     * @Date        2019/12/6 14:40
     * @Return      java.lang.Object
     */
    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object == null) return Response.SUCCESS();
        return Response.SUCCESS(object);
    }

    /**
     * @Description 判断是否处理
     * @Author      dayu
     * @Date        2019/12/6 14:51
     * @Return      java.lang.Boolean
     */
    private Boolean filter(MethodParameter parameter){
        Class<?> clazz = parameter.getDeclaringClass();
        if (filterClass.contains(clazz.getName())) return true;
        return false;
    }
}
