package analysis.exception;

/**
 * 异常枚举类
 */
public enum ExceptionEnum {
    ERROR(-1, "参数异常"),
    PARAME_ERROR(-2, "参数异常"),
    USER_NOT_FOUND(-3, "用户不存在"),
    PASSWORD_ERROR(-4, "账号或密码错误"),
    USER_EXSIST(-5, "用户已存在");

    private Integer code;
    private String  msg;

    private ExceptionEnum(Integer code, String msg){
        this.code = code;
        this.msg  = msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
