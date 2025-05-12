package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.services.AgendaService;

@RestController
@RequestMapping(value = "/agendas")
public class AgendaResource {

	@Autowired
	private AgendaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> findAll() {
		List<Agenda> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<Agenda> findById(@PathVariable String id) {
		Agenda obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	

}
