package com.unialfa.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BibliotecaApplicationTests {

	static {
		if (System.getenv("API_SECURITY_TOKEN_SECRET") == null
				&& System.getProperty("API_SECURITY_TOKEN_SECRET") == null) {
			System.setProperty("API_SECURITY_TOKEN_SECRET", "x".repeat(48));
		}
	}

	@Test
	void contextLoads() {
		// assertTrue(false);
	}

}
