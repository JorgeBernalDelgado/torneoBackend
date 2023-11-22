package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.repository.EquipoRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoServiceTest {

    @Mock
    public EquipoRepository equipoRepository;

    @InjectMocks
    public EquipoService equipoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Equipo> equipos = new ArrayList<>(); // Agregar equipos a la lista
        when(equipoRepository.findAll()).thenReturn(equipos);

        // Act
        List<Equipo> resultado = equipoService.listar();

        // Assert
        assertEquals(equipos, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Equipo equipo = new Equipo(/* Construir el objeto con los datos necesarios */);
        when(equipoRepository.save(equipo)).thenReturn(equipo);

        // Act
        Equipo resultado = equipoService.save(equipo);

        // Assert
        assertEquals(equipo, resultado);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Equipo equipo = new Equipo(/* Construir el objeto con los datos necesarios */);
        when(equipoRepository.save(equipo)).thenReturn(equipo);

        // Act
        Equipo resultado = equipoService.update(equipo);

        // Assert
        assertEquals(equipo, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int equipoId = 123; // Id de ejemplo para el equipo

        // Act
        equipoService.delete(equipoId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio de equipo
        verify(equipoRepository, times(1)).deleteById(equipoId);
    }

    @Test
    public void testListarEquipo() {
        // Arrange
        int equipoId = 456; // Id de ejemplo para el equipo
        Equipo equipo = new Equipo(/* Construir el objeto con los datos necesarios */);
        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipo));

        // Act
        Optional<Equipo> resultado = equipoService.listarEquipo(equipoId);

        // Assert
        assertEquals(Optional.of(equipo), resultado);
    }

    @Test
    public void testListarEquipoByTorneo() {
        // Arrange
        Campeonato campeonatoMock = new Campeonato(/* Construir el objeto con los datos necesarios */);
        List<Equipo> equipos = new ArrayList<>(); // Agregar equipos a la lista
        when(equipoRepository.findByIdCampeonato(campeonatoMock)).thenReturn(equipos);

        // Act
        List<Equipo> resultado = equipoService.listarEquipoByTorneo(campeonatoMock);

        // Assert
        assertEquals(equipos, resultado);
    }

    @Test
    public void testListarEquipoByName() {
        // Arrange
        String nombre = "Equipo Ejemplo"; // Nombre de ejemplo para el equipo
        List<Equipo> equipos = new ArrayList<>(); // Agregar equipos a la lista
        when(equipoRepository.findByNombre(nombre)).thenReturn(equipos);

        // Act
        List<Equipo> resultado = equipoService.listarEquipoByName(nombre);

        // Assert
        assertEquals(equipos, resultado);
    }

    @Test
    public void testListarEquipoByDelegado() {
        // Arrange
        Usuario usuarioMock = new Usuario(/* Construir el objeto con los datos necesarios */);
        Campeonato campeonatoMock = new Campeonato(/* Construir el objeto con los datos necesarios */);
        List<Equipo> equipos = new ArrayList<>(); // Agregar equipos a la lista
        when(equipoRepository.findByDelegadoAndIdCampeonato(usuarioMock, campeonatoMock)).thenReturn(equipos);

        // Act
        List<Equipo> resultado = equipoService.listarEquipoByDelegado(usuarioMock, campeonatoMock);

        // Assert
        assertEquals(equipos, resultado);
    }

    @Test
    public void testListarPosicionEquipos() {
        // Arrange
        Integer torneoMock = 789; // Valor de ejemplo para el torneo
        Integer grupoMock = 1; // Valor de ejemplo para el grupo
        List<Equipo> equipos = new ArrayList<>(); // Agregar equipos a la lista
        when(equipoRepository.getPosicionEquipos(torneoMock, grupoMock)).thenReturn(equipos);

        // Act
        List<Equipo> resultado = equipoService.listarPosicionEquipos(torneoMock, grupoMock);

        // Assert
        assertEquals(equipos, resultado);
    }
}
