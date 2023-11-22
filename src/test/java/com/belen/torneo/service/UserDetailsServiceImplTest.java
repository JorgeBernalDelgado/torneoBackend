package com.belen.torneo.service;

import com.belen.torneo.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UsuarioExiste() {
        // Arrange
        String username = "usuarioEjemplo";
        Usuario usuario = new Usuario(/* Construir el objeto con los datos necesarios */);
        usuario.setUsuario("usuarioEjemplo");
        when(usuarioService.getByUsuario(username)).thenReturn(Optional.of(usuario));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        // Verificar que se haya llamado al método getByUsuario del servicio de usuario
        verify(usuarioService, times(1)).getByUsuario(username);
        // Verificar que el UserDetails retornado tenga los datos esperados
        assertEquals(username, userDetails.getUsername());
        // Puedes verificar más atributos de UserDetails si es necesario
    }

}
