package com.EmployeeManagement.WorkingManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.WorkingManagementSystem.repository.DepartmentRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.EmployeeRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.ProjectRepository;
import com.EmployeeManagement.WorkingManagementSystem.exception.EmployeeNotFound;
import com.EmployeeManagement.WorkingManagementSystem.model.*;
import com.EmployeeManagement.WorkingManagementSystem.exception.*;

import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    //services for Employee part
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        Employee oldEmployeeInfo = employeeRepository.findById(id).get();
        oldEmployeeInfo.setName(employee.getName());
        oldEmployeeInfo.setPosition(employee.getPosition());
        oldEmployeeInfo.setDepartment_id(employee.getDepartment_id());
        employeeRepository.save(oldEmployeeInfo);
        return oldEmployeeInfo;
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployees = this.employeeRepository
                                            .findById(id)
                                            .orElseThrow(() -> new EmployeeNotFound
                                            ("Employee by id "+id+"was not found"));
        this.employeeRepository.delete(existingEmployees);
    }

    //services for Department part
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    public Department addDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department, Long id) {
        Department oldDepartmentInfo = departmentRepository.findById(id).get();
        oldDepartmentInfo.setDepartment_name(department.getDepartment_name());
        departmentRepository.save(oldDepartmentInfo);
        return oldDepartmentInfo;
    }

    public void deleteDepartment(Long id) {
        Department existingDepartment = this.departmentRepository
                                            .findById(id)
                                            .orElseThrow(() -> new DepartmentNotFound
                                            ("Department by id "+id+"was not found"));
        this.departmentRepository.delete(existingDepartment);
    }

    //service for Project part
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).get();
    }

    public Project addProject(Project project){
        return projectRepository.save(project);
    }

    public Project updateProject(Project project, Long id) {
        Project oldProjectInfo = projectRepository.findById(id).get();
        oldProjectInfo.setProject_name(project.getProject_name());
        oldProjectInfo.setEmployee_id(project.getEmployee_id());
        projectRepository.save(oldProjectInfo);
        return oldProjectInfo;
    }

    public void deleteProject(Long id) {
        Project existingProject = this.projectRepository
                                            .findById(id)
                                            .orElseThrow(() -> new ProjectNotFound
                                            ("Project by id "+id+"was not found"));
        this.projectRepository.delete(existingProject);
    }
} 
