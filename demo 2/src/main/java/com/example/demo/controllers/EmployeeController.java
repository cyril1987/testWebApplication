package com.example.demo.controllers;

import com.example.demo.dao.dataservices.EmployeeDataService;
import com.example.demo.dao.utility.JsonHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "/iconcile")
public class EmployeeController {


    @GetMapping("/allEmployees")
    public String getEmployees() {
        EmployeeDataService employeeDataService = new EmployeeDataService();
        return JsonHelper.getJsonString(employeeDataService.getEmployees());
    }

    @GetMapping("/employee")
    public String getEmployeeByName(@RequestParam(value = "name") String name) {
        /*Sample: http://localhost:8081/iconcile/employee?name=Pau  */
        EmployeeDataService employeeDataService = new EmployeeDataService();
        return JsonHelper.getJsonString(employeeDataService.getEmployeeByName(name));

    }

    @GetMapping("/isAlive")
    public boolean isAlive() {
        return true;
    }

}