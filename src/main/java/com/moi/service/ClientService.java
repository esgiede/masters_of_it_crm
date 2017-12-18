package com.moi.service;

import java.util.List;

import com.moi.entity.Client;

public interface ClientService {
    List<Client> getAllClients();
	Client getClientById(Long id);
	void addClient(Client client);
	void updateClient(Client client, Long id);
	void deleteClient(Long id);
}
