package com.cts.sba.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.sba.projectmanager.bean.Project;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
