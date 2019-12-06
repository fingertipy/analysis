package analysis.exception;

/**
 * 异常枚举类
 */
public enum ExceptionEnum {
    PARAME_ERROR(-1, "参数异常"),
    USER_NOT_FOUND(-2, "用户不存在"),
    PASSWORD_ERROR(-3, "账号或密码错误");

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
