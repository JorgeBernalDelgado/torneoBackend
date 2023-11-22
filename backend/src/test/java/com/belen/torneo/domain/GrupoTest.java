package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GrupoTest {

    private Grupo grupo;
    private Campeonato campeonato;

    @BeforeEach
    public void setUp() {
        grupo = new Grupo();

        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de prueba");

        grupo.setId(1);
        grupo.setCodigo("Grupo A");
        grupo.setIdCampeonato(campeonato);
    }

    @Test
    public void testGetters() {
        assertEquals(1, grupo.getId());
        assertEquals("Grupo A", grupo.getCodigo());
        assertEquals(campeonato, grupo.getIdCampeonato());
    }
}
