package springbook.learningTest.spring.ioc.scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springbook.learningTest.spring.ioc.bean.ConsolePrinter;
import springbook.learningTest.spring.ioc.bean.Hello;
import springbook.learningTest.spring.ioc.bean.Printer;

@Component
public class HelloComponent {


    @Bean
    public Hello hello(){
        return new Hello();
    }

    @Bean
    public Printer printer(){
        return new ConsolePrinter();
    }


}
