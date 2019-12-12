package analysis.constatns;

/**
 * @Description 全局常量
 * @Author      dayu
 * @Date        2019/12/6 15:11
 * @Version     v1.0
 */
public class GlobalConstants {
    /****************************************** 返回响应码 ********************************************/
    public static final int FAILURE           = 100;  //响应失败
    public static final int SUCCESS           = 200; //响应成功
    public static final int PARAME_ERROR      = 300;  //参数错误
    public static final int SERVER_ERROR      = 500; //服务器错误
    public static final int REQUEST_NOT_FOUND = 404; //未找到请求

    /****************************************** 返回响应消息 *******************************************/
    public static final String SUCCESS_MSG           = "成功";        //响应成功
    public static final String FAILURE_MSG           = "失败";        //响应失败
    public static final String PARAME_ERROR_MSG      = "参数错误";    //参数错误
    public static final String SERVER_ERROR_MSG      = "服务器错误";  //服务器错误
    public static final String REQUEST_NOT_FOUND_MSG = "请求路径错误";//未找到请求

    /****************************************** 分析模型类型 *******************************************/
    public static final int ANALYSIS_MODEL_1 = 1; //模型：1
    public static final int ANALYSIS_MODEL_2 = 2; //模型：2

    /****************************************** 操作系统类型 *******************************************/
    public static final String SYSTEM_WINDOWS = "Windows"; //windows
}
