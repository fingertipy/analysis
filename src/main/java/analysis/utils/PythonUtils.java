package analysis.utils;


import analysis.entity.ModelResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import analysis.constatns.GlobalConstants;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class PythonUtils {

    private PythonUtils(){}

    //windows python executor
    private static String windowsExecutor;

    @Value("${python.executor.windows}")
    public void setWindowsExecutor(String windowsExecutor){
        this.windowsExecutor = windowsExecutor;
    }

    //linux python executor
    private static String linuxExecutor;

    @Value("${python.executor.linux}")
    public void setLinuxExecutor(String linuxExecutor){
        this.linuxExecutor = linuxExecutor;
    }

    //logger
    private static Logger logger = LoggerFactory.getLogger(PythonUtils.class);

    /**
     * @Description 匹配系统执行器
     * @Author      dayu
     * @Date        2019/12/12 16:11
     * @Param
     * @Return      java.lang.String
     */
    private static String matchExecutor(){
        String system   = System.getProperty("os.name");
        String executor = linuxExecutor;
        //windows系统
        if (system.contains(GlobalConstants.SYSTEM_WINDOWS))
            executor = windowsExecutor;
        return executor;
    }

    /**
     * @Description execute python program
     * @Author      dayu
     * @Date        2019/12/3 10:56
     * @Param
     * @Return      void
     */
    public static void exec(String path){
        try {
            logger.info("======================== Execute python program : start===========================");
            LogUtils.info("macth executor path: {}, model path: {}", matchExecutor(), path);
            //执行脚本
            Process process = Runtime.getRuntime().exec(new String[]{matchExecutor(), path});
            //接口执行结果
            BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            //处理执行结果
            while ((line = buffer.readLine()) != null){
                System.out.println(line);
            }
            //关闭资源
            buffer.close();
            process.waitFor();
            logger.info("======================== Execute python program :   end===========================");
        } catch (Exception e) {
            logger.error("Execute python program exception: ", e);
        }
    }

    /**
     * @Description execute python program
     * @Author      dayu
     * @Date        2019/12/3 10:56
     * @Param
     * @Return      void
     */
    public static ModelResult exec(String path, String args){
        try {
            logger.info("======================== Execute python program : start===========================");
            LogUtils.info("macth executor path: {}, model path: {}", matchExecutor(), path);
            //执行脚本
            Process process = Runtime.getRuntime().exec(new String[]{matchExecutor(), path, args});
            //接口执行结果
            BufferedReader buffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //响应结果缓冲s
            StringBuffer resultBuffer = new StringBuffer();
            //缓冲
            StringBuffer result = new StringBuffer();
            //处理执行结果
            String line = null;
            while ((line = buffer.readLine()) != null){
                resultBuffer.append(line);
            }
            //关闭资源
            buffer.close();
            process.waitFor();
            logger.info("======================== Execute python program :   end===========================");
            //返回默认数据
            if (resultBuffer.length() == 0) return defaultModelResult();
            //返回响应数据
            return JSONObject.parseObject(resultBuffer.toString(), ModelResult.class);
        } catch (Exception e) {
            logger.error("Execute python program exception: ", e);
        }
        return defaultModelResult();
    }

    /**
     * 返回默认数据
     * @return
     */
    private static ModelResult defaultModelResult(){
        return new ModelResult();
    }
}
