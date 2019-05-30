/**
 * 
 */
package com.cts.sba.projectmanager.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.cts.sba.projectmanager.bean.Task.TaskId;

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "Task")
//@IdClass(TaskId.class)
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Task_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	@Column(name = "Parent_ID")
	private int parentId;
	@Column(name = "Project_ID")
	private int projectId;
	@NotNull
    @Size(max = 100)
	@Column(name = "Task")
	private String taskName;
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
	@NotNull
    @Size(max = 100)
	@Column(name = "Status")
	private String status;
	@Transient
	private ParentTask parentTask;
	@Transient
	private int userId;
	
	 /*public static class TaskId implements Serializable {

		 private static final long serialVersionUID = 1L;   
		 private ParentTask parentId;
	     private Project projectId;

	        public TaskId() {}

	        public TaskId(ParentTask parentId, Project projectId) {
	            this.parentId = parentId;
	            this.projectId = projectId;
	        }

	        @Override
	        public boolean equals(Object o) {

	            if (o == this) {
	                return true;
	            }
	            if (!(o instanceof Task)) {
	                return false;
	            }
	            Task task = (Task) o;
	            return Objects.equals(parentId, task.getParentId()) &&
	                   Objects.equals(projectId, task.getProjectId());
	        }

	        @Override
	        public int hashCode() {
	            return Objects.hash(parentId, projectId);
	        }
	   }*/
	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
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
	 * @return the parentTask
	 */
	public ParentTask getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
