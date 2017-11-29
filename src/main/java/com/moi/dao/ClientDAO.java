package com.moi.dao;

import java.util.List;

import com.moi.entity.Client;

public interface ClientDAO {

	List<Client> getAllClients();
	Client getClientById(int clientId);
	void addClient(Client client);
	void updateClient(Client client);
	void deleteClient(int clientId);
	
}
