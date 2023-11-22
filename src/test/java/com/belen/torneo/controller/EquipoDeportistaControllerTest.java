package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.DeportistaService;
import com.belen.torneo.service.EquipoDeportistaService;
import com.belen.torneo.service.EquipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipoDeportistaControllerTest {

    @Mock
    private EquipoDeportistaService equipoDeportistaService;

    @Mock
    private CampeonatoService campeonatoService;

    @Mock
    private DeportistaService deportistaService;

    @Mock
    private EquipoService equipoService;

    @InjectMocks
    private EquipoDeportistaController equipoDeportistaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEquipoDeportistaByCampeonato_ValidParameters_ReturnsListOfEquipoDeportista() {
        // Datos de prueba
        int torneoId = 1;
        int jugadorId = 2;
        Campeonato campeonato = new Campeonato();
        Deportista deportista = new Deportista();
        List<EquipoDeportista> equipoDeportistaList = new ArrayList<>();
        // Agrega elementos a equipoDeportistaList si es necesario

        // Configurar comportamiento simulado para los servicios
        when(campeonatoService.listarCampeonato(torneoId)).thenReturn(Optional.of(campeonato));
        when(deportistaService.listarDeportista(jugadorId)).thenReturn(Optional.of(deportista));
        when(equipoDeportistaService.listarEquipoDeportistaByCampeonato(campeonato, deportista)).thenReturn(equipoDeportistaList);

        // Ejecutar el método a probar
        List<EquipoDeportista> result = equipoDeportistaController.getEquipoDeportistaByCampeonato(torneoId, jugadorId);

        // Verificar el resultado
        assertEquals(equipoDeportistaList, result);
    }

    @Test
    public void testGetEquipoDeportistaByEquipo() {
        // Datos de prueba
        int equipoId = 1;
        int campeonatoId = 2;

        Equipo equipo = new Equipo();
        equipo.setId(equipoId);

        Campeonato campeonato = new Campeonato();
        campeonato.setId(campeonatoId);

        List<EquipoDeportista> equipoDeportistaList = new ArrayList<>();

        // Configurar comportamiento simulado para los servicios
        when(equipoService.listarEquipo(equipoId)).thenReturn(Optional.of(equipo));
        when(campeonatoService.listarCampeonato(campeonatoId)).thenReturn(Optional.of(campeonato));
        when(equipoDeportistaService.listarEquipoDeportistaByEquipo(equipo, campeonato)).thenReturn(equipoDeportistaList);

        // Ejecutar el método a probar
        List<EquipoDeportista> result = equipoDeportistaController.getEquipoDeportistaByEquipo(equipoId, campeonatoId);

        // Verificar el resultado esperado
        assertEquals(equipoDeportistaList, result);
    }

    @Test
    void testGetDeportistaAnotaciones() {
        // Datos de prueba
        Integer torneoId = 1;
        List<EquipoDeportista> deportistas = new ArrayList<>();
        // Agrega aquí algunos objetos EquipoDeportista a la lista "deportistas"

        // Configura el comportamiento del mock del servicio
        when(equipoDeportistaService.listarDeportistaAnotaciones(torneoId)).thenReturn(deportistas);

        // Ejecuta el método a probar
        List<EquipoDeportista> resultado = equipoDeportistaController.getDeportistaAnotaciones(torneoId);

        // Verifica el resultado esperado
        assertEquals(deportistas, resultado);
    }

    @Test
    void testGetVallaMenosVencida() {
        // Datos de prueba
        Integer torneoId = 1;
        List<EquipoDeportista> vallaMenosVencida = new ArrayList<>();
        // Agrega aquí algunos objetos EquipoDeportista a la lista "vallaMenosVencida"

        // Configura el comportamiento del mock del servicio
        when(equipoDeportistaService.listarVallaMenosVencida(torneoId)).thenReturn(vallaMenosVencida);

        // Ejecuta el método a probar
        List<EquipoDeportista> resultado = equipoDeportistaController.getVallaMenosVencida(torneoId);

        // Verifica el resultado esperado
        assertEquals(vallaMenosVencida, resultado);
    }

}
