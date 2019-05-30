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

import com.cts.sba.projectmanager.bean.User;
import com.cts.sba.projectmanager.service.ProjectManagerService;

/**
 * Controller class for Project Manager Application
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping({"/user"})
public class UserController {

	@Autowired
	private ProjectManagerService projectManagerService;
	
	Logger logger = LoggerFactory.getLogger(ParentTaskController.class);
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		User newUser = null;
		try {
			newUser = projectManagerService.addUser(user);
			logger.info(messageSource.getMessage("task.added", new Object[0], null));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return newUser;
	}
	
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		User dbUser = null;
		try {
			dbUser = projectManagerService.getUser(id);
			logger.info("Existing task retrieved");
			if(dbUser != null) {
				dbUser.setEmpId(user.getEmpId());
				dbUser.setFirstName(user.getFirstName());
				dbUser.setLastName(user.getLastName());
				dbUser.setUserProjectId(user.getUserProjectId());
				dbUser.setUserTaskId(user.getUserTaskId());
				dbUser = projectManagerService.addUser(dbUser);
				logger.info(messageSource.getMessage("task.updated", new Object[0], Locale.US));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return dbUser;
	}
	
	@GetMapping("/fetchUsers")
	public List<User> fetchUsers() {
		List<User> userList = null;
		try {
			userList = projectManagerService.fetchUsers();
			logger.info(messageSource.getMessage("tasks.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return userList;
	}
	
	@GetMapping("/getUser/{userId}")
	public User getUser(@PathVariable String userId) {
		User user = null;
		try {
			user = projectManagerService.getUser(userId);
			logger.info(messageSource.getMessage("task.fetched", new Object[0], Locale.US));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} 
		
		return user;
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public int deleteUser(@PathVariable String userId) {
		User user = null;
		int returnCode = 0;
		try {
			user = projectManagerService.getUser(userId);
			projectManagerService.deleteUser(user);
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
