
package com.belen.torneo.controller;

import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Grupo;
import com.belen.torneo.dto.DataGrupo;
import com.belen.torneo.service.GrupoService;
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
@Api(tags = "Grupo", description = "Métodos de la tabla grupo")
public class GrupoController {
	
	private final Logger log = LoggerFactory.getLogger(GrupoController.class);
	
	@Autowired
	private GrupoService grupoService;
	
	@GetMapping("/grupos/listar")
	@ApiOperation("Listar todos los grupos")
	public List<Grupo> getGrupos() {
		log.debug("Request to get all Grupos");
        return grupoService.listar();
	}
	
	@PostMapping("/grupos")
	@ApiOperation("Crear un grupo")
    public ResponseEntity<Grupo> createGrupo(@RequestBody Grupo grupo) {
		try {
			if (grupo.getId() != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	        Grupo grupoGuardar = grupoService.save(grupo);
	        return new ResponseEntity<>(grupoGuardar, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping(value="/grupos/listarGrupo", params="grupo")
	@ApiOperation("Listar un grupo enviando el id")
	public Optional<Grupo> getGrupo(@RequestParam Integer grupo) {
		log.debug("Request to get all Grupos");
        return grupoService.listarGrupo(grupo);
	}

	@PostMapping("/grupos/createGrupos")
	@ApiOperation("Crewr un grupo enviando el id del grupo y el id del campeonato")
	public void createGrupos(@RequestBody DataGrupo data) {
		grupoService.createGrupos(data.getGrupo(),data.getCampeonato());
	}

	@GetMapping(value= "/grupos/listarGrupoByTorneo", params="torneo")
	@ApiOperation("Listar grupos enviando el id del campeonato")
	public List<Grupo> getGrupoByTorneo(@RequestParam Integer torneo) {
		return grupoService.listarGrupoByTorneo(torneo);
	}
	
}
