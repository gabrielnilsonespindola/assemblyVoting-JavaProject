package com.gabrielnilsonespindola.assemblyVoting.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.dto.UserDTO;
import com.gabrielnilsonespindola.assemblyVoting.repository.UserRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> {
	        log.error("User com ID {} não encontrado", id);
	        return new ObjectNotFoundException("Objeto/User não encontrado");
	    });
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public User fromDTO(UserDTO objDtO) {
		return new User(objDtO.getId(), objDtO.getName());
	}

}
