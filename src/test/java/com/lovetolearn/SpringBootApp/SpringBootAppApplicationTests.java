package com.lovetolearn.SpringBootApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootAppApplicationTests {

	@Autowired
	private SpringBootAppApplication springBootAppApplication;
	@Test
	void contextLoads() {
		assert(springBootAppApplication != null);
	}

}
