package com.belen.torneo.service;

import com.belen.torneo.domain.*;
import com.belen.torneo.repository.CampeonatoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampeonatoServiceTest {

    @Mock
    private CampeonatoRepository campeonatoRepository;

    @Mock
    private EquipoService equipoService;

    @Mock
    private EquipoDeportistaService equipoDeportistaService;

    @Mock
    private DetallePartidoService detallePartidoService;

    @Mock
    private GrupoService grupoService;

    @InjectMocks
    private CampeonatoService campeonatoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Campeonato> campeonatos = new ArrayList<>(); // Agregar campeonatos a la lista
        when(campeonatoRepository.findAll()).thenReturn(campeonatos);

        // Act
        List<Campeonato> resultado = campeonatoService.listar();

        // Assert
        assertEquals(campeonatos, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Campeonato campeonato = new Campeonato(/* Construir el objeto con los datos necesarios */);
        when(campeonatoRepository.save(campeonato)).thenReturn(campeonato);

        // Act
        Campeonato resultado = campeonatoService.save(campeonato);

        // Assert
        assertEquals(campeonato, resultado);
    }

    @Test
    public void testListarCampeonatoExistente() {
        // Arrange
        Integer campeonatoId = 1;
        Campeonato campeonatoMock = new Campeonato(); // Crear un objeto Campeonato simulado
        when(campeonatoRepository.findById(campeonatoId)).thenReturn(Optional.of(campeonatoMock));

        // Act
        Optional<Campeonato> resultado = campeonatoService.listarCampeonato(campeonatoId);

        // Assert
        assertEquals(campeonatoMock, resultado.get());
    }

    @Test
    public void testListarCampeonatoNoExistente() {
        // Arrange
        Integer campeonatoId = 2;
        when(campeonatoRepository.findById(campeonatoId)).thenReturn(Optional.empty());

        // Act
        Optional<Campeonato> resultado = campeonatoService.listarCampeonato(campeonatoId);

        // Assert
        assertEquals(Optional.empty(), resultado);
    }

    @Test
    public void testDeleteCampeonatoExistente() {
        // Arrange
        int campeonatoId = 1;
        Campeonato campeonatoMock = new Campeonato();
        when(campeonatoRepository.findById(campeonatoId)).thenReturn(Optional.of(campeonatoMock));

        // Simular las listas de equipos, deportistas, detalles de partidos y grupos
        List<Equipo> listaEquipos = new ArrayList<>();
        List<EquipoDeportista> listaDeportistas = new ArrayList<>();
        listaDeportistas.add(new EquipoDeportista());
        List<DetallePartido> listaDetalles = new ArrayList<>();
        List<Grupo> listaGrupos = new ArrayList<>();
        Grupo grupo = new Grupo();
        grupo.setId(1);
        listaGrupos.add(grupo);

        when(equipoService.listarEquipoByTorneo(campeonatoMock)).thenReturn(listaEquipos);
        /*when(equipoDeportistaService.listarEquipoDeportistaByEquipo(new Equipo(), new Campeonato()))
                .thenReturn(listaDeportistas);*/
        when(detallePartidoService.listarDetallePartidoByTorneo(campeonatoMock)).thenReturn(listaDetalles);
        //when(grupoService.listarGrupoByTorneo(campeonatoId)).thenReturn(listaGrupos);

        // Act
        campeonatoService.delete(campeonatoId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio
        verify(campeonatoRepository, times(1)).deleteById(campeonatoId);

        // Verificar que se haya llamado al método delete de los servicios de equipoDeportista y equipo por cada deportista y equipo
        for (Equipo equipo : listaEquipos) {
            verify(equipoService, times(1)).delete(equipo.getId());
            for (EquipoDeportista deportista : listaDeportistas) {
                verify(equipoDeportistaService, times(1)).delete(deportista.getId());
            }
        }

        // Verificar que se haya llamado al método delete de detallePartidoService por cada detalle de partido
        for (DetallePartido detalle : listaDetalles) {
            verify(detallePartidoService, times(1)).delete(detalle.getId());
        }

        // Verificar que se haya llamado al método delete de grupoService por cada grupo
        /*for (Grupo data : listaGrupos) {
            verify(grupoService, times(1)).delete(data.getId());
        }*/
    }

}
