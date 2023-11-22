package com.belen.torneo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TorneoApplicationTests {

	@Test
	public void testMainMethod() {
		// Simula el arranque de la aplicación Spring Boot
		TorneoApplication.main(new String[]{});

		// Si no se produjeron excepciones durante el arranque, se considera exitosa la prueba
	}

}
