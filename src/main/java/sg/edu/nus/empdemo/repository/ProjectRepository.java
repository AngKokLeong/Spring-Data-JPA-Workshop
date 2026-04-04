package sg.edu.nus.empdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
