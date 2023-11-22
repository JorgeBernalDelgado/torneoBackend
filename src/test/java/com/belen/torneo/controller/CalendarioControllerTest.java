package com.belen.torneo.controller;

import com.belen.torneo.domain.Calendario;
import com.belen.torneo.service.CalendarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalendarioControllerTest {

    @Mock
    private CalendarioService calendarioService;

    @InjectMocks
    private CalendarioController calendarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCalendario_WithValidCalendario_ReturnsCreatedResponse() {
        Calendario calendario = new Calendario();
        calendario.setId(null);

        Calendario calendarioGuardar = new Calendario();
        calendarioGuardar.setId(1);

        when(calendarioService.save(calendario)).thenReturn(calendarioGuardar);
        ResponseEntity<Calendario> response = calendarioController.createCalendario(calendario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(calendarioGuardar, response.getBody());
        verify(calendarioService, times(1)).save(calendario);
    }

    @Test
    void getCalendarios_WithValidCampeonato_ReturnsListOfCalendarios() {
        int campeonatoId = 1;
        List<Calendario> expectedCalendarios = Arrays.asList(
                new Calendario(),
                new Calendario()
        );

        when(calendarioService.listarDia(campeonatoId)).thenReturn(expectedCalendarios);
        List<Calendario> result = calendarioController.getCalendarios(campeonatoId);

        assertEquals(expectedCalendarios, result);
        verify(calendarioService, times(1)).listarDia(campeonatoId);
    }

    @Test
    void delete_CallsCalendarioServiceDeleteWithCorrectId() {
        int idToDelete = 1;
        calendarioController.delete(idToDelete);
        verify(calendarioService, times(1)).delete(idToDelete);
    }

    @Test
    void getCalendariosWithToken_WithValidCampeonato_ReturnsListOfCalendarios() {
        int campeonatoId = 1;
        List<Calendario> expectedCalendarios = Arrays.asList(
                new Calendario(),
                new Calendario()
        );

        when(calendarioService.listarDia(campeonatoId)).thenReturn(expectedCalendarios);
        List<Calendario> result = calendarioController.getCalendariosWithToken(campeonatoId);

        assertEquals(expectedCalendarios, result);
        verify(calendarioService, times(1)).listarDia(campeonatoId);
    }

    @Test
    void getCalendariosByCampeonato_WithValidCampeonato_ReturnsListOfCalendarios() {
        int campeonatoId = 1;
        List<Calendario> expectedCalendarios = Arrays.asList(
                new Calendario(),
                new Calendario()
        );

        when(calendarioService.listarDia(campeonatoId)).thenReturn(expectedCalendarios);
        List<Calendario> result = calendarioController.getCalendariosByCampeonato(campeonatoId);

        assertEquals(expectedCalendarios, result);
        verify(calendarioService, times(1)).listarDia(campeonatoId);
    }

    @Test
    void update_WithValidCalendario_ReturnsCreatedResponse() {
        Calendario calendario = new Calendario();
        calendario.setId(1);

        Calendario calendarioActualizado = new Calendario();
        calendarioActualizado.setId(1);
        calendarioActualizado.setCategoria("Nueva categoria");

        when(calendarioService.update(calendario)).thenReturn(calendarioActualizado);
        ResponseEntity<Calendario> response = calendarioController.update(calendario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(calendarioActualizado, response.getBody());
        verify(calendarioService, times(1)).update(calendario);
    }

    @Test
    void update_WithException_ReturnsInternalServerErrorResponse() {
        Calendario calendario = new Calendario();
        calendario.setId(1);

        when(calendarioService.update(calendario)).thenThrow(new RuntimeException());
        ResponseEntity<Calendario> response = calendarioController.update(calendario);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody());
        verify(calendarioService, times(1)).update(calendario);
    }


    @Test
    void createCalendario_WithException_ReturnsInternalServerErrorResponse() {
        Calendario calendario = new Calendario();
        calendario.setId(null);

        when(calendarioService.save(calendario)).thenThrow(new RuntimeException());
        ResponseEntity<Calendario> response = calendarioController.createCalendario(calendario);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(calendarioService, times(1)).save(calendario);
    }

    @Test
    public void testCreateCalendarioBadRequest() {
        // Datos de prueba
        Calendario calendario = new Calendario(); // Inicializa el objeto Calendario con los datos necesarios para la prueba
        calendario.setId(1); // Establece un ID no nulo para simular una solicitud de creación inválida

        // Ejecutar el método del controlador que queremos probar
        ResponseEntity<Calendario> responseEntity = calendarioController.createCalendario(calendario);

        // Verificar el resultado
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());

        // Verificar que el método del servicio NO fue llamado, ya que la solicitud es inválida
        verify(calendarioService, never()).save(calendario);
    }

}
