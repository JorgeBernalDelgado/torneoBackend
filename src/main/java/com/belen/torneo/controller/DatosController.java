package com.belen.torneo.controller;

import com.belen.torneo.domain.Datos;
import com.belen.torneo.service.DatosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Setter
@RequestMapping("/api")
@Api(tags = "Datos", description = "Métodos de la tabla datos")
public class DatosController {

    private final Logger log = LoggerFactory.getLogger(CampeonatoController.class);

    @Autowired
    private DatosService datosService;

    @GetMapping("/datos/listar")
    @ApiOperation("Listar todos los datos")
    public List<Datos> getDatos() {
        log.debug("Request to get all datos");
        return datosService.listar();
    }

    @PostMapping("/datos")
    @ApiOperation("Crear un datos")
    public ResponseEntity<Datos> createDatos(@RequestBody Datos datos) {
        try {
            if (datos.getId() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Datos datosGuardar = datosService.save(datos);
            return new ResponseEntity<>(datosGuardar, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value= "/datos/listarDatosByCategoria", params="categoria")
    @ApiOperation("Listar datos enviando la categoria")
    public List<Datos> getDatosByCategoria(@RequestParam String categoria) {
        return datosService.listarDatosByCategoria(categoria);
    }

    @DeleteMapping("/datos/{id}")
    @ApiOperation("Eliminar un dato")
    public void delete(@PathVariable("id") int id) {
        datosService.delete(id);
    }
}
