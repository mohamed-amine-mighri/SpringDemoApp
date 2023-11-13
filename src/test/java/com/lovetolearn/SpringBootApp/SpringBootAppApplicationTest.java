package com.lovetolearn.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.mockito.Mockito.mock;

public class SpringBootAppApplicationTest {

    @Test
    public void contextLoads() {
        SpringBootAppApplication.main(new String[]{}); // Ensure the application context loads
    }

    @Test
    public void configure() {
        SpringBootAppApplication application = new SpringBootAppApplication();
        SpringApplicationBuilder builderMock = mock(SpringApplicationBuilder.class);

        application.configure(builderMock);

        // Add assertions as needed based on the behavior you expect in the configure method
    }
}
