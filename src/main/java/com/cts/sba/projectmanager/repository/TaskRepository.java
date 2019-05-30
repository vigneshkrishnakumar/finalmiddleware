/**
 * 
 */
package com.cts.sba.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.sba.projectmanager.bean.Task;

/**
 * @author Admin
 *
 */
@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("SELECT t FROM Task t WHERE LOWER(t.projectId) = LOWER(:projectId)")
	public List<Task> findByProject(@Param("projectId") String projectId);
}
