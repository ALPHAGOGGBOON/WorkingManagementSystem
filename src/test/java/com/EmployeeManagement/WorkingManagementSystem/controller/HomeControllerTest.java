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

import com.EmployeeManagement.WorkingManagementSystem.model.Department;
import com.EmployeeManagement.WorkingManagementSystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(HomeController.class)
@ActiveProfiles("local")
@MockBean(JpaMetamodelMappingContext.class)
public class HomeControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private EmployeeService employeeService;

    @Autowired
	private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", password = "password")
    void shouldReturn200BecauseValidCredentials() throws Exception {
        String username = "admin";
        String password = "password";

        mockMvc.perform(get("http://localhost:8080/")
                .with(SecurityMockMvcRequestPostProcessors
                .httpBasic(username, password)))
                .andExpect(status().isOk());
    }
}
