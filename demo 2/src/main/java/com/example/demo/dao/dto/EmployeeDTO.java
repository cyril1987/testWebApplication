package com.example.demo.dao.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {
    int id;
    String name;
    int age;
    @JsonIgnore
    int salary;
    String address;
}
