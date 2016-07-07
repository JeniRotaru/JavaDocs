package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by user on 7/7/2016.
 */
@Table(name = "departments")
public class Department {

    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "location_id")
    private Long location;
    @Id(name = "department_id")
    private Long id;

    public String getDepartmentName() {
        return departmentName;
    }

    public Long getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setLocation(Long location) {
        this.location = location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", location=" + location +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (!departmentName.equals(that.departmentName)) return false;
        if (!location.equals(that.location)) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = departmentName.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
