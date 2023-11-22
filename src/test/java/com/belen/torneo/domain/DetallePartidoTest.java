package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DetallePartidoTest {

    private DetallePartido detallePartido;
    private Equipo equipoA;
    private Equipo equipoB;
    private Campeonato campeonato;

    @BeforeEach
    public void setUp() {
        detallePartido = new DetallePartido();

        equipoA = new Equipo();
        equipoA.setId(1);
        equipoA.setNombre("Equipo A");

        equipoB = new Equipo();
        equipoB.setId(2);
        equipoB.setNombre("Equipo B");

        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de prueba");

        detallePartido.setId(1);
        detallePartido.setEquipoa(equipoA);
        detallePartido.setEquipob(equipoB);
        detallePartido.setFecha(LocalDate.of(2023, 7, 21));
        detallePartido.setHora("15:00");
        detallePartido.setAnotacionesa(2);
        detallePartido.setAnotacionesb(1);
        detallePartido.setArbitro("Árbitro de prueba");
        detallePartido.setJuez1("Juez 1");
        detallePartido.setJuez2("Juez 2");
        detallePartido.setCategoria("Categoría de prueba");
        detallePartido.setEquipoGanador("Equipo A");
        detallePartido.setInformeArbitral("Informe de prueba");
        detallePartido.setCampeonato(campeonato);
    }

    @Test
    public void testGetters() {
        assertEquals(1, detallePartido.getId());
        assertEquals(equipoA, detallePartido.getEquipoa());
        assertEquals(equipoB, detallePartido.getEquipob());
        assertEquals(LocalDate.of(2023, 7, 21), detallePartido.getFecha());
        assertEquals("15:00", detallePartido.getHora());
        assertEquals(2, detallePartido.getAnotacionesa());
        assertEquals(1, detallePartido.getAnotacionesb());
        assertEquals("Árbitro de prueba", detallePartido.getArbitro());
        assertEquals("Juez 1", detallePartido.getJuez1());
        assertEquals("Juez 2", detallePartido.getJuez2());
        assertEquals("Categoría de prueba", detallePartido.getCategoria());
        assertEquals("Equipo A", detallePartido.getEquipoGanador());
        assertEquals("Informe de prueba", detallePartido.getInformeArbitral());
        assertEquals(campeonato, detallePartido.getCampeonato());
    }
}
