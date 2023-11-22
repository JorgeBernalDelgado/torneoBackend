package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.DetallePartido;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.DetallePartidoService;
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
@Api(tags = "Detalle Partido", description = "Métodos de la tabla detalle_partido")
public class DetallePartidoController {

    private final Logger log = LoggerFactory.getLogger(DetallePartidoController.class);

    @Autowired
    private DetallePartidoService detallePartidoService;

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping("/detallePartidos/listar")
    @ApiOperation("Listar todos los partidos jugados con su detalle")
    public List<DetallePartido> getDetallePartidos() {
        log.debug("Request to get all Equipos");
        return detallePartidoService.listar();
    }

    @PostMapping("/detallePartidos")
    @ApiOperation("Crear un partido con su detalle")
    public ResponseEntity<DetallePartido> createDetallePartido(@RequestBody DetallePartido detallePartido) {
        try {
            if (detallePartido.getId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            DetallePartido valueGuardar = detallePartidoService.save(detallePartido);
            return new ResponseEntity<>(valueGuardar, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value= "/detallePartidos/listarDetalleByCampeonato", params="torneo")
    @ApiOperation("Listar el detalle de los partidos jugados")
    public List<DetallePartido> getDetallePartido(@RequestParam Integer torneo) {
        log.debug("Request to get all detalle");
        Optional<Campeonato> campeonato = campeonatoService.listarCampeonato(torneo);
        return detallePartidoService.listarDetallePartidoByTorneo(campeonato.get());
    }
}
