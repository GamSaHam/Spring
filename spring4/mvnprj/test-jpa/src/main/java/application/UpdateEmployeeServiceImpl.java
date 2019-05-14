package application;

import domain.Employee;
import domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class UpdateEmployeeServiceImpl implements UpdateEmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void updateEmployee(UpdateRequest updateReq) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findOne(updateReq.getEmployeeId());

        if(employee == null){

            throw new EmployeeNotFoundException();
        }

        employee.setAddress(updateReq.getNewAddress());


    }


}
