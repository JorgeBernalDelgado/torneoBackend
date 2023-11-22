package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PartidoTest {

    private Partido partido;
    private Equipo equipoA;
    private Equipo equipoB;

    @BeforeEach
    public void setUp() {
        partido = new Partido();

        equipoA = new Equipo();
        equipoA.setId(1);
        equipoA.setNombre("Equipo A");

        equipoB = new Equipo();
        equipoB.setId(2);
        equipoB.setNombre("Equipo B");

        partido.setId(1);
        partido.setEquipoA(equipoA);
        partido.setEquipoB(equipoB);
        partido.setFecha(LocalDate.of(2023, 7, 21));
        partido.setHora("15:00");
        partido.setCancha(1);
        partido.setNivel("Alto");
    }

    @Test
    public void testGetters() {
        assertEquals(1, partido.getId());
        assertEquals(equipoA, partido.getEquipoA());
        assertEquals(equipoB, partido.getEquipoB());
        assertEquals(LocalDate.of(2023, 7, 21), partido.getFecha());
        assertEquals("15:00", partido.getHora());
        assertEquals(1, partido.getCancha());
        assertEquals("Alto", partido.getNivel());
    }
}
