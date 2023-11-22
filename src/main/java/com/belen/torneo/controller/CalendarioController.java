package com.belen.torneo.controller;

import com.belen.torneo.domain.Calendario;
import com.belen.torneo.service.CalendarioService;
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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${spring.domain}")
@Api(tags = "Calendario", description = "Métodos de la tabla calendario")
public class CalendarioController {

    private final Logger log = LoggerFactory.getLogger(CalendarioController.class);

    @Autowired
    private CalendarioService calendarioService;

    @PostMapping("/calendarios")
    @ApiOperation("Crear un calendario")
    public ResponseEntity<Calendario> createCalendario(@RequestBody Calendario calendario) {
        try {
            if (calendario.getId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Calendario calendarioGuardar = calendarioService.save(calendario);
            return new ResponseEntity<>(calendarioGuardar, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/calendarios/listar", params="idCampeonato")
    @ApiOperation("Listar calendarios enviando el id de un campeonato")
    public List<Calendario> getCalendarios(@RequestParam Integer campeonato) {
        log.debug("Request to get all Calendarios");
        return calendarioService.listarDia(campeonato);
    }

    @DeleteMapping("/calendarios/{id}")
    @ApiOperation("Eliminar calendario envuando el id")
    public void delete(@PathVariable("id") int id) {
        calendarioService.delete(id);
    }

    @PutMapping("/calendarios")
    @ApiOperation("Actualizar un calendario")
    public ResponseEntity<Calendario> update(@RequestBody Calendario calendario) {
        try {
            Calendario calendarioEditar = calendarioService.update(calendario);
            return new ResponseEntity<>(calendarioEditar, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/calendarios/listarConToken", params="campeonato")
    @ApiOperation("Listar calendarios enviando el id del campeonato con token")
    public List<Calendario> getCalendariosWithToken(@RequestParam Integer campeonato) {
        log.debug("Request to get all Calendarios");
        return calendarioService.listarDia(campeonato);
    }

    @GetMapping(value="/calendarios/listarByCampeonato", params="campeonato")
    @ApiOperation("Listar calednarios enviando el id del campeonato")
    public List<Calendario> getCalendariosByCampeonato(@RequestParam Integer campeonato) {
        log.debug("Request to get all Calendarios");
        return calendarioService.listarDia(campeonato);
    }
}
