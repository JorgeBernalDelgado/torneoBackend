package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EquipoTest {

    private Equipo equipo;
    private Campeonato campeonato;
    private Usuario delegado;
    private Grupo grupo;

    @BeforeEach
    public void setUp() {
        equipo = new Equipo();

        campeonato = new Campeonato();
        campeonato.setId(1);
        campeonato.setNombreCampeonato("Campeonato de prueba");

        delegado = new Usuario();
        delegado.setId(1);
        delegado.setNombre("Delegado de prueba");

        grupo = new Grupo();
        grupo.setId(1);
        grupo.setCodigo("Grupo de prueba");

        equipo.setId(1);
        equipo.setNombre("Equipo de prueba");
        equipo.setIdCampeonato(campeonato);
        equipo.setDelegado(delegado);
        equipo.setIdGrupo(grupo);
        equipo.setPuntos(10);
        equipo.setEstado("Activo");
    }

    @Test
    public void testGetters() {
        assertEquals(1, equipo.getId());
        assertEquals("Equipo de prueba", equipo.getNombre());
        assertEquals(campeonato, equipo.getIdCampeonato());
        assertEquals(delegado, equipo.getDelegado());
        assertEquals(grupo, equipo.getIdGrupo());
        assertEquals(10, equipo.getPuntos());
        assertEquals("Activo", equipo.getEstado());
    }
}
