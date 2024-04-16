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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/api/department")
@Tag(
        name = "Department Controller All CRUD API",
        description = "this is the class that implements all the CRUD api related to " +
                "department management"
)
@Slf4j
@CacheConfig(cacheNames  = "departments")
public class DepartmentController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(
        summary = "finding all departments from db ",
              description = "getting all department from db using this api"
      )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 OK"
    )

    @GetMapping
    public List<Department> findAllDepartments() {
        return employeeService.getAllDepartments();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Optional<Department> findDepartmentById(@PathVariable Long id){
        log.info("fetching the student with id " + id + "from DB");
        return Optional.ofNullable(employeeService.getDepartmentById(id));
    }

    @PostMapping("/addDepartments")
    public Department createDepartment(@RequestBody @Valid Department department) {
        return employeeService.addDepartment(department);
    }

    @PutMapping("/update/{id}")
    @CachePut(key = "#id")
    public Department updateDepartment(@RequestBody @Valid Department department, @PathVariable Long id) {
        return employeeService.updateDepartment(department, id);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id")
    public void deletDepartment(@PathVariable Long id) {
        employeeService.deleteDepartment(id);
    }
}
