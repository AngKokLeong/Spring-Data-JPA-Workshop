package sg.edu.nus.empdemo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToOne
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToMany
    @JoinTable(name = "employee_projects")
    private List<Project> projects;

    public void assignDepartment(Department dept){

    }

    public void enrollInCourse(Course course){

    }

    public void joinProject(Project project){

    }

}
