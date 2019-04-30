package springbook.learningTest.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinitionUtils {

    public static void printBeanDefinition(GenericApplicationContext gac){


        List<List<String>> roleBeanInfos = new ArrayList<List<String>>();


        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());

        for(String name : gac.getBeanDefinitionNames()){

            int role = gac.getBeanDefinition(name).getRole();
            List<String> beanInfos = roleBeanInfos.get(role);
            beanInfos.add(role + "\t" + name + "\t" + gac.getBean(name).getClass().getName());

        }

        for(List<String> beanInfos : roleBeanInfos){
            for(String beanInfo : beanInfos){
                System.out.println(beanInfo);
            }

        }


    }

    @Test
    public void printBeanInfo(){

        printBeanDefinition(new GenericXmlApplicationContext("/test-applicationContext.xml"));

    }




}
