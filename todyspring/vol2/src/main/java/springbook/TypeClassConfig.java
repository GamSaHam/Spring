package springbook;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbook.learningTest.spring.ioc.bean.TypeClass;

@Configuration
public class TypeClassConfig {


    @Value("1.2") double rate;
    @Value("1,2,3") int[] intArr;

    @Bean
    public TypeClass typeClass(){
        TypeClass typeClass = new TypeClass();
        typeClass.setRate(rate);
        typeClass.setIntArr(intArr);

        return typeClass;

    }


}
