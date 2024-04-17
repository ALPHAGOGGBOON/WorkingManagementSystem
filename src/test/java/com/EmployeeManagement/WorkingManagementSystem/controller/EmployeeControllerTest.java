package com.EmployeeManagement.WorkingManagementSystem.controller;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.EmployeeManagement.WorkingManagementSystem.model.Employee;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeController.class)
@ActiveProfiles("local")
@MockBean(JpaMetamodelMappingContext.class)
public class EmployeeControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private EmployeeService employeeService;

    @Autowired
	private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void init() {
        employee = new Employee();
        employee.setEmployee_id(1L);
        employee.setName("John");
        employee.setPosition("AIR");
        employee.setDepartment_id(1L);
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldReturn200BecauseValidCredentials() throws Exception {
        String username = "admin";
        String password = "password";

        mockMvc.perform(get("/api/employee")
                .with(SecurityMockMvcRequestPostProcessors
                .httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldfindallDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldfindADepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", 1L)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = {"admin"})
    void shouldAddOneDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/addEmployees")
        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
       .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = {"admin"})
    void shouldUpdateOneDepartment() throws Exception {
        mockMvc.perform(put("/api/employee/update/{id}", 1L)
        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
       .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldDeleteDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/delete/{id}", 1L)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
