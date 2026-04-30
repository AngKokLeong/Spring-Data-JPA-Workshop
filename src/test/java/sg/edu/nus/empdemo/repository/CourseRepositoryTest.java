package sg.edu.nus.empdemo.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;


@DataJpaTest
@DisplayName("Course Repository Test")
public class CourseRepositoryTest {

    private TestEntityManager testEntityManager;
    private CourseRepository courseRepository;
    
    public CourseRepositoryTest(TestEntityManager testEntityManager, CourseRepository courseRepository){
        this.testEntityManager = testEntityManager;
        this.courseRepository = courseRepository;
    }





}
