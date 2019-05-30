/**
 * 
 */
package com.cts.sba.projectmanager.testcontroller;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.cts.sba.projectmanager.controller.ParentTaskController;
import com.cts.sba.projectmanager.service.ProjectManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class ParentTaskControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private ParentTaskController parentTaskController;
	
	@MockBean
	private ProjectManagerService projectManagerService;
	
	private List<ParentTask> parentList = null;
	
	@Before
	public void setUp() throws Exception {
		ParentTask parent1 = getParent1();
		ParentTask parent2 = getParent2();
		parentList = new ArrayList<ParentTask>();
		parentList.add(parent1);
		parentList.add(parent2);
	}
	
	@Test
	public void fetchTasksTest() throws Exception{
		
		when(projectManagerService.fetchParentTasks()).thenReturn(parentList);
		mockMvc.perform(get("/parent/fetchParentTasks")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].parentId", is(1)))
        .andExpect(jsonPath("$[1].parentId", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		when(projectManagerService.getParentTask(String.valueOf(getParent1().getParentId()))).thenReturn(getParent1());
		mockMvc.perform(get("/parent/getParentTask/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("parentId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addTaskTest() throws Exception{
		
		when(projectManagerService.addParentTask(getParent1())).thenReturn(getParent1());
		mockMvc.perform(post("/parent/saveParentTask")
		.content(asJsonString(getParent1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
  //     .andExpect(jsonPath("$[0].parentId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		
		when(projectManagerService.getParentTask(String.valueOf(getParent2().getParentId()))).thenReturn(getParent2());
		getParent2().setParentTask("Updated Parent 2");
		when(projectManagerService.addParentTask(getParent2())).thenReturn(getParent2());
		mockMvc.perform(post("/parent/saveParentTask")
		.content(asJsonString(getParent1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
  //     .andExpect(jsonPath("$[0].parentId", is(1)))
        .andDo(print());		
	}
	
	public ParentTask getParent1() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(1);
		parentTask.setParentTask("Parent 1");
		return parentTask;
	}
	
	public ParentTask getParent2() {
		ParentTask parentTask = new ParentTask();
		parentTask.setParentId(2);
		parentTask.setParentTask("Parent 2");
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
