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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.sba.projectmanager.bean.Task;
import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.service.ProjectManagerService;

/**
 * Controller class for Project Manager Application
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping({"/task"})
public class TaskController {

	@Autowired
	private ProjectManagerService projectManagerService;
	
	Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@PostMapping("/saveTask")
	public Task saveTask(@RequestBody Task task) {
		Task newTask = null;
		User user = null;
		try {
			newTask = projectManagerService.addTask(task);
			user = projectManagerService.getUser(String.valueOf(task.getUserId()));
			if(user != null) {
				user.setUserProjectId(newTask.getProjectId());
				user.setUserTaskId(newTask.getTaskId());
				projectManagerService.addUser(user);
			}
			logger.info(messageSource.getMessage("task.added", new Object[0], null));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return newTask;
	}
	
	@PutMapping("/updateTask/{id}")
	public Task updateTask(@PathVariable String id, @RequestBody Task task) {
		Task dbTask = null;
		User user = null;
		try {
			dbTask = projectManagerService.getTask(id);
			logger.info("Existing task retrieved");
			if(dbTask != null) {
				dbTask.setTaskName(task.getTaskName());
				dbTask.setPriority(task.getPriority());
				dbTask.setStartDate(task.getStartDate());
				dbTask.setEndDate(task.getEndDate());
				dbTask.setStatus(task.getStatus());
				dbTask = projectManagerService.addTask(dbTask);
				user = projectManagerService.getUser(String.valueOf(task.getUserId()));
				if(user != null) {
					user.setUserProjectId(dbTask.getProjectId());
					user.setUserTaskId(dbTask.getTaskId());
					projectManagerService.addUser(user);
				}
				logger.info(messageSource.getMessage("task.updated", new Object[0], Locale.US));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return dbTask;
	}
	
	@GetMapping("/fetchTasks")
	public List<Task> fetchTasks() {
		List<Task> taskList = null;
		try {
			taskList = projectManagerService.fetchTasks();
			logger.info(messageSource.getMessage("tasks.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return taskList;
	}
	
	@GetMapping("/getTask/{taskId}")
	public Task getTask(@PathVariable String taskId) {
		Task task = null;
		User user = null;
		try {
			task = projectManagerService.getTask(taskId);
			user = projectManagerService.getUserByProject(String.valueOf(task.getProjectId()));
			if(user != null) {
				task.setUserId(user.getUserId());
			}
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return task;
	}
	
}
