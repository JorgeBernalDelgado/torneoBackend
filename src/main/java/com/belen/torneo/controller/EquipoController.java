package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.exceptions.TorneoException;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.EquipoService;
import com.belen.torneo.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${spring.domain}")
@Api(tags = "Equipo", description = "Métodos de la tabla equipo")
public class EquipoController {
	
	private final Logger log = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private EquipoService equipoService;

	@Autowired
	private CampeonatoService campeonatoService;

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/equipos/listar")
	@ApiOperation("Listar todos los equipos")
	public List<Equipo> getEquipos() {
		log.debug("Request to get all Equipos");
        return equipoService.listar();
	}
	
	@PostMapping("/equipos")
	@ApiOperation("Crear un equipo")
    public ResponseEntity<?> createEquipo(@RequestBody Equipo equipo) {
		try {
			if (equipo.getId() != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
			if(validarDelegado(equipo)){
				Equipo equipooGuardar = equipoService.save(equipo);
				return new ResponseEntity<>(equipooGuardar, HttpStatus.CREATED);
			};
	        throw new TorneoException("El delegado ya tiene otro equipo a cargo! o el nombre del equipo ya existe!");
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@PutMapping("/equipos")
	@ApiOperation("Actualizar un equipo")
	public ResponseEntity<Equipo> update(@RequestBody Equipo equipo) {
		try {
			Equipo equipoEditar = equipoService.update(equipo);
			return new ResponseEntity<>(equipoEditar, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/equipos/{id}")
	@ApiOperation("Eliminar un equipo enviando el id")
	public void delete(@PathVariable("id") int id) {
		equipoService.delete(id);
	}

	@GetMapping(value= "/equipos/listarEquipo", params="equipo")
	@ApiOperation("Listar un equipo enviando el id")
	public Optional<Equipo> getEquipo(@RequestParam Integer equipo) {
		log.debug("Request to get all equipos");
		return equipoService.listarEquipo(equipo);
	}

	@GetMapping(value= "/equipos/listarEquipoByTorneo", params="torneo")
	@ApiOperation("Listar equipos enviando el id del campeonaro")
	public List<Equipo> getEquipoByTorneo(@RequestParam Integer torneo) {
		Optional<Campeonato> campeonato = campeonatoService.listarCampeonato(torneo);
		log.debug("Request to get all equipos");
		return equipoService.listarEquipoByTorneo(campeonato.get());
	}

	@GetMapping(value= "/equipos/listarEquipoByName", params="nombre")
	@ApiOperation("Listar un equipo enviando el nombre")
	public List<Equipo> getEquipoByName(@RequestParam String nombre) {
		return equipoService.listarEquipoByName(nombre);
	}

	@GetMapping(value= "/equipos/listarEquipoByDelegado", params={"delegado","torneo"})
	@ApiOperation("Listar equipos enviando el id del delgado y el id del campeonato")
	public List<Equipo> getEquipoByDelegado(@RequestParam Integer delegado, @RequestParam Integer torneo) {
		Optional<Usuario> usuario = usuarioService.listarUsuario(delegado);
		Optional<Campeonato> campeonato = campeonatoService.listarCampeonato(torneo);
		return equipoService.listarEquipoByDelegado(usuario.get(), campeonato.get());
	}

	@GetMapping(value= "/equipos/listarPosicionEquipos", params={"torneo","grupo"})
	@ApiOperation("Listar la posición de los equipos")
	public List<Equipo> getPosicionEquipos(@RequestParam Integer torneo, @RequestParam Integer grupo) {
		log.debug("Request to get all deportistas");
		return equipoService.listarPosicionEquipos(torneo, grupo);
	}

	public boolean validarDelegado(Equipo equipo) {
		List<Equipo> equipoValid = equipoService.listarEquipoByDelegado(equipo.getDelegado(), equipo.getIdCampeonato());
		List<Equipo> equipoValidNombre = equipoService.listarEquipoByName(equipo.getNombre());
		if (equipoValid.size() > 0) {
			return false;
		}
		else if(equipoValidNombre.size() > 0){
			return false;
		}
		return true;
	}

}
