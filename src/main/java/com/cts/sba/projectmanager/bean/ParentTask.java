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

/**
 * @author Admin
 *
 */
@Entity
@Table(name = "Parent_Task")
public class ParentTask implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Parent_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parentId;
	@Column(name = "Parent_Task")
	private String parentTask;
	
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the parentTask
	 */
	public String getParentTask() {
		return parentTask;
	}
	/**
	 * @param parentTask the parentTask to set
	 */
	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

}
