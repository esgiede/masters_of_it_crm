package com.moi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moi.entity.ClientsHasProjects;

@Transactional
@Repository
public class ClientsHasProjectsDAOImpl implements ClientsHasProjectsDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ClientsHasProjects> getAllChp() {
		String hql = "FROM ClientsHasProjects as chp ORDER BY chp.chpId";
		return (List<ClientsHasProjects>) entityManager.createQuery(hql).getResultList();
	}

	public void addClientsHasProjects(ClientsHasProjects chp) {
		entityManager.persist(chp);
	}

	public ClientsHasProjects getChpById(int chpId) {
		return entityManager.find(ClientsHasProjects.class, chpId);
	}

	public void updateClientsHasProjects(ClientsHasProjects chp) {
		ClientsHasProjects chps = getChpById(chp.getChpId());
		chps.setClient(chp.getClient());
		chps.setProject(chp.getProject());
		entityManager.flush();
	}

	public void deleteClientsHasProjects(int chpId) {
		entityManager.remove(chpId);
		
	}

	public boolean cphExist(int clientId, int projectId) {
		String hql = "FROM ClientsHasProjects as chps WHERE chps.clientId = ? and chps.projectId = ?";
		int count = entityManager.createQuery(hql).setParameter(1, clientId).setParameter(2, projectId).getResultList().size();
		return count > 0 ? true : false;
	}

}
