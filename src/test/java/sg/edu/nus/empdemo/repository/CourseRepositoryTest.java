package sg.edu.nus.empdemo.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import sg.edu.nus.empdemo.entity.Course;
import sg.edu.nus.empdemo.entity.Employee;


@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DisplayName("Course Repository Test")
public class CourseRepositoryTest {

    private final TestEntityManager testEntityManager;
    private final CourseRepository courseRepository;
    private final EmployeeRepository employeeRepository;
    
    public CourseRepositoryTest(TestEntityManager testEntityManager, CourseRepository courseRepository, EmployeeRepository employeeRepository){
        this.testEntityManager = testEntityManager;
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
    }


    @Test
    void findByNameContainingIgnoreCase(){

        Course courseEntity = new Course("Science", 2.5, LocalDate.of(2026, 10, 24));
        this.testEntityManager.persistAndFlush(courseEntity);
        this.testEntityManager.clear();

        List<Course> result = courseRepository.findByNameContainingIgnoreCase("scie");

        assertThat(result).isNotEmpty();
    }

    @Test
    @DisplayName("Find Courses after a specific date")
    void findByStartsAfter(){
        Course scienceCourse = new Course("Science", 2.5, LocalDate.of(2026, 10, 24));
        Course mathCourse = new Course("Mathemtics", 2.5, LocalDate.of(2026,9, 24));

        this.testEntityManager.persistAndFlush(scienceCourse);
        this.testEntityManager.persistAndFlush(mathCourse);
        this.testEntityManager.clear();

        List<Course> result = courseRepository.findByStartsAfter(LocalDate.of(2026, 10, 01));

        assertThat(result).filteredOnAssertions(course -> assertThat(course.getStarts()).isAfter(LocalDate.of(2026, 10,01)));
        assertThat(result).hasSize(1);
    }
    //findByDurationInMonthsGreaterThanEqual

    @Test
    @DisplayName("Find Courses By DurationInMonths Greater Than or Equal")
    void findByDurationInMonthsGreaterThanEqual(){
        Course scienceCourse = new Course("Science", 9.0, LocalDate.of(2026, 10, 24));
        Course mathCourse = new Course("Mathemtics", 8.0, LocalDate.of(2026,9, 24));
        Course simpleMathCourse = new Course("Mathemtics", 1.0, LocalDate.of(2026,11, 24));

        this.testEntityManager.persistAndFlush(scienceCourse);
        this.testEntityManager.persistAndFlush(mathCourse);
        this.testEntityManager.persistAndFlush(simpleMathCourse);
        this.testEntityManager.clear();

        List<Course> result = courseRepository.findByDurationInMonthsGreaterThanEqual(8.0);

        assertThat(result).filteredOnAssertions(course -> assertThat(course.getDurationInMonths()).isGreaterThanOrEqualTo(8.0));
        assertThat(result).hasSize(2);

    }

    @Test
    @DisplayName("Find Courses by EmployeeId")
    void findAllByEmployeeID(){

        Employee employeeA = new Employee("TEST");
        

        Course scienceCourse = new Course("Science", 9.0, LocalDate.of(2026, 10, 24));
        Course mathCourse = new Course("Mathemtics", 8.0, LocalDate.of(2026,9, 24));
        Course simpleMathCourse = new Course("Mathemtics", 1.0, LocalDate.of(2026,11, 24));

        // Set the employee to enroll to 2 courses first before persisting
        employeeA.enrollInCourse(simpleMathCourse);
        employeeA.enrollInCourse(mathCourse);

        this.testEntityManager.persistAndFlush(employeeA);

        //After persisting employee then set each course employee have enrolled to 
        mathCourse.setEmployee(employeeA);
        simpleMathCourse.setEmployee(employeeA);

        // Persist the changes
        this.testEntityManager.persistAndFlush(scienceCourse);
        this.testEntityManager.persistAndFlush(mathCourse);
        this.testEntityManager.persistAndFlush(simpleMathCourse);
   

        this.testEntityManager.clear();

        List<Course> result = courseRepository.findAllByEmployeeId(employeeA.getId());

        //expect only 2 courses under this employeeid because only 2 courses are registered to this employee.
        assertThat(result).hasSize(2);
    }


}