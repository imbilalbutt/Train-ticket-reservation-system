package pk.imbilalbutt.bussiness.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.model.Employee;

import java.util.List;

@Repository(value="EmployeeRepository")
public interface EmployeeRepository extends BaseRepository<Employee> {

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    @Query(" SELECT empl " +
            " FROM Employee empl ")
    List<Employee> getEmployeeByCourseId(Long courseId);

}
