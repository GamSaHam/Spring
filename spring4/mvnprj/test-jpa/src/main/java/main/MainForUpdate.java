package main;

import application.EmployeeNotFoundException;
import application.UpdateEmployeeService;
import application.UpdateRequest;
import domain.Address;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForUpdate {

    public static void main(String[] args) throws EmployeeNotFoundException {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("/springconf.xml");

        UpdateEmployeeService updateEmpSvc = ctx.getBean(UpdateEmployeeService.class);

        updateEmpSvc.updateEmployee(new UpdateRequest(1L, new Address("서울시", "관악구", "173-6")));

        ctx.close();


    }


}
