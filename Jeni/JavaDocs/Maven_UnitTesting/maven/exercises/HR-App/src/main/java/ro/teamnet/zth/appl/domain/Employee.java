package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;

import java.util.Date;

/**
 * Created by user on 7/7/2016.
 */
public class Employee {

    @Id(name = "employee_id")
    private Long employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private Long phoneNumber;
    @Column(name = "hire_date")
    private Date date;
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "salary")
    private Long salary;
    @Column(name = "commission_pct")
    private Long commission;
    @Column(name = "manager_id")
    private Long managerId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", date=" + date +
                ", jobId='" + jobId + '\'' +
                ", salary=" + salary +
                ", commission=" + commission +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!employeeId.equals(employee.employeeId)) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;
        if (!email.equals(employee.email)) return false;
        if (!phoneNumber.equals(employee.phoneNumber)) return false;
        if (!date.equals(employee.date)) return false;
        if (!jobId.equals(employee.jobId)) return false;
        if (!salary.equals(employee.salary)) return false;
        if (!commission.equals(employee.commission)) return false;
        if (!managerId.equals(employee.managerId)) return false;
        return departmentId.equals(employee.departmentId);

    }

    @Override
    public int hashCode() {
        int result = employeeId.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + jobId.hashCode();
        result = 31 * result + salary.hashCode();
        result = 31 * result + commission.hashCode();
        result = 31 * result + managerId.hashCode();
        result = 31 * result + departmentId.hashCode();
        return result;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public Long getCommission() {
        return commission;
    }

    public Long getSalary() {
        return salary;
    }

    public String getJobId() {
        return jobId;
    }

    public Date getDate() {
        return date;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    @Column(name = "department_id")
    private Long departmentId;

}
