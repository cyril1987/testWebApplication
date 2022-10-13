package com.example.demo.persistance.repository;

import com.example.demo.persistance.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {

    @Query("SELECT a FROM EmployeeEntity a where a.empName like %:name% ")
    List<EmployeeEntity> findEmployees(@Param("name") String name);
}
