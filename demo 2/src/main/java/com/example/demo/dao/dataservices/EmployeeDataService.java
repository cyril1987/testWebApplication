package com.example.demo.dao.dataservices;

import com.example.demo.dao.dto.EmployeeDTO;
import com.example.demo.persistance.entity.EmployeeEntity;
import com.example.demo.persistance.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDataService {

    private final EmployeeRepository employeeRepository;

    private static EmployeeDTO getEmployeeDTO(EmployeeEntity emp) {

        return EmployeeDTO.builder()
                .id(emp.getEmpId())
                .name(emp.getEmpName())
                .address(emp.getAddress())
                .age(emp.getAge())
                .salary(emp.getSalary())
                .build();
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        try {
            employeeRepository.findAll().forEach(emp -> {

                EmployeeDTO employeeDTO = getEmployeeDTO(emp);

                employeeDTOList.add(employeeDTO);
            });

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Returning no of employees: " + employeeDTOList.size());
        return employeeDTOList;
    }

    public List<EmployeeDTO> getEmployeeByName(String searchName) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        try {
            employeeRepository.findEmployees(searchName).forEach(emp ->
                    employeeDTOList.add(getEmployeeDTO(emp))

            );

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Returning no of employees: " + employeeDTOList.size());
        return employeeDTOList;
    }
}
