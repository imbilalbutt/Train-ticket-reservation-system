package pk.imbilalbutt.bussiness.repository;

import org.springframework.stereotype.Repository;
import pk.imbilalbutt.bussiness.model.Course;

@Repository(value= "CourseRepository")
public interface CourseRepository extends BaseRepository<Course> {


}
