package ro.teamnet.zth.appl.domain;

import java.util.Date;

/**
 * Created by user on 7/7/2016.
 */
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private Date hireData; // => ?
    private Job jobId;
    private Long salary;
    private Long commission;
    private Long managerId;
    private Long departemntId;

}
