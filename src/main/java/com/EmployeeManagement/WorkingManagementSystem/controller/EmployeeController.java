package com.EmployeeManagement.WorkingManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import com.EmployeeManagement.WorkingManagementSystem.model.Employee;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@Tag(
        name = "Employee Controller All CRUD API",
        description = "this is the class that implements all the CRUD api related to " +
                "Employee management"
)
@Slf4j
@CacheConfig(cacheNames  = "employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(
        summary = "finding all employee from db ",
              description = "getting all employee from db using this api"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 OK"
    )

    @GetMapping
    public List<Employee> findAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Optional<Employee> findEmployeeById(@PathVariable Long id){
        log.info("fetching the student with id " + id + "from DB");
        return Optional.ofNullable(employeeService.getEmployeeById(id));
    }

    @PostMapping("/addEmployees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update/{id}")
    @CachePut(key = "#id")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id")
    public void deletEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
