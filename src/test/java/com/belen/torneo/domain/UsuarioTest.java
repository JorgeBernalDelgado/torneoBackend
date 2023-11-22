package com.belen.torneo.domain;

import com.belen.torneo.enums.ERol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setUsuario("usuario1");
        usuario.setIdentificacion("12345");
        usuario.setContrasena("password");
        usuario.setCelular(1234567890L);
        usuario.setNombre("John");
        usuario.setApellido("Doe");

        Set<Rol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre(ERol.ROL_ADMIN);
        roles.add(rol);
        usuario.setRoles(roles);
    }

    @Test
    public void testGetters() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre(ERol.ROL_ADMIN);
        assertEquals(1, usuario.getId());
        assertEquals("usuario1", usuario.getUsuario());
        assertEquals("12345", usuario.getIdentificacion());
        assertEquals("password", usuario.getContrasena());
        assertEquals(1234567890L, usuario.getCelular());
        assertEquals("John", usuario.getNombre());
        assertEquals("Doe", usuario.getApellido());

        assertEquals(1, usuario.getRoles().size());
    }
}
