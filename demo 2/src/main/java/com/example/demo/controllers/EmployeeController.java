package com.example.demo.controllers;

import com.example.demo.dao.dataservices.EmployeeDataService;
import com.example.demo.dao.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(path = "/iconcile")
@Validated
public class EmployeeController {

    @Autowired
    private EmployeeDataService employeeDataService;

    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeDataService.getEmployees());
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByName(
            @RequestParam(value = "name")
            @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
            @Pattern(regexp = "^[a-zA-Z\\s\\-']+$", message = "Name must contain only letters, spaces, hyphens, and apostrophes")
            String name) {
        return ResponseEntity.ok(employeeDataService.getEmployeeByName(name));
    }

    @GetMapping("/isAlive")
    public boolean isAlive() {
        return true;
    }

}