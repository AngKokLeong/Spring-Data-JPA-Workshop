package sg.edu.nus.empdemo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Courses")
public class Course {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration_in_months")
    private Double durationInMonths;

    @Column(name = "course_starts")
    private LocalDate starts;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name="employee_id")
    private Employee employee;


    public Course(){}

    public Course(String name, Double durationInMonths, LocalDate starts) {
        this.name = name;
        this.durationInMonths = durationInMonths;
        this.starts = starts;
    }

    public Long getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Double getDurationInMonths() {
        return durationInMonths;
    }


    public void setDurationInMonths(Double durationInMonths) {
        this.durationInMonths = durationInMonths;
    }


    public LocalDate getStarts() {
        return starts;
    }


    public void setStarts(LocalDate starts) {
        this.starts = starts;
    }


    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    

}
