package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeesList = new EmployeeServiceImpl();

    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        return  employeesList.findAllEmployees();
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParam(name="id") Long id) {;
        return employeesList.findOneEmployee(id);
    }

    @MyRequestMethod(urlPath = "/delete", methodType = "DELETE")
    public void deleteEmployee(@MyRequestParam(name="id") Long id) {;
        employeesList.deleteOneEmployee(id);
    }

    @MyRequestMethod(urlPath = "/save", methodType = "POST")
    public void saveOneEmployee(@MyRequestParam(name="firstName") String firstName, @MyRequestParam(name="lastName") String lastName) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeesList.saveOneEmployee(employee);
    }
}
