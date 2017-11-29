package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Client;
import com.moi.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		Client client = clientService.getClientById(id);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	@GetMapping("clients")
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> list = clientService.getAllClients();
		return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
		
	}
	@PostMapping("clients")
	public ResponseEntity<Void> addCLient(@RequestBody Client client, UriComponentsBuilder builder) {
		clientService.addClient(client);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getClientId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("clients")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		clientService.updateClient(client);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}
	@DeleteMapping("clients/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
