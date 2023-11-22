package com.belen.torneo.exception;

import com.belen.torneo.exceptions.TorneoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TorneoExceptionTest {

    @Test
    public void testTorneoExceptionConstructor() {
        // Mensaje esperado para la excepción
        String expectedMessage = "Este es un mensaje de prueba";

        // Crea una instancia de la excepción
        TorneoException torneoException = new TorneoException(expectedMessage);

        // Verifica que el mensaje de la excepción sea el esperado
        assertEquals(expectedMessage, torneoException.getMessage());
    }
}
