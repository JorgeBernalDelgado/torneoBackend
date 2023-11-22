package com.belen.torneo.jwt;

import com.belen.torneo.domain.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class JwtProviderTest {

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtProvider jwtProvider;

    @Mock
    private JwtParser jwtParser;

    private final String secret = "mi_clave_secreta";
    private final int expiration = 3600;
    private static final String SECRET_KEY = "your-secret-key";

    @BeforeEach
    public void setUp() {
        jwtProvider.setSecret(secret);
        jwtProvider.setExpiration(expiration);
    }

    @Test
    public void testGenerateToken_ValidAuthentication_Success() {
        // Simulamos una implementación real de Authentication y UserDetails
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal("usuario123", "password123");
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioPrincipal, null);

        JwtProvider jwtProvider = new JwtProvider();
        jwtProvider.setSecret(secret);
        jwtProvider.setExpiration(expiration);

        String token = jwtProvider.generateToken(authentication);

        assertNotNull(token);
    }

    @Test
    public void testGetNombreUsuarioFromToken_ValidToken_Success() {
        String token = Jwts.builder()
                .setSubject("usuario")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);

        assertEquals("usuario", nombreUsuario);
    }

    @Test
    public void testValidateToken_ValidToken_Success() {
        String token = Jwts.builder()
                .setSubject("usuario")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        boolean isValid = jwtProvider.validateToken(token);

        assertTrue(isValid);
    }

    @Test
    public void testValidateToken_EmptyToken_Failure() {
        assertFalse(jwtProvider.validateToken(""));
    }

    @Test
    public void testValidateToken_InvalidToken_Failure() {
        // Token sin firma válida (incorrecta clave secreta)
        String invalidToken = Jwts.builder()
                .setSubject("usuario")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, "clave_incorrecta")
                .compact();

        assertFalse(jwtProvider.validateToken(invalidToken));
    }

    @Test
    public void testValidateToken_EmptyToken() {
        // Simula el comportamiento de lanzar una Excepción IllegalArgumentException
        when(jwtParser.parseClaimsJws(any())).thenThrow(IllegalArgumentException.class);

        // Ejecuta el método con un token vacío
        boolean result = jwtProvider.validateToken("");

        // Verifica que el resultado sea falso (false)
        assertFalse(result);
    }

    @Test
    public void testValidateToken_ExpiredToken_ExceptionThrown() {
        String expiredToken = JwtProviderTest.generateExpiredToken();
        boolean result = jwtProvider.validateToken(expiredToken);

        assertFalse(result);
    }

    @Test
    public void testValidateToken_UnsupportedToken_ExceptionThrown() {
        String unsupportedToken = JwtProviderTest.generateUnsupportedToken();
        boolean result = jwtProvider.validateToken(unsupportedToken);

        assertFalse(result);
    }

    @Test
    public void testValidateToken_MalformedToken() {
        when(jwtParser.parseClaimsJws(any())).thenThrow(MalformedJwtException.class);

        boolean result = jwtProvider.validateToken("token_mal_formado");

        assertFalse(result);
    }

    public static String generateExpiredToken() {
        // Fecha actual
        Date now = new Date();

        // Fecha de expiración en el pasado (por ejemplo, hace 1 minuto)
        Date expirationDate = new Date(now.getTime() - 60000);

        // Generar el token con la fecha de expiración en el pasado
        String token = Jwts.builder()
                .setSubject("username")
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return token;
    }

    public static String generateUnsupportedToken() {
        // Generar el token con un algoritmo de firma no soportado (por ejemplo, "HS256")
        String token = Jwts.builder()
                .setSubject("username")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }
}
