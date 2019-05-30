/**
 * 
 */
package com.cts.sba.projectmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.dao.ProjectManagerDAO;

/**
 * @author Admin
 *
 */
@Service("projectManagerService")
public class ProjectManagerService {

	@Autowired
	private ProjectManagerDAO projectManagerDAO;
	
	public ParentTask addParentTask(ParentTask parentTask) throws Exception {
		ParentTask newParentTask = new ParentTask();
		newParentTask = projectManagerDAO.persist(parentTask);
		return newParentTask;
	}
	
	public ParentTask getParentTask(String parentId) throws Exception {
		ParentTask dbParentTask = new ParentTask();
		dbParentTask = projectManagerDAO.fetchOneParent(parentId);
		return dbParentTask;
	}
	
	public List<ParentTask> fetchParentTasks() throws Exception {
		List<ParentTask> parentTasks = new ArrayList<ParentTask>();
		parentTasks = projectManagerDAO.fetchAllParents();
		return parentTasks;
	}
	
	public Task addTask(Task task) throws Exception {
		Task newTask = new Task();
		newTask = projectManagerDAO.persist(task);
		return newTask;
	}
	
	public Task getTask(String id) throws Exception {
		Task dbTask = new Task();
		dbTask = projectManagerDAO.fetchOneTask(id);
		return dbTask;
	}
	
	public List<Task> getProjectTask(String projectId) throws Exception {
		
		List<Task> dbTasks = projectManagerDAO.fetchProjectTasks(projectId);
		return dbTasks;
	}
	
	public List<Task> fetchTasks() throws Exception {
		List<Task> tasks = new ArrayList<Task>();
		tasks = projectManagerDAO.fetchAllTasks();
		return tasks;
	}
	
	public void deleteTask(Task task) throws Exception {
		projectManagerDAO.delete(task);
	}
	
	public Project addProject(Project project) throws Exception {
		Project newProject = new Project();
		newProject = projectManagerDAO.persist(project);
		return newProject;
	}
	
	public Project getProject(String id) throws Exception {
		Project dbProject = new Project();
		dbProject = projectManagerDAO.fetchOneProject(id);
		return dbProject;
	}
	
	public List<Project> fetchProjects() throws Exception {
		List<Project> projects = new ArrayList<Project>();
		projects = projectManagerDAO.fetchAllProjects();
		return projects;
	}
	
	public void deleteProject(Project project) throws Exception {
		projectManagerDAO.delete(project);
	}
	
	public User addUser(User user) throws Exception {
		User newUser = new User();
		newUser = projectManagerDAO.persist(user);
		return newUser;
	}
	
	public User getUser(String id) throws Exception {
		User dbUser = new User();
		dbUser = projectManagerDAO.fetchOneUser(id);
		return dbUser;
	}
	
	public User getUserByProject(String id) throws Exception {
		User dbUser = new User();
		dbUser = projectManagerDAO.fetchOneUserByProject(id);
		return dbUser;
	}
	
	public List<User> fetchUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		users = projectManagerDAO.fetchAllUsers();
		return users;
	}
	
	public void deleteUser(User user) throws Exception {
		projectManagerDAO.delete(user);
	}
}
