package com.example.demo.persistance.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "company")
public class EmployeeEntity {

    @Id
    @Column(name = "id")
    private int empId;

    @Column(name = "name")
    private String empName;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private int salary;


}
