package com.mishra.journal;

import com.mishra.journal.entity.User;
import com.mishra.journal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class JournalApplicationTests {
	@Autowired
	private UserService userService;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Disabled("temporarily skipped")
	@ParameterizedTest
	@CsvSource({
			"ram","tom","RAM"
	})
	void findUserByNametest(String username) {
		try {
			assertNotNull(userService.getUserByName(username).orElse(null));
		} catch (Exception e) {
			log.error("error occurred ",e);
		}
	}

	@Disabled("temporarily skipped")
	@ParameterizedTest
	@ArgumentsSource(ArguementBuilderTest.class)
	void validateUserTest(User user) {
        assertTrue(true);
		assertEquals(Objects.requireNonNull(userService.getUserByName(user.getUsername()).orElse(null)).getPassword(), passwordEncoder.encode(user.getPassword()));
	}

	@Disabled("temporarily skipped")
	@ParameterizedTest
	@CsvSource({
			"1,2,5",
			"2,3,7",
			"4,6,10"
	})
	void testAdd(int a, int b, int expected) {
		assertEquals(expected,a+b);
	}

}
