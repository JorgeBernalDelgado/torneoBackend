package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.DetallePartido;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.DetallePartidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetallePartidoControllerTest {

    @Mock
    private DetallePartidoService detallePartidoService;

    @Mock
    private CampeonatoService campeonatoService;

    @InjectMocks
    private DetallePartidoController detallePartidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetDetallePartidos_ReturnsListOfDetallePartido() {
        // Datos de prueba
        DetallePartido detallePartido1 = new DetallePartido(/* Agregar los datos del primer detalle de partido */);
        DetallePartido detallePartido2 = new DetallePartido(/* Agregar los datos del segundo detalle de partido */);

        // Crear una lista con los detalles de partido
        List<DetallePartido> detallePartidos = new ArrayList<>();
        detallePartidos.add(detallePartido1);
        detallePartidos.add(detallePartido2);

        // Configurar el comportamiento esperado del servicio mock
        when(detallePartidoService.listar()).thenReturn(detallePartidos);

        // Ejecutar el método getDetallePartidos() del controlador
        List<DetallePartido> resultado = detallePartidoController.getDetallePartidos();

        // Verificar el resultado esperado
        assertEquals(detallePartidos.size(), resultado.size());
        // Puedes agregar más aserciones aquí para verificar el contenido de la lista, si es necesario.
    }

    @Test
    public void testCreateDetallePartido_ValidDetallePartido_ReturnsCreatedResponse() {
        // Datos de prueba
        DetallePartido detallePartido = new DetallePartido(/* Agregar los datos del detalle de partido válido */);

        // Configurar el comportamiento esperado del servicio mock
        when(detallePartidoService.save(detallePartido)).thenReturn(detallePartido);

        // Ejecutar el método createDetallePartido() del controlador
        ResponseEntity<DetallePartido> response = detallePartidoController.createDetallePartido(detallePartido);

        // Verificar el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(detallePartido, response.getBody());
    }

    @Test
    public void testCreateDetallePartido_DuplicateId_ReturnsBadRequest() {
        // Datos de prueba
        DetallePartido detallePartido = new DetallePartido();
        detallePartido.setId(1);

        // Ejecutar el método createDetallePartido() del controlador
        ResponseEntity<DetallePartido> response = detallePartidoController.createDetallePartido(detallePartido);

        // Verificar el resultado esperado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testCreateDetallePartido_InternalServerError_ReturnsInternalServerError() {
        // Datos de prueba
        DetallePartido detallePartido = new DetallePartido(/* Agregar los datos del detalle de partido válido */);

        // Configurar el comportamiento esperado del servicio mock para lanzar una excepción
        when(detallePartidoService.save(detallePartido)).thenThrow(new RuntimeException());

        // Ejecutar el método createDetallePartido() del controlador
        ResponseEntity<DetallePartido> response = detallePartidoController.createDetallePartido(detallePartido);

        // Verificar el resultado esperado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testGetDetallePartido() {
        Integer torneoId = 1;
        Campeonato campeonato = new Campeonato();
        campeonato.setId(torneoId);
        List<DetallePartido> expectedDetallePartidos = new ArrayList<>();
        when(campeonatoService.listarCampeonato(torneoId)).thenReturn(Optional.of(campeonato));
        when(detallePartidoService.listarDetallePartidoByTorneo(campeonato)).thenReturn(expectedDetallePartidos);
        List<DetallePartido> actualDetallePartidos = detallePartidoController.getDetallePartido(torneoId);
        assertEquals(expectedDetallePartidos, actualDetallePartidos);
    }
}
