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

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cts.sba.projectmanager.ProjectmanagerApplicationTests;
import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.controller.TaskController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class TaskIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private TaskController taskController;
	
	
	@Test
	public void fetchTasksTest() throws Exception{
		
		mockMvc.perform(get("/task/fetchTasks")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].taskId", is(1)))
        .andExpect(jsonPath("$[1].taskId", is(3)))
        .andDo(print());		
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		mockMvc.perform(get("/task/getTask/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("taskId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addTaskTest() throws Exception{
		
		mockMvc.perform(post("/task/saveTask")
		.content(asJsonString(getTask1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("taskId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		
		getTask2().setTaskName("Updated Task 2");
		mockMvc.perform(put("/task/updateTask/3")
		.content(asJsonString(getTask2()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("taskId", is(3)))
        .andDo(print());		
	}
	
	public Task getTask1() {
		Task task = new Task();
		task.setTaskId(1);
		task.setTaskName("Task 1");
		task.setParentId(1);
		task.setProjectId(1);
		task.setUserId(1);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setPriority(12);
		task.setStatus("Started");
		task.setParentTask(getParent());
		return task;
	}
	
	public Task getTask2() {
		Task task = new Task();
		task.setTaskId(2);
		task.setTaskName("Task 2");
		task.setParentId(1);
		task.setProjectId(1);
		task.setUserId(1);
		task.setStartDate(new Date());
		task.setEndDate(new Date());
		task.setPriority(12);
		task.setStatus("Started");
		task.setParentTask(getParent());
		return task;
	}
	
	public ParentTask getParent() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("Parent 1");
		return parentTask;
	}
	
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
