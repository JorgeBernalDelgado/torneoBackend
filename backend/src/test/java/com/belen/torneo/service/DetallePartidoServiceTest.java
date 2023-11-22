package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.DetallePartido;
import com.belen.torneo.repository.DetallePartidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetallePartidoServiceTest {

    @Mock
    private DetallePartidoRepository detallePartidoRepository;

    @InjectMocks
    private DetallePartidoService detallePartidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<DetallePartido> detallePartidos = new ArrayList<>(); // Agregar detallePartidos a la lista
        when(detallePartidoRepository.findAll()).thenReturn(detallePartidos);

        // Act
        List<DetallePartido> resultado = detallePartidoService.listar();

        // Assert
        assertEquals(detallePartidos, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        DetallePartido detallePartido = new DetallePartido(/* Construir el objeto con los datos necesarios */);
        when(detallePartidoRepository.save(detallePartido)).thenReturn(detallePartido);

        // Act
        DetallePartido resultado = detallePartidoService.save(detallePartido);

        // Assert
        assertEquals(detallePartido, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int detallePartidoId = 1;

        // Act
        detallePartidoService.delete(detallePartidoId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio de detallePartido
        verify(detallePartidoRepository, times(1)).deleteById(detallePartidoId);
    }

    @Test
    public void testListarDetallePartidoByTorneo() {
        // Arrange
        Campeonato campeonatoMock = new Campeonato(/* Construir el objeto con los datos necesarios */);
        List<DetallePartido> detallePartidos = new ArrayList<>(); // Agregar detallePartidos a la lista
        when(detallePartidoRepository.findByCampeonato(campeonatoMock)).thenReturn(detallePartidos);

        // Act
        List<DetallePartido> resultado = detallePartidoService.listarDetallePartidoByTorneo(campeonatoMock);

        // Assert
        assertEquals(detallePartidos, resultado);
    }
}
