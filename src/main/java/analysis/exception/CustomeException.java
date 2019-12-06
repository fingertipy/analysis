package analysis.exception;

import lombok.Data;
import lombok.Getter;

/**
 * 自定义yic
 */
@Getter
public class CustomeException extends RuntimeException {
    private Integer code; //异常代码
    private String  msg;  //异常描述

    public CustomeException(Integer code, String msg){
        this.code = code;
        this.msg  = msg;
    }

    public CustomeException(ExceptionEnum em){
        this.code = em.getCode();
        this.msg  = em.getMsg();
    }
}
