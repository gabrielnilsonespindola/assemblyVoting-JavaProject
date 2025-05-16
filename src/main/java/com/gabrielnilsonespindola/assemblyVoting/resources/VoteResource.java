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

@RestController
@RequestMapping(value = "/votes")
public class VoteResource {

	@Autowired
	private VoteService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<VoteDTO>> findAll() {
		List<Vote> list = service.findAll();
		List<VoteDTO> listDto = list.stream().map(x -> new VoteDTO(x)).collect(Collectors.toList());
	    return ResponseEntity.ok().body(listDto);   

	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<VoteDTO> findById(@PathVariable String id) {
		Vote obj = service.findById(id);
		return ResponseEntity.ok().body(new VoteDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody VoteDTO objDto) {        //GENERICO
		Vote obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/init")
	public ResponseEntity<Void> registerVote(@RequestBody VoteDTO dto) {
	    Vote obj = service.fromDTO(dto);
	    obj = service.registerVote(obj); 
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	    return ResponseEntity.created(uri).build();
	}
	

}
