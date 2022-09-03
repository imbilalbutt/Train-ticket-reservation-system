package pk.imbilalbutt.bussiness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto extends BaseDto implements Serializable {

    private Long id;
    private String officeAddress;
    private String officeNumber;
    private Date officeStartDate;

    private UserDto user;
    private List<CourseDto> courses;
}
