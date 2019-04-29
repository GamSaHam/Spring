package springbook.learningTest.spring.ioc.bean;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import springbook.AnnotatedHelloConfig;
import springbook.HelloConfig2;
import springbook.TypeClassConfig;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
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

    @Test
    public void annotationHelloConfigTest(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);

        AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);

        assertThat(hello, is(notNullValue()));

        AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);

        assertThat(config, is(notNullValue()));

        assertThat(config.annotatedHello(), is(hello));

    }

    @Test
    public void constructorArgTest(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/test-applicationContext.xml");

        assertThat(ctx, is(notNullValue()));


        Hello hello = ctx.getBean("hello", Hello.class);

        hello.print();


        Hello hello2 = ctx.getBean("hello2", Hello.class);

        assertThat(hello2, is(notNullValue()));

        Hello hello3 = ctx.getBean("hello3", Hello.class);

        assertThat(hello3, is(notNullValue()));

        Hello hello4 = ctx.getBean("hello4", Hello.class);
        hello4.print();
        assertThat(hello4, is(notNullValue()));

    }

    @Test
    public void componentScanTest(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/test-applicationContext2.xml");

        assertThat(ctx, is(notNullValue()));

        Hello hello = ctx.getBean("hello", Hello.class);

        hello.print();



    }

    @Test
    public void valueAnnotationTest(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloConfig2.class);

        assertThat(ctx, is(notNullValue()));

        Hello hello = ctx.getBean("hello", Hello.class);

        hello.print();

        Hello hello2 = ctx.getBean("hello2", Hello.class);

        hello2.print();

    }

    @Test
    public void typeClassTest(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/test-type-applicationContext.xml");

        TypeClass typeClass = ctx.getBean("typeClass", TypeClass.class);

        assertThat(typeClass.flag , is(true));
        assertThat(typeClass.list.get(0), is("Spring"));
        assertThat(typeClass.map.get("Kim"), is(30));
        assertThat(typeClass.settings.get("username").toString(), is("root"));
        assertThat(typeClass.str, is(nullValue()));

        TypeClass typeClass2 = ctx.getBean("typeClass2", TypeClass.class);

        assertThat(typeClass2.flag, is(false));

        ctx.close();

        ApplicationContext configCtx = new AnnotationConfigApplicationContext(TypeClassConfig.class);

        TypeClass typeClass3 = configCtx.getBean("typeClass", TypeClass.class);

        assertThat(typeClass3.rate, is(1.2));
        assertThat(typeClass3.intArr[0], is(1));

    }

    @Test
    public void propertyPlaceHolderTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/test-properties-applicationContext.xml");

        PropertiesClass propertiesClass = ctx.getBean("propertiesClass", PropertiesClass.class);

        assertThat(propertiesClass.username, is("Spring"));

        // @Value에서도 ${} 치환자를 사용할 수 있다.


    }

    @Test
    public void spElTest(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/test-spel-applicationContext.xml");

        Hello hello = ac.getBean("hello", Hello.class);

        assertThat(hello.name, is("Spring"));

        // spel도 @Vaild에도 적용가능하다.

    }

    @Test
    public void singletonScope(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, SingletonClientBean.class);

        ac.getBean(SingletonBean.class).setName("test singleton");

        Set<SingletonBean> beans = new HashSet<SingletonBean>();

        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingletonBean.class));

        assertThat(beans.size(), is(1)); // 해쉬셋을 활용하여 주소가 동일해서 사이즈가 1개가 되는것을 확인 한다.

        beans.add(ac.getBean(SingletonClientBean.class).bean1);
        beans.add(ac.getBean(SingletonClientBean.class).bean2);

        assertThat(beans.size(), is(1));

        System.out.println(ac.getBean(SingletonClientBean.class).bean1.name);



    }

    static class SingletonBean{
        String name;
        public void setName(String name){
            this.name = name;
        }
    }
    static class SingletonClientBean{
        @Autowired
        SingletonBean bean1;

        @Autowired
        SingletonBean bean2;

    }

    @Test
    public void prototypeScopeTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, PrototypeClientBean.class);

        Set<PrototypeBean> beans = new HashSet<PrototypeBean>();

        beans.add(ac.getBean(PrototypeBean.class));
        beans.add(ac.getBean(PrototypeBean.class));

        assertThat(beans.size(), is(2)); // 해쉬셋으로 주소가 같은지 체크

        beans.add(ac.getBean(PrototypeClientBean.class).bean1);
        beans.add(ac.getBean(PrototypeClientBean.class).bean2);

        assertThat(beans.size(), is(4));

    }

    @Scope("prototype")
    static class PrototypeBean{

    }

    static class PrototypeClientBean{
        @Autowired
        PrototypeBean bean1;

        @Autowired
        PrototypeBean bean2;

    }

    @Test
    public void namingBeanTest(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("/test-applicationContext.xml");

        assertThat(ac.getBean("test1"), is(notNullValue()));
        assertThat(ac.getBean("test2"), is(notNullValue()));

        ac.close();

        // Bean(init-method = "initResource") 로 초기화를 지정 할 수 있다.

        // 제거
        // destroy-method
        // @PreDestroy
        // @Bean(destoryMehtod = "")



    }







}
