package com.belen.torneo.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class JwtEntryPointTest {

    @Mock
    private Logger logger;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authenticationException;

    @InjectMocks
    private JwtEntryPoint jwtEntryPoint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCommence_Success() throws IOException, ServletException {
        // Simular una solicitud HTTP y una respuesta HTTP
        when(request.getRequestURI()).thenReturn("/api/usuarios/listarUsuariosAdmin");
        when(authenticationException.getMessage()).thenReturn("Some error message");

        // Ejecutar el método commence
        jwtEntryPoint.commence(request, response, authenticationException);

        // Verificar que se envió una respuesta de error con el código SC_UNAUTHORIZED
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
    }

}
