package sg.edu.nus.empdemo.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

@DataJpaTest
@DisplayName("Project Repository Test")
public class ProjectRepositoryTest {
    
    private TestEntityManager testEntityManager;
    private ProjectRepository projectRepository;

    public ProjectRepositoryTest(TestEntityManager testEntityManager, ProjectRepository projectRepository){
        this.testEntityManager = testEntityManager;
        this.projectRepository = projectRepository;
    }

}
