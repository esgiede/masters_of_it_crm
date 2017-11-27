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

	public ProjectsHasEmployees getPheById(int pheId) {
		return entityManager.find(ProjectsHasEmployees.class, pheId);
	}

	public void updateProjectHasEmployees(ProjectsHasEmployees phe) {
		ProjectsHasEmployees phes = getPheById(phe.getPheId());
		phes.setEmployeeId(phe.getEmployeeId());
		phes.setProjectId(phe.getProjectId());
		entityManager.flush();
		
	}

	public void deleteProjectsHasEmployees(int pheId) {
		entityManager.remove(getPheById(pheId));
	}

	public boolean projectExist(int projectId, int employeeId) {
		String hql = "FROM ProjectsHasEmployees as phe WHERE phe.projectId = ? and phe.employeeId = ?";
		int count = entityManager.createQuery(hql).setParameter(1, projectId).setParameter(2, employeeId).getResultList().size();
		return count > 0 ? true : false;
	}
	
}
