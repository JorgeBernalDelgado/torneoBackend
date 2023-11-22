package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.EquipoService;
import com.belen.torneo.service.UsuarioService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoControllerTest {

    @Mock
    private EquipoService equipoService;

    @Mock
    private CampeonatoService campeonatoService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private EquipoController equipoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEquipos_ReturnsListOfEquipos() {
        // Datos de prueba
        Equipo equipo1 = new Equipo(/* Agregar los datos del equipo 1 */);
        Equipo equipo2 = new Equipo(/* Agregar los datos del equipo 2 */);
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(equipo1);
        equipos.add(equipo2);

        // Configurar el comportamiento esperado del servicio mock
        when(equipoService.listar()).thenReturn(equipos);

        // Ejecutar el método getEquipos() del controlador
        List<Equipo> resultado = equipoController.getEquipos();

        // Verificar el resultado esperado
        assertEquals(2, resultado.size());
        assertEquals(equipo1, resultado.get(0));
        assertEquals(equipo2, resultado.get(1));
    }

    @Test
    public void testCreateEquipo_NewEquipo_CreatesEquipoAndReturnsCreatedResponse() {
        // Datos de prueba
        Equipo equipo = new Equipo(/* Agregar los datos del equipo */);

        // Configurar el comportamiento esperado del servicio mock
        when(equipoService.save(equipo)).thenReturn(equipo);

        // Ejecutar el método createEquipo() del controlador
        ResponseEntity<?> response = equipoController.createEquipo(equipo);

        // Verificar el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(equipo, response.getBody());
    }

    @Test
    public void testCreateEquipo_EquipoWithId_ReturnsBadRequest() {
        // Datos de prueba
        Equipo equipo = new Equipo();
        equipo.setId(1); // Simulando que el equipo ya tiene un ID

        // Ejecutar el método createEquipo() del controlador
        ResponseEntity<?> response = equipoController.createEquipo(equipo);

        // Verificar el resultado esperado
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testUpdate_ValidEquipo_ReturnsCreatedResponse() {
        // Datos de prueba
        Equipo equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("Equipo de prueba");

        // Mock del comportamiento del servicio
        when(equipoService.update(any(Equipo.class))).thenReturn(equipo);

        // Ejecuta el método a probar
        ResponseEntity<Equipo> response = equipoController.update(equipo);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(equipo, response.getBody());
    }

    @Test
    public void testUpdate_InvalidEquipo_ReturnsInternalServerError() {
        // Datos de prueba
        Equipo equipo = new Equipo();
        equipo.setId(null); // Equipo sin ID (inválido).

        // Mock del comportamiento del servicio
        when(equipoService.update(any(Equipo.class))).thenThrow(new RuntimeException("Error al actualizar equipo"));

        // Ejecuta el método a probar
        ResponseEntity<Equipo> response = equipoController.update(equipo);

        // Verifica el resultado esperado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDelete_ValidId_CallsEquipoServiceDelete() {
        // Datos de prueba
        int equipoId = 1;

        // Ejecuta el método a probar
        equipoController.delete(equipoId);

        // Verifica que el método delete() en el servicio se haya llamado una vez con el ID proporcionado
        verify(equipoService, times(1)).delete(equipoId);
    }

    @Test
    public void testGetEquipo_ValidId_CallsEquipoServiceListarEquipo() {
        // Datos de prueba
        int equipoId = 1;
        Equipo equipoMock = new Equipo(); // Puedes crear un objeto mock de la clase Equipo

        // Configura el comportamiento del servicio mock para que retorne el equipo mock cuando se llame a listarEquipo() con el ID proporcionado.
        when(equipoService.listarEquipo(equipoId)).thenReturn(Optional.of(equipoMock));

        // Ejecuta el método a probar
        Optional<Equipo> result = equipoController.getEquipo(equipoId);

        // Verifica que el método listarEquipo() en el servicio se haya llamado una vez con el ID proporcionado
        verify(equipoService, times(1)).listarEquipo(equipoId);

        // Verifica que el resultado retornado por el controlador sea igual al equipo mock
        assertEquals(Optional.of(equipoMock), result);
    }

    @Test
    public void testGetEquipoByTorneo_ValidTorneoId_CallsCampeonatoServiceAndEquipoService() {
        // Datos de prueba
        int torneoId = 1;
        Campeonato campeonatoMock = new Campeonato(); // Puedes crear un objeto mock de la clase Campeonato
        List<Equipo> equiposMock = new ArrayList<>(); // Puedes crear una lista de objetos mock de la clase Equipo

        // Configura el comportamiento del servicio mock para que retorne el campeonato mock cuando se llame a listarCampeonato() con el ID proporcionado.
        when(campeonatoService.listarCampeonato(torneoId)).thenReturn(Optional.of(campeonatoMock));
        // Configura el comportamiento del servicio mock para que retorne la lista de equipos mock cuando se llame a listarEquipoByTorneo() con el campeonato mock.
        when(equipoService.listarEquipoByTorneo(campeonatoMock)).thenReturn(equiposMock);

        // Ejecuta el método a probar
        List<Equipo> result = equipoController.getEquipoByTorneo(torneoId);

        // Verifica que el método listarCampeonato() en el servicio se haya llamado una vez con el ID proporcionado
        verify(campeonatoService, times(1)).listarCampeonato(torneoId);

        // Verifica que el método listarEquipoByTorneo() en el servicio se haya llamado una vez con el campeonato mock
        verify(equipoService, times(1)).listarEquipoByTorneo(campeonatoMock);

        // Verifica que el resultado retornado por el controlador sea igual a la lista de equipos mock
        assertEquals(equiposMock, result);
    }

    @Test
    public void testGetEquipoByName_ValidTorneoId() {
        // Datos de prueba
        String torneoName = "Campeonato De futbol";

        List<Equipo> equiposMock = new ArrayList<>(); // Puedes crear una lista de objetos mock de la clase Equipo

        // Configura el comportamiento del servicio mock para que retorne la lista de equipos mock cuando se llame a listarEquipoByTorneo() con el campeonato mock.
        when(equipoService.listarEquipoByName(torneoName)).thenReturn(equiposMock);

        // Ejecuta el método a probar
        List<Equipo> result = equipoController.getEquipoByName(torneoName);

        // Verifica que el método listarEquipoByTorneo() en el servicio se haya llamado una vez con el campeonato mock
        verify(equipoService, times(1)).listarEquipoByName(torneoName);

        // Verifica que el resultado retornado por el controlador sea igual a la lista de equipos mock
        assertEquals(equiposMock, result);
    }

    @Test
    public void testGetEquipoByDelegado() {
        // Datos de prueba
        int delegadoId = 123;
        int torneoId = 456;

        Usuario delegado = new Usuario();
        Campeonato campeonato = new Campeonato();
        List<Equipo> equipos = new ArrayList<>();

        // Configurar comportamiento simulado para los servicios
        when(usuarioService.listarUsuario(anyInt())).thenReturn(Optional.of(delegado));
        when(campeonatoService.listarCampeonato(anyInt())).thenReturn(Optional.of(campeonato));
        when(equipoService.listarEquipoByDelegado(any(Usuario.class), any(Campeonato.class))).thenReturn(equipos);

        // Ejecutar el método a probar
        List<Equipo> result = equipoController.getEquipoByDelegado(delegadoId, torneoId);

        // Verificar el resultado
        assertEquals(equipos, result);
    }

    @Test
    public void testGetPosicionEquipos() {
        // Datos de prueba
        int torneoId = 123;
        int grupoId = 456;

        List<Equipo> equipos = new ArrayList<>();

        // Configurar comportamiento simulado para el servicio
        when(equipoService.listarPosicionEquipos(anyInt(), anyInt())).thenReturn(equipos);

        // Ejecutar el método a probar
        List<Equipo> result = equipoController.getPosicionEquipos(torneoId, grupoId);

        // Verificar el resultado
        assertEquals(equipos, result);
    }

    @Test
    public void testValidarDelegado_WithValidEquipo_ReturnsTrue() {
        // Datos de prueba
        Equipo equipo = new Equipo();
        equipo.setDelegado(new Usuario());
        equipo.setNombre("Equipo A");
        Campeonato campeonato = new Campeonato();
        equipo.setIdCampeonato(campeonato);

        // Configurar comportamiento simulado para el servicio
        when(equipoService.listarEquipoByDelegado(any(), any())).thenReturn(new ArrayList<>());
        when(equipoService.listarEquipoByName(anyString())).thenReturn(new ArrayList<>());

        // Ejecutar el método a probar
        boolean result = equipoController.validarDelegado(equipo);

        // Verificar el resultado
        assertTrue(result);
    }

    @Test
    public void testValidarDelegado_WithInvalidEquipo_ReturnsFalse() {
        // Datos de prueba
        Equipo equipo = new Equipo();
        equipo.setDelegado(new Usuario());
        equipo.setNombre("Equipo A");
        Campeonato campeonato = new Campeonato();
        equipo.setIdCampeonato(campeonato);

        List<Equipo> equiposByDelegado = new ArrayList<>();
        equiposByDelegado.add(new Equipo());

        // Configurar comportamiento simulado para el servicio
        when(equipoService.listarEquipoByDelegado(any(), any())).thenReturn(equiposByDelegado);
        when(equipoService.listarEquipoByName(anyString())).thenReturn(new ArrayList<>());

        // Ejecutar el método a probar
        boolean result = equipoController.validarDelegado(equipo);

        // Verificar el resultado
        assertFalse(result);
    }

    @Test
    void createEquipo_WithException_ReturnsInternalServerErrorResponse() {
        Equipo equipo = new Equipo();
        equipo.setId(null);

        when(equipoService.save(equipo)).thenThrow(new RuntimeException());
        ResponseEntity<?> response = equipoController.createEquipo(equipo);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(equipoService, times(1)).save(equipo);
    }

}
