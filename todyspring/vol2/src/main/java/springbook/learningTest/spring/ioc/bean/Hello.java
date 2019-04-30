package springbook.learningTest.spring.ioc.bean;


import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class Hello {
    String name;

    @Autowired // 현재 등록된 빈에 printer 항목이 있으면 자동 매칭 시켜준다 autowire byName이랑 동작방식이 비슷
    @Qualifier("printer") // 대상 후보가 여러개일 경우 id를 지정해줄 수 있다.
                          // xml에서는 <qualifier value="printer"> bean 태그를 추가해 주어야한다.
    Printer printer;

    public Hello(){

    }


    /*@Autowired / 생성자에도 부여할 수 있다. 하나의 생성자에만 부여할 수 있다.*/
    public Hello(String name, Printer printer){
        this.name = name;
        this.printer = printer;
    }


    public void setName(String name){
        this.name = name;
    }

    @Resource(name = "printer") // <property name="printer" ref = printer" /> 와 같다.
    // application.xml에 <context:annotation-config /> 을 추가해 주어야 한다.
    public void setPrinter(Printer printer){
        this.printer = printer;
    }

    public String sayHello(){
        return "learningTest.IocContainer.Hello "+ name;
    }

    public void print(){

        this.printer.print(sayHello());
    }

    public void initMethodTest(){

        System.out.println("Call the init-method");
    }

    public void destroyMethodTest(){
        System.out.println("Call the destroy-mehtod");
    }



    @PostConstruct
    public void init(){
        System.out.println("Init");


    }




}
