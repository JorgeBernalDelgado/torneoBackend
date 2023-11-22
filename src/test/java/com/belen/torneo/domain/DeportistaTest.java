package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DeportistaTest {

    private Deportista deportista;

    @BeforeEach
    public void setUp() {
        deportista = new Deportista();
        deportista.setId(1);
        deportista.setIdentificacion("123456789");
        deportista.setTipoIdentificacion(1);
        deportista.setNombre("Juan");
        deportista.setApellido("Pérez");
        deportista.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        deportista.setFotoDeportista("ruta/foto.jpg");
        deportista.setNumeroCamiseta(10);
        deportista.setPosicion("Delantero");
        deportista.setGolesRecibidos(5);
        deportista.setAnotaciones(15);
        deportista.setDocumento("ruta/documento.pdf");
        deportista.setCestas(20);
    }

    @Test
    public void testGetters() {
        assertEquals(1, deportista.getId());
        assertEquals("123456789", deportista.getIdentificacion());
        assertEquals(1, deportista.getTipoIdentificacion());
        assertEquals("Juan", deportista.getNombre());
        assertEquals("Pérez", deportista.getApellido());
        assertEquals(LocalDate.of(1990, 5, 15), deportista.getFechaNacimiento());
        assertEquals("ruta/foto.jpg", deportista.getFotoDeportista());
        assertEquals(10, deportista.getNumeroCamiseta());
        assertEquals("Delantero", deportista.getPosicion());
        assertEquals(5, deportista.getGolesRecibidos());
        assertEquals(15, deportista.getAnotaciones());
        assertEquals("ruta/documento.pdf", deportista.getDocumento());
        assertEquals(20, deportista.getCestas());
    }
}
