package com.belen.torneo.service;

import com.belen.torneo.domain.Datos;
import com.belen.torneo.repository.DatosRepository;
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
public class DatosServiceTest {

    @InjectMocks
    private DatosService datosService;

    @Mock
    private DatosRepository datosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        Datos dato = new Datos(/* Construir el objeto con los datos necesarios */);
        when(datosService.save(dato)).thenReturn(dato);

        // Act
        Datos resultado = datosService.save(dato);

        // Assert
        assertEquals(dato, resultado);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Datos> dato = new ArrayList<>(); // Agregar calendarios a la lista
        when(datosRepository.findAll()).thenReturn(dato);

        // Act
        List<Datos> resultado = datosService.listar();

        // Assert
        assertEquals(dato, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int idDato = 1;

        // Act
        datosService.delete(idDato);

        // Assert
        verify(datosRepository, times(1)).deleteById(idDato);
    }

    @Test
    public void testListarDatosByCategoria() {
        // Arrange
        String dato = "categoria";
        List<Datos> datos = new ArrayList<>(); // Agregar datos a la lista
        when(datosRepository.findByCategoria(dato)).thenReturn(datos);

        // Act
        List<Datos> resultado = datosService.listarDatosByCategoria(dato);

        // Assert
        assertEquals(datos, resultado);
    }

}
