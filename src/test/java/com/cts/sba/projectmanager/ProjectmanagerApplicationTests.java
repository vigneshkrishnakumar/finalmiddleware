package com.cts.sba.projectmanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.sba.projectmanager.controller.ParentTaskController;
import com.cts.sba.projectmanager.controller.ProjectController;
import com.cts.sba.projectmanager.controller.TaskController;
import com.cts.sba.projectmanager.controller.UserController;
import com.cts.sba.projectmanager.dao.ProjectManagerDAO;
import com.cts.sba.projectmanager.service.ProjectManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.cts.sba.projectmanager")
public class ProjectmanagerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@TestConfiguration
    static class ProjectManagerImplTestContextConfiguration {
  
        /*@Bean
        public ParentTaskController parentTaskController() {
            return new ParentTaskController();
        }
        @Bean
        public TaskController taskController() {
            return new TaskController();
        }
        @Bean
        public ProjectController projectController() {
            return new ProjectController();
        }
        @Bean
        public UserController userController() {
            return new UserController();
        }
        @Bean
        public ProjectManagerService projectManagerService() {
            return new ProjectManagerService();
        }
        @Bean
        public ProjectManagerDAO projectManagerDAOTest() {
            return new ProjectManagerDAO();
        }*/
    }
}
