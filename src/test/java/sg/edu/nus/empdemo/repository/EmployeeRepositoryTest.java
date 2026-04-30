package sg.edu.nus.empdemo.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

@DataJpaTest
@DisplayName("Employee Repository Test")
public class EmployeeRepositoryTest {
    
    private TestEntityManager testEntityManager;
    private EmployeeRepository employeeRepository;

    public EmployeeRepositoryTest(TestEntityManager testEntityManager, EmployeeRepository employeeRepository){
        this.testEntityManager = testEntityManager;
        this.employeeRepository = employeeRepository;
    }

}
