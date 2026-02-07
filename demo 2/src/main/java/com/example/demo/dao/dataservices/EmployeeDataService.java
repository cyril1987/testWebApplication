package com.example.demo.dao.dataservices;

import com.example.demo.dao.dto.EmployeeDTO;
import com.example.demo.persistance.entity.EmployeeEntity;
import com.example.demo.persistance.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeDataService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDataService.class);

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
            logger.error("Error retrieving employees", e);

        }
        logger.debug("Returning no of employees: {}", employeeDTOList.size());
        return employeeDTOList;
    }

    public List<EmployeeDTO> getEmployeeByName(String searchName) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        try {
            employeeRepository.findEmployees(searchName).forEach(emp ->
                    employeeDTOList.add(getEmployeeDTO(emp))

            );

        } catch (Exception e) {
            logger.error("Error searching employees by name", e);

        }
        logger.debug("Returning no of employees: {}", employeeDTOList.size());
        return employeeDTOList;
    }
}
