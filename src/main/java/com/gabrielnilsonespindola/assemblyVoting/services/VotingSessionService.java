package com.gabrielnilsonespindola.assemblyVoting.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Service;

import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingResultDTO;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingSessionDTO;
import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VotingSessionRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

@Service
public class VotingSessionService {

	@Autowired
	private VotingSessionRepository repo;
	
	@Autowired
	private VoteRepository repoVote;

	@DBRef
	private Agenda agenda;

	@Autowired
	private AgendaService service;

	public List<VotingSession> findAll() {
		return repo.findAll();
	}

	public VotingSession findById(String id) {
		Optional<VotingSession> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public VotingSession insert(VotingSession obj) {
		return repo.insert(obj);
	}

	public VotingSession fromDTO(VotingSessionDTO objDtO) {
		return new VotingSession(objDtO.getId(), objDtO.getOpening(), objDtO.getClosure(),objDtO.getDurationInMinutes(), objDtO.getAgenda());
	}

	public VotingSession startSession(String agendaId, Optional<Long> durationInMinutes) {
		Agenda agenda = service.findById(agendaId); // Buscar a pauta/agenda pelo ID

		// Criar nova sessão de votação
		VotingSession session = new VotingSession();                //1 - Encontra a pauta / metodofindById de AgendaService(Injeção de dependencia).Se não houver é lançado a exceção personalizada "Object Not Found" que consta no metodo FindById.
		session.setAgenda(agenda); // associar a pauta              //2 - Cria uma sessão de votação e associa com a pauta - Instancia VotingSession e utiliza chamada Set de Agenda associando ambos. 
		session.setOpening(LocalDateTime.now());                    //3 - Define abertura da sessão  com base no horario atual do usuario com a chamada ".now" ,utilizando o "set" para inserir este valor, e o atributo Opening de "VotingSession"
                                                                    //4 - Determina duração da sessão utilizando do parametro do metodo "durationInMinutes" ,"Optional" vindo como forma de haver um valor para duração("duration") da sessão e caso não tenha(empty), que possa persistir com o valor definido (1L) - 1 minuto, por padrão default. Dessa forma não correndo risco de dar sessão sem tempo ou null.
		// Define a duração: padrão = 1 minuto                      //4 - Calcula o "horário de encerramento da sessão", somando o tempo desejado à hora de abertura.Inserindo o valor de encerramento com a chamada "set" e o atributo "Closure" da classe "VotingSession". "get" "Opening" de "VotingSession" dessa vez ja com valor definido, concluindo para "minutes" e depois fazendo chamada de "repo" para salvar no banco de dados.
		long duration = durationInMinutes.orElse(1L);               
		session.setClosure(session.getOpening().plusMinutes(duration));

		// Salvar a sessão
		return repo.save(session);
	}
		
	
	public VotingResultDTO getVotingResult(String agendaId) {
	    
	    Agenda agenda = service.findById(agendaId);   // 1. Buscar a agenda pelo ID	    
	    List<Vote> votes = repoVote.findByAgendaId(agendaId); // 2. Buscar todos os votos relacionados a essa agenda (supondo que o repoVote tenha esse método)
	    
	    int yes = 0;            // 3. Contadores para votos sim e não
	    int no = 0;
	    
	    
	    for (Vote vote : votes) {
	        VoteStatus status = vote.getVoteStatus();         // 4. Percorrer todos os votos e contar
	        if (status == VoteStatus.YES) {
	            yes++;
	        } else if (status == VoteStatus.NO) {
	            no++;
	        }
	    }
	    
	    String result;
	    if (yes > no) {
	        result = "Aprovado";        // 5. Definir o resultado baseado na contagem
	    } else if (no > yes) {
	        result = "Rejeitado";
	    } else {
	        result = "Empate";
	    }
	    
	    
	    return new VotingResultDTO(
	        agenda.getId(),
	        agenda.getTitle(),         // 6. Montar e retornar o DTO com o resultado
	        yes,
	        no,
	        result
	    );
	}

	
	

}
