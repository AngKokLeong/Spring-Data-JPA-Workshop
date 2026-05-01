package sg.edu.nus.empdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
    Optional<Project> findByNameEquals(String projectName);

}
