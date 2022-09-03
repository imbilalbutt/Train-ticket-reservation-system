package pk.imbilalbutt.bussiness.service;

import pk.imbilalbutt.bussiness.dto.EmployeeDto;
import pk.imbilalbutt.bussiness.model.Employee;

import java.util.List;

public interface EmployeeService extends BaseService<Employee, EmployeeDto> {

    List<Employee> getEmployeeByCourseId(Long courseId);

}

