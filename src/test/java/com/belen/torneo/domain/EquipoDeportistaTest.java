package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EquipoDeportistaTest {

    private EquipoDeportista equipoDeportista;
    private Equipo equipo;
    private Deportista deportista;
    private Campeonato campeonato;

    @BeforeEach
    public void setUp() {
        equipoDeportista = new EquipoDeportista();

        equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("Equipo de prueba");

        deportista = new Deportista();
        deportista.setId(1);
        deportista.setNombre("Deportista de prueba");

        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de prueba");

        equipoDeportista.setId(1);
        equipoDeportista.setIdEquipo(equipo);
        equipoDeportista.setIdDeportista(deportista);
        equipoDeportista.setIdCampeonato(campeonato);
    }

    @Test
    public void testGetters() {
        assertEquals(1, equipoDeportista.getId());
        assertEquals(equipo, equipoDeportista.getIdEquipo());
        assertEquals(deportista, equipoDeportista.getIdDeportista());
        assertEquals(campeonato, equipoDeportista.getIdCampeonato());
    }
}
