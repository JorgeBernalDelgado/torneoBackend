package com.belen.torneo.controller;

import com.belen.torneo.domain.Grupo;
import com.belen.torneo.dto.DataGrupo;
import com.belen.torneo.service.GrupoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GrupoControllerTest {

    @Mock
    private GrupoService grupoService;

    @InjectMocks
    private GrupoController grupoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGrupos() {
        // Datos de prueba
        List<Grupo> grupos = new ArrayList<>();
        // Agrega aquí algunos objetos Grupo a la lista "grupos"

        // Configura el comportamiento del mock del servicio
        when(grupoService.listar()).thenReturn(grupos);

        // Ejecuta el método a probar
        List<Grupo> resultado = grupoController.getGrupos();

        // Verifica el resultado esperado
        assertEquals(grupos, resultado);
    }

    @Test
    void testCreateGrupo_GrupoWithId_ReturnsBadRequest() {
        // Datos de prueba
        Grupo grupo = new Grupo();
        grupo.setId(1); // Grupo con ID, lo cual debería dar lugar a un BAD_REQUEST

        // Ejecuta el método a probar
        ResponseEntity<Grupo> response = grupoController.createGrupo(grupo);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreateGrupo_ValidGrupo_ReturnsCreatedResponse() {
        // Datos de prueba
        Grupo grupo = new Grupo();
        grupo.setCodigo("Grupo A"); // Grupo válido, no tiene ID

        // Configura el comportamiento del mock del servicio
        when(grupoService.save(grupo)).thenReturn(grupo); // Devuelve el mismo grupo sin cambios

        // Ejecuta el método a probar
        ResponseEntity<Grupo> response = grupoController.createGrupo(grupo);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(grupo, response.getBody());
    }

    @Test
    void testCreateGrupo_ServiceError_ReturnsInternalServerError() {
        // Datos de prueba
        Grupo grupo = new Grupo();
        grupo.setCodigo("Grupo B"); // Grupo válido, no tiene ID

        // Configura el comportamiento del mock del servicio para lanzar una excepción
        when(grupoService.save(grupo)).thenThrow(new RuntimeException("Error al guardar el grupo"));

        // Ejecuta el método a probar
        ResponseEntity<Grupo> response = grupoController.createGrupo(grupo);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetGrupo_ExistingGrupo_ReturnsGrupo() {
        // Datos de prueba
        int grupoId = 1;
        Grupo grupo = new Grupo();
        grupo.setId(grupoId);
        grupo.setCodigo("Grupo A");

        // Configura el comportamiento del mock del servicio
        when(grupoService.listarGrupo(grupoId)).thenReturn(Optional.of(grupo));

        // Ejecuta el método a probar
        Optional<Grupo> result = grupoController.getGrupo(grupoId);

        // Verifica el resultado esperado
        assertTrue(result.isPresent());
        assertEquals(grupo, result.get());
    }

    @Test
    void testGetGrupo_NonExistingGrupo_ReturnsEmptyOptional() {
        // Datos de prueba
        int grupoId = 2;

        // Configura el comportamiento del mock del servicio
        when(grupoService.listarGrupo(grupoId)).thenReturn(Optional.empty());

        // Ejecuta el método a probar
        Optional<Grupo> result = grupoController.getGrupo(grupoId);

        // Verifica el resultado esperado
        assertFalse(result.isPresent());
    }

    @Test
    void testCreateGrupos_CallsGrupoServiceWithCorrectData() {
        // Datos de prueba
        DataGrupo data = new DataGrupo();
        data.setCampeonato(1);
        data.setGrupo(1);

        // Ejecuta el método a probar
        grupoController.createGrupos(data);

        // Verifica que el servicio se haya llamado con los datos correctos
        verify(grupoService).createGrupos(data.getGrupo(), data.getCampeonato());
    }

    @Test
    void testGetGrupoByTorneo_ReturnsListOfGrupos() {
        // Datos de prueba
        Integer torneoId = 1;
        Grupo grupo1 = new Grupo();
        grupo1.setId(1);
        grupo1.setCodigo("Grupo A");
        Grupo grupo2 = new Grupo();
        grupo2.setId(2);
        grupo2.setCodigo("Grupo B");
        List<Grupo> grupos = Arrays.asList(grupo1, grupo2);

        // Configura el comportamiento del servicio
        when(grupoService.listarGrupoByTorneo(torneoId)).thenReturn(grupos);

        // Ejecuta el método a probar
        List<Grupo> resultado = grupoController.getGrupoByTorneo(torneoId);

        // Verifica el resultado
        assertEquals(grupos, resultado);
    }
}
