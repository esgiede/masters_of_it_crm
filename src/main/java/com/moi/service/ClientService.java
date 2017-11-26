package com.moi.service;

import java.util.List;

import com.moi.entity.Client;

public interface ClientService {
	List<Client> getAllClients();
	boolean addClient(Client client);
}
