/**
 * 
 */
package com.cts.sba.projectmanager.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "Project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Project_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectId;
	@NotNull
    @Size(max = 100)
	@Column(name = "Project")
	private String project;
	@NotNull
	@Column(name = "Start_Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@NotNull
	@Column(name = "End_Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	@NotNull
	@Column(name = "Priority")
	private int priority;
	@Transient
	private List<Task> tasks;
	@Transient
	private int managerId;
	@Transient
	private String managerName;
	@Transient
	private int compTasks;
	
	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}
	/**
	 * @return the task
	 */
	public List<Task> getTasks() {
		return tasks;
	}
	/**
	 * @param task the task to set
	 */
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	/**
	 * @return the managerId
	 */
	public int getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return the managerName
	 */
	public String getManagerName() {
		return managerName;
	}
	/**
	 * @param managerName the managerName to set
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	/**
	 * @return the compTasks
	 */
	public int getCompTasks() {
		return compTasks;
	}
	/**
	 * @param compTasks the compTasks to set
	 */
	public void setCompTasks(int compTasks) {
		this.compTasks = compTasks;
	}

}
