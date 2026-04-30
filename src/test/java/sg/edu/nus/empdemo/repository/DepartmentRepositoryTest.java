package sg.edu.nus.empdemo.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

@DataJpaTest
@DisplayName("Department Repository Test")
public class DepartmentRepositoryTest {
    
    private TestEntityManager testEntityManager;
    private DepartmentRepository departmentRepository;

    public DepartmentRepositoryTest(TestEntityManager testEntityManager, DepartmentRepository departmentRepository){
        this.testEntityManager = testEntityManager;
        this.departmentRepository = departmentRepository;
    }


}
