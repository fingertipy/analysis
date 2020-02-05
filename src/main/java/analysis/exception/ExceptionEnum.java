package analysis.exception;

/**
 * 异常枚举类
 */
public enum ExceptionEnum {
    ERROR(-1, "参数异常"),
    PARAME_ERROR(-2, "参数异常"),
    USER_NOT_FOUND(-3, "用户不存在"),
    PASSWORD_ERROR(-4, "账号或密码错误"),
    USER_EXSIST(-5, "用户已存在"),
    EMPTY_FILE(-6, "文件为空"),
    CREATE_DIRECTORY_FAILED(-7, "文件目录创建失败"),
    FILE_EXISTS(-8, "文件已存在")
    ;

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
