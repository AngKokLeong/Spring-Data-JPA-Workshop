package sg.edu.nus.empdemo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
    Optional<Project> findByNameEquals(String projectName);

    Optional<Project> findByNameContainingIgnoreCase(String projectName);

    List<Project> findByEndDateAfter(LocalDate endDate);

    @Query("SELECT p FROM Project p WHERE p.startDate >= :start AND p.endDate < :end")
    List<Project> findByDateRange(
        @Param("start") LocalDate startDate, 
        @Param("end") LocalDate endDate);

    @EntityGraph(attributePaths="employees") //EntityGraph allows 
    @Query("SELECT p FROM Project p WHERE p.id = ?1")
    List<Project> findByIdWithEmployees(Long id);

    @EntityGraph(attributePaths="employees")
    @Query("SELECT DISTINCT p FROM Project p JOIN p.employees e WHERE e.id = :employeeId")
    List<Project> findByEmployeeIdWithProjects(@Param("employeeId") Long employeeId);
}
