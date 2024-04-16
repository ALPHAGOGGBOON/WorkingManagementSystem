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

import com.EmployeeManagement.WorkingManagementSystem.model.Project;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/api/project")
@Tag(
        name = "Project Controller All CRUD API",
        description = "this is the class that implements all the CRUD api related to " +
                "project management"
)
@Slf4j
@CacheConfig(cacheNames  = "projects")
public class ProjectController {
    
    @Autowired
    private EmployeeService employeeService;

    @Operation(
        summary = "control all project assignment from db ",
              description = "getting all project from db using this api"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP status 200 OK"
    )

    @GetMapping
    public List<Project> findAllProjects() {
        return employeeService.getAllProjects();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public Optional<Project> findProjectById(@PathVariable Long id){
        log.info("fetching the student with id " + id + "from DB");
        return Optional.ofNullable(employeeService.getProjectById(id));
    }

    @PostMapping("/addProject")
    public Project createProject(@RequestBody Project project) {
        return employeeService.addProject(project);
    }

    @PutMapping("/update/{id}")
    @CachePut(key = "#id")
    public Project updateProject(@RequestBody Project project, @PathVariable Long id) {
        return employeeService.updateProject(project, id);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id")
    public void deleteProject(@PathVariable Long id) {
        employeeService.deleteProject(id);
    }
}
