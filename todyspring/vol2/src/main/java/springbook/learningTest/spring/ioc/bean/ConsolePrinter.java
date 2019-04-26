package springbook.learningTest.spring.ioc.bean;


public class ConsolePrinter implements Printer {

    public void print(String message) {
        System.out.println(message);
    }


}
