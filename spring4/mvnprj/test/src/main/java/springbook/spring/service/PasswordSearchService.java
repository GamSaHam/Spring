package springbook.spring.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PasswordSearchService {


    public void initPool(){

        System.out.println("init pool");

    }


    public void destroyPool(){

        System.out.println("destroy Pool");

    }


}
