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

	public Project getProjectById(int projectId) {
		return entityManager.find(Project.class, projectId);
	}

	public void updateProject(Project project) {
		Project pct = getProjectById(project.getProjectId());
		pct.setName(project.getName());
		pct.setStartDate(project.getStartDate());
		pct.setEndDate(project.getEndDate());
		entityManager.flush();
	}

	public void deleteProject(int projectId) {
		entityManager.remove(getProjectById(projectId));
	}
}
