package sg.edu.nus.empdemo.entity;

import java.util.ArrayList;
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

    public Employee(){
        this.projects = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public Employee(String name){
        this.name = name;
        this.projects = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public Employee(String name, Department department){
        this.name = name;
        this.assignDepartment(department);
        this.projects = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void assignDepartment(Department dept){
        this.department = dept;
    }

    public void enrollInCourse(Course course){
        this.courses.add(course);
    }

    public void joinProject(Project project){
        this.projects.add(project);
    }

}
