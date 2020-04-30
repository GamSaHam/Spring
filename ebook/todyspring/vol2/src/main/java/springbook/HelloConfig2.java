package springbook;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springbook.learningTest.spring.ioc.bean.ConsolePrinter;
import springbook.learningTest.spring.ioc.bean.Hello;
import springbook.learningTest.spring.ioc.bean.Printer;

@Configuration
@PropertySource(value = "/database.properties")
public class HelloConfig2 {

    @Value("${db.username}")
    private String username;

    @Bean
    public Hello hello(){
        Hello hello = new Hello();
        hello.setName(this.username);
        return hello;


    }

    @Bean
    public Hello hello2(@Value("${db.username}") String name){
        Hello hello2 = new Hello();
        hello2.setName(name);
        return hello2;

    }


    @Bean
    public Printer printer(){
        Printer printer = new ConsolePrinter();
        return printer;

    }



}
