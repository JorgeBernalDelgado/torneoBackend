package com.belen.torneo.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MensajeTest {

    @Test
    public void testSetAndGetMensaje() {
        Mensaje mensajeObj = new Mensaje("Este es un mensaje de prueba");

        // Verificar que el mensaje se establece correctamente
        String mensajeTexto = "Este es un mensaje de prueba";
        mensajeObj.setMensaje(mensajeTexto);

        // Verificar que el mensaje se obtiene correctamente utilizando el método get
        assertEquals(mensajeTexto, mensajeObj.getMensaje());
    }
}
