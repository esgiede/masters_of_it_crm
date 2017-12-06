package com.moi.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.entity.Client;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository){
		this.clientRepository = clientRepository;
	}
	@Override
	public List<Client> getAllClients(){
		List<Client> list = new LinkedList<>();
		for(Client clt : clientRepository.findAll())
		{
			list.add(clt);
		}
		return list;
	}
	@Override
	public Client getClientById(Long id) {
		return clientRepository.findOne(id);
	}
	@Override
	public void addClient(Client client) {
		clientRepository.save(client);
	}
	@Override
	public void updateClient(Client client) {
		clientRepository.save(client);
	}
	@Override
	public void deleteClient(Long id) {
		clientRepository.delete(id);
	}



	
}
