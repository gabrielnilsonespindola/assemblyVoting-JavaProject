package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.dto.VoteDTO;
import com.gabrielnilsonespindola.assemblyVoting.services.VoteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/votes")
public class VoteResource {

	@Autowired
	private VoteService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VoteDTO>> findAll() {
		log.info("Inicio do Metodo findAll");
		List<Vote> list = service.findAll();
		log.info("Chamada da lista de usuarios");
		List<VoteDTO> listDto = list.stream().map(x -> new VoteDTO(x)).collect(Collectors.toList());
		log.info("Retorno lista de usuarios");
		log.info("Final do metodo findAll");
		return ResponseEntity.ok().body(listDto);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VoteDTO> findById(@PathVariable String id) {
		log.info("Inicio do Metodo findById");
		log.info("Buscando User por Id {}", id);
		Vote obj = service.findById(id);
		log.info("Retorno metodo findById, {}",id);
		log.info("Final do metodo findById");
		return ResponseEntity.ok().body(new VoteDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> registerVote(@RequestBody VoteDTO dto) {
		log.info("Inicio do Metodo registerVote");
		Vote obj = service.fromDTO(dto);
		log.info("Chamada fromDTO");
		obj = service.registerVote(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		log.info("Retorno metodo registerVote {}",uri);
		log.info("Final do metodo registerVote");
		return ResponseEntity.created(uri).build();
	}

}
