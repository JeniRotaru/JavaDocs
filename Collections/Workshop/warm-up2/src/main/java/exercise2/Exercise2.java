package exercise2;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 2: Overriding equals() and hashCode() methods.
 *              You have a Student class in the package of this exercise that contains several fields (id, firstName,
 *              lastName, averageGrade). Override toString(), equals() and hashCode() methods on this class.
 *              For this exercise you need to follow in order, all the TODO steps.
 */
public class Exercise2 {
    private List<Student> studentList;
    private List<Long> cnps;
    private List<StudentWithoutHashCode> studentListWithoutHashCode;
    private List<StudentWithoutEqualsAndHashCode> studentListWithoutEqualsAndHashCode;

    public Exercise2(List<Student> studentList, List<Long> cnps){
        this.studentList = studentList;
        this.studentListWithoutHashCode = new ArrayList<StudentWithoutHashCode>();
        this.studentListWithoutEqualsAndHashCode = new ArrayList<StudentWithoutEqualsAndHashCode>();
        for(int i = 0; i < studentList.size(); i++){
            this.studentListWithoutHashCode.add(new StudentWithoutHashCode(studentList.get(i)));
            this.studentListWithoutEqualsAndHashCode.add(new StudentWithoutEqualsAndHashCode(studentList.get(i)));
        }
        this.cnps = cnps;
    }

    public void addStudents(){

        System.out.println("The list of students is: " + studentList);

        // TODO Exercise 2 a) In order to print the elements of Student class in a human readable way you should override
        // TODO the toString() method from Student class -> TODO Exercise 2 a)

        // A Set with elements of type Student without the methods equals() and hashCode() implemented
        Set<StudentWithoutEqualsAndHashCode> studentSetWithoutEqualsAndHashCode =
                new HashSet<StudentWithoutEqualsAndHashCode>();
        studentSetWithoutEqualsAndHashCode.addAll(studentListWithoutEqualsAndHashCode);

        // A Map with elements of type Student without the methods equals() and hashCode() implemented
        Map<StudentWithoutEqualsAndHashCode, Long> studentMapWithoutEqualsAndHashCode =
                new HashMap<StudentWithoutEqualsAndHashCode, Long>();

        // Adding the elements of type Student without the methods equals() and hashCode() implemented in the Map
        for(StudentWithoutEqualsAndHashCode student : studentSetWithoutEqualsAndHashCode){
            studentMapWithoutEqualsAndHashCode.put(student, cnps.get(student.getId()));
        }

        System.out.println("The set of students is: " + studentSetWithoutEqualsAndHashCode);
        System.out.println("The map of students is: " + studentMapWithoutEqualsAndHashCode);

        // TODO Exercise 2 b) After you finished the Exercise 2 a) run the test file and look at the content ofSS
        // TODO the collections studentSetWithoutEqualsAndHashCode and studentMapWithoutEqualsAndHashCode. What do you notice?
        // TODO Go to Exercise 2 c) from Student class and implement equals() method

        // A Set with elements of type Student without the method hashCode() implemented
        Set<StudentWithoutHashCode> studentSetWithoutHashCode = new HashSet<StudentWithoutHashCode>();
        studentSetWithoutHashCode.addAll(studentListWithoutHashCode);

        // A Map with elements of type Student without the method hashCode() implemented
        Map<StudentWithoutHashCode, Long> studentMapWithoutHashCode = new HashMap<StudentWithoutHashCode, Long>();

        // Adding the elements of type Student without the method hashCode() implemented in the Map
        for(StudentWithoutHashCode student : studentSetWithoutHashCode){
            studentMapWithoutHashCode.put(student, cnps.get(student.getId()));
        }

        // TODO Exercise 2 e) After you finished the Exercise 2 c) run the test file and look at the content of
        // TODO the collections studentSetWithoutHashCode and studentMapWithoutHashCode. What do you notice?

        System.out.println("The set of students with equals implemented is: " + studentSetWithoutHashCode);
        System.out.println("The map of students with equals implemented is: " + studentMapWithoutHashCode);

        // TODO Exercise 2 f) Go to TODO Exercise 2 g) from Student class and implement hashCode() method

        // A Set with elements of type Student
        Set<Student> studentSet = new HashSet<Student>();
        studentSet.addAll(studentList);

        // A Map with elements of type Student
        Map<Student, Long> studentMap = new HashMap<Student, Long>();

        // Adding the elements of type Student in the Map
        for(Student student : studentSet){
            studentMap.put(student, cnps.get(student.getId()));
        }

        // TODO Exercise 2 i) After you finished the Exercise 2 g) run the test file and look at the content of
        // TODO the collections studentSet and studentMap. What do you notice this time?

        System.out.println("The set of students with equals and hashCode implemented is: " + studentSet);
        System.out.println("The map of students with equals and hashCode implemented is: " + studentMap);

    }
}
