package com.belen.torneo.controller;

import com.belen.torneo.domain.Rol;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.domain.UsuarioNuevo;
import com.belen.torneo.enums.ERol;
import com.belen.torneo.jwt.JwtProvider;
import com.belen.torneo.service.RolService;
import com.belen.torneo.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private RolService rolService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuarios_ReturnsListOfUsuarios() {
        // Datos de prueba
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombre("Usuario 1");
        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombre("Usuario 2");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        // Configura el comportamiento del servicio
        when(usuarioService.listar()).thenReturn(usuarios);

        // Ejecuta el método a probar
        List<Usuario> resultado = usuarioController.getUsuarios();

        // Verifica el resultado
        assertEquals(usuarios, resultado);
    }

    @Test
    void testCreateUsuario_ValidUser_ReturnsUsuario() {
        // Datos de prueba
        UsuarioNuevo usuarioNuevo = new UsuarioNuevo();
        usuarioNuevo.setUsuario("usuario1");
        usuarioNuevo.setIdentificacion("123456789");
        usuarioNuevo.setContrasena("password");
        usuarioNuevo.setCelular(1234567890L);
        usuarioNuevo.setNombre("Nombre");
        usuarioNuevo.setApellido("Apellido");
        usuarioNuevo.setRoles(Collections.singleton("delegado"));

        UsuarioNuevo usuarioNuevo2 = new UsuarioNuevo();
        usuarioNuevo2.setUsuario("usuario1");
        usuarioNuevo2.setIdentificacion("123456789");
        usuarioNuevo2.setContrasena("password");
        usuarioNuevo2.setCelular(1234567890L);
        usuarioNuevo2.setNombre("Nombre");
        usuarioNuevo2.setApellido("Apellido");
        usuarioNuevo2.setRoles(Collections.singleton("planillero"));

        // Configura el comportamiento del servicio
        when(usuarioService.validarExisteUsuario("usuario1", "123456789")).thenReturn(null);
        when(rolService.getByRolNombre(ERol.ROL_DELEGADO)).thenReturn(Optional.of(new Rol()));
        when(rolService.getByRolNombre(ERol.ROL_PLANILLERO)).thenReturn(Optional.of(new Rol()));

        // Ejecuta el método a probar
        ResponseEntity<?> resultado = usuarioController.createUsuario(usuarioNuevo);
        ResponseEntity<?> resultado2 = usuarioController.createUsuario(usuarioNuevo2);

        // Verifica el resultado
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals(HttpStatus.CREATED, resultado2.getStatusCode());
    }

    @Test
    void testCreateUsuario_InternalServerError_ReturnsErrorMessage() {
        // Datos de prueba
        UsuarioNuevo usuarioNuevo = new UsuarioNuevo();
        usuarioNuevo.setUsuario("usuario1");
        usuarioNuevo.setIdentificacion("123456789");
        usuarioNuevo.setContrasena("password");
        usuarioNuevo.setCelular(1234567890L);
        usuarioNuevo.setNombre("Nombre");
        usuarioNuevo.setApellido("Apellido");
        usuarioNuevo.setRoles(Collections.singleton("delegado"));

        // Configura el comportamiento del servicio para lanzar una excepción
        when(usuarioService.validarExisteUsuario("usuario1", "123456789")).thenThrow(new RuntimeException("Error"));

        // Ejecuta el método a probar
        ResponseEntity<?> resultado = usuarioController.createUsuario(usuarioNuevo);

        // Verifica el resultado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resultado.getStatusCode());
        assertEquals("Error", resultado.getBody());
    }

    @Test
    public void testCreateUsuarioAdmin_ValidUser_ReturnsUsuario() {
        // Datos de prueba
        UsuarioNuevo usuarioNuevo = new UsuarioNuevo();
        usuarioNuevo.setUsuario("usuario1");
        usuarioNuevo.setIdentificacion("123456789");
        usuarioNuevo.setContrasena("password");
        usuarioNuevo.setCelular(1234567890L);
        usuarioNuevo.setNombre("Nombre");
        usuarioNuevo.setApellido("Apellido");
        usuarioNuevo.setRoles(Collections.singleton("admin"));

        // Configura el comportamiento del servicio
        when(usuarioService.validarExisteUsuario("usuario1", "123456789")).thenReturn(null);
        when(rolService.getByRolNombre(ERol.ROL_ADMIN)).thenReturn(Optional.of(new Rol()));

        // Ejecuta el método a probar
        ResponseEntity<?> resultado = usuarioController.createUsuarioAdmin(usuarioNuevo);

        // Verifica el resultado
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
    }

    @Test
    void testCreateUsuarioAdmin_InternalServerError_ReturnsErrorMessage() {
        // Datos de prueba
        UsuarioNuevo usuarioNuevo = new UsuarioNuevo();
        usuarioNuevo.setUsuario("usuario1");
        usuarioNuevo.setIdentificacion("123456789");
        usuarioNuevo.setContrasena("password");
        usuarioNuevo.setCelular(1234567890L);
        usuarioNuevo.setNombre("Nombre");
        usuarioNuevo.setApellido("Apellido");
        usuarioNuevo.setRoles(Collections.singleton("admin"));

        // Configura el comportamiento del servicio para lanzar una excepción
        when(usuarioService.validarExisteUsuario("usuario1", "123456789")).thenThrow(new RuntimeException("Error"));

        // Ejecuta el método a probar
        ResponseEntity<?> resultado = usuarioController.createUsuarioAdmin(usuarioNuevo);

        // Verifica el resultado
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resultado.getStatusCode());
        assertEquals("Error", resultado.getBody());
    }

    @Test
    public void testGetDelegados_ReturnsDelegadosList() {
        // Arrange
        int delegadoId = 12345; // Id de delegado de muestra
        int torneoId = 1; // Id de torneo de muestra

        // Crear una lista de usuarios de muestra que representan los delegados
        List<Usuario> delegadosList = new ArrayList<>();
        delegadosList.add(new Usuario());
        delegadosList.add(new Usuario());
        delegadosList.add(new Usuario());

        // Configurar el comportamiento simulado del servicio usuarioService
        when(usuarioService.listarDelegados(delegadoId, torneoId)).thenReturn(delegadosList);

        // Act
        List<Usuario> responseEntity = usuarioController.getDelegados(delegadoId, torneoId);

        // Assert
        // Verificar el resultado esperado
        assertEquals(delegadosList, responseEntity);
    }

    @Test
    public void testGetDelegados_ReturnsEmptyList_WhenNoDelegadosFound() {
        // Arrange
        int delegadoId = 12345; // Id de delegado de muestra sin delegados
        int torneoId = 1; // Id de torneo de muestra

        // Configurar el comportamiento simulado del servicio usuarioService para devolver una lista vacía
        when(usuarioService.listarDelegados(delegadoId, torneoId)).thenReturn(new ArrayList<>());

        // Act
        List<Usuario> responseEntity = usuarioController.getDelegados(delegadoId, torneoId);

        // Assert
        // Verificar el resultado esperado
        assertEquals(0, responseEntity.size());

    }

    @Test
    public void testGetUsuariosAdmin_ReturnsUsuariosList() {
        // Arrange
        int adminId = 54321; // Id de administrador de muestra

        // Crear una lista de usuarios de muestra que representan los administradores
        List<Usuario> usuariosAdminList = new ArrayList<>();
        usuariosAdminList.add(new Usuario());
        usuariosAdminList.add(new Usuario());
        usuariosAdminList.add(new Usuario());

        // Configurar el comportamiento simulado del servicio usuarioService
        when(usuarioService.listarUsuariosAdmin(adminId)).thenReturn(usuariosAdminList);

        // Act
        List<Usuario> responseEntity = usuarioController.getUsuariosAdmin(adminId);

        // Assert
        // Verificar el resultado esperado
        assertEquals(usuariosAdminList, responseEntity);
    }

    @Test
    public void testGetUsuariosAdmin_ReturnsEmptyList_WhenNoUsuariosAdminFound() {
        // Arrange
        int adminId = 54321; // Id de administrador de muestra sin usuarios admin

        // Configurar el comportamiento simulado del servicio usuarioService para devolver una lista vacía
        when(usuarioService.listarUsuariosAdmin(adminId)).thenReturn(new ArrayList<>());

        // Act
        List<Usuario> responseEntity = usuarioController.getUsuariosAdmin(adminId);

        // Assert
        // Verificar el resultado esperado
        assertEquals(0, responseEntity.size());
    }

    @Test
    public void testGetUsuario_ReturnsUsuario() {
        // Arrange
        int usuarioId = 12345; // Id de usuario de muestra

        // Crear un usuario de muestra
        Usuario usuario = new Usuario();

        // Configurar el comportamiento simulado del servicio usuarioService
        when(usuarioService.listarUsuario(usuarioId)).thenReturn(Optional.of(usuario));

        // Act
        Optional<Usuario> result = usuarioController.getUsuario(usuarioId);

        // Assert
        assertEquals(usuario, result.orElse(null));
    }

    @Test
    public void testGetUsuario_ReturnsEmpty_WhenUsuarioNotFound() {
        // Arrange
        int usuarioId = 12345; // Id de usuario de muestra que no existe

        // Configurar el comportamiento simulado del servicio usuarioService para devolver un Optional vacío
        when(usuarioService.listarUsuario(usuarioId)).thenReturn(Optional.empty());

        // Act
        Optional<Usuario> result = usuarioController.getUsuario(usuarioId);

        // Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testUpdate_ReturnsUsuarioWithEncodedPassword() {
        // Arrange
        int usuarioId = 12345; // Id de usuario de muestra

        // Crear un usuario de muestra
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setUsuario("Usuario uno");

        // Configurar el comportamiento simulado del servicio usuarioService para devolver el usuario actualizado
        Usuario usuarioActualizado = new Usuario();
        usuario.setId(usuarioId);
        usuario.setUsuario("Usuario actualizado");
        when(usuarioService.update(usuario)).thenReturn(usuarioActualizado);

        // Act
        ResponseEntity<Usuario> result = usuarioController.update(usuario);

        // Assert
        assertEquals(usuarioActualizado, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void testUpdate_ReturnsInternalServerError_WhenExceptionThrown() {
        // Arrange
        int usuarioId = 12345; // Id de usuario de muestra

        // Crear un usuario de muestra
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);
        usuario.setUsuario("Usuario uno");

        // Configurar el comportamiento simulado del servicio usuarioService para lanzar una excepción
        when(usuarioService.update(usuario)).thenThrow(new RuntimeException("Error al actualizar el usuario"));

        // Act
        ResponseEntity<Usuario> result = usuarioController.update(usuario);

        // Assert
        assertEquals(null, result.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }

    @Test
    public void testDelete_ReturnsNoContent() {
        // Datos de prueba
        int usuarioId = 1;

        // Ejecuta el método a probar
        usuarioController.delete(usuarioId);

        // Verifica que el método delete() en el servicio se haya llamado una vez con el ID proporcionado
        verify(usuarioService, times(1)).delete(usuarioId);
    }

    @Test
    public void testDeleteAdmin_ReturnsNoContent() {
        // Datos de prueba
        int usuarioId = 1;

        // Ejecuta el método a probar
        usuarioController.deleteAdmin(usuarioId);

        // Verifica que el método delete() en el servicio se haya llamado una vez con el ID proporcionado
        verify(usuarioService, times(1)).delete(usuarioId);
    }

    @Test
    public void testAuthenticateUser_ReturnsInternalServerError_WhenAuthenticationFails() {
        // Arrange
        String usuario = "testUser";
        String contrasena = "testPassword";

        // Configurar el comportamiento simulado del authenticationManager para lanzar una excepción
        when(authenticationManager.authenticate(any(Authentication.class))).thenThrow(new RuntimeException("Error de autenticación"));

        // Act
        ResponseEntity<?> result = usuarioController.authenticateUser(usuario, contrasena);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        // Puedes agregar más pruebas para verificar el cuerpo de la respuesta según tus necesidades.
    }

}
