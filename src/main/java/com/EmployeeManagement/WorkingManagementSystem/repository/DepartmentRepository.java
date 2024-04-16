package com.EmployeeManagement.WorkingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long>{

}
