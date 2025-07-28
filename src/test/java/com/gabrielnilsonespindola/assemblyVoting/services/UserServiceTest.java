package com.gabrielnilsonespindola.assemblyVoting.services;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gabrielnilsonespindola.assemblyVoting.domain.User;
import com.gabrielnilsonespindola.assemblyVoting.repository.UserRepository;
import com.gabrielnilsonespindola.assemblyVoting.services.exceptions.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	UserRepository userRepository;

	User user;

	@InjectMocks
	UserService userService;

	@BeforeEach
	void setUpInsert() {
		user = new User();
		user.setId("1L");
		user.setName("Gabriel");
	}

	@Nested
	class findAll {

		@Test
		void findAllOk() {
			when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
			List<User> users = userService.findAll();
			assertNotNull(users);
			assertEquals(Collections.singletonList(user), users);

		}
	}

	@Nested
	class findById {

		@Test
		void findByIdOk() {
			when(userRepository.findById("1L")).thenReturn(Optional.of(user));

			var user1 = userService.findById("1L");

			assertEquals(user1, user);
			assertNotNull(user1);
			verify(userRepository).findById("1L");
			verifyNoMoreInteractions(userRepository);

		}

		@Test
		void findByIdNotOk() {

			final ObjectNotFoundException objException = assertThrows(ObjectNotFoundException.class,
					() -> userService.findById(null));

			assertNotNull(objException);			
			assertThat(objException.getCause(), nullValue());
			verify(userRepository).findById(null);
			verifyNoMoreInteractions(userRepository);

		}
	}

	@Nested
	class insert {

		@Test
		void insertNewUser() {

			when(userRepository.insert(any(User.class))).thenReturn(user);
			var insertUser = userService.insert(user);
			assertNotNull(insertUser);
			assertEquals(insertUser, user);
			verify(userRepository).insert(any(User.class));

		}
	}
}
