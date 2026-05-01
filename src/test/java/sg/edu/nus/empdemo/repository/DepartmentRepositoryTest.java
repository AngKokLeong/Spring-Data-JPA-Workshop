package sg.edu.nus.empdemo.repository;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import sg.edu.nus.empdemo.entity.Department;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DisplayName("Department Repository Test")
public class DepartmentRepositoryTest {
    
    private final TestEntityManager testEntityManager;
    private final DepartmentRepository departmentRepository;

    public DepartmentRepositoryTest(TestEntityManager testEntityManager, DepartmentRepository departmentRepository){
        this.testEntityManager = testEntityManager;
        this.departmentRepository = departmentRepository;
    }

    @Test
    @DisplayName("Find Department by exact department name")
    void findByDepartmentName(){

        Department department = new Department("English");

        this.testEntityManager.persistAndFlush(department);
        this.testEntityManager.clear();

        Optional<Department> departmentResult = departmentRepository.findByNameEquals(department.getName());

        assertThat(departmentResult.isPresent()).isEqualTo(true);
    }


}
