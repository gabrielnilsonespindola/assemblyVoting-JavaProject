package com.gabrielnilsonespindola.assemblyVoting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielnilsonespindola.assemblyVoting.repository.AgendaRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.UserRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
				
	}
	
	

}
