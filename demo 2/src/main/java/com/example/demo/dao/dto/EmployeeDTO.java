package com.example.demo.dao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    int id;
    String name;
    int age;
    int salary;
    String address;
}
