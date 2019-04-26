package learningTest.IocContainer;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class HelloTest {

    @Test
    public void helloBeanTest() {
        StaticApplicationContext ac = new StaticApplicationContext(); // 스프링 학습 테스트때만 사용되는 Ioc 컨테이너
        ac.registerSingleton("hello1", Hello.class);


        Hello hello = ac.getBean("hello1", Hello.class);

        assertThat(hello, is(notNullValue()));

    }

    @Test
    public void hellBean2Test(){

        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class);


        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);

        helloDef.getPropertyValues().addPropertyValue("name", "Spring");

        ac.registerBeanDefinition("hello2", helloDef);

        assertThat(ac.getBean("hello1", Hello.class), is(notNullValue()));
        assertThat(ac.getBean("hello2", Hello.class), is(notNullValue()));

        assertThat(ac.getBean("hello2", Hello.class).sayHello(), is("learningTest.IocContainer.Hello Spring"));

        assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));

    }

    @Test
    public void registerBeanWithDependency(){



        StaticApplicationContext ac = new StaticApplicationContext();

        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));

        BeanDefinition helloDef = new RootBeanDefinition(Hello.class);

        helloDef.getPropertyValues().addPropertyValue("name", "Spring");
        helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));

        ac.registerBeanDefinition("hello", helloDef);

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        // printer 라는 빈을 등록을 했는데
        // 어째서 toString 을 하면 learningTest.IocContainer.Hello Spring이 되는것일까?
        // printer addPropertValue 에서 RuntimeBeanReference를 통해서 등록이 되어진다.

        assertThat(ac.getBean("printer").toString(), is("learningTest.IocContainer.Hello Spring"));

    }

    



}
