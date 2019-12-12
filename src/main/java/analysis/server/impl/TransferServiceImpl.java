package analysis.server.impl;

import analysis.constatns.GlobalConstants;
import analysis.server.TransferService;
import analysis.utils.FileUtils;
import analysis.utils.LogUtils;
import analysis.utils.PythonUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 传输接口实现
 * @Author      dayu
 * @Date        2019/12/12 12:19
 * @Version     v1.0
 */
@Service
public class TransferServiceImpl implements TransferService {

    @Value("${python.analysis.model.windows}")
    private String windowsPath;

    @Value("${python.analysis.model.linux}")
    private String linuxPath;

    /**
     * @Description 匹配模型路径
     * @Author      dayu
     * @Date        2019/12/12 16:11
     * @Param
     * @Return      java.lang.String
     */
    private String matchModelPath(){
        String system   = System.getProperty("os.name");
        String path = linuxPath;
        //windows系统
        if (system.contains(GlobalConstants.SYSTEM_WINDOWS))
            path = windowsPath;
        return path;
    }

    @Override
    public String invok(Integer type, String... args) {
        String result = null;
        switch (type){
            case GlobalConstants.ANALYSIS_MODEL_1:
                result = PythonUtils.exec(matchModelPath(), args);
                break;
            case GlobalConstants.ANALYSIS_MODEL_2:
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public <T> T invok(Class<T> clazz, Integer type, String... args){
        String result = null;
        switch (type){
            case GlobalConstants.ANALYSIS_MODEL_1:
                result = PythonUtils.exec(matchModelPath(), args);
                break;
            case GlobalConstants.ANALYSIS_MODEL_2:
                break;
            default:
                break;
        }
        return JSONObject.parseObject(result, clazz);
    }

    @Override
    public String saveFile(MultipartFile file) throws Exception {
        try{
            return FileUtils.saveFile(file);
        } catch (Exception e){
            throw e;
        }
    }
}
