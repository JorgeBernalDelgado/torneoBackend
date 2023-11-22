package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.service.CampeonatoService;
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
@Api(tags = "Campeonato", description = "Métodos de la tabla campeonato")
public class CampeonatoController {
	
	private final Logger log = LoggerFactory.getLogger(CampeonatoController.class);
	
	@Autowired
	private CampeonatoService campeonatoService;
	
	@GetMapping("/campeonatos/listar")
	@ApiOperation("Listar todos los campeonatos")
	public List<Campeonato> getCampeonatos() {
		log.debug("Request to get all Campeonatos");
        return campeonatoService.listar();
	}
	
	@PostMapping("/campeonatos")
	@ApiOperation("Crear un campeonato")
    public ResponseEntity<Campeonato> createCampeonato(@RequestBody Campeonato campeonato) {
		try {
			if (campeonato.getId() != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        }
	        Campeonato campeonatoGuardar = campeonatoService.save(campeonato);
	        return new ResponseEntity<>(campeonatoGuardar, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@GetMapping(value="/campeonatos/listarCampeonato", params="campeonato")
	@ApiOperation("Listar campeonato enviando el id")
	public Optional<Campeonato> getCampeonato(@RequestParam Integer campeonato) {
		log.debug("Request to get all Campeonatos");
        return campeonatoService.listarCampeonato(campeonato);
	}

	@DeleteMapping("/campeonatos/{id}")
	@ApiOperation("Eliminar campeonato enviando el id")
	public void delete(@PathVariable("id") int id) {
		campeonatoService.delete(id);
	}
	
}
