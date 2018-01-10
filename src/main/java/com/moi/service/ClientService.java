package com.moi.service;

import java.util.List;

import com.moi.entity.Client;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;

public interface ClientService {
    List<Client> getAllClients();
	Client getClientById(Long id) throws ObjectNotFoundException;
	void addClient(Client client) throws ObjectAlreadyExistException;
	void updateClient(Client client, Long id) throws ObjectNotFoundException;
	void deleteClient(Long id) throws ObjectNotFoundException, ObjectDeletingException;
	boolean clientExist(Client client);
}
