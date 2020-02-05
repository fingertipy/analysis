package analysis.utils;

import analysis.constatns.GlobalConstants;
import analysis.exception.CustomeException;
import analysis.exception.ExceptionEnum;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description 文件上传下载工具类
 * @Author      dayu
 * @Date        2019/12/12 17:15
 * @Version     v1.0
 */
@Component
public class FileUtils {

    private FileUtils(){}

    private static String windowsFilePath;

    @Value("${file.path.windows}")
    public void setWindowsFilePath(String windowsFilePath) {
        this.windowsFilePath = windowsFilePath;
    }

    private static String linuxFilePath;

    @Value("${file.path.linux}")
    public void setLinuxFilePath(String linuxFilePath) {
        this.linuxFilePath = linuxFilePath;
    }

    /**
     * @Description 匹配文件上传路径
     * @Author      dayu
     * @Date        2019/12/12 16:11
     * @Param
     * @Return      java.lang.String
     */
    private static String matchFilePath(){
        String system   = System.getProperty("os.name");
        String path = linuxFilePath;
        //windows系统
        if (system.contains(GlobalConstants.SYSTEM_WINDOWS))
            path = windowsFilePath;
        return path;
    }

    /**
     * @Description 上传文件
     * @Author      dayu
     * @Date        2019/12/12 17:25
     * @Param       multipartFile
     * @Return      java.lang.String
     */
    public static String saveFile(MultipartFile multipartFile) throws Exception{
        //文件为空
        if (multipartFile == null || multipartFile.isEmpty())
            throw new CustomeException(ExceptionEnum.EMPTY_FILE);
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        //生成文件路径
        String filePath = genarateFilePath(fileName);
        //创建文件
        File file = createFile(filePath);
        //上传文件
        multipartFile.transferTo(file);
        //返回附件路径
        return filePath;
    }

    /**
     * @Description 创建文件
     * @Author      dayu
     * @Date        2019/12/12 17:38
     * @Param       path
     * @Return      java.io.File
     */
    private static File createFile(String path) throws Exception{
        String fullPath = FilenameUtils.getFullPath(path);
        File file = new File(fullPath);
        //创建文件目录
        if (!file.isDirectory())
            if (!file.mkdirs()) throw new CustomeException(ExceptionEnum.CREATE_DIRECTORY_FAILED);
        //判断文件是否存在
        file = new File(path);
        if (file.exists())
            throw new CustomeException(ExceptionEnum.FILE_EXISTS);
        //创建文件
        file.createNewFile();
        return file;
    }

    /**
     * @Description 生成文件路径
     * @Author      dayu
     * @Date        2019/12/12 17:57
     * @Param       fileName
     * @Return      java.lang.String
     */
    private static String genarateFilePath(String fileName){
        return matchFilePath() + fileName;
    }

    /**
     * @Description 删除文件
     * @Author      dayu
     * @Date        2019/12/12 18:04
     * @Param       filePath
     * @Return      java.lang.Boolean
     */
    public static Boolean deleteFile(String filePath){
        File file = new File(filePath);
        return file.delete();
    }
}
