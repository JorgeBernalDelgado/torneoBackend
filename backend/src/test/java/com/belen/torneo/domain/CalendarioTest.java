package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalendarioTest {

    private Calendario calendario;
    private Date staticDate;

    private Campeonato campeonato;

    @BeforeEach
    public void setUp() throws ParseException {
        calendario = new Calendario();
        calendario.setId(1);
        calendario.setEquipoA("Equipo A");
        calendario.setEquipoB("Equipo B");
        calendario.setCategoria("Categoria");

        // Crear una fecha estática usando SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        staticDate = sdf.parse("2023-07-21");
        calendario.setFecha(staticDate);
        calendario.setHora(staticDate);

        // Mock del objeto Campeonato
        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de prueba");
        calendario.setIdCampeonato(campeonato);
    }

    @Test
    public void testGettersAndSetters() {
        // Formatear la fecha del objeto Calendario para comparar solo la parte de la fecha
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String calendarioDateStr = sdf.format(calendario.getFecha());
        String calendarioHourStr = sdf.format(calendario.getHora());
        String staticDateStr = sdf.format(staticDate);
        assertEquals(1, calendario.getId());
        assertEquals("Equipo A", calendario.getEquipoA());
        assertEquals("Equipo B", calendario.getEquipoB());
        assertEquals("Categoria", calendario.getCategoria());
        assertEquals(staticDateStr, calendarioDateStr);
        assertEquals(staticDateStr, calendarioHourStr);
        assertEquals(campeonato, calendario.getIdCampeonato());
    }

    @Test
    public void testIdCampeonato() {
        Campeonato campeonatoMock = calendario.getIdCampeonato();
    }
}
