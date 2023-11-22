package com.belen.torneo.controller;

import com.belen.torneo.domain.Datos;
import com.belen.torneo.service.DatosService;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DatosControllerTest {

    @InjectMocks
    private DatosController datosController;

    @Mock
    private DatosService datosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDatos_ReturnsListOfDatos() {
        List<Datos> expectedDatos = Arrays.asList(
                new Datos(),
                new Datos()
        );

        when(datosService.listar()).thenReturn(expectedDatos);
        List<Datos> result = datosController.getDatos();

        assertEquals(expectedDatos, result);
        verify(datosService, times(1)).listar();
    }

    @Test
    public void testCreateDatos_ValidDatos_ReturnsCreatedResponse() {
        // Datos de prueba
        Datos dato = new Datos();
        dato.setCategoria("categoria");
        dato.setCodigo(1);
        dato.setNombre("juvenil");

        // Configura el comportamiento del servicio mockeado para devolver el campeonato guardado
        when(datosService.save(dato)).thenReturn(dato);

        // Ejecuta el método a probar
        ResponseEntity<Datos> response = datosController.createDatos(dato);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dato, response.getBody());
    }

    @Test
    void testGetDatosCampeonato() {
        // Datos de prueba
        String categoria = "categoria";
        List<Datos> datos = new ArrayList<>();

        // Configura el comportamiento del mock del servicio
        when(datosService.listarDatosByCategoria(categoria)).thenReturn(datos);

        // Ejecuta el método a probar
        List<Datos> resultado = datosController.getDatosByCategoria(categoria);

        // Verifica el resultado esperado
        assertEquals(datos, resultado);
    }

    @Test
    public void testDelete_DatoEliminado() {
        // Datos de prueba
        int idDatosAEliminar = 1;

        // Ejecuta el método a probar
        datosController.delete(idDatosAEliminar);

        // Verifica el comportamiento esperado del servicio mockeado
        verify(datosService, times(1)).delete(idDatosAEliminar);
    }

    @Test
    void createDatos_WithException_ReturnsInternalServerErrorResponse() {
        Datos dato = new Datos();
        dato.setId(null);

        when(datosService.save(dato)).thenThrow(new RuntimeException());
        ResponseEntity<Datos> response = datosController.createDatos(dato);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(datosService, times(1)).save(dato);
    }

    @Test
    public void testCreateDatosBadRequest() {
        // Datos de prueba
        Datos dato = new Datos();
        dato.setId(1);

        // Ejecutar el método del controlador que queremos probar
        ResponseEntity<Datos> responseEntity = datosController.createDatos(dato);

        // Verificar el resultado
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());

        // Verificar que el método del servicio NO fue llamado, ya que la solicitud es inválida
        verify(datosService, never()).save(dato);
    }

}
