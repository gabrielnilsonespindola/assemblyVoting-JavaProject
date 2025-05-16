package com.gabrielnilsonespindola.assemblyVoting.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielnilsonespindola.assemblyVoting.repository.AgendaRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.UserRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VotingSessionRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		agendaRepository.deleteAll();   //RESETAR E LIMPAR PARA ENTAO IMPLEMENTAR NOVOS DADOS SEM PROBLEMAS AO "DB"
		voteRepository.deleteAll();
		
		
		// ---- IMPLEMENTAÇÃO DE DADOS INICIAIS
		
		
		userRepository.saveAll(Arrays.asList()); 
		agendaRepository.saveAll(Arrays.asList());     // SALVANDO DADOS ATUALIZADOS E CORRIGIDOS "DB"
		voteRepository.saveAll(Arrays.asList());
				
	}
	
	

}
