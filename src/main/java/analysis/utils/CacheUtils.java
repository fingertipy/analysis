package analysis.utils;

import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cache utils
 */
public class CacheUtils {

    private static boolean isRun = false;

    private static CacheUtils localcache;

    private static Timer timer;

    //lock
    private static byte[] lock = new byte[0];

    //default out time
    private static Long DEFAULT_OUT_TIME = 30 * 60 * 1000L;

    //default clear time
    private static Long DEFAULT_CLEAR_TIME = 60 * 1000L;

    //remove list
    private static List<String> removeList = new ArrayList<>();

    //cache
    private static ConcurrentHashMap<String, CacheValue> cache = new ConcurrentHashMap();

    private CacheUtils(){}

    //init CacheUtils
    public static void initCacheUtils(){
        synchronized (lock){
            localcache = new CacheUtils();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    clearCache();
                }
            }, 0, DEFAULT_CLEAR_TIME);
        }
    }

    //default put
    public static void put (String key, Object value){
        put(key, value, DEFAULT_OUT_TIME);
    }

    //put
    public static void put(String key, Object value, Long timeout){
        if (key == null) return;
        CacheValue cacheValue = new CacheValue();
        cacheValue.setValue(value);
        cacheValue.setTimeout(timeout);
        cache.put(key, cacheValue);
    }

    //get
    public static Object get(String key){
        if (key == null) return null;
        if (!cache.containsKey(key)) return null;
        CacheValue cacheValue = cache.get(key);
        return cacheValue.getValue();
    }

    //contains
    public static boolean contains(String key){
        return get(key) != null;
    }

    //remove
    public static void remove(String key){
        cache.remove(key);
    }

    //clear cache
    private static void clearCache(){
        if (isRun) return;
        synchronized (lock){
            try {
                //update isRun status
                isRun = true;
                //clear remove lsit
                if (removeList.isEmpty() == false) removeList.clear();
                //start time
                long startTime = System.currentTimeMillis();
                //cache empty, do nothing
                if (cache.isEmpty() == true) return;
                Iterator<Map.Entry<String, CacheValue>> iterator = cache.entrySet().iterator();
                //clear mark
                while (iterator.hasNext()){
                    Map.Entry<String, CacheValue> entry = iterator.next();
                    //entry value is null
                    if (entry.getValue() == null) removeList.add(entry.getKey());
                    //entry time out
                    if (entry.getValue().isTimeout()) removeList.add(entry.getKey());
                }
                //clear cache
                if (removeList.isEmpty()) return;
                for (String key : removeList){
                    cache.remove(key);
                }
                //clear log
                LogUtils.info("#缓存清理#清理:{},剩余:{},耗时:{}ms", removeList.size(), cache.size(), System.currentTimeMillis() - startTime);
                //reset remove lsit
                removeList.clear();
            } catch (Exception e) {
                LogUtils.error(e, "缓存清理异常");
            } finally {
                isRun = false;
            }
        }
    }

    /**
     * 缓存存储类
     */
    @Data
    static  class CacheValue{
        private String key;
        private Object value;
        private Long timeout;
        private Long createTime;

        public CacheValue(){
            createTime = System.currentTimeMillis() - 100;
        }

        public boolean isTimeout(){
            //value is null
            if (value == null) return true;
            //time out
            if (System.currentTimeMillis() - createTime >= timeout) return true;
            return false;
        }
    }
}
