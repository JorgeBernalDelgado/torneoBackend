package com.belen.torneo.service;

import com.belen.torneo.domain.Calendario;
import com.belen.torneo.repository.CalendarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalendarioServiceTest {

    @Mock
    private CalendarioRepository calendarioRepository;

    @InjectMocks
    private CalendarioService calendarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        Calendario calendario = new Calendario(/* Construir el objeto con los datos necesarios */);
        when(calendarioRepository.save(calendario)).thenReturn(calendario);

        // Act
        Calendario resultado = calendarioService.save(calendario);

        // Assert
        assertEquals(calendario, resultado);
    }

    @Test
    public void testListarDia() {
        // Arrange
        Integer campeonato = 1;
        List<Calendario> calendarios = new ArrayList<>(); // Agregar calendarios a la lista
        when(calendarioRepository.listarDia(campeonato)).thenReturn(calendarios);

        // Act
        List<Calendario> resultado = calendarioService.listarDia(campeonato);

        // Assert
        assertEquals(calendarios, resultado);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Calendario> calendarios = new ArrayList<>(); // Agregar calendarios a la lista
        when(calendarioRepository.findAll()).thenReturn(calendarios);

        // Act
        List<Calendario> resultado = calendarioService.listar();

        // Assert
        assertEquals(calendarios, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int idCalendario = 1;

        // Act
        calendarioService.delete(idCalendario);

        // Assert
        verify(calendarioRepository, times(1)).deleteById(idCalendario);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Calendario calendario = new Calendario(/* Construir el objeto con los datos necesarios */);
        when(calendarioRepository.save(calendario)).thenReturn(calendario);

        // Act
        Calendario resultado = calendarioService.update(calendario);

        // Assert
        assertEquals(calendario, resultado);
    }
}
