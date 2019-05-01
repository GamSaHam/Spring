package springbook.learningTest.spring.ioc.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import springbook.learningTest.spring.ioc.bean.Hello;
import springbook.learningTest.spring.ioc.config.AppConfig;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class AppConfigTest {

    @Test
    public void appConfigTest(){

        GenericApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        assertThat(ac.getBean("hello", Hello.class), is(notNullValue()));

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

    }





}
