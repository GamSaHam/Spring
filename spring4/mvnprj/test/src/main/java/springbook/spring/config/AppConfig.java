package springbook.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springbook.spring.dao.User;

@Configuration
@Profile("dev")
public class AppConfig {

    @Bean
    public User user(){
        return new User("BaeJungNam");
    }



}
