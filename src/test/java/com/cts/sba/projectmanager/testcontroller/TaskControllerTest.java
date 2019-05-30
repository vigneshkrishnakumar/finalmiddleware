/**
 * 
 */
package com.cts.sba.projectmanager.testcontroller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cts.sba.projectmanager.ProjectmanagerApplicationTests;
import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.controller.TaskController;
import com.cts.sba.projectmanager.service.ProjectManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private TaskController taskController;
	
	@MockBean
	private ProjectManagerService projectManagerService;
	
	private List<Task> taskList = null;
	
	@Before
	public void setUp() throws Exception {
		Task task1 = getTask1();
		Task task2 = getTask2();
		taskList = new ArrayList<Task>();
		taskList.add(task1);
		taskList.add(task2);
	}
	
	@Test
	public void fetchTasksTest() throws Exception{
		
		when(projectManagerService.fetchTasks()).thenReturn(taskList);
		mockMvc.perform(get("/task/fetchTasks")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].taskId", is(1)))
        .andExpect(jsonPath("$[1].taskId", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		when(projectManagerService.getTask(String.valueOf(getTask1().getTaskId()))).thenReturn(getTask1());
		mockMvc.perform(get("/task/getTask/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("taskId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addTaskTest() throws Exception{
		
		when(projectManagerService.addTask(getTask1())).thenReturn(getTask1());
		mockMvc.perform(post("/task/saveTask")
		.content(asJsonString(getTask1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
    //   .andExpect(jsonPath("$[0].taskId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		
		when(projectManagerService.getTask(String.valueOf(getTask2().getTaskId()))).thenReturn(getTask2());
		getTask2().setTaskName("Updated Task 2");
		when(projectManagerService.addTask(getTask2())).thenReturn(getTask2());
		mockMvc.perform(post("/task/saveTask")
		.content(asJsonString(getTask2()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
   //    .andExpect(jsonPath("$[0].taskId", is(1)))
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
