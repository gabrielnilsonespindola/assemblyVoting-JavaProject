package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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

import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingSessionDTO;
import com.gabrielnilsonespindola.assemblyVoting.services.VotingSessionService;

@RestController
@RequestMapping(value = "/sessions")
public class VotingSessionResource {

	@Autowired
	private VotingSessionService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VotingSessionDTO>> findAll() {
		List<VotingSession> list = service.findAll();
		List<VotingSessionDTO> listDto = list.stream().map(x -> new VotingSessionDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VotingSessionDTO> findById(@PathVariable String id) {
		VotingSession obj = service.findById(id);
		return ResponseEntity.ok().body(new VotingSessionDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST) // GENERICO
	public ResponseEntity<Void> insert(@RequestBody VotingSessionDTO objDto) {
		VotingSession obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PostMapping("/init")
	public ResponseEntity<Void> startSession(@RequestBody VotingSessionDTO objDto) {
		String agendaId = objDto.getAgenda().getId();
		Optional<Long> duration = Optional.ofNullable(objDto.getDurationInMinutes());

		VotingSession obj = service.startSession(agendaId, duration);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
