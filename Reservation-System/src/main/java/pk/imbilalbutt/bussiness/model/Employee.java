package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private Long id;

    @Column(name="OFFICE_ADDRESS")
    private String officeAddress;

    @Column(name="OFFICE_NUMBER")
    private String officeNumber;

    @Column(name="OFFICE_START_DATE")
    private Date officeStartDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            // Table Name = EMPLOYEE_TAUGHT_COURSE
            name = "EMPLOYEE_TAUGHT_COURSE",
            // First Column in EMPLOYEE_TAUGHT_COURSE Table
            joinColumns = @JoinColumn(name = "FK_EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID"),
            // Second Column in EMPLOYEE_TAUGHT_COURSE Table
            inverseJoinColumns = @JoinColumn(name= "FK_COURSE_ID", referencedColumnName = "COURSE_ID")
    )
    private List<Course> courses;
}
