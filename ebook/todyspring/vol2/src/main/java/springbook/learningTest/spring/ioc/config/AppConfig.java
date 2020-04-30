package springbook.learningTest.spring.ioc.config;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springbook.learningTest.spring.ioc.scanner.IServiceMarker;

import javax.xml.ws.Service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Configuration
/*@ComponentScan("springbook.learningTest.spring.ioc.scanner")*/
/*@ComponentScan(basePackageClasses = IServiceMarker.class)*/
/*@ComponentScan(basePackages = "springbook.learningTest.spring.ioc",excludeFilters = @ComponentScan.Filter(Configuration.class)) // 제외할 대상 선택*/
/*@ComponentScan(basePackages = "springbook.learningTest.spring.ioc", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AppConfig.class))*/
/*@Import(AppConfig.class) // 외부에 있는 Configuration을 임포트 하고 싶을 때 사용*/
/*@ImportResource("") // xml 빈설정을 가져올대 사용되어 진다. ""문장안에는 확장자가 .xml 인 파일경로를 넣어준다. */
/*@EnableTransactionManagement // tx:annotation-driven 테그와 동일하게 동작한다. @Transactional 을 사용할수 있게 해주는 어노테이션이다*/

@PropertySource("/database.properties")
public class AppConfig {

    @Value("${db.username}")
    String username;

    @Bean
    public String getUsername(){
        return username;
    }


}
