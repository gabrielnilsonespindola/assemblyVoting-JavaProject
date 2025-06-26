package com.gabrielnilsonespindola.assemblyVoting.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gabrielnilsonespindola.assemblyVoting.domain.Agenda;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.domain.Vote;
import com.gabrielnilsonespindola.assemblyVoting.repository.VoteRepository;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

	@Mock
	VoteRepository voteRepository;

	Vote vote;

	Agenda agenda;

	User user;

	@Mock
	UserService userService;

	@Mock
	AgendaService agendaService;

	@InjectMocks
	VoteService voteService;

	@Nested
	class registerVote {

		@BeforeEach
		void setUpVoteOk() {
			agenda = new Agenda();
			agenda.setId("1L");

			user = new User();
			user.setId("1L");

			vote = new Vote();
			vote.setAgenda(agenda);
			vote.setUser(user);

		}

		@Test
		void registerVoteOkAndSaveRepository() {

			when(voteRepository.save(any(Vote.class))).thenReturn(vote);
			Vote voteOk = assertDoesNotThrow(() -> voteService.registerVote(vote));
			assertNotNull(voteOk);
			assertThat(voteOk.getUser().getId(), is("1L"));
			assertThat(voteOk.getAgenda().getId(), is("1L"));
			verify(voteRepository).save(vote);

		}
	}

	@BeforeEach
	void setUpVoteNotOk() {
		agenda = new Agenda();
		agenda.setId("1L");

		user = new User();
		user.setId("1L");

		vote = new Vote();
		vote.setAgenda(agenda);
		vote.setUser(user);

	}

	@Test
	void registerVoteNotOkAndThrowException() {

		when(voteRepository.findByAgendaIdAndUserId("1L", "1L")).thenReturn(Optional.of(vote));

		final IllegalArgumentException objException = assertThrows(IllegalArgumentException.class,
				() -> voteService.registerVote(vote));

		assertThat(objException.getMessage(), is("Usuário já votou nesta pauta."));
		verify(voteRepository).findByAgendaIdAndUserId("1L", "1L");
		verifyNoMoreInteractions(voteRepository);

	}
}
