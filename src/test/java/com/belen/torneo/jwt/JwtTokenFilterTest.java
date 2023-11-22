package com.belen.torneo.jwt;

import com.belen.torneo.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JwtTokenFilterTest {

    @Mock
    private JwtProvider jwtProvider;

    //@Mock
    //private UserDetailsService userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternal_ValidToken_Success() throws ServletException, IOException {
        // Token válido
        String validToken = "valid_token";
        String username = "user";
        UserDetails userDetails = new User(username, "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(jwtProvider.validateToken(validToken)).thenReturn(true);
        when(jwtProvider.getNombreUsuarioFromToken(validToken)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);

        // Verificar que se establece la autenticación en el contexto de seguridad
        UsernamePasswordAuthenticationToken expectedAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        assertEquals(expectedAuth, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testDoFilterInternal_InvalidToken_NoAuthentication() throws ServletException, IOException {
        // Token inválido
        String invalidToken = "invalid_token";

        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidToken);
        when(jwtProvider.validateToken(invalidToken)).thenReturn(false);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        SecurityContextHolder.getContext().setAuthentication(null);
        // Verificar que no se establece ninguna autenticación en el contexto de seguridad
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testDoFilterInternal_NoToken_NoAuthentication() throws ServletException, IOException {
        // No se proporciona token en la solicitud
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtTokenFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);

        // Verificar que no se establece ninguna autenticación en el contexto de seguridad
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
