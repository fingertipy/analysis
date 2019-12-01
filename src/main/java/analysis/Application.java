package analysis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("analysis.mapper")
public class Application {

    /**
     * 程序入口
     * @param args
     */
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
