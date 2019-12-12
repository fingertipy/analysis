package analysis.server;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 传输接口
 * @Author      dayu
 * @Date        2019/12/12 12:15
 * @Version     v1.0
 */
public interface TransferService {
    /**
     * @Description 执行
     * @Author      dayu
     * @Date        2019/12/12 12:18
     * @Param       type
     * @Param       args
     * @Return      java.lang.String
     */
    String invok(Integer type, String... args);

    /**
     * @Description 执行并返回数据对象
     * @Author      dayu
     * @Date        2019/12/12 15:47
     * @Param       clazz
     * @Param       type
     * @Param       args
     * @Return      T
     */
    <T> T invok(Class<T> clazz, Integer type, String... args);

    /**
     * @Description 上传文件
     * @Author      dayu
     * @Date        2019/12/12 18:10
     * @Param       file
     * @Return      java.lang.String
     */
    String saveFile(MultipartFile file) throws Exception;
}
