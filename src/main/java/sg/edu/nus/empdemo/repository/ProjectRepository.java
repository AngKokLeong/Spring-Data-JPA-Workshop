package sg.edu.nus.empdemo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
    Optional<Project> findByNameEquals(String projectName);

    Optional<Project> findByNameContainingIgnoreCase(String projectName);

    List<Project> findByEndDateAfter(LocalDate endDate);

}
