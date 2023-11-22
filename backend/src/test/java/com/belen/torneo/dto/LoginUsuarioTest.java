package com.belen.torneo.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginUsuarioTest {

    @InjectMocks
    private LoginUsuario loginUsuario;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testUsuarioContrasenaValidos() {
        loginUsuario.setUsuario("usuario123");
        loginUsuario.setContrasena("contrasena456");
        LoginUsuario loginUsuario1 = new LoginUsuario();
        loginUsuario1.setUsuario(loginUsuario.getUsuario());
        loginUsuario1.setContrasena(loginUsuario.getContrasena());

        when(bindingResult.hasErrors()).thenReturn(false);

        assertFalse(bindingResult.hasErrors());
    }

    @Test
    public void testUsuarioVacio() {
        loginUsuario.setUsuario("");
        loginUsuario.setContrasena("contrasena456");

        when(bindingResult.hasErrors()).thenReturn(true);

        assertTrue(bindingResult.hasErrors());
    }

    @Test
    public void testContrasenaVacia() {
        loginUsuario.setUsuario("usuario123");
        loginUsuario.setContrasena("");

        when(bindingResult.hasErrors()).thenReturn(true);

        assertTrue(bindingResult.hasErrors());
    }
}
