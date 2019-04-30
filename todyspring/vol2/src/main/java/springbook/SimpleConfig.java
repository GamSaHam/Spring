package springbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.learningTest.spring.ioc.bean.ConsolePrinter;
import springbook.learningTest.spring.ioc.bean.Hello;
import springbook.learningTest.spring.ioc.bean.Printer;

@Configuration
public class SimpleConfig {
    @Autowired
    public Hello hello;

    @Bean
    Hello hello(){
        return new Hello();
    }

    @Bean
    Printer printer(){
        return new ConsolePrinter();
    }


}
