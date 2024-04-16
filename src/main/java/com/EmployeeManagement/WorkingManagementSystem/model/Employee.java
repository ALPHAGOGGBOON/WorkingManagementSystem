package com.EmployeeManagement.WorkingManagementSystem.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.ToString;


/*
 * Employee requirement schema
 * employee_ID, name, position, department_ID
 */
@Entity
@Table(name="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="employee_ID")
    private Long employee_id;

    @NotNull(message = "The name cannot be null")
    @NotEmpty(message ="The name cannot be empty")
    @NotBlank(message = "The name cannot be blank")
    @Column(name="name")
    private String name;

    @NotNull(message = "The position cannot be null")
    @NotEmpty(message ="The position cannot be empty")
    @NotBlank(message = "The position cannot be blank")
    @Column(name="position")
    private String position;

    @Column(name="department_ID")
    private Long department_id;

    @CreatedDate
	private Date createDate;
	@LastModifiedDate
	private Date lastModifiedDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String modifiedBy;
}
