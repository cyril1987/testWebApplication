package com.example.demo.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.dao.dataservices.EmployeeDataService;
import com.example.demo.dao.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/iconcile")
public class EmployeeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/allEmployees")
    public List<EmployeeDTO> getEmployees() {
        EmployeeDataService employeeDataService = new EmployeeDataService();
        return employeeDataService.getEmployees();

    }

    @GetMapping("/employee")
    public List<EmployeeDTO>  invoices(@RequestParam(value = "name") String name) {
        /*Sample: http://localhost:8081/iconcile/employee?name=Pau  */
        EmployeeDataService employeeDataService = new EmployeeDataService();
        return employeeDataService.getEmployeeByName(name);
    }


}