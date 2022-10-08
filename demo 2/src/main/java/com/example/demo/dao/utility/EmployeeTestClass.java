package com.example.demo.dao.utility;

import com.example.demo.dao.dataservices.EmployeeDataService;

public class EmployeeTestClass {

    public static void main(String args[]){
        EmployeeDataService employeeDataService = new EmployeeDataService();
        employeeDataService.getEmployees();
    }
}
