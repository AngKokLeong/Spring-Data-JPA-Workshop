package sg.edu.nus.empdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.empdemo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
