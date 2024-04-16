package com.EmployeeManagement.WorkingManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagement.WorkingManagementSystem.model.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    
}
