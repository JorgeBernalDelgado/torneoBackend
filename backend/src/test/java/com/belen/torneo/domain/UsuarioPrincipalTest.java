package com.belen.torneo.domain;

import com.belen.torneo.enums.ERol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioPrincipalTest {

    private UsuarioPrincipal usuario;
    @BeforeEach
    public void setUp() {
        // Configurar datos de prueba antes de cada prueba individual
        usuario = new UsuarioPrincipal("John Doe", "john.doe", "contrasena", null, 1);
    }

    @Test
    public void testConstructorAndGetters() {
        String nombre = "John Doe";
        String usuario = "john";
        String contrasena = "password";
        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        Integer idUsuario = 1;

        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(nombre, usuario, contrasena, authorities, idUsuario);

        assertEquals(nombre, usuarioPrincipal.getNombre());
        assertEquals(usuario, usuarioPrincipal.getUsername());
        assertEquals(contrasena, usuarioPrincipal.getPassword());
        assertEquals(authorities, usuarioPrincipal.getAuthorities());
        assertEquals(idUsuario, usuarioPrincipal.getIdUsuario());
    }

    @Test
    public void testBuild() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Jane Doe");
        usuario.setUsuario("jane");
        usuario.setContrasena("secret");
        usuario.setId(2);
        Rol rol = new Rol();
        rol.setNombre(ERol.ROL_ADMIN);
        usuario.setRoles(Collections.singleton(rol));

        UsuarioPrincipal usuarioPrincipal = UsuarioPrincipal.build(usuario);

        assertEquals(usuario.getNombre(), usuarioPrincipal.getNombre());
        assertEquals(usuario.getUsuario(), usuarioPrincipal.getUsername());
        assertEquals(usuario.getContrasena(), usuarioPrincipal.getPassword());
        assertTrue(usuarioPrincipal.getAuthorities().contains(new SimpleGrantedAuthority("ROL_ADMIN")));
        assertEquals(usuario.getId(), usuarioPrincipal.getIdUsuario());
    }

    @Test
    public void testUserDetailsMethods() {
        // Crear un UsuarioPrincipal de prueba
        String usuario = "testuser";
        String contrasena = "password";
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuario, contrasena);

        // Verificar los métodos de UserDetails
        assertEquals(usuario, usuarioPrincipal.getUsername());
        assertEquals(contrasena, usuarioPrincipal.getPassword());
        assertTrue(usuarioPrincipal.getAuthorities() == null);
        assertTrue(usuarioPrincipal.isAccountNonExpired());
        assertTrue(usuarioPrincipal.isAccountNonLocked());
        assertTrue(usuarioPrincipal.isCredentialsNonExpired());
        assertTrue(usuarioPrincipal.isEnabled());
    }

}
