package sg.edu.nus.empdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
    List<Course> findByNameContainingIgnoreCase(String Name);

}
