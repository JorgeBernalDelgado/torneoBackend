package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Grupo;
import com.belen.torneo.repository.CampeonatoRepository;
import com.belen.torneo.repository.GrupoRepository;
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
public class GrupoServiceTest {

    @Mock
    private GrupoRepository grupoRepository;
    @Mock
    private CampeonatoRepository campeonatoRepository;

    @InjectMocks
    private GrupoService grupoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Grupo> grupos = new ArrayList<>(); // Agregar grupos a la lista
        when(grupoRepository.findAll()).thenReturn(grupos);

        // Act
        List<Grupo> resultado = grupoService.listar();

        // Assert
        assertEquals(grupos, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Grupo grupo = new Grupo(/* Construir el objeto con los datos necesarios */);
        when(grupoRepository.save(grupo)).thenReturn(grupo);

        // Act
        Grupo resultado = grupoService.save(grupo);

        // Assert
        assertEquals(grupo, resultado);
    }

    @Test
    public void testListarGrupo() {
        // Arrange
        int grupoId = 456; // Id de ejemplo para el grupo
        Grupo grupo = new Grupo(/* Construir el objeto con los datos necesarios */);
        when(grupoRepository.findById(grupoId)).thenReturn(Optional.of(grupo));

        // Act
        Optional<Grupo> resultado = grupoService.listarGrupo(grupoId);

        // Assert
        assertEquals(Optional.of(grupo), resultado);
    }

    @Test
    public void testCreateGrupos() {
        // Arrange
        int grupoMock = 3; // Valor de ejemplo para el grupo
        int campeonatoMock = 789; // Valor de ejemplo para el campeonato
        Optional<Campeonato> campeonatoSaveMock = Optional.of(new Campeonato(/* Construir el objeto con los datos necesarios */));
        when(campeonatoRepository.findById(campeonatoMock)).thenReturn(campeonatoSaveMock);

        // Act
        grupoService.createGrupos(grupoMock, campeonatoMock);

        // Assert
        // Verificar que se haya llamado al método save del repositorio de grupo el número de veces esperado
        verify(grupoRepository, times(grupoMock)).save(any(Grupo.class));
    }

    @Test
    public void testListarGrupoByTorneo() {
        // Arrange
        int torneoMock = 123; // Valor de ejemplo para el torneo
        Optional<Campeonato> campeonatoSaveMock = Optional.of(new Campeonato(/* Construir el objeto con los datos necesarios */));
        List<Grupo> grupos = new ArrayList<>(); // Agregar grupos a la lista
        when(campeonatoRepository.findById(torneoMock)).thenReturn(campeonatoSaveMock);
        when(grupoRepository.findByIdCampeonato(campeonatoSaveMock.get())).thenReturn(grupos);

        // Act
        List<Grupo> resultado = grupoService.listarGrupoByTorneo(torneoMock);

        // Assert
        assertEquals(grupos, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int grupoId = 789; // Id de ejemplo para el grupo

        // Act
        grupoService.delete(grupoId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio de grupo
        verify(grupoRepository, times(1)).deleteById(grupoId);
    }
}
