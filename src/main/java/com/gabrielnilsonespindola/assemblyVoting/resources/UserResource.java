package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.dto.UserDTO;
import com.gabrielnilsonespindola.assemblyVoting.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		log.info("Inicio do Metodo findAll");
		List<User> list = service.findAll();
		log.info("Chamada da lista de usuarios");
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		log.info("Retorno lista de usuarios");
		log.info("Final do metodo");
		return ResponseEntity.ok().body(listDto);		
		}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		log.info("Inicio do Metodo findById");
		log.info("Buscando User por Id {}", id);
		User obj = service.findById(id);
		log.info("Retorno metodo findById, {}",id);
		log.info("Final do metodo findById");
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		log.info("Inicio do Metodo insert");
		User obj = service.fromDTO(objDto);
		log.info("Chamada fromDTO");
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		log.info("Retorno metodo insert {}",uri);
		log.info("Final do metodo insert");
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}/agendas", method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> findAgendas(@PathVariable String id) { 
		log.info("Inicio do Metodo findAgendas");
		User obj = service.findById(id);
		log.info("Retorno agendas por Id User {}",id);
		log.info("Final do metodo findAgendas");
		return ResponseEntity.ok().body(obj.getAgendas());
	}

}
