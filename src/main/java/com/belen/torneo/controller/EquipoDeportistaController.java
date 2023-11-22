package com.belen.torneo.controller;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.DeportistaService;
import com.belen.torneo.service.EquipoDeportistaService;
import com.belen.torneo.service.EquipoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${spring.domain}")
@Api(tags = "Equipo Deportista", description = "Métodos de la tabla equipo_deportista")
public class EquipoDeportistaController {

    private final Logger log = LoggerFactory.getLogger(EquipoDeportistaController.class);

    @Autowired
    private EquipoDeportistaService equipoDeportistaService;

    @Autowired
    private CampeonatoService campeonatoService;

    @Autowired
    private DeportistaService deportistaService;

    @Autowired
    private EquipoService equipoService;

    @GetMapping(value= "/equiposDeportistas/listarEquipoDeportistasByCampeonato", params={"torneo","jugador"})
    @ApiOperation("Listar todos los deportistas con su equipo y campeonato enviando el id del campeonato y el id del deportista")
    public List<EquipoDeportista> getEquipoDeportistaByCampeonato(@RequestParam Integer torneo, @RequestParam Integer jugador) {
        Optional<Campeonato> campeonato = campeonatoService.listarCampeonato(torneo);
        Optional<Deportista> deportista = deportistaService.listarDeportista(jugador);
        log.debug("Request to get all equipos");
        return equipoDeportistaService.listarEquipoDeportistaByCampeonato(campeonato.get(), deportista.get());
    }

    @GetMapping(value= "/equiposDeportistas/listarEquipoDeportistasByEquipo", params={"equipo", "campeonato"})
    @ApiOperation("Listar todos los deportistas con su equipo y campeonato enviando el id del campeonato y el id del equipo")
    public List<EquipoDeportista> getEquipoDeportistaByEquipo(@RequestParam Integer equipo, @RequestParam Integer campeonato) {
        Optional<Equipo> equipoValue = equipoService.listarEquipo(equipo);
        Optional<Campeonato> campeonatoValue = campeonatoService.listarCampeonato(campeonato);
        log.debug("Request to get all equipos");
        return equipoDeportistaService.listarEquipoDeportistaByEquipo(equipoValue.get(),campeonatoValue.get());
    }

    @GetMapping(value= "/equiposDeportistas/listarDeportistaAnotaciones", params="torneo")
    @ApiOperation("Listar los primeros 10 deportistas que tengan mas anotaciones en todo el campeonato")
    public List<EquipoDeportista> getDeportistaAnotaciones(@RequestParam Integer torneo) {
        log.debug("Request to get all deportistas");
        return equipoDeportistaService.listarDeportistaAnotaciones(torneo);
    }

    @GetMapping(value= "/equiposDeportistas/listarVallaMenosVencida", params="torneo")
    @ApiOperation("Listar los primeros 10 porteros que tengan menos goles recibidos")
    public List<EquipoDeportista> getVallaMenosVencida(@RequestParam Integer torneo) {
        log.debug("Request to get all deportistas");
        return equipoDeportistaService.listarVallaMenosVencida(torneo);
    }

}
