package ro.teamnet.zth.appl.controller;

//import org.json.*;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeService;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;

import java.util.List;

/**
 * Created by user on 7/14/2016.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {

    private final EmployeeService employeesList = new EmployeeServiceImpl(); //=>?!
//    private final EmployeeService employeesList;


//    public EmployeeController(EmployeeService employeesList) {
//        this.employeesList = employeesList;
//    }

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

    // http://docs.oracle.com/javaee/7/api/javax/json/JsonObject.html
    /*@MyRequestMethod(urlPath = "/save", methodType = "POST")
    public Employee saveOneEmployee(@MyRequestParam(name="employee") JSONObject param) {
        Employee employeeNew = new Employee();
        String firstName = param.getString("firstName");
        String lastName = param.getString("lastName");
        String email = param.getString("email");
        String phoneNumber = param.getString("phoneNumber");
        String jobId = param.getString("jobId");
        BigDecimal salary;
        BigDecimal commissionPct;
        Long managerId;
        Long departmentId;
        Date hireDate;
        employeeNew.setFirstName(firstName);
        return employeesList.saveOneEmployee(employeeNew);
    }*/
}
