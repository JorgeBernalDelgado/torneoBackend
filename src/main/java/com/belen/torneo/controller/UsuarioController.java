package com.belen.torneo.controller;

import com.belen.torneo.domain.Rol;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.domain.UsuarioNuevo;
import com.belen.torneo.domain.UsuarioPrincipal;
import com.belen.torneo.dto.JwtDto;
import com.belen.torneo.enums.ERol;
import com.belen.torneo.exceptions.TorneoException;
import com.belen.torneo.jwt.JwtProvider;
import com.belen.torneo.service.RolService;
import com.belen.torneo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Api(tags = "Usuario", description = "Métodos de la tabla usuario")
public class UsuarioController {
	
	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	RolService rolService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/usuarios/listar")
	@ApiOperation("Listar todos los usuarios")
	public List<Usuario> getUsuarios() {
		log.debug("Request to get all usuarios");
        return usuarioService.listar();
	}

	@PostMapping("/usuarios")
	@ApiOperation("Crear un usuario de rol delegado o planillero")
    public ResponseEntity<?> createUsuario(@Valid @RequestBody UsuarioNuevo usuarioNuevo) {
		try {
			Usuario usuarioValidate = usuarioService.validarExisteUsuario(usuarioNuevo.getUsuario(), usuarioNuevo.getIdentificacion());
			if(usuarioValidate == null){
				Usuario usuario =
						new Usuario(usuarioNuevo.getUsuario(), usuarioNuevo.getIdentificacion(),passwordEncoder.encode(usuarioNuevo.getContrasena()),
								usuarioNuevo.getCelular(),usuarioNuevo.getNombre(),usuarioNuevo.getApellido());
				Set<Rol> roles = new HashSet<>();
				if(usuarioNuevo.getRoles().contains("delegado"))
					roles.add(rolService.getByRolNombre(ERol.ROL_DELEGADO).get());
				else if(usuarioNuevo.getRoles().contains("planillero"))
					roles.add(rolService.getByRolNombre(ERol.ROL_PLANILLERO).get());
				usuario.setRoles(roles);
				Usuario usuarioGuardar = usuarioService.save(usuario);
				return new ResponseEntity<>(usuarioGuardar, HttpStatus.CREATED);
			}else{
				throw new TorneoException("Usuario o Identificación Ya existe");
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }

	@PostMapping("/usuarios/crearAdmin")
	@ApiOperation("Crear un usuario de rol admin")
	public ResponseEntity<?> createUsuarioAdmin(@Valid @RequestBody UsuarioNuevo usuarioNuevo) {
		try {
			Usuario usuarioValidate = usuarioService.validarExisteUsuario(usuarioNuevo.getUsuario(), usuarioNuevo.getIdentificacion());
			if(usuarioValidate == null){
				Usuario usuario =
						new Usuario(usuarioNuevo.getUsuario(), usuarioNuevo.getIdentificacion(),passwordEncoder.encode(usuarioNuevo.getContrasena()),
								usuarioNuevo.getCelular(),usuarioNuevo.getNombre(),usuarioNuevo.getApellido());
				Set<Rol> roles = new HashSet<>();
				if(usuarioNuevo.getRoles().contains("admin"))
					roles.add(rolService.getByRolNombre(ERol.ROL_ADMIN).get());
				usuario.setRoles(roles);
				Usuario usuarioGuardar = usuarioService.save(usuario);
				return new ResponseEntity<>(usuarioGuardar, HttpStatus.CREATED);
			}else{
				throw new TorneoException("Usuario o Identificación Ya existe");
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping(value= "/usuarios/listarDelegados", params={"delegado","torneo"})
	@ApiOperation("Listar un usuario delegado o planillero enviando el id")
	public List<Usuario> getDelegados(@RequestParam Integer delegado, @RequestParam Integer torneo) {
		log.debug("Request to get all usuarios");
        return usuarioService.listarDelegados(delegado,torneo);
	}

	@GetMapping(value= "/usuarios/listarUsuariosAdmin", params="admin")
	@ApiOperation("Listar un usuario admin enviando el id")
	public List<Usuario> getUsuariosAdmin(@RequestParam Integer admin) {
		log.debug("Request to get all usuarios");
		return usuarioService.listarUsuariosAdmin(admin);
	}
	
	@GetMapping(value= "/usuarios/listarUsuario", params="usuario")
	@ApiOperation("Listar un usuario enviando el id")
	public Optional<Usuario> getUsuario(@RequestParam Integer usuario) {
		log.debug("Request to get all usuarios");
        return usuarioService.listarUsuario(usuario);
	}
	
	@PutMapping("/usuarios")
	@ApiOperation("Actualizar un usuario")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		try {
			usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
	        Usuario usuarioEditar = usuarioService.update(usuario);
	        return new ResponseEntity<>(usuarioEditar, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	}  
	
	@DeleteMapping("/usuarios/{id}")
	@ApiOperation("Eliminar un usuario planillero o delegado enviando el id")
	public void delete(@PathVariable("id") int id) {
		usuarioService.delete(id);
	}

	@DeleteMapping("/usuarios/deleteAdmin/{id}")
	@ApiOperation("Eliminar un usuario admin el id")
	public void deleteAdmin(@PathVariable("id") int id) {
		usuarioService.delete(id);
	}

	@GetMapping(value="/usuarios/signin", params={"usuario", "contrasena"})
	@ApiOperation("Autenticar el usuario enviando el usuario y contraseña")
	public ResponseEntity<?> authenticateUser(@RequestParam String usuario, @RequestParam String contrasena) {
		try{
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario, contrasena));
			UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtProvider.generateToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(),usuarioPrincipal.getIdUsuario() );
			return new ResponseEntity(jwtDto, HttpStatus.OK);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
