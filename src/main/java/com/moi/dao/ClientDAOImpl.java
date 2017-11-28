package com.moi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moi.entity.Client;

@Transactional
@Repository
public class ClientDAOImpl implements ClientDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Client> getAllClients() {
		String hql = "FROM Client as clt ORDER BY clt.clientId";
		return (List<Client>) entityManager.createQuery(hql).getResultList();
	}

	public Client getClientById(int clientId) {
		return entityManager.find(Client.class, clientId);
	}

	public void addClient(Client client) {
		entityManager.persist(client);
	}

	public void updateClient(Client client) {
		Client clt = getClientById(client.getClientId());
		clt.setName(client.getName());
		clt.setAddress(client.getAddress());
		clt.setContact(client.getContact());
		clt.setPhone(client.getPhone());
		entityManager.flush();
	}

	public void deleteClient(int clientId) {
		entityManager.remove(getClientById(clientId));

	}

	public boolean clientExist(String name) {
		String hql = "FROM Client as clt WHERE clt.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name).getResultList().size();
		return count > 0 ? true : false;
	}
	
}
