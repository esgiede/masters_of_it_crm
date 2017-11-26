package com.moi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.moi.entity.Client;
import com.moi.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("clients")
	public ResponseEntity<List<Client>> getAllClients() {
		List<Client> list = clientService.getAllClients();
		return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("client")
	public ResponseEntity<Void> addCLient(@RequestBody Client client, UriComponentsBuilder builder) {
                boolean flag = clientService.addClient(client);
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/client/{id}").buildAndExpand(client.getClientId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
