package com.belen.torneo.service;

import com.belen.torneo.domain.Deportista;
import com.belen.torneo.repository.DeportistaRepository;
import com.belen.torneo.repository.EquipoDeportistaRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeportistaServiceTest {

    @Mock
    private DeportistaRepository deportistaRepository;

    @Mock
    private EquipoDeportistaRepository equipoDeportistaRepository;

    @InjectMocks
    private DeportistaService deportistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Deportista> deportistas = new ArrayList<>(); // Agregar deportistas a la lista
        when(deportistaRepository.findAll()).thenReturn(deportistas);

        // Act
        List<Deportista> resultado = deportistaService.listar();

        // Assert
        assertEquals(deportistas, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Deportista deportista = new Deportista(/* Construir el objeto con los datos necesarios */);
        when(deportistaRepository.save(deportista)).thenReturn(deportista);

        // Act
        Deportista resultado = deportistaService.save(deportista);

        // Assert
        assertEquals(deportista, resultado);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Deportista deportista = new Deportista(/* Construir el objeto con los datos necesarios */);
        when(deportistaRepository.save(deportista)).thenReturn(deportista);

        // Act
        Deportista resultado = deportistaService.update(deportista);

        // Assert
        assertEquals(deportista, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int deportistaId = 1;
        Deportista deportistaMock = new Deportista(/* Construir el objeto con los datos necesarios */);
        when(deportistaRepository.findById(deportistaId)).thenReturn(Optional.of(deportistaMock));

        // Act
        deportistaService.delete(deportistaId);

        // Assert
        // Verificar que se haya llamado al método deleteByIdDeportista del repositorio de equipoDeportista
        verify(equipoDeportistaRepository, times(1)).deleteByIdDeportista(deportistaMock);

        // Verificar que se haya llamado al método deleteById del repositorio de deportista
        verify(deportistaRepository, times(1)).deleteById(deportistaId);
    }

    @Test
    public void testListarDeportistaExistente() {
        // Arrange
        Integer deportistaId = 1;
        Deportista deportistaMock = new Deportista(/* Construir el objeto con los datos necesarios */);
        when(deportistaRepository.findById(deportistaId)).thenReturn(Optional.of(deportistaMock));

        // Act
        Optional<Deportista> resultado = deportistaService.listarDeportista(deportistaId);

        // Assert
        assertEquals(deportistaMock, resultado.get());
    }

    @Test
    public void testListarDeportistaNoExistente() {
        // Arrange
        Integer deportistaId = 2;
        when(deportistaRepository.findById(deportistaId)).thenReturn(Optional.empty());

        // Act
        Optional<Deportista> resultado = deportistaService.listarDeportista(deportistaId);

        // Assert
        assertEquals(Optional.empty(), resultado);
    }
}
