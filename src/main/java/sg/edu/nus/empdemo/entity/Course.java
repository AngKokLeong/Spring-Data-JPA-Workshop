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


}
