package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by user on 7/15/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private  final EmployeeDao employeeDao = new EmployeeDao();

    @Override
    public List<Employee> findAllEmployees() {
        return  employeeDao.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public void deleteOneEmployee(Long id) {
        Employee employeeDelete = new Employee();
        employeeDelete = employeeDao.getEmployeeById(id);
        employeeDao.deleteEmployee(employeeDelete);
    }

    @Override
    public void saveOneEmployee(Employee employee) {
        employeeDao.insertEmployee(employee);
    }

}
