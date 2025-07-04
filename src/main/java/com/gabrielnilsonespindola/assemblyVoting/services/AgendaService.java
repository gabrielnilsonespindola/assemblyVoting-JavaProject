package com.gabrielnilsonespindola.assemblyVoting.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.dto.AgendaDTO;
import com.gabrielnilsonespindola.assemblyVoting.repository.AgendaRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repo;

	public List<Agenda> findAll() {
		return repo.findAll();
	}

	public Agenda findById(String id) {
		Optional<Agenda> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
	        log.error("Agenda com ID {} não encontrado", id);
	        return new ObjectNotFoundException("Objeto não encontrado");
	    });
	}

	public Agenda insertFormulary(Agenda obj) {
		return repo.insert(obj);
	}

	public Agenda fromDTO(AgendaDTO objDtO) {
		return new Agenda(objDtO.getId(), objDtO.getTitle(), objDtO.getNumber(), objDtO.getDate());

	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public Agenda update(Agenda obj) {
		Agenda newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Agenda newObj, Agenda obj) {
		newObj.setId(obj.getId());
		newObj.setTitle(obj.getTitle());
		newObj.setNumber(obj.getNumber());
		newObj.setDescricao(obj.getDescricao());
		newObj.setDate(obj.getDate());

	}

}
