package sg.edu.nus.empdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.empdemo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Optional<Department> findByNameEquals(String departmentName); 
    
    Optional<Department> findByNameContainingIgnoreCase(String departmentName);
}
