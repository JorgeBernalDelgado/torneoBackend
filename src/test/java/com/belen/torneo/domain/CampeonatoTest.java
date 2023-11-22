package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CampeonatoTest {

    private Campeonato campeonato;

    @BeforeEach
    public void setUp() {
        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de Prueba");
        campeonato.setEstado("Activo");
        campeonato.setDeporte(1);
        campeonato.setCategoria(2);
        campeonato.setRama("Masculino");
        campeonato.setPlanilla(3);
        campeonato.setLocalidad(4);
        campeonato.setRangoAnnio(2023);
        campeonato.setDivision(5);
        campeonato.setCarnet("ABCDE");
    }

    @Test
    public void testGetters() {
        assertEquals(1, campeonato.getId());
        assertEquals("Campeonato de Prueba", campeonato.getNombreCampeonato());
        assertEquals("Activo", campeonato.getEstado());
        assertEquals(1, campeonato.getDeporte());
        assertEquals(2, campeonato.getCategoria());
        assertEquals("Masculino", campeonato.getRama());
        assertEquals(3, campeonato.getPlanilla());
        assertEquals(4, campeonato.getLocalidad());
        assertEquals(2023, campeonato.getRangoAnnio());
        assertEquals(5, campeonato.getDivision());
        assertEquals("ABCDE", campeonato.getCarnet());
    }
}
