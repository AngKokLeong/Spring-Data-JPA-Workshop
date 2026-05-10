package sg.edu.nus.empdemo.repository;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import sg.edu.nus.empdemo.entity.Department;
import sg.edu.nus.empdemo.entity.Employee;

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

    @Test
    @DisplayName("Find Department by partial department name ignoring case")
    void findByNameContainingIgnoreCase(){
        Department department = new Department("English");

        this.testEntityManager.persistAndFlush(department);
        this.testEntityManager.clear();

        Optional<Department> departmentResult = departmentRepository.findByNameContainingIgnoreCase("ngl");

        assertThat(departmentResult.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Fetch a department and its assigned employee by Department ID")
    void findByIdWithEmployee(){
        
        Employee employeeA = new Employee("EmployeeA");
        Department englishDepartment = new Department("English_Department");


        this.testEntityManager.persistAndFlush(employeeA);
        this.testEntityManager.persistAndFlush(englishDepartment);

        employeeA.assignDepartment(englishDepartment);
        englishDepartment.setEmployee(employeeA);


        this.testEntityManager.persistAndFlush(employeeA);
        this.testEntityManager.persistAndFlush(englishDepartment);



        this.testEntityManager.clear();

        Optional<Department> departmentResult = departmentRepository.findByIdWithEmployee(englishDepartment.getId());

        assertThat(departmentResult.isPresent()).isEqualTo(true);
    }



}
