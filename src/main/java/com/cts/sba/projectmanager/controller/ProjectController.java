/**
 * 
 */
package com.cts.sba.projectmanager.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.bean.Project;
import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.service.ProjectManagerService;

/**
 * Controller class for Project Manager Application
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping({"/project"})
public class ProjectController {

	@Autowired
	private ProjectManagerService projectManagerService;
	
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@PostMapping("/saveProject")
	public Project saveProject(@RequestBody Project project) {
		Project newProject = null;
		User user = null;
		try {
			newProject = projectManagerService.addProject(project);
			user = projectManagerService.getUser(String.valueOf(project.getManagerId()));
			if(user != null) {
				user.setUserProjectId(newProject.getProjectId());
				projectManagerService.addUser(user);
			}
			logger.info(messageSource.getMessage("task.added", new Object[0], null));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return newProject;
	}
	
	@PutMapping("/updateProject/{id}")
	public Project updateProject(@PathVariable String id, @RequestBody Project project) {
		Project dbProject = null;
		User user = null;
		try {
			dbProject = projectManagerService.getProject(id);
			user = projectManagerService.getUser(String.valueOf(project.getManagerId()));
			logger.info("Existing task retrieved");
			if(dbProject != null) {
				dbProject.setProject(project.getProject());
				dbProject.setPriority(project.getPriority());
				dbProject.setStartDate(project.getStartDate());
				dbProject.setEndDate(project.getEndDate());
				dbProject = projectManagerService.addProject(dbProject);
				if(user != null) {
					user.setUserProjectId(dbProject.getProjectId());
					projectManagerService.addUser(user);
				}
				logger.info(messageSource.getMessage("task.updated", new Object[0], Locale.US));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return dbProject;
	}
	
	@GetMapping("/fetchProjects")
	public List<Project> fetchProjects() {
		List<Project> projectList = null;
		ParentTask parentTask = null;
		User user = null;
		List<Task> tasks = null;
		int count = 0;
		try {
			projectList = projectManagerService.fetchProjects();
			for(Project dbProject : projectList) {
				tasks = projectManagerService.getProjectTask(String.valueOf(dbProject.getProjectId()));
				for(Task task : tasks) {
					parentTask = projectManagerService.getParentTask(String.valueOf(task.getParentId()));
					task.setParentTask(parentTask);
					if(task.getStatus().equalsIgnoreCase("Completed")) {
						count++;
					}
				}
				dbProject.setTasks(tasks);
				dbProject.setCompTasks(count);
				user = projectManagerService.getUserByProject(String.valueOf(dbProject.getProjectId()));
				if(user != null) {
					dbProject.setManagerId(user.getUserId());
					dbProject.setManagerName(user.getFirstName());
				}
			}
			logger.info(messageSource.getMessage("tasks.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return projectList;
	}
	
	@GetMapping("/getProject/{projectId}")
	public Project getProject(@PathVariable String projectId) {
		Project project = null;
		ParentTask parentTask = null;
		User user = null;
		List<Task> tasks = null;
		int count = 0;
		try {
			project = projectManagerService.getProject(projectId);
			if(project != null) {
				tasks = projectManagerService.getProjectTask(String.valueOf(project.getProjectId()));
				for(Task task : tasks) {
					parentTask = projectManagerService.getParentTask(String.valueOf(task.getParentId()));
					task.setParentTask(parentTask);
					if(task.getStatus().equalsIgnoreCase("Completed")) {
						count++;
					}
				}
				project.setTasks(tasks);
				project.setCompTasks(count);
				user = projectManagerService.getUserByProject(projectId);
				if(user != null) {
					project.setManagerId(user.getUserId());
					project.setManagerName(user.getFirstName());
				}
			}
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return project;
	}
	
	@DeleteMapping("/deleteProject/{projectId}")
	public int deleteProject(@PathVariable String projectId) {
		User user = null;
		Project project = null;
		int returnCode = 0;
		List<Task> tasks = null;
		try {
			project = projectManagerService.getProject(projectId);
			if(project != null) {
				tasks = projectManagerService.getProjectTask(String.valueOf(project.getProjectId()));
				projectManagerService.deleteProject(project);
				for(Task task : tasks) {
					projectManagerService.deleteTask(task);
				}
				user = projectManagerService.getUserByProject(projectId);
				if(user != null) {
					user.setUserProjectId(0);
					projectManagerService.addUser(user);
				}
			}
			returnCode = 200;
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			returnCode = -1;
		} 
		
		return returnCode;
	}
	
}
