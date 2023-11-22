package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.service.DeportistaService;
import com.belen.torneo.service.EquipoDeportistaService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeportistaControllerTest {

    @Mock
    private DeportistaService deportistaService;

    @Mock
    private EquipoDeportistaService equipoDeportistaService;

    @InjectMocks
    private DeportistaController deportistaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getDeportistas_ReturnsListOfDeportistas() {
        List<Deportista> expectedDeportistas = Arrays.asList(
                new Deportista(),
                new Deportista()
        );

        when(deportistaService.listar()).thenReturn(expectedDeportistas);
        List<Deportista> result = deportistaController.getDeportistas();

        assertEquals(expectedDeportistas, result);
        verify(deportistaService, times(1)).listar();
    }

    @Test
    void createDeportista_ValidDeportista_ReturnsCreatedResponse() {
        EquipoDeportista deportista = new EquipoDeportista();
        Deportista deportistaAGuardar = new Deportista();
        deportista.setIdDeportista(deportistaAGuardar);
        Equipo equipo = new Equipo();
        Campeonato campeonato = new Campeonato();
        campeonato.setNombreCampeonato("Campeonato de Fútbol");
        campeonato.setEstado("En curso");
        campeonato.setDeporte(1);
        campeonato.setCategoria(2);
        campeonato.setRama("Masculino");
        campeonato.setPlanilla(12345);
        campeonato.setLocalidad(456);
        campeonato.setRangoAnnio(2023);
        campeonato.setDivision(1);
        campeonato.setCarnet("ABC123");
        deportista.setIdEquipo(equipo);
        deportista.setIdCampeonato(campeonato);

        when(equipoDeportistaService.listarEquipoDeportistaByEquipo(any(), any())).thenReturn(new ArrayList<>());
        when(deportistaService.save(any())).thenReturn(deportistaAGuardar);
        ResponseEntity<?> response = deportistaController.createDeportista(deportista);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(deportistaAGuardar, response.getBody());
        verify(equipoDeportistaService, times(1)).listarEquipoDeportistaByEquipo(any(), any());
        verify(deportistaService, times(1)).save(any());
    }

    @Test
    public void testGetDeportista() {
        // Datos de prueba
        Integer idDeportista = 1;
        Deportista deportistaMock = new Deportista();
        deportistaMock.setId(idDeportista);
        // Configura el comportamiento del servicio mockeado
        when(deportistaService.listarDeportista(idDeportista)).thenReturn(Optional.of(deportistaMock));

        // Ejecuta el método a probar
        Optional<Deportista> resultado = deportistaController.getDeportista(idDeportista);

        // Verifica el resultado
        assert(resultado.isPresent()); // Verifica que el resultado no es null
        assert(resultado.get().getId().equals(idDeportista)); // Verifica que el id del deportista retornado coincide con el idDeportista proporcionado
    }

    @Test
    public void testGetDeportista_DeportistaNoEncontrado() {
        // Datos de prueba
        Integer idDeportista = 1;

        // Configura el comportamiento del servicio mockeado para retornar un Optional vacío
        when(deportistaService.listarDeportista(idDeportista)).thenReturn(Optional.empty());

        // Ejecuta el método a probar
        Optional<Deportista> resultado = deportistaController.getDeportista(idDeportista);

        // Verifica el resultado
        assert(!resultado.isPresent()); // Verifica que el resultado sea un Optional vacío
    }

    @Test
    public void testUpdate_DeportistaActualizado() {
        // Datos de prueba
        Deportista deportistaMock = new Deportista();
        deportistaMock.setId(1);

        // Configura el comportamiento del servicio mockeado
        when(deportistaService.update(deportistaMock)).thenReturn(deportistaMock);

        // Ejecuta el método a probar
        ResponseEntity<Deportista> resultado = deportistaController.update(deportistaMock);

        // Verifica el resultado
        assert(resultado.getStatusCode().equals(HttpStatus.CREATED)); // Verifica que el estado de la respuesta sea HttpStatus.CREATED
        assert(resultado.getBody() != null); // Verifica que la respuesta contenga el deportista actualizado
        assert(resultado.getBody().getId().equals(deportistaMock.getId())); // Verifica que el ID del deportista retornado coincida con el ID del deportista proporcionado
    }

    @Test
    public void testUpdate_ErrorInterno() {
        // Datos de prueba
        Deportista deportistaMock = new Deportista();
        deportistaMock.setId(1);

        // Configura el comportamiento del servicio mockeado para lanzar una excepción
        when(deportistaService.update(deportistaMock)).thenThrow(new RuntimeException());

        // Ejecuta el método a probar
        ResponseEntity<Deportista> resultado = deportistaController.update(deportistaMock);

        // Verifica el resultado
        assert(resultado.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)); // Verifica que el estado de la respuesta sea HttpStatus.INTERNAL_SERVER_ERROR
        assert(resultado.getBody() == null); // Verifica que la respuesta no contenga ningún deportista (se espera un valor nulo debido a la excepción lanzada)
    }

    @Test
    public void testDelete_DeportistaEliminado() {
        // Datos de prueba
        int idDeportistaAEliminar = 1;

        // Ejecuta el método a probar
        deportistaController.delete(idDeportistaAEliminar);

        // Verifica el comportamiento esperado del servicio mockeado
        verify(deportistaService, times(1)).delete(idDeportistaAEliminar);
    }

    @Test
    public void testDelete_DeportistaNoEncontrado() {
        // Datos de prueba
        int idDeportistaNoExistente = 999;

        // Configura el comportamiento del servicio mockeado para lanzar una excepción
        doThrow(new RuntimeException()).when(deportistaService).delete(idDeportistaNoExistente);

        // Ejecuta el método a probar
        try {
            deportistaController.delete(idDeportistaNoExistente);
            // La excepción debería haber sido lanzada, así que si el flujo llega a este punto, la prueba falla.
            assert(false);
        } catch (Exception e) {
            // Verifica que se haya lanzado la excepción esperada
            assert(e instanceof RuntimeException);
        }

        // Verifica el comportamiento esperado del servicio mockeado
        verify(deportistaService, times(1)).delete(idDeportistaNoExistente);
    }

    @Test
    void createDeportista_InvalidDeportista_ReturnsInternalServerErrorResponse() {
        // Arrange
        EquipoDeportista deportista = new EquipoDeportista();
        Equipo equipo = new Equipo();
        Campeonato campeonato = new Campeonato();
        campeonato.setNombreCampeonato("Campeonato de Fútbol");
        campeonato.setEstado("En curso");
        campeonato.setDeporte(1);
        campeonato.setCategoria(2);
        campeonato.setRama("Masculino");
        campeonato.setPlanilla(12345);
        campeonato.setLocalidad(456);
        campeonato.setRangoAnnio(2023);
        campeonato.setDivision(1);
        campeonato.setCarnet("ABC123");
        deportista.setIdEquipo(equipo);
        deportista.setIdCampeonato(campeonato);

        when(equipoDeportistaService.listarEquipoDeportistaByEquipo(any(), any())).thenReturn(new ArrayList<>());
        when(deportistaService.save(any())).thenThrow(new RuntimeException("Algo salió mal"));

        ResponseEntity<?> response = deportistaController.createDeportista(deportista);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(equipoDeportistaService, times(1)).listarEquipoDeportistaByEquipo(any(), any());
        verify(deportistaService, times(1)).save(any());
    }

}
