package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="COURSE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COURSE_ID")
    private Long id;

    @Column(name="COURSE_NAME")
    private String courseName;

    @Column(name="COURSE_CODE")
    private String courseCode;

    @Column(name="CREDIT_HOURS")
    private Long creditHours;

    // BiDirectional ManyToMany
    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Employee> employees;

}
