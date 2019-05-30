/**
 * 
 */
package com.cts.sba.projectmanager.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.repository.ParentTaskRepository;
import com.cts.sba.projectmanager.repository.ProjectRepository;
import com.cts.sba.projectmanager.repository.TaskRepository;
import com.cts.sba.projectmanager.repository.UserRepository;

/**
 * @author Admin
 *
 */
@Repository("projectManagerDAO")
public class ProjectManagerDAO {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;

	public ParentTask persist(ParentTask parentTask) {
		ParentTask newParentTask = parentTaskRepository.save(parentTask);
		return newParentTask;
	}
	
	public List<ParentTask> fetchAllParents() {
		List<ParentTask> parentTasks = parentTaskRepository.findAll();
		return parentTasks;
	}
	
	public ParentTask fetchOneParent(String parentId) {
		Optional<ParentTask> dbParentTask = parentTaskRepository.findById(Integer.parseInt(parentId));
		if(dbParentTask.isPresent()) {
			return dbParentTask.get();
		}
		return null;
	}
	
	public Task persist(Task task) {
		Task newTask = taskRepository.save(task);
		return newTask;
	}
	
	public List<Task> fetchAllTasks() {
		List<Task> tasks = taskRepository.findAll();
		return tasks;
	}
	
	public Task fetchOneTask(String taskId) {
		Optional<Task> dbTask = taskRepository.findById(Integer.parseInt(taskId));
		if(dbTask.isPresent()) {
			return dbTask.get();
		}
		return null;
	}
	
	public List<Task> fetchProjectTasks(String projectId) {
		List<Task> dbTasks = taskRepository.findByProject(projectId);
		return dbTasks;
	}
	
	public void delete(Task task) {
		taskRepository.delete(task);	
	}
	
	public Project persist(Project project) {
		Project newProject = projectRepository.save(project);
		return newProject;
	}
	
	public List<Project> fetchAllProjects() {
		List<Project> projects = projectRepository.findAll();
		return projects;
	}
	
	public Project fetchOneProject(String projectId) {
		Optional<Project> dbProject = projectRepository.findById(Integer.parseInt(projectId));
		if(dbProject.isPresent()) {
			return dbProject.get();
		}
		return null;
	}
	
	public void delete(Project project) {
		projectRepository.delete(project);	
	}
	
	public User persist(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	public List<User> fetchAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public User fetchOneUser(String userId) {
		Optional<User> dbUser = userRepository.findById(Integer.parseInt(userId));
		if(dbUser.isPresent()) {
			return dbUser.get();
		}
		return null;
	}
	
	public User fetchOneUserByProject(String projectId) {
		User dbUser = userRepository.findByProject(projectId);
		return dbUser;
	}

}
