package com.moi.service;

import java.util.List;

import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
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
	public Client getClientById(Long id) throws ObjectNotFoundException {
		if(clientRepository.exists(id)){
			return clientRepository.findOne(id);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
		}
	}
	public void addClient(Client client) throws ObjectAlreadyExistException {
		if(!clientExist(client)){
			clientRepository.save(client);
		}else{
			throw new ObjectAlreadyExistException("Klient o podanej nazwie juz istnieje");
		}
	}
	public void updateClient(Client client, Long id) throws ObjectNotFoundException {
		if(clientRepository.exists(id)){
				client.setId(id);
				clientRepository.save(client);
		}else{
			throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
		}
	}
	public void deleteClient(Long id) throws ObjectNotFoundException, ObjectDeletingException {
		if(clientRepository.exists(id)){
			if(clientRepository.findOne(id).getProject().isEmpty()){
				clientRepository.delete(id);
			}else {
				throw new ObjectDeletingException("Klient posiada przypisane projekty.");
			}
		}else{
			throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
		}
	}
	public boolean clientExist(Client client){
		boolean isExist = false;

		for(Client temp : clientRepository.findAll()){
			if(temp.getName().equalsIgnoreCase(client.getName())){
				isExist = true;
			}
		}
		return isExist;
	}
}
