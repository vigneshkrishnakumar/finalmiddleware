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
import com.cts.sba.projectmanager.controller.ParentTaskController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class ParentTaskIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private ParentTaskController parentTaskController;
	
	
	@Test
	public void fetchTasksTest() throws Exception{
		
		mockMvc.perform(get("/parent/fetchParentTasks")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].parentId", is(1)))
        .andExpect(jsonPath("$[1].parentId", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		mockMvc.perform(get("/parent/getParentTask/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("parentId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addTaskTest() throws Exception{
		
		mockMvc.perform(post("/parent/saveParentTask")
		.content(asJsonString(getParent1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("parentId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		
		getParent2().setParentTask("Updated Parent 2");
		mockMvc.perform(put("/parent/updateParentTask/2")
		.content(asJsonString(getParent2()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("parentId", is(2)))
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
