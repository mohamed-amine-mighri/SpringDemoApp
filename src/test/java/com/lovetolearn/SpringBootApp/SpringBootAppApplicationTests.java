package com.lovetolearn.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SpringBootAppApplicationTests {
	@Autowired
	private SpringBootAppApplication springBootAppApplication;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() {
		// Check that the Spring context loads successfully
		assert(springBootAppApplication != null);

	}

	@Test
	public void homeEndpointReturnsHello() {
		String url = "http://localhost:" + 8080 + "/";
		String response = restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Hello");
	}
}
