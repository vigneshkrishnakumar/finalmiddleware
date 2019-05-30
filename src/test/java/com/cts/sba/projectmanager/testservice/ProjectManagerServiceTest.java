/**
 * 
 */
package com.cts.sba.projectmanager.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.cts.sba.projectmanager.ProjectmanagerApplicationTests;
import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.dao.ProjectManagerDAO;
import com.cts.sba.projectmanager.service.ProjectManagerService;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProjectmanagerApplicationTests.class})
@AutoConfigureMockMvc
public class ProjectManagerServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	private ProjectManagerService projectManagerService;
	
	@MockBean
	private ProjectManagerDAO projectManagerDAO;
	
	private List<ParentTask> parentList = null;
	private List<Task> taskList = null;
	private List<Project> projectList = null;
	private List<User> userList = null;
	
	@Before
	public void setUpParent() throws Exception {
		ParentTask parent1 = getParent1();
		ParentTask parent2 = getParent2();
		parentList = new ArrayList<ParentTask>();
		parentList.add(parent1);
		parentList.add(parent2);
	}
	
	@Test
	public void fetchParentTasksTest() throws Exception{
		
		when(projectManagerDAO.fetchAllParents()).thenReturn(parentList);
		assertThat(projectManagerService.fetchParentTasks(), hasSize(2));
		assertThat(projectManagerService.fetchParentTasks(), hasItems(isA(ParentTask.class)));
	}
	
	@Test
	public void getParentTaskTest() throws Exception{
		
		when(projectManagerDAO.fetchOneParent(String.valueOf(getParent1().getParentId()))).thenReturn(getParent1());
		assertThat(getParent1().getParentId()).isEqualTo(getParent1().getParentId());	
	}
	
	@Test
	public void addParentTaskTest() throws Exception{
		
		when(projectManagerDAO.persist(getParent1())).thenReturn(getParent1());
	}
	
	@Test
	public void updateParentTaskTest() throws Exception{
		
		when(projectManagerDAO.fetchOneParent(String.valueOf(getParent2().getParentId()))).thenReturn(getParent2());
		getParent2().setParentTask("Updated Parent 2");
		when(projectManagerDAO.persist(getParent2())).thenReturn(getParent2());	
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
	
	@Before
	public void setUpTask() throws Exception {
		Task task1 = getTask1();
		Task task2 = getTask2();
		taskList = new ArrayList<Task>();
		taskList.add(task1);
		taskList.add(task2);
	}
	
	@Test
	public void fetchTasksTest() throws Exception{
		
		when(projectManagerDAO.fetchAllTasks()).thenReturn(taskList);
		assertThat(projectManagerService.fetchTasks(), hasSize(2));
		assertThat(projectManagerService.fetchTasks(), hasItems(isA(Task.class)));		
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		when(projectManagerDAO.fetchOneTask(String.valueOf(getTask1().getTaskId()))).thenReturn(getTask1());
		assertThat(getTask1().getTaskId()).isEqualTo(getTask1().getTaskId());	
	}
	
	@Test
	public void addTaskTest() throws Exception{
		
		when(projectManagerDAO.persist(getTask1())).thenReturn(getTask1());	
	}
	
	@Test
	public void updateTaskTest() throws Exception{
		
		when(projectManagerDAO.fetchOneTask(String.valueOf(getTask2().getTaskId()))).thenReturn(getTask2());
		getTask2().setTaskName("Updated Task 2");
		when(projectManagerDAO.persist(getTask2())).thenReturn(getTask2());	
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
	
	@Before
	public void setUpProject() throws Exception {
		Project project1 = getProject1();
		Project project2 = getProject2();
		projectList = new ArrayList<Project>();
		projectList.add(project1);
		projectList.add(project2);
	}
	
	@Test
	public void fetchProjectsTest() throws Exception{
		
		when(projectManagerDAO.fetchAllProjects()).thenReturn(projectList);
		assertThat(projectManagerService.fetchProjects(), hasSize(2));
		assertThat(projectManagerService.fetchProjects(), hasItems(isA(Project.class)));
	}
	
	@Test
	public void getProjectTest() throws Exception{
		
		when(projectManagerDAO.fetchOneProject(String.valueOf(getProject1().getProjectId()))).thenReturn(getProject1());		
	}
	
	@Test
	public void addProjectTest() throws Exception{
		
		when(projectManagerDAO.persist(getProject1())).thenReturn(getProject1());	
	}
	
	@Test
	public void updateProjectTest() throws Exception{
		
		when(projectManagerDAO.fetchOneProject(String.valueOf(getProject2().getProjectId()))).thenReturn(getProject2());
		getProject2().setProject("Updated Project 2");
		when(projectManagerDAO.persist(getProject2())).thenReturn(getProject2());
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
	
	@Before
	public void setUpUser() throws Exception {
		User user1 = getUser1();
		User user2 = getUser2();
		userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
	}
	
	@Test
	public void fetchUsersTest() throws Exception{
		
		when(projectManagerDAO.fetchAllUsers()).thenReturn(userList);
		assertThat(projectManagerService.fetchUsers(), hasSize(2));
		assertThat(projectManagerService.fetchUsers(), hasItems(isA(User.class)));	
	}
	
	@Test
	public void getUserTest() throws Exception{
		
		when(projectManagerDAO.fetchOneUser(String.valueOf(getUser1().getUserId()))).thenReturn(getUser1());		
	}
	
	@Test
	public void addUserTest() throws Exception{
		
		when(projectManagerDAO.persist(getUser1())).thenReturn(getUser1());	
	}
	
	@Test
	public void updateUserTest() throws Exception{
		
		when(projectManagerDAO.fetchOneUser(String.valueOf(getUser2().getUserId()))).thenReturn(getUser2());
		getUser2().setFirstName("Updated User 2");
		when(projectManagerDAO.persist(getUser1())).thenReturn(getUser1());		
	}
	
	public User getUser1() {
		User user = new User();
		user.setEmpId(123);
		user.setFirstName("User 1");
		user.setLastName("Test");
		user.setUserId(1);
		user.setUserProjectId(1);
		user.setUserTaskId(1);
		return user;
	}
	
	public User getUser2() {
		User user = new User();
		user.setEmpId(321);
		user.setFirstName("User 2");
		user.setLastName("Test");
		user.setUserId(2);
		user.setUserProjectId(2);
		user.setUserTaskId(2);
		return user;
	}
}
