package com.example.demo.dao.dataservices;

import com.example.demo.dao.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataService {

    public List<EmployeeDTO> getEmployees() {
        PostGreDatabaseConnector postGreDatabaseConnector = new PostGreDatabaseConnector();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        Connection dbConnection = postGreDatabaseConnector.getPostGreConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setId(id);
                employeeDTO.setName(name);
                employeeDTO.setAddress(address);
                employeeDTO.setAge(age);
                employeeDTOList.add(employeeDTO);

            }
            rs.close();
            stmt.close();
            dbConnection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Returning no of employees: " + employeeDTOList.size());
        return employeeDTOList;
    }
}
