package com.belen.torneo.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JwtDtoTest {

    @Test
    public void testJwtDtoFields() {
        // Datos de prueba
        String token = "token123";
        String bearer = "Bearer";
        String usuario = "usuario123";
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        Integer idUsuario = 1;

        // Crear objeto JwtDto
        JwtDto jwtDto = new JwtDto();
        jwtDto.setToken(token);
        jwtDto.setBearer(bearer);
        jwtDto.setUsuario(usuario);
        jwtDto.setAuthorities(authorities);
        jwtDto.setIdUsuario(idUsuario);

        JwtDto jwtDto2 = new JwtDto(token, usuario, authorities, idUsuario);
        assertEquals(token, jwtDto2.getToken());

        // Verificar que los campos se han establecido correctamente
        assertEquals(token, jwtDto.getToken());
        assertEquals(bearer, jwtDto.getBearer());
        assertEquals(usuario, jwtDto.getUsuario());
        assertEquals(authorities, jwtDto.getAuthorities());
        assertEquals(idUsuario, jwtDto.getIdUsuario());
    }
}
