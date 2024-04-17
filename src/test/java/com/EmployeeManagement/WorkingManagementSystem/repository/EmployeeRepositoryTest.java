package com.EmployeeManagement.WorkingManagementSystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.EmployeeManagement.WorkingManagementSystem.model.Employee;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach //set up the dummy values
    void init() {
        employee = new Employee();
        employee.setEmployee_id(1L);
        employee.setName("John");
        employee.setPosition("AIR");
        employee.setDepartment_id(1L);
        //department.getCreateDate();
        //department.getCreatedBy();
        //department.getModifiedBy();
        //department.getLastModifiedDate();
    }

    @Test
    @DisplayName("It should sava the employee into database")
    void savaEmployee() {
        Employee newEmployee = employeeRepository.save(employee);
        assertNotNull(newEmployee);
		assertThat(newEmployee.getEmployee_id()).isNotEqualTo(null);
    }

	@Test
	@DisplayName("It should return the employee list with size of 1")
    void getAllDepartments() {
        //departmentRepository.save(department);

        List<Employee> list = employeeRepository.findAll();

        assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(1, list.size()); //ggwp maybe it refering the real database?!?!
    }

    @Test
    @DisplayName("It should return by its id")
    void getDepartmentById() {
        Employee newEmployee = employeeRepository.findById(employee.getEmployee_id()).get();

        assertNotNull(newEmployee);
        assertEquals(newEmployee.getName(), employee.getName());
    } //refering the real database confirmed

    @Test
    @DisplayName("It should update the employee ")
    void updateDepartment() {
        Employee existingEmployee = employeeRepository.findById(employee.getEmployee_id()).get();
        existingEmployee.setName("haiyaEmployee");
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        assertEquals(existingEmployee.getName(), updatedEmployee.getName());

    }

    @Test
	@DisplayName("It should delete the existing employee")
	void deleteMovie() {
		
		Long id = employee.getEmployee_id();
		
		employeeRepository.delete(employee);
		
		List<Employee> list = employeeRepository.findAll();
		
		Optional<Employee> exitingEmployee = employeeRepository.findById(id);
		
		assertEquals(1, list.size()); //the real time database one of it is deleted
		assertThat(exitingEmployee).isEmpty();
		
	}
}
