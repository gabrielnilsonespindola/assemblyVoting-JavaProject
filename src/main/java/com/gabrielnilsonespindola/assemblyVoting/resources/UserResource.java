package com.gabrielnilsonespindola.assemblyVoting.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielnilsonespindola.assemblyVoting.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User maria = new User("1" , "Maria" , "SIM");
		User gabriel = new User("2" , "gabriel" ,"NAO");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria,gabriel));
		return ResponseEntity.ok().body(list);
		
	}

}
