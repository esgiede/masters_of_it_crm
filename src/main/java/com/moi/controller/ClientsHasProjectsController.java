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

import com.moi.entity.ClientsHasProjects;
import com.moi.service.ClientsHasProjectsService;

@Controller
public class ClientsHasProjectsController {

	@Autowired
	private ClientsHasProjectsService clientsHasProjectsService;
	
	@GetMapping("chp/{id}")
	public ResponseEntity<ClientsHasProjects> getChpById(@PathVariable("id") Integer id) {
		ClientsHasProjects chp = clientsHasProjectsService.getChpById(id);
		return new ResponseEntity<ClientsHasProjects>(chp, HttpStatus.OK);
	}
	
	@GetMapping("chp")
	public ResponseEntity<List<ClientsHasProjects>> getAllChp() {
		List<ClientsHasProjects> list = clientsHasProjectsService.getAllChp();
		return new ResponseEntity<List<ClientsHasProjects>>(list, HttpStatus.OK);
		
	}
	
	@PostMapping("chp")
	public ResponseEntity<Void> addClientsHasProjects(@RequestBody ClientsHasProjects chp, UriComponentsBuilder builder) {
		boolean flag = clientsHasProjectsService.addClientsHasProjects(chp);
        if (flag == false) {
	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/chp/{id}").buildAndExpand(chp.getChpId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("chp")
	public ResponseEntity<ClientsHasProjects> updateClient(@RequestBody ClientsHasProjects chp) {
		clientsHasProjectsService.updateClientsHasProjects(chp);
		return new ResponseEntity<ClientsHasProjects>(chp, HttpStatus.OK);
	}
	@DeleteMapping("chp/{id}")
	public ResponseEntity<Void> deleteClientsHasProjects(@PathVariable("id") Integer id) {
		clientsHasProjectsService.deleteClientsHasProjects(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
