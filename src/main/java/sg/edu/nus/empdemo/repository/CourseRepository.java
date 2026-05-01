package sg.edu.nus.empdemo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    
    List<Course> findByNameContainingIgnoreCase(String Name);
    List<Course> findByStartsAfter(LocalDate date);
    List<Course> findByDurationInMonthsGreaterThanEqual(Double durationInMonths);

    List<Course> findAllByEmployeeId(Long id);

    @Query("select c from Course c inner join Employee e on c.employee.id = e.id where c.id = ?1")
    Optional<Course> findByIdWithEmployee(Long courseId);

    List<Course> findByEmployeeIdAndStartsAfter(Long id, LocalDate date);
}
