package application;

public interface UpdateEmployeeService {

    public void updateEmployee(UpdateRequest updateReq) throws EmployeeNotFoundException;
}
