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

import com.EmployeeManagement.WorkingManagementSystem.model.Project;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    private Project project;

    @BeforeEach //set up the dummy values
    void init() {
        project = new Project();
        project.setProject_id(1L);
        project.setProject_name("IT_Project");
        //department.getCreateDate();
        //department.getCreatedBy();
        //department.getModifiedBy();
        //department.getLastModifiedDate();
    }

    @Test
    @DisplayName("It should sava the project into database")
    void savaProject() {
        Project newProject = projectRepository.save(project);
        assertNotNull(newProject);
		assertThat(newProject.getProject_id()).isNotEqualTo(null);
    }

	@Test
	@DisplayName("It should return the project list with size of 1")
    void getAllProjects() {
        //ProjectRepository.save(Project);

        List<Project> list = projectRepository.findAll();

        assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(3, list.size()); //ggwp maybe it refering the real database?!?!
    }

    @Test
    @DisplayName("It should return by its id")
    void getProjectById() {
        Project newProject = projectRepository.findById(project.getProject_id()).get();

        assertNotNull(newProject);
        assertEquals(newProject.getProject_name(), project.getProject_name());
    } //refering the real database confirmed

    @Test
    @DisplayName("It should update the project")
    void updateProject() {
        Project existingProject = projectRepository.findById(project.getProject_id()).get();
        existingProject.setProject_name("haiyaProject");
        Project updatedProject = projectRepository.save(existingProject);

        assertEquals(existingProject.getProject_name(), updatedProject.getProject_name());

    }

    @Test
	@DisplayName("It should delete the existing project")
	void deleteMovie() {
		
		Long id = project.getProject_id();
		
		projectRepository.delete(project);
		
		List<Project> list = projectRepository.findAll();
		
		Optional<Project> exitingProject = projectRepository.findById(id);
		
		assertEquals(1, list.size()); //the real time database one of it is deleted
		assertThat(exitingProject).isEmpty();
		
	}
}
