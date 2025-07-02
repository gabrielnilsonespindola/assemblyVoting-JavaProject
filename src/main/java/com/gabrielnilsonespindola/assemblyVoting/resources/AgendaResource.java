package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.dto.AgendaDTO;
import com.gabrielnilsonespindola.assemblyVoting.services.AgendaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/agendas")
public class AgendaResource {

	@Autowired
	private AgendaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> findAll() {
		log.info("Inicio do Metodo findAll");
		List<Agenda> list = service.findAll();
		log.info("Chamada retorno da lista de usuarios");		
		log.info("Final do metodo findAll");
		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Agenda> findById(@PathVariable String id) {
		log.info("Inicio do Metodo findById");
		log.info("Buscando User por Id {}", id);
		Agenda obj = service.findById(id);
		log.info("Chamada retorno findById");
		log.info("Final do metodo findById");
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertFormulary(@RequestBody AgendaDTO objDto) {
		log.info("Inicio do Metodo insertFormulary");
		Agenda obj = service.fromDTO(objDto);
		log.info("Chamada fromDTO");
		obj = service.insertFormulary(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		log.info("Retorno metodo insertFormulary {}",uri);		
		log.info("Final do metodo insertFormulary");
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		log.info("Inicio do Metodo delete");
		service.delete(id);
		log.info("Chamada delete");		
		log.info("Final do metodo delete");
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody AgendaDTO objDto, @PathVariable String id) {
		log.info("Inicio do Metodo update");
		Agenda obj = service.fromDTO(objDto);
		log.info("Chamada fromDTO");
		obj.setId(id);
		obj = service.update(obj);
		log.info("Retorno metodo update {}",obj.getId());		
		log.info("Final do metodo update");
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}/votes", method = RequestMethod.GET)
	public ResponseEntity<List<Vote>> findVotes(@PathVariable String id) { 
		log.info("Inicio do Metodo findVotes");
		Agenda obj = service.findById(id);
		log.info("Retorno metodo findVotes por agenda {}",obj.getVotes());					
		log.info("Final do metodo findVotes");
		return ResponseEntity.ok().body(obj.getVotes());
	}

}
