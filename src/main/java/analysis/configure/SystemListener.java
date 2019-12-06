package analysis.configure;

import analysis.utils.CacheUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 系统启动监听器
 */
@Component
public class SystemListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //初始化本地缓存
        CacheUtils.initCacheUtils();
    }
}
