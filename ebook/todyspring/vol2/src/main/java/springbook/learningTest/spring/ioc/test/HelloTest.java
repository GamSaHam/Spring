package springbook.learningTest.spring.ioc.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import springbook.AnnotatedHelloConfig;
import springbook.HelloConfig2;
import springbook.SimpleConfig;
import springbook.TypeClassConfig;
import springbook.learningTest.spring.ioc.bean.*;
import springbook.learningTest.spring.ioc.config.AppConfig;
import springbook.learningTest.spring.util.BeanDefinitionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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


        assertThat(typeClass.isFlag() , is(true));
        assertThat(typeClass.getList().get(0), is("Spring"));
        assertThat(typeClass.getMap().get("Kim"), is(30));
        assertThat(typeClass.getSettings().get("username").toString(), is("root"));
        assertThat(typeClass.getStr(), is(nullValue()));

        TypeClass typeClass2 = ctx.getBean("typeClass2", TypeClass.class);

        assertThat(typeClass2.isFlag(), is(false));

        ctx.close();

        ApplicationContext configCtx = new AnnotationConfigApplicationContext(TypeClassConfig.class);

        TypeClass typeClass3 = configCtx.getBean("typeClass", TypeClass.class);

        assertThat(typeClass3.getRate(), is(1.2));
        assertThat(typeClass3.getIntArr()[0], is(1));

    }

    @Test
    public void propertyPlaceHolderTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/test-properties-applicationContext.xml");

        PropertiesClass propertiesClass = ctx.getBean("propertiesClass", PropertiesClass.class);

        assertThat(propertiesClass.getUsername(), is("Spring"));

        // @Value에서도 ${} 치환자를 사용할 수 있다.


    }

    @Test
    public void spElTest(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/test-spel-applicationContext.xml");

        Hello hello = ac.getBean("hello", Hello.class);

        assertThat(hello.getName(), is("Spring"));

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

    @Test
    public void simpleServiceTest(){

        ApplicationContext ac = new GenericXmlApplicationContext("/test-containerInfraBean-applicationContext.xml");


        SimpleConfig sc = ac.getBean(SimpleConfig.class);
        sc.hello.sayHello();

    }

    @Test
    public void profileTest(){
        GenericXmlApplicationContext gac = new GenericXmlApplicationContext();

        gac.getEnvironment().setActiveProfiles("dev");

        gac.load(getClass(), "/test-profile-applicationContext.xml");
        gac.refresh();

        BeanDefinitionUtils.printBeanDefinition(gac);
        assertTrue(gac.getBean("printer", Printer.class) instanceof StringPrinter);

        // JVM 커맨드 파라미터에 프로파일을 활성화 시킬수 있다.

        // Dspring.profiles.active = dev

        //@Profile("dev")로 지정 해서 사용할 수 있다.


    }

    @Test
    public void environmentPropertiesTest() throws IOException, URISyntaxException {

        // 시스템 프로퍼티를 설정한 예시

        Properties p = new Properties();
        p.put("db.username", "root");

        PropertySource<?> ps = new PropertiesPropertySource("customPropertySource", p);

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        ac.getEnvironment().getPropertySources().addFirst(ps);

        String username = (String)ac.getEnvironment().getPropertySources().get("customPropertySource").getProperty("db.username");


        assertThat(username, is("root"));

    }

    @Test
    public void propertySourceExampleTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        assertThat(ac.getBean("getUsername", String.class), is("Spring"));




    }













}
