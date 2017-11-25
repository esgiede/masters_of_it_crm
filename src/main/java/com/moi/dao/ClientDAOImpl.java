package com.moi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.moi.entity.Client;

@Transactional
@Repository
public class ClientDAOImpl implements ClientDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {
		String hql = "FROM Client as clt ORDER BY clt.clientId";
		return (List<Client>) entityManager.createQuery(hql).getResultList();
	}

}
