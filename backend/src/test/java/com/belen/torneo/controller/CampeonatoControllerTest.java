package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.service.CampeonatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampeonatoControllerTest {

    @Mock
    private CampeonatoService campeonatoService;

    @InjectMocks
    private CampeonatoController campeonatoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCampeonatos_ReturnsListOfCampeonatos() {
        List<Campeonato> expectedCampeonatos = Arrays.asList(
                new Campeonato(),
                new Campeonato()
        );

        when(campeonatoService.listar()).thenReturn(expectedCampeonatos);
        List<Campeonato> result = campeonatoController.getCampeonatos();

        assertEquals(expectedCampeonatos, result);
        verify(campeonatoService, times(1)).listar();
    }

    @Test
    void getCampeonato_WithValidCampeonato_ReturnsOptionalCampeonato() {
        int campeonatoId = 1;
        Campeonato campeonatoEsperado = new Campeonato();
        campeonatoEsperado.setId(1);

        Optional<Campeonato> optionalCampeonato = Optional.of(campeonatoEsperado);

        when(campeonatoService.listarCampeonato(campeonatoId)).thenReturn(optionalCampeonato);
        Optional<Campeonato> result = campeonatoController.getCampeonato(campeonatoId);

        assertEquals(optionalCampeonato, result);
        verify(campeonatoService, times(1)).listarCampeonato(campeonatoId);
    }

    @Test
    void getCampeonato_WithInvalidCampeonato_ReturnsEmptyOptional() {
        int campeonatoId = 1;
        Optional<Campeonato> optionalCampeonato = Optional.empty();

        when(campeonatoService.listarCampeonato(campeonatoId)).thenReturn(optionalCampeonato);
        Optional<Campeonato> result = campeonatoController.getCampeonato(campeonatoId);

        assertEquals(optionalCampeonato, result);
        verify(campeonatoService, times(1)).listarCampeonato(campeonatoId);
    }

    @Test
    public void testCreateCampeonato_ValidCampeonato_ReturnsCreatedResponse() {
        // Datos de prueba
        Campeonato campeonato = new Campeonato();
        campeonato.setNombreCampeonato("Campeonato de prueba");
        campeonato.setEstado("Activo");
        campeonato.setDeporte(1);
        // Otros atributos necesarios para el campeonato

        // Configura el comportamiento del servicio mockeado para devolver el campeonato guardado
        when(campeonatoService.save(campeonato)).thenReturn(campeonato);

        // Ejecuta el método a probar
        ResponseEntity<Campeonato> response = campeonatoController.createCampeonato(campeonato);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(campeonato, response.getBody());
    }

    @Test
    public void testCreateCampeonato_CampeonatoWithId_ReturnsBadRequest() {
        // Datos de prueba
        Campeonato campeonato = new Campeonato();
        campeonato.setId(1); // Se establece un ID para simular que es un campeonato existente

        // Ejecuta el método a probar y verifica que devuelve un bad request
        ResponseEntity<Campeonato> response = campeonatoController.createCampeonato(campeonato);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateCampeonato_ExceptionDuringSave_ReturnsInternalServerError() {
        // Datos de prueba
        Campeonato campeonato = new Campeonato();
        campeonato.setNombreCampeonato("Campeonato de prueba");
        campeonato.setEstado("Activo");
        campeonato.setDeporte(1);
        // Otros atributos necesarios para el campeonato

        // Configura el comportamiento del servicio mockeado para lanzar una excepción durante el guardado
        when(campeonatoService.save(campeonato)).thenThrow(new RuntimeException("Error during save"));

        // Ejecuta el método a probar
        ResponseEntity<Campeonato> response = campeonatoController.createCampeonato(campeonato);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    /*@Test
    void createCampeonato_WithValidCampeonato_ReturnsCreatedResponse() {
        Campeonato campeonato = new Campeonato();
        campeonato.setId(null);

        Campeonato campeonatoGuardado = new Campeonato();
        campeonatoGuardado.setId(1);

        when(campeonatoService.save(campeonato)).thenReturn(campeonatoGuardado);
        ResponseEntity<Campeonato> response = campeonatoController.createCampeonato(campeonato);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(campeonatoGuardado, response.getBody());
        verify(campeonatoService, times(1)).save(campeonato);
    }*/

    @Test
    void delete_CallsCampeonatoServiceDeleteWithCorrectId() {
        int idToDelete = 1;
        campeonatoController.delete(idToDelete);
        verify(campeonatoService, times(1)).delete(idToDelete);
    }

    /*@Test
    void createCampeonato_WithException_ReturnsInternalServerErrorResponse() {
        Campeonato campeonato = new Campeonato();
        campeonato.setId(null);

        when(campeonatoService.save(campeonato)).thenThrow(new RuntimeException());
        ResponseEntity<Campeonato> response = campeonatoController.createCampeonato(campeonato);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(campeonatoService, times(1)).save(campeonato);
    }*/
}
