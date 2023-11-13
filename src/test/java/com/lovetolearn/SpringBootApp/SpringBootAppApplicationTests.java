package com.lovetolearn.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootAppApplicationTests {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void contextLoads() {
		// Check that the Spring context loads successfully
	}

	@Test
	public void homeEndpointReturnsHello() {
		String url = "http://localhost:" + port + "/";
		String response = restTemplate.getForObject(url, String.class);
		assertThat(response).contains("Hello");
	}
}
