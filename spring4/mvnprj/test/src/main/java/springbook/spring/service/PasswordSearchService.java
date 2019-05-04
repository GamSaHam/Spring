package springbook.spring.service;

import org.springframework.stereotype.Component;

@Component
public class PasswordSearchService {


    public void initPool(){

        System.out.println("init pool");

    }


    public void destroyPool(){

        System.out.println("destroy Pool");

    }


}
