/**
 * 
 */
package com.cts.sba.projectmanager.integrationtest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cts.sba.projectmanager.ProjectmanagerApplicationTests;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.controller.ProjectController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class ProjectIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private ProjectController projectController;
	
	
	@Test
	public void fetchProjectsTest() throws Exception{
		
		mockMvc.perform(get("/project/fetchProjects")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].projectId", is(1)))
        .andExpect(jsonPath("$[1].projectId", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getProjectTest() throws Exception{
		
		mockMvc.perform(get("/project/getProject/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("projectId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addProjectTest() throws Exception{
		
		mockMvc.perform(post("/project/saveProject")
		.content(asJsonString(getProject1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("projectId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateProjectTest() throws Exception{
		
		getProject2().setProject("Updated Project 2");
		mockMvc.perform(put("/project/updateProject/2")
		.content(asJsonString(getProject2()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("projectId", is(2)))
        .andDo(print());		
	}
	
	public Project getProject1() {
		Project project = new Project();
		project.setCompTasks(2);
		project.setEndDate(new Date());
		project.setManagerId(1);
		project.setManagerName("User 1");
		project.setPriority(12);
		project.setProject("Project 1");
		project.setProjectId(1);
		project.setStartDate(new Date());
		project.setTasks(new ArrayList<Task>());
		return project;
	}
	
	public Project getProject2() {
		Project project = new Project();
		project.setCompTasks(2);
		project.setEndDate(new Date());
		project.setManagerId(2);
		project.setManagerName("User 2");
		project.setPriority(12);
		project.setProject("Project 2");
		project.setProjectId(2);
		project.setStartDate(new Date());
		project.setTasks(new ArrayList<Task>());
		return project;
	}
	
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
