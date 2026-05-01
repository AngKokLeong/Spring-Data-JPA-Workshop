package sg.edu.nus.empdemo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.TestConstructor;

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

}
