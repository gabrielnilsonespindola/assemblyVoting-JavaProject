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
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingResultDTO;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingSessionDTO;
import com.gabrielnilsonespindola.assemblyVoting.services.VotingSessionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/sessions")
public class VotingSessionResource {

	@Autowired
	private VotingSessionService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VotingSessionDTO>> findAll() {
		log.info("Inicio do Metodo findAll");
		List<VotingSession> list = service.findAll();
		log.info("Chamada da lista de usuarios");
		List<VotingSessionDTO> listDto = list.stream().map(x -> new VotingSessionDTO(x)).collect(Collectors.toList());
		log.info("Retorno lista de usuarios");
		log.info("Final do metodo findAll");
		return ResponseEntity.ok().body(listDto);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<VotingSessionDTO> findById(@PathVariable String id) {
		log.info("Inicio do Metodo findById");
		log.info("Buscando User por Id {}", id);
		VotingSession obj = service.findById(id);
		log.info("Retorno metodo findById, {}",id);
		log.info("Final do metodo findById");
		return ResponseEntity.ok().body(new VotingSessionDTO(obj));
	}

	@PostMapping
	public ResponseEntity<Void> startSession(@RequestBody VotingSessionDTO objDto) {
		log.info("Inicio do Metodo startSession");
		String agendaId = objDto.getAgenda().getId();
		Optional<Long> duration = Optional.ofNullable(objDto.getDurationInMinutes());
		VotingSession obj = service.startSession(agendaId, duration);
		log.info("Chamada metodo startSession");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		log.info("Retorno metodo registerVote {}",uri);
		log.info("Final do metodo startSession");
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/result/{agendaId}", method = RequestMethod.GET)
	public ResponseEntity<VotingResultDTO> getVotingResult(@PathVariable String agendaId) {
		log.info("Inicio do Metodo getVotingResult");
		VotingResultDTO result = service.getVotingResult(agendaId);
		log.info("Chamada metodo getVotingResult");
		log.info("Final do metodo getVotingResult");
		return ResponseEntity.ok().body(result);
	}

}
