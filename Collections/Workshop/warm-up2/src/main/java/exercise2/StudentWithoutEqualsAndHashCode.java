package exercise2;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 */
public class StudentWithoutEqualsAndHashCode {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Double averageGrade;
    private final Student student;

    public StudentWithoutEqualsAndHashCode(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.averageGrade = student.getAverageGrade();
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public Student getStudent() {
        return student;
    }

}
