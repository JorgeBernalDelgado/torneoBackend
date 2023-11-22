package com.belen.torneo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.xml.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UsuarioNuevoTest {

    private UsuarioNuevo usuarioNuevo;

    @BeforeEach
    public void setUp() {
        usuarioNuevo = new UsuarioNuevo();
        usuarioNuevo.setNombre("John");
        usuarioNuevo.setApellido("Doe");
        usuarioNuevo.setUsuario("johndoe");
        usuarioNuevo.setIdentificacion("johndoe");
        usuarioNuevo.setContrasena("johndoe");
        usuarioNuevo.setCelular(1234567890L);
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        usuarioNuevo.setRoles(roles);
    }

    @Test
    public void testValidUsuarioNuevo() {
        assertEquals("John", usuarioNuevo.getNombre());
        assertEquals("Doe", usuarioNuevo.getApellido());
        assertEquals("johndoe", usuarioNuevo.getUsuario());
        assertEquals("johndoe", usuarioNuevo.getIdentificacion());
        assertEquals(1234567890L, usuarioNuevo.getCelular());
        assertEquals("johndoe", usuarioNuevo.getContrasena());

        assertEquals(1, usuarioNuevo.getRoles().size());
        // Verificar si el usuario tiene los roles esperados
        assertTrue(usuarioNuevo.getRoles().contains("admin"));
    }
}
