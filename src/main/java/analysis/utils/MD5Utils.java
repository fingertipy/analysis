package analysis.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class MD5Utils {

    private MD5Utils(){}

    private static final String MD5 = "MD5";

    public static String MD5Code(String ... args){
        try {
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(arrayToString(args).getBytes());
            byte[] bytes = digest.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int j = 0; j < bytes.length; j++) {
                i = bytes[j];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (Exception e) {
            LogUtils.error(e, "获取MD5值异常");
        }
        return null;
    }

    /**
     * 数组转换成字符串
     * @param args
     * @return
     */
    private static String arrayToString(String[] args){
        StringBuilder builder = new StringBuilder();
        //参数校验
        if (args == null || args.length == 0) return builder.toString();
        for (String arg : args){
            builder.append(arg);
        }
        return builder.toString();
    }
}
