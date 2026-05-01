package sg.edu.nus.empdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.TestConstructor;

import sg.edu.nus.empdemo.entity.Employee;
import sg.edu.nus.empdemo.entity.Project;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@DisplayName("Project Repository Test")
public class ProjectRepositoryTest {
    
    private final TestEntityManager testEntityManager;
    private final ProjectRepository projectRepository;

    public ProjectRepositoryTest(TestEntityManager testEntityManager, ProjectRepository projectRepository){
        this.testEntityManager = testEntityManager;
        this.projectRepository = projectRepository;
    }

    @Test
    @DisplayName("")
    void findProjectByExactName(){

        Project project = new Project("test", "some project description", LocalDate.of(2026, 05, 01), LocalDate.of(2026, 06,01));

        this.testEntityManager.persistAndFlush(project);
        this.testEntityManager.clear();

        Optional<Project> result = this.projectRepository.findByNameEquals(project.getName());
        
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isEqualTo(project.getName());
    }

    @Test
    @DisplayName("Find Project by partial Project Name while ignoring case")
    void findProjectByNameContainingIgnoreCase(){

        Project project = new Project("test", "some project description", LocalDate.of(2026, 05, 01), LocalDate.of(2026, 06,01));

        this.testEntityManager.persistAndFlush(project);
        this.testEntityManager.clear();

        Optional<Project> result = this.projectRepository.findByNameContainingIgnoreCase("te");
        
        assertThat(result).isNotEmpty();
        assertThat(result.get().getName()).isEqualTo(project.getName());
    }
    
    @Test
    @DisplayName("Find projects ending after a specific date")
    void findProjectsEndingAfterASpecificDate(){

        Project projectA = new Project("Project A", "some project description", LocalDate.of(2026, 05, 01), LocalDate.of(2026, 6, 01));
        Project projectB = new Project("Project B", "some project description", LocalDate.of(2026, 06, 01), LocalDate.of(2026, 8, 01));
        Project projectC = new Project("Project C", "some project description", LocalDate.of(2026, 07, 01), LocalDate.of(2026, 9, 01));
       


        this.testEntityManager.persistAndFlush(projectA);
        this.testEntityManager.persistAndFlush(projectB);
        this.testEntityManager.persistAndFlush(projectC);
        this.testEntityManager.clear();

        List<Project> result = this.projectRepository.findByEndDateAfter(LocalDate.of(2026, 7, 1));
        
        assertThat(result).hasSize(2);
        assertThat(result).filteredOnAssertions(project -> assertThat(project.getEndDate()).isAfter(LocalDate.of(2026, 7, 1)));

    }


    @Test
    @DisplayName("Find Projects within a specific start and end date range")
    void findByDateRange(){
        Project projectA = new Project("Project A", "some project description", LocalDate.of(2026, 05, 01), LocalDate.of(2026, 6, 01));
        Project projectB = new Project("Project B", "some project description", LocalDate.of(2026, 05, 10), LocalDate.of(2026, 6, 20));
        Project projectC = new Project("Project C", "some project description", LocalDate.of(2026, 06, 01), LocalDate.of(2026, 8, 01));
        Project projectD = new Project("Project D", "some project description", LocalDate.of(2026, 07, 01), LocalDate.of(2026, 9, 01));


        this.testEntityManager.persistAndFlush(projectA);
        this.testEntityManager.persistAndFlush(projectB);
        this.testEntityManager.persistAndFlush(projectC);
        this.testEntityManager.persistAndFlush(projectD);

        this.testEntityManager.clear();

        LocalDate startDate = LocalDate.of(2026, 6, 1);
        LocalDate endDate = LocalDate.of(2026, 9, 01);

        List<Project> result = this.projectRepository.findByDateRange(startDate, endDate);
        
        assertThat(result).filteredOnAssertions(project -> assertThat(project.getStartDate()).isAfterOrEqualTo(startDate)).extracting(Project::getName).contains(projectC.getName(), projectD.getName());
        assertThat(result).filteredOnAssertions(project -> assertThat(project.getEndDate()).isBeforeOrEqualTo(endDate)).extracting(Project::getName).contains(projectC.getName(), projectD.getName());
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("Fetch Project and its assigned employees by Project ID")
    void findByIdWithEmployees(){

        Employee employeeA = new Employee("Test");
        Employee employeeB = new Employee("TEST B");

        Project projectC = new Project("Project C", "some project description", LocalDate.of(2026, 06, 01), LocalDate.of(2026, 8, 01));
        Project projectD = new Project("Project D", "some project description", LocalDate.of(2026, 07, 01), LocalDate.of(2026, 9, 01));

        employeeA.joinProject(projectC);
        employeeB.joinProject(projectC);

        projectC.assignEmployeeIntoProject(employeeA);
        projectC.assignEmployeeIntoProject(employeeB);


        this.testEntityManager.persistAndFlush(projectC);


        this.testEntityManager.persistAndFlush(employeeA);
        this.testEntityManager.persistAndFlush(employeeB);


        this.testEntityManager.clear();

        List<Project> projectList = this.projectRepository.findByIdWithEmployees(projectC.getId());

        assertThat(projectList).filteredOnAssertions(project -> assertThat(project.getEmployees().size()).isEqualTo(2)).flatExtracting(Project::getEmployees).extracting(Employee::getName).contains(employeeA.getName(), employeeB.getName());
    }

    @Test
    @DisplayName("Find Projects by employeeId")
    void findByEmployeeIdProjects(){

        Employee employeeA = new Employee("Test");
        Employee employeeB = new Employee("TEST B");
        Employee employeeC = new Employee("TEST C");
        Employee employeeD = new Employee("TEST D");


        Project projectC = new Project("Project C", "some project description", LocalDate.of(2026, 06, 01), LocalDate.of(2026, 8, 01));

        employeeA.joinProject(projectC);
        employeeB.joinProject(projectC);
        employeeC.joinProject(projectC);

        projectC.assignEmployeeIntoProject(employeeA);
        projectC.assignEmployeeIntoProject(employeeB);
        projectC.assignEmployeeIntoProject(employeeC);

        this.testEntityManager.persistAndFlush(projectC);


        this.testEntityManager.persistAndFlush(employeeA);
        this.testEntityManager.persistAndFlush(employeeB);



        Project projectD = new Project("Project D", "some project description", LocalDate.of(2026, 07, 01), LocalDate.of(2026, 9, 01));


        employeeC.joinProject(projectD);
        employeeD.joinProject(projectD);

        projectD.assignEmployeeIntoProject(employeeC);
        projectD.assignEmployeeIntoProject(employeeD);


        this.testEntityManager.persistAndFlush(projectD);

        this.testEntityManager.persistAndFlush(employeeC);
        this.testEntityManager.persistAndFlush(employeeD);

        this.testEntityManager.clear();

        List<Project> projectList = this.projectRepository.findByEmployeeIdWithProjects(employeeC.getId());


        assertThat(projectList).hasSize(2);
        //assertThat(projectList).filteredOnAssertions(project -> assertThat(project.getEmployees().size()).isEqualTo(2)).flatExtracting(Project::getEmployees).extracting(Employee::getName).contains(employeeA.getName(), employeeB.getName());
    }
    
}
