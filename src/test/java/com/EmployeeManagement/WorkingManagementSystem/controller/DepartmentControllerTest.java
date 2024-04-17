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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.EmployeeManagement.WorkingManagementSystem.model.Department;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DepartmentController.class)
@ActiveProfiles("local")
@MockBean(JpaMetamodelMappingContext.class)
public class DepartmentControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private EmployeeService employeeService;

    @Autowired
	private ObjectMapper objectMapper;

    private Department department;

    @BeforeEach
    void init() {
        department = new Department();
        department.setDepartment_id(1L);
        department.setDepartment_name("IT_Department");
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldReturn200BecauseValidCredentials() throws Exception {
        String username = "admin";
        String password = "password";

        mockMvc.perform(get("/api/department")
                .with(SecurityMockMvcRequestPostProcessors
                .httpBasic(username, password)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldfindallDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/department")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldfindADepartmentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/{id}", 1L)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = {"admin"})
    void shouldAddOneDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/department/addDepartments")
        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(department)))
       .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "password", roles = {"admin"})
    void shouldUpdateOneDepartment() throws Exception {
        mockMvc.perform(put("/api/department/update/{id}", 1L)
        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password"))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(department)))
       .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldDeleteDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/department/delete/{id}", 1L)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "password")))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
