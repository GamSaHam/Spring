package springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.learningTest.spring.ioc.bean.AnnotatedHello;

@Configuration
public class AnnotatedHelloConfig {

    @Bean
    public AnnotatedHello annotatedHello(){
        return new AnnotatedHello();
    }

}
