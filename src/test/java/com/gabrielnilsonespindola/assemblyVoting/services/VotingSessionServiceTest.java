package com.gabrielnilsonespindola.assemblyVoting.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.domain.VotingSession;
import com.gabrielnilsonespindola.assemblyVoting.dto.VotingResultDTO;
import com.gabrielnilsonespindola.assemblyVoting.enums.VoteStatus;
import com.gabrielnilsonespindola.assemblyVoting.repository.AgendaRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;
import com.gabrielnilsonespindola.assemblyVoting.repository.VotingSessionRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceTest {

	@Mock
	VotingSessionRepository votingSessionRepository;

	@Mock
	VoteRepository voteRepository;

	@Mock
	AgendaRepository agendaRepository;

	Agenda agenda;

	Vote vote1;

	Vote vote2;

	Vote vote3;

	VotingSession sessionVoting;

	@Mock
	AgendaService agendaService;

	@InjectMocks
	VotingSessionService votingSessionService;

	@Nested
	class startSession {

		@BeforeEach
		void setUp() {
			String agendaId = "1L";
			agenda = new Agenda();
			agenda.setId(agendaId);
			sessionVoting = new VotingSession();
			sessionVoting.setAgenda(agenda);
			sessionVoting.setDurationInMinutes(1L);
			sessionVoting.setOpening(LocalDateTime.now());
			sessionVoting.setClosure(sessionVoting.getOpening().plusMinutes(1L));

		}

		@Test
		void startSessionOk() {
			String agendaId = "1L";
			Optional<Long> durationInMinutes = Optional.of(1L);

			when(votingSessionRepository.save(any(VotingSession.class))).thenReturn(sessionVoting);

			VotingSession resultSession = votingSessionService.startSession(agendaId, durationInMinutes);

			assertNotNull(resultSession);
			assertEquals(sessionVoting.getId(), resultSession.getId());
			verify(agendaService).findById(agendaId);
			verify(votingSessionRepository).save(resultSession);
		}

		@Test
		void startSessionNotOk() {
			String agendaId = null;
			Optional<Long> durationInMinutes = Optional.of(1L);

			when(agendaService.findById(agendaId)).thenThrow(new ObjectNotFoundException("PAUTA INEXISTENTE"));

			final ObjectNotFoundException objException = assertThrows(ObjectNotFoundException.class,
					() -> votingSessionService.startSession(agendaId, durationInMinutes));
			assertThat(objException.getMessage(), is("PAUTA INEXISTENTE"));
			verify(agendaService).findById(agendaId);
			verifyNoInteractions(votingSessionRepository);

		}
	}

	@Nested
	class getVotingResult {

		@Nested
		class WhenVotesAreEqual {

			@BeforeEach
			void setUpVotesDraw() {
				String agendaId = "1L";
				agenda = new Agenda();
				agenda.setId(agendaId);
				agenda.setTitle("Pauta");
				vote1 = new Vote();
				vote1.setVoteStatus(VoteStatus.YES);
				vote2 = new Vote();
				vote2.setVoteStatus(VoteStatus.NO);

			}

			@Test
			void getVotingYesOrNoDraw() {

				String agendaId = "1L";
				List<Vote> votes = List.of(vote1, vote2);

				when(agendaService.findById(agendaId)).thenReturn(agenda);
				when(voteRepository.findByAgendaId(agendaId)).thenReturn(votes);

				VotingResultDTO votingResult = votingSessionService.getVotingResult(agendaId);

				assertNotNull(votingResult);
				assertEquals(1, votingResult.getYesVotes());
				assertEquals(1, votingResult.getNoVotes());
				assertEquals("Empate", votingResult.getResult());
				assertEquals("Pauta", votingResult.getAgendaTitle());
				assertThat(votes.size(), is(2));
				verify(agendaService).findById(agendaId);
				verify(voteRepository).findByAgendaId(agendaId);
				verifyNoMoreInteractions(voteRepository);

			}
		}

		@Nested
		class WhenYesVotesAreGreater {

			@BeforeEach
			void setUpVotesYes() {
				String agendaId = "1L";
				agenda = new Agenda();
				agenda.setId(agendaId);
				agenda.setTitle("Pauta");
				vote1 = new Vote();
				vote1.setVoteStatus(VoteStatus.YES);
				vote2 = new Vote();
				vote2.setVoteStatus(VoteStatus.NO);
				vote3 = new Vote();
				vote3.setVoteStatus(VoteStatus.YES);

			}

			@Test
			void getVotingMoreYesThanNo() {

				String agendaId = "1L";
				List<Vote> votes = List.of(vote1, vote2, vote3);

				when(agendaService.findById(agendaId)).thenReturn(agenda);
				when(voteRepository.findByAgendaId(agendaId)).thenReturn(votes);

				VotingResultDTO votingResult = votingSessionService.getVotingResult(agendaId);

				assertNotNull(votingResult);
				assertEquals(2, votingResult.getYesVotes());
				assertEquals(1, votingResult.getNoVotes());
				assertEquals("Aprovado", votingResult.getResult());
				assertEquals("Pauta", votingResult.getAgendaTitle());
				assertThat(votes.size(), is(3));
				verify(agendaService).findById(agendaId);
				verify(voteRepository).findByAgendaId(agendaId);
				verifyNoMoreInteractions(voteRepository);

			}
		}

		@Nested
		class WhenNoVotesAreGreater {

			@BeforeEach
			void setUpVotesNo() {
				String agendaId = "1L";
				agenda = new Agenda();
				agenda.setId(agendaId);
				agenda.setTitle("Pauta");
				vote1 = new Vote();
				vote1.setVoteStatus(VoteStatus.NO);
				vote2 = new Vote();
				vote2.setVoteStatus(VoteStatus.NO);
				vote3 = new Vote();
				vote3.setVoteStatus(VoteStatus.YES);

			}

			@Test
			void getVotingMoreNoThanYes() {

				String agendaId = "1L";
				List<Vote> votes = List.of(vote1, vote2, vote3);

				when(agendaService.findById(agendaId)).thenReturn(agenda);
				when(voteRepository.findByAgendaId(agendaId)).thenReturn(votes);

				VotingResultDTO votingResult = votingSessionService.getVotingResult(agendaId);

				assertNotNull(votingResult);
				assertEquals(1, votingResult.getYesVotes());
				assertEquals(2, votingResult.getNoVotes());
				assertEquals("Rejeitado", votingResult.getResult());
				assertEquals("Pauta", votingResult.getAgendaTitle());
				assertThat(votes.size(), is(3));
				verify(agendaService).findById(agendaId);
				verify(voteRepository).findByAgendaId(agendaId);
				verifyNoMoreInteractions(voteRepository);

			}
		}
	}
}
