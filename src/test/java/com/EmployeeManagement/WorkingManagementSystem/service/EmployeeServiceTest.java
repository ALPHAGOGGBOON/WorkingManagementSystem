package com.EmployeeManagement.WorkingManagementSystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;
import com.EmployeeManagement.WorkingManagementSystem.model.Employee;
import com.EmployeeManagement.WorkingManagementSystem.model.Project;
import com.EmployeeManagement.WorkingManagementSystem.repository.DepartmentRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.EmployeeRepository;
import com.EmployeeManagement.WorkingManagementSystem.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private Department department;
    private Project project;

    @BeforeEach //set up the dummy values
    void init() {
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

    // Testing Employee service methods
    @Test 
    void savaEmployeee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee newEmployee = employeeService.addEmployee(employee);

        assertNotNull(newEmployee);
        assertThat(newEmployee.getName()).isEqualTo("John");
    }

    @Test
    void getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(employee);

        when(employeeRepository.findAll()).thenReturn(list);
        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(1, employees.size());
        assertNotNull(employees);
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        Employee existingEmployee = employeeService.getEmployeeById(employee.getEmployee_id());
        assertNotNull(existingEmployee);
        assertThat(existingEmployee.getEmployee_id()).isNotNull();
    }

    @Test
    void updateEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        employee.setName("John Cena");
        Employee existingEmployee = employeeService.updateEmployee(employee, employee.getEmployee_id());

        assertNotNull(existingEmployee);
        assertEquals("John Cena", employee.getName());
    }

    @Test
    void deleteEmployee() {
        Long employeeId = 1L;
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(any(Employee.class));

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).delete(employee);
    }

    // Departments service methods
    @Test
    void savaDepartments() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        Department newDepartment = employeeService.addDepartment(department);

        assertNotNull(newDepartment);
        assertThat(newDepartment.getDepartment_name()).isEqualTo("IT_Department");        
    }

    @Test
    void getAllDepartments() {
        List<Department> list = new ArrayList<>();
        list.add(department);

        when(departmentRepository.findAll()).thenReturn(list);
        List<Department> departments = employeeService.getAllDepartments();

        assertEquals(1, departments.size());
        assertNotNull(departments);
    }
    @Test
    void getDepartmentById() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        Department existingDepartment = employeeService.getDepartmentById(department.getDepartment_id());
        assertNotNull(existingDepartment);
        assertThat(existingDepartment.getDepartment_id()).isNotNull();
    }

    @Test
    void updateDepartment() {
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));

        when(departmentRepository.save(any(Department.class))).thenReturn(department);
        department.setDepartment_name("DepartmentofIT");
        Department existingDepartment = employeeService.updateDepartment(department, department.getDepartment_id());

        assertNotNull(existingDepartment);
        assertEquals("DepartmentofIT", department.getDepartment_name());
    }

    @Test
    void deleteDepartment() {
        Long departmentId = 1L;
        when(departmentRepository.findById(anyLong())).thenReturn(Optional.of(department));
        doNothing().when(departmentRepository).delete(any(Department.class));

        employeeService.deleteDepartment(departmentId);

        verify(departmentRepository, times(1)).delete(department);
    }

    // Departments service methods
    @Test
    void savaProjects() {
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project newProject = employeeService.addProject(project);

        assertNotNull(newProject);
        assertThat(newProject.getProject_name()).isEqualTo("IT_Project");        
    }

    @Test
    void getAllProjects() {
        List<Project> list = new ArrayList<>();
        list.add(project);

        when(projectRepository.findAll()).thenReturn(list);
        List<Project> projects = employeeService.getAllProjects();

        assertEquals(1, projects.size());
        assertNotNull(projects);
    }

    @Test
    void getProjectById() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        Project existingProject = employeeService.getProjectById(project.getProject_id());
        assertNotNull(existingProject);
        assertThat(existingProject.getProject_id()).isNotNull();
    }

    @Test
    void updateProject() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class))).thenReturn(project);
        project.setProject_name("ProjectofIT");
        Project existingProject = employeeService.updateProject(project, project.getProject_id());

        assertNotNull(existingProject);
        assertEquals("ProjectofIT", project.getProject_name());
    }

    @Test
    void deleteProject() {
        Long projectId = 1L;
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        doNothing().when(projectRepository).delete(any(Project.class));

        employeeService.deleteProject(projectId);

        verify(projectRepository, times(1)).delete(project);
    }
}
