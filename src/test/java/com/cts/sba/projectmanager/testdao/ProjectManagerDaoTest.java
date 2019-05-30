/**
 * 
 */
package com.cts.sba.projectmanager.testdao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalTo;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.sba.projectmanager.ProjectmanagerApplicationTests;
import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.dao.ProjectManagerDAO;

/**
 * @author 269012
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ContextConfiguration(classes = {ProjectmanagerApplicationTests.class})
public class ProjectManagerDaoTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProjectManagerDAO projectManagerDAO;
	
	@Test
	public void getParentTaskTest() throws Exception{
		
		entityManager.persist(getParent1());
		ParentTask parent = projectManagerDAO.fetchOneParent("1");
		assertThat(parent, hasProperty("parentTask", equalTo("Parent 1")));
	}
	
	public ParentTask getParent1() {
		ParentTask parentTask = new ParentTask();
//		parentTask.setParentId(1);
		parentTask.setParentTask("Parent 1");
		return parentTask;
	}
	
	public ParentTask getParent2() {
		ParentTask parentTask = new ParentTask();
//		parentTask.setParentId(2);
		parentTask.setParentTask("Parent 2");
		return parentTask;
	}
	
	@Test
	public void getTaskTest() throws Exception{
		
		entityManager.persist(getTask1());
		Task task = projectManagerDAO.fetchOneTask("1");
		assertThat(task, hasProperty("taskName", equalTo("Task 1")));
	}
	
	public Task getTask1() {
		Task task = new Task();
	//	task.setTaskId(1);
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
	//	task.setTaskId(2);
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
	
	@Test
	public void getProjectTest() throws Exception{
		
		entityManager.persist(getProject1());
		Project project = projectManagerDAO.fetchOneProject("1");
		assertThat(project, hasProperty("project", equalTo("Project 1")));	
	}
	
	public Project getProject1() {
		Project project = new Project();
		project.setEndDate(new Date());
		project.setPriority(12);
		project.setProject("Project 1");
//		project.setProjectId(1);
		project.setStartDate(new Date());
		return project;
	}
	
	public Project getProject2() {
		Project project = new Project();
		project.setEndDate(new Date());
		project.setPriority(12);
		project.setProject("Project 2");
	//	project.setProjectId(2);
		project.setStartDate(new Date());
		return project;
	}
	
	@Test
	public void getUserTest() throws Exception{
		
		entityManager.persist(getUser1());
		User user = projectManagerDAO.fetchOneUser("1");
		assertThat(user, hasProperty("firstName", equalTo("User 1")));		
	}
	@After
	public void cleanup() {
	this.entityManager.clear();
	}
	
	public User getUser1() {
		User user = new User();
		user.setEmpId(123);
		user.setFirstName("User 1");
		user.setLastName("Test");
	//	user.setUserId(1);
		user.setUserProjectId(1);
		user.setUserTaskId(1);
		return user;
	}
	
	public User getUser2() {
		User user = new User();
		user.setEmpId(321);
		user.setFirstName("User 2");
		user.setLastName("Test");
//		user.setUserId(2);
		user.setUserProjectId(2);
		user.setUserTaskId(2);
		return user;
	}
}
