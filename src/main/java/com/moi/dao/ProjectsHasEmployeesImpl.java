package com.moi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moi.entity.ProjectsHasEmployees;

@Transactional
@Repository
public class ProjectsHasEmployeesImpl implements ProjectsHasEmployeesDAO {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<ProjectsHasEmployees> getAllPhe(){
		String hql = "FROM ProjectsHasEmployees as phe ORDER BY phe.pheId";
		return (List<ProjectsHasEmployees>) entityManager.createQuery(hql).getResultList();
	}
	
	public void addProjectsHasEmployees(ProjectsHasEmployees phe) {
		entityManager.persist(phe);
	}
	
}
