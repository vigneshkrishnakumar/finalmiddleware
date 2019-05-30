/**
 * 
 */
package com.cts.sba.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.sba.projectmanager.bean.User;

/**
 * @author Admin
 *
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE LOWER(u.userProjectId) = LOWER(:projectId)")
	public User findByProject(@Param("projectId") String projectId);
}
