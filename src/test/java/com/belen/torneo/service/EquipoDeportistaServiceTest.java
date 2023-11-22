package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.repository.EquipoDeportistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipoDeportistaServiceTest {

    @Mock
    private EquipoDeportistaRepository equipoDeportistaRepository;

    @InjectMocks
    private EquipoDeportistaService equipoDeportistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        EquipoDeportista equipoDeportista = new EquipoDeportista(/* Construir el objeto con los datos necesarios */);
        when(equipoDeportistaRepository.save(equipoDeportista)).thenReturn(equipoDeportista);

        // Act
        EquipoDeportista resultado = equipoDeportistaService.save(equipoDeportista);

        // Assert
        assertEquals(equipoDeportista, resultado);
    }

    @Test
    public void testListarEquipoDeportistaByCampeonato() {
        // Arrange
        Campeonato campeonatoMock = new Campeonato(/* Construir el objeto con los datos necesarios */);
        Deportista deportistaMock = new Deportista(/* Construir el objeto con los datos necesarios */);
        List<EquipoDeportista> equipoDeportistas = new ArrayList<>(); // Agregar equipoDeportistas a la lista
        when(equipoDeportistaRepository.findByIdCampeonatoAndIdDeportista(campeonatoMock, deportistaMock))
                .thenReturn(equipoDeportistas);

        // Act
        List<EquipoDeportista> resultado = equipoDeportistaService.listarEquipoDeportistaByCampeonato(campeonatoMock, deportistaMock);

        // Assert
        assertEquals(equipoDeportistas, resultado);
    }

    @Test
    public void testListarEquipoDeportistaByEquipo() {
        // Arrange
        Equipo equipoMock = new Equipo(/* Construir el objeto con los datos necesarios */);
        Campeonato campeonatoMock = new Campeonato(/* Construir el objeto con los datos necesarios */);
        List<EquipoDeportista> equipoDeportistas = new ArrayList<>(); // Agregar equipoDeportistas a la lista
        when(equipoDeportistaRepository.findByIdEquipoAndIdCampeonato(equipoMock, campeonatoMock))
                .thenReturn(equipoDeportistas);

        // Act
        List<EquipoDeportista> resultado = equipoDeportistaService.listarEquipoDeportistaByEquipo(equipoMock, campeonatoMock);

        // Assert
        assertEquals(equipoDeportistas, resultado);
    }

    @Test
    public void testListarDeportistaAnotaciones() {
        // Arrange
        Integer torneoMock = 123; // Valor de ejemplo para el torneo
        List<EquipoDeportista> equipoDeportistas = new ArrayList<>(); // Agregar equipoDeportistas a la lista
        when(equipoDeportistaRepository.getDeportistaAnotaciones(torneoMock))
                .thenReturn(equipoDeportistas);

        // Act
        List<EquipoDeportista> resultado = equipoDeportistaService.listarDeportistaAnotaciones(torneoMock);

        // Assert
        assertEquals(equipoDeportistas, resultado);
    }

    @Test
    public void testListarVallaMenosVencida() {
        // Arrange
        Integer torneoMock = 456; // Valor de ejemplo para el torneo
        List<EquipoDeportista> equipoDeportistas = new ArrayList<>(); // Agregar equipoDeportistas a la lista
        when(equipoDeportistaRepository.getVallaMenosVencida(torneoMock))
                .thenReturn(equipoDeportistas);

        // Act
        List<EquipoDeportista> resultado = equipoDeportistaService.listarVallaMenosVencida(torneoMock);

        // Assert
        assertEquals(equipoDeportistas, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int equipoDeportistaId = 789; // Id de ejemplo para el equipoDeportista

        // Act
        equipoDeportistaService.delete(equipoDeportistaId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio de equipoDeportista
        verify(equipoDeportistaRepository, times(1)).deleteById(equipoDeportistaId);
    }
}
