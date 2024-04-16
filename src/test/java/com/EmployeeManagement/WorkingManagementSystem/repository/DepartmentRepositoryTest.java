package com.EmployeeManagement.WorkingManagementSystem.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach //set up the dummy values
    void init() {
        department = new Department();
        department.setDepartment_id(1L);
        department.setDepartment_name("IT_Department");
        //department.getCreateDate();
        //department.getCreatedBy();
        //department.getModifiedBy();
        //department.getLastModifiedDate();
    }

    @Test
    @DisplayName("It should sava the department into database")
    void savaDepartment() {
        Department newDepartment = departmentRepository.save(department);
        assertNotNull(newDepartment);
		assertThat(newDepartment.getDepartment_id()).isNotEqualTo(null);
    }

	@Test
	@DisplayName("It should return the department list with size of 1")
    void getAllDepartments() {
        //departmentRepository.save(department);

        List<Department> list = departmentRepository.findAll();

        assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(3, list.size()); //ggwp maybe it refering the real database?!?!
    }

    @Test
    @DisplayName("It should return by its id")
    void getDepartmentById() {
        Department newDepartment = departmentRepository.findById(department.getDepartment_id()).get();

        assertNotNull(newDepartment);
        assertEquals(newDepartment.getDepartment_name(), department.getDepartment_name());
    } //refering the real database confirmed

    @Test
    @DisplayName("It should update the department")
    void updateDepartment() {
        Department existingDepartment = departmentRepository.findById(department.getDepartment_id()).get();
        existingDepartment.setDepartment_name("haiyaDepartment");
        Department updatedDepartment = departmentRepository.save(existingDepartment);

        assertEquals(existingDepartment.getDepartment_name(), updatedDepartment.getDepartment_name());

    }

    @Test
	@DisplayName("It should delete the existing department")
	void deleteMovie() {
		
		Long id = department.getDepartment_id();
		
		departmentRepository.delete(department);
		
		List<Department> list = departmentRepository.findAll();
		
		Optional<Department> exitingDepartment = departmentRepository.findById(id);
		
		assertEquals(2, list.size()); //the real time database one of it is deleted
		assertThat(exitingDepartment).isEmpty();
		
	}
}
