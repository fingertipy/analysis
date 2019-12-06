package analysis.entity;

import analysis.constatns.GlobalConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description 返回统一对象
 * @Author      dayu
 * @Date        2019/12/6 14:31
 * @Version     v1.0
 */
@Data
@ToString
@AllArgsConstructor
public class Response<T> implements Serializable {
    private Integer code; //响应状态
    private String  msg;  //响应消息
    private T       data; //返回数据

    //私有化构造器
    private Response(){}

    public static Response SUCCESS(){
        Response response = new Response();
        response.setCode(GlobalConstants.SUCCESS);
        response.setMsg(GlobalConstants.SUCCESS_MSG);
        return response;
    }

    public static Response SUCCESS(Object data){
        Response response = new Response();
        response.setData(data);
        response.setCode(GlobalConstants.SUCCESS);
        response.setMsg(GlobalConstants.SUCCESS_MSG);
        return response;
    }

    public static Response ERROR(){
        Response response = new Response();
        response.setCode(GlobalConstants.FAILURE);
        response.setMsg(GlobalConstants.FAILURE_MSG);
        return response;
    }
}
