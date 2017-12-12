package com.moi.controller;

import java.util.List;

import com.moi.entity.dto.ClientCreateDTO;
import com.moi.entity.dto.ClientUpdateDTO;
import com.moi.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Client;
import com.moi.service.ClientService;

@Controller
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> list = clientService.getAllClients();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
		Client client = clientService.getClientById(id);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Void> addClient(@RequestBody @DTO(ClientCreateDTO.class) Client client, UriComponentsBuilder builder) {
		clientService.addClient(client);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<Client> updateClient(@RequestBody @DTO(ClientUpdateDTO.class) Client client) {
		clientService.updateClient(client);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
		clientService.deleteClient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
}
