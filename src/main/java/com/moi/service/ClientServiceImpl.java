package com.moi.service;

import com.google.common.base.Optional;
import com.moi.entity.Client;
import com.moi.errors.exceptions.ObjectAlreadyExistException;
import com.moi.errors.exceptions.ObjectDeletingException;
import com.moi.errors.exceptions.ObjectNotFoundException;
import com.moi.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClientsByPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client getClientById(Long id) throws ObjectNotFoundException {

        Optional<Client> tempClient = Optional.fromNullable(clientRepository.findOne(id));

        if(tempClient.isPresent()){
            return tempClient.get();
        }else {
            throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
        }
    }

    public void addClient(Client client) throws ObjectAlreadyExistException {
        if (!clientExist(client)) {
            clientRepository.save(client);
        } else {
            throw new ObjectAlreadyExistException("Klient o podanej nazwie juz istnieje");
        }
    }

    public void updateClient(Client client, Long id) throws ObjectNotFoundException {
        Optional<Client> tempClient = Optional.fromNullable(clientRepository.findOne(id));

        if(tempClient.isPresent()){
            tempClient.get().setId(id);
            clientRepository.save(tempClient.get());
        }else{
            throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
        }
    }

    public void deleteClient(Long id) throws ObjectNotFoundException, ObjectDeletingException {
        Optional<Client> tempClient = Optional.fromNullable(clientRepository.findOne(id));

        if(tempClient.isPresent()){
            if(clientRepository.findOne(id).getProject().isEmpty()){
                clientRepository.delete(id);
            }else{
                throw new ObjectDeletingException("Klient posiada przypisane projekty.");
            }
        }else{
            throw new ObjectNotFoundException("Nie znaleziono klienta o podanym Id");
        }
    }

    public boolean clientExist(Client client) {
        boolean isExist = false;

        for (Client temp : clientRepository.findAll()) {
            if (temp.getName().equalsIgnoreCase(client.getName())) {
                isExist = true;
            }
        }
        return isExist;
    }
}
