import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//托管的意思
@SpringBootApplication
//扫描包下面带@RestController标签的类
@ComponentScan("com.course")
public class Application {
    public static void main(String[] args) {
        //固定格式
        SpringApplication.run(Application.class,args);
    }
}
