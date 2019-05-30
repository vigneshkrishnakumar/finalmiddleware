/**
 * 
 */
package com.cts.sba.projectmanager.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import com.cts.sba.projectmanager.bean.User.UserId;

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "User_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@NotNull
    @Size(max = 100)
	@Column(name = "First_Name")
	private String firstName;
	@NotNull
    @Size(max = 100)
	@Column(name = "Last_Name")
	private String lastName;
	@NotNull
	@Column(name = "Employee_ID")
	private int empId;
	@Column(name = "Task_ID")
	private int userTaskId;
	@Column(name = "Project_ID")
	private int userProjectId;
	
	/*public static class UserId implements Serializable {

		 private static final long serialVersionUID = 1L;   
		 private int userTaskId;
	     private int userProjectId;

	        public UserId() {}

	        public UserId(int userTaskId, int userProjectId) {
	            this.userTaskId = userTaskId;
	            this.userProjectId = userProjectId;
	        }

	        @Override
	        public boolean equals(Object o) {

	            if (o == this) {
	                return true;
	            }
	            if (!(o instanceof User)) {
	                return false;
	            }
	            User user = (User) o;
	            return Objects.equals(userTaskId, user.getUserTaskId()) &&
	                   Objects.equals(userProjectId, user.getUserProjectId());
	        }

	        @Override
	        public int hashCode() {
	            return Objects.hash(userTaskId, userProjectId);
	        }
	   }*/
	
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
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * @return the userTaskId
	 */
	public int getUserTaskId() {
		return userTaskId;
	}
	/**
	 * @param userTaskId the userTaskId to set
	 */
	public void setUserTaskId(int userTaskId) {
		this.userTaskId = userTaskId;
	}
	/**
	 * @return the userProjectId
	 */
	public int getUserProjectId() {
		return userProjectId;
	}
	/**
	 * @param userProjectId the userProjectId to set
	 */
	public void setUserProjectId(int userProjectId) {
		this.userProjectId = userProjectId;
	}
	
	
}
