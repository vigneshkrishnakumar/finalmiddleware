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
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.controller.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class UserIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private UserController userController;
	
	@Test
	public void fetchUsersTest() throws Exception{
		
		mockMvc.perform(get("/user/fetchUsers")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].userId", is(1)))
        .andExpect(jsonPath("$[1].userId", is(2)))
        .andDo(print());		
	}
	
	@Test
	public void getUserTest() throws Exception{
		
		mockMvc.perform(get("/user/getUser/1")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("userId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void addUserTest() throws Exception{
		
		mockMvc.perform(post("/user/saveUser")
		.content(asJsonString(getUser1()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("userId", is(1)))
        .andDo(print());		
	}
	
	@Test
	public void updateUserTest() throws Exception{
		
		getUser2().setFirstName("Updated User 2");
		mockMvc.perform(put("/user/updateUser/2")
		.content(asJsonString(getUser2()))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
       .andExpect(jsonPath("userId", is(2)))
        .andDo(print());		
	}
	
	public User getUser1() {
		User user = new User();
		user.setUserId(1);
		user.setEmpId(123);
		user.setFirstName("User 1");
		user.setLastName("Test");
		user.setUserProjectId(1);
		user.setUserTaskId(1);
		return user;
	}
	
	public User getUser2() {
		User user = new User();
		user.setUserId(2);
		user.setEmpId(321);
		user.setFirstName("User 2");
		user.setLastName("Test");
		user.setUserProjectId(2);
		user.setUserTaskId(2);
		return user;
	}
	
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
}
