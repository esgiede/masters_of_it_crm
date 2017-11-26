package com.moi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moi.entity.Project;

@Transactional
@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Project> getAllProjects() {
		String hql = "FROM Project as pr ORDER BY pr.projectId";
		return (List<Project>) entityManager.createQuery(hql).getResultList();
	}
	
	public void addProject(Project project) {
		entityManager.persist(project);
	}
	
}
