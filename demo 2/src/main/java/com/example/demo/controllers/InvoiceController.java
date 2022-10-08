package com.example.demo.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.dao.dataservices.EmployeeDataService;
import com.example.demo.dao.dto.EmployeeDTO;
import com.example.demo.service.Invoice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/iconcile")
public class InvoiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/invoices")
    public Invoice getInvoices(@RequestParam(value = "name", defaultValue = "invoice 1!") String name) {
        return new Invoice(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        EmployeeDataService employeeDataService = new EmployeeDataService();
        return employeeDataService.getEmployees();

    }


}