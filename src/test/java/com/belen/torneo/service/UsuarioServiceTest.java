package com.belen.torneo.service;

import com.belen.torneo.domain.Usuario;
import com.belen.torneo.repository.UsuarioRepository;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListar() {
        // Arrange
        List<Usuario> usuarios = new ArrayList<>(); // Agregar usuarios a la lista
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.listar();

        // Assert
        assertEquals(usuarios, resultado);
    }

    @Test
    public void testSave() {
        // Arrange
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.save(usuario);

        // Assert
        assertEquals(usuario, resultado);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.update(usuario);

        // Assert
        assertEquals(usuario, resultado);
    }

    @Test
    public void testDelete() {
        // Arrange
        int usuarioId = 123; // Id de ejemplo para el usuario

        // Act
        usuarioService.delete(usuarioId);

        // Assert
        // Verificar que se haya llamado al método deleteById del repositorio de usuario
        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    public void testListarDelegados() {
        // Arrange
        int delegadoId = 456; // Id de ejemplo para el delegado
        int torneoId = 1;
        int torneoId2 = 0;
        List<Usuario> usuarios = new ArrayList<>(); // Agregar usuarios a la lista
        when(usuarioRepository.listarDelegado(delegadoId, torneoId)).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.listarDelegados(delegadoId, torneoId);

        // Assert
        assertEquals(usuarios, resultado);

        if(torneoId2  == 0){
            List<Usuario> usuarios2 = new ArrayList<>(); // Agregar usuarios a la lista
            when(usuarioRepository.listarPlanillero(delegadoId)).thenReturn(usuarios);

            // Act
            List<Usuario> resultado2 = usuarioService.listarDelegados(delegadoId, torneoId2);

            // Assert
            assertEquals(usuarios2, resultado2);
        }
    }

    @Test
    public void testListarUsuariosAdmin() {
        // Arrange
        int adminId = 789; // Id de ejemplo para el administrador
        List<Usuario> usuarios = new ArrayList<>(); // Agregar usuarios a la lista
        when(usuarioRepository.listarUsuarioAdmin(adminId)).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.listarUsuariosAdmin(adminId);

        // Assert
        assertEquals(usuarios, resultado);
    }

    @Test
    public void testListarUsuario() {
        // Arrange
        int usuarioId = 987; // Id de ejemplo para el usuario
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> resultado = usuarioService.listarUsuario(usuarioId);

        // Assert
        assertEquals(Optional.of(usuario), resultado);
    }

    @Test
    public void testGetByUsuario() {
        // Arrange
        String nombreUsuario = "usuarioEjemplo";
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        when(usuarioRepository.findByUsuario(nombreUsuario)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> resultado = usuarioService.getByUsuario(nombreUsuario);

        // Assert
        assertEquals(Optional.of(usuario), resultado);
    }

    @Test
    public void testValidarExisteUsuario() {
        // Arrange
        String nombreUsuario = "usuarioEjemplo";
        String identificacion = "12345";
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        when(usuarioRepository.validarExisteUsuario(nombreUsuario, identificacion)).thenReturn(usuario);

        // Act
        Usuario resultado = usuarioService.validarExisteUsuario(nombreUsuario, identificacion);

        // Assert
        assertEquals(usuario, resultado);
    }
}
