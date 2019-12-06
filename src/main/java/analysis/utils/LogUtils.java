package analysis.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description log util
 * @Author      dayu
 * @Date        2019/12/6 18:06
 * @Version     v1.0
 */
public class LogUtils {

    //获取日志对象
    private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);

    //私有化构造器
    private LogUtils(){}

    /**
     * @Description 打印日志
     * @Author      dayu
     * @Date        2019/12/6 18:15
     * @Param       ex
     * @Param       msg
     * @Param       args
     * @Return      void
     */
    public static void error(Exception ex, String msg, Object ... args){
        StringWriter writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        if (args.length > 0){
            logger.error(msg + " 原因: \n{}\n", ArrayUtils.add(args, writer.toString()));
        } else {
            logger.error(msg + " 原因: \n{}\n", writer.toString());
        }
    }
}
