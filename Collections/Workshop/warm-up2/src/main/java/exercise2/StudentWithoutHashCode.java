package exercise2;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 */
public class StudentWithoutHashCode {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final Double averageGrade;
    private final Student student;

    public StudentWithoutHashCode(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.averageGrade = student.getAverageGrade();
        this.student = student;

    }

    public Integer getId() {
        return id;
    }

    public Student getStudent() {
        return student;
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

    @Override
    public boolean equals(Object o) {
        return student.equals(o);
    }

    @Override
    public String toString() {
        return student.toString();
    }
}
