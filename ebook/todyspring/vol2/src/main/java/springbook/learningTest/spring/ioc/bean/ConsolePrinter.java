package springbook.learningTest.spring.ioc.bean;


import org.springframework.stereotype.Component;

@Component(value = "printer")
public class ConsolePrinter implements Printer {

    public void print(String message) {
        System.out.println(message);
    }


}
