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

import jakarta.persistence.OneToMany;
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


    @OneToOne(targetEntity = Department.class)
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToMany(targetEntity = Project.class)
    @JoinTable(name = "employee_projects")
    private List<Project> projects;


    @OneToMany(mappedBy = "employee")
    private List<Course> courses;

    public void assignDepartment(Department dept){
        
    }

    public void enrollInCourse(Course course){

    }

    public void joinProject(Project project){

    }

}
