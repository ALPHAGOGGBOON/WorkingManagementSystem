package com.EmployeeManagement.WorkingManagementSystem.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;
import com.EmployeeManagement.WorkingManagementSystem.model.Employee;
import com.EmployeeManagement.WorkingManagementSystem.model.Project;
import com.EmployeeManagement.WorkingManagementSystem.repository.DepartmentRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.EmployeeRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.ProjectRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagementIntegrateTest {
    
    @LocalServerPort
	private int port;

    private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;

    private Department department;
    private Employee employee;
    private Project project;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

    @BeforeEach //set up the dummy values
    void beforeEach() {

        baseUrl = baseUrl + ":" + port + "/api/";

        employee = new Employee();
        employee.setEmployee_id(1L);
        employee.setName("John");
        employee.setPosition("Permenant");
        employee.setDepartment_id(1L);

        department = new Department();
        department.setDepartment_id(1L);
        department.setDepartment_name("IT_Department");

        project = new Project();
        project.setProject_id(1L);
        project.setProject_name("IT_Project");
        project.setEmployee_id(1L);
    }

    @AfterEach
	public void afterSetup() {
		projectRepository.deleteAll();
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
	}

    @Test //problem unauthorize access 
    void shouldFetchAllDepartment() {
        
        List<Department> list = restTemplate.getForObject(baseUrl + "/department", List.class);

        assertThat(list.size()).isNotNull();
    }
}
