package com.moi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.dao.ClientDAO;
import com.moi.entity.Client;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDAO clientDAO;

	public List<Client> getAllClients() {
		return clientDAO.getAllClients();
	}

	public synchronized boolean addClient(Client client) {
		if (clientDAO.clientExist(client.getName())) {
			return false;
		} else {
			clientDAO.addClient(client);
			return true;
		}
	}

	public Client getClientById(int clientId) {
		Client obj = clientDAO.getClientById(clientId);
		return obj;
	}

	public void updateClient(Client client) {
		clientDAO.updateClient(client);
	}

	public void deleteClient(int clientId) {
		clientDAO.deleteClient(clientId);
	}
	
}
