package sg.edu.nus.empdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.empdemo.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
