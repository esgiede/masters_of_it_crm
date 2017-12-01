package com.moi.service;

import java.util.List;

import com.moi.entity.Client;

public interface ClientService {
    List<Client> getAllClients();
	Client getClientById(Long clientId);
	void addClient(Client client);
	void updateClient(Client client);
	void deleteClient(Long clientId);
}
