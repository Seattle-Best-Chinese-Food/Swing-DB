package com.example.swing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.awt.GraphicsEnvironment;

@SpringBootTest
class SwingApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("GraphicsEnvironment.isHeadless() = " + GraphicsEnvironment.isHeadless());
	}
}
