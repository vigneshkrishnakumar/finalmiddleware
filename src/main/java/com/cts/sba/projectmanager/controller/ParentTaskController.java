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

import com.cts.sba.projectmanager.bean.ParentTask;
import com.cts.sba.projectmanager.service.ProjectManagerService;


/**
 * Controller class for Project Manager Application
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping({"/parent"})
public class ParentTaskController {

	@Autowired
	private ProjectManagerService projectManagerService;
	
	Logger logger = LoggerFactory.getLogger(ParentTaskController.class);
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@PostMapping("/saveParentTask")
	public ParentTask saveParentTask(@RequestBody ParentTask task) {
		ParentTask newParentTask = null;
		try {
			newParentTask = projectManagerService.addParentTask(task);
			logger.info(messageSource.getMessage("task.added", new Object[0], null));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return newParentTask;
	}
	
	@PutMapping("/updateParentTask/{id}")
	public ParentTask updateParentTask(@PathVariable String id, @RequestBody ParentTask parentTask) {
		ParentTask dbParentTask = null;
		try {
			dbParentTask = projectManagerService.getParentTask(id);
			logger.info("Existing task retrieved");
			if(dbParentTask != null) {
				dbParentTask.setParentTask(parentTask.getParentTask());
				dbParentTask = projectManagerService.addParentTask(dbParentTask);
				logger.info(messageSource.getMessage("task.updated", new Object[0], Locale.US));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return dbParentTask;
	}
	
	@GetMapping("/fetchParentTasks")
	public List<ParentTask> fetchParentTasks() {
		List<ParentTask> parentList = null;
		try {
			parentList = projectManagerService.fetchParentTasks();
			logger.info(messageSource.getMessage("tasks.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return parentList;
	}
	
	@GetMapping("/getParentTask/{parentId}")
	public ParentTask getParentTask(@PathVariable String parentId) {
		ParentTask parentTask = null;
		try {
			parentTask = projectManagerService.getParentTask(parentId);
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return parentTask;
	}
	
}
