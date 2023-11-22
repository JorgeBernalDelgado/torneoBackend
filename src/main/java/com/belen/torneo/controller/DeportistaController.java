package com.belen.torneo.controller;

import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.exceptions.TorneoException;
import com.belen.torneo.service.CampeonatoService;
import com.belen.torneo.service.DeportistaService;
import com.belen.torneo.service.EquipoDeportistaService;
import com.belen.torneo.service.EquipoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
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
@Setter
@RequestMapping("/api")
@CrossOrigin(origins = "${spring.domain}")
@Api(tags = "Deportista", description = "Métodos de la tabla deportista")
public class DeportistaController {

    private final Logger log = LoggerFactory.getLogger(DeportistaController.class);

    @Autowired
    private DeportistaService deportistaService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private CampeonatoService campeonatoService;

    @Autowired
    private EquipoDeportistaService equipoDeportistaService;

    @GetMapping("/deportistas/listar")
    @ApiOperation("Listar todos los deportistas")
    public List<Deportista> getDeportistas() {
        log.debug("Request to get all deportistas");
        return deportistaService.listar();
    }

    @PostMapping("/deportistas")
    @ApiOperation("Crear un deportista")
    public ResponseEntity<?> createDeportista(@RequestBody EquipoDeportista deportista) {
        try {
            if(validarNumeroDeportista(deportista)){
                Deportista deportistaGuardar = deportistaService.save(deportista.getIdDeportista());
                EquipoDeportista equipoDeportistaGuardar = new EquipoDeportista();
                equipoDeportistaGuardar.setIdEquipo(deportista.getIdEquipo());
                equipoDeportistaGuardar.setIdDeportista(deportistaGuardar);
                equipoDeportistaGuardar.setIdCampeonato(deportista.getIdCampeonato());
                equipoDeportistaService.save(equipoDeportistaGuardar);
                return new ResponseEntity<>(deportistaGuardar, HttpStatus.CREATED);
            }
            else{
                throw new TorneoException("Su equipo ya cuenta con el Número máximo de jugadores permitidos!");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value= "/deportistas/listarDeportista", params="deportista")
    @ApiOperation("Listar deportista enviando el id")
    public Optional<Deportista> getDeportista(@RequestParam Integer deportista) {
        log.debug("Request to get all deportistas");
        return deportistaService.listarDeportista(deportista);
    }

    @PutMapping("/deportistas")
    @ApiOperation("Actualizar un deportista")
    public ResponseEntity<Deportista> update(@RequestBody Deportista deportista) {
        try {
            Deportista deportistaEditar = deportistaService.update(deportista);
            return new ResponseEntity<>(deportistaEditar, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deportistas/{id}")
    @ApiOperation("Eliminar un deportista")
    public void delete(@PathVariable("id") int id) {
        deportistaService.delete(id);
    }

    public boolean validarNumeroDeportista(EquipoDeportista deportista){
        List<EquipoDeportista> deportistas = equipoDeportistaService.listarEquipoDeportistaByEquipo
                (deportista.getIdEquipo(), deportista.getIdCampeonato());
        if(deportista.getIdCampeonato().getDeporte() == 1 && deportistas.size() >= 15){
            return false;
        }else if(deportista.getIdCampeonato().getDeporte() == 2 && deportistas.size() >= 12){
            return false;
        }else if(deportista.getIdCampeonato().getDeporte() == 3 && deportistas.size() >= 10){
            return false;
        }
        return true;
    }
}
