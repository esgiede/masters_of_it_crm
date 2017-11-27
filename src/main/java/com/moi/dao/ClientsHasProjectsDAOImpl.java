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

}
