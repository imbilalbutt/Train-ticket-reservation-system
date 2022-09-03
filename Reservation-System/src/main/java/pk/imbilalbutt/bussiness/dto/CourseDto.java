package pk.imbilalbutt.bussiness.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseDto extends BaseDto implements Serializable {

    private Long id;
    private String courseName;
    private String courseCode;
    private Long creditHours;

//    private List<EmployeeDto> employees;
}
