package com.moi.service;

import java.util.List;

import com.moi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.entity.Client;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository){
		this.clientRepository = clientRepository;
	}
	public List<Client> getAllClients(){ return clientRepository.findAll(); }
	public Client getClientById(Long id) {
		return clientRepository.findOne(id);
	}
	public void addClient(Client client) { clientRepository.save(client); }
	public void updateClient(Client client) {
		clientRepository.save(client);
	}
	public void deleteClient(Long id) {
		clientRepository.delete(id);
	}



	
}
