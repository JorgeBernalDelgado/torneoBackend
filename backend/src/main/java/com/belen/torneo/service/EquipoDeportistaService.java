package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Deportista;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.EquipoDeportista;
import com.belen.torneo.repository.EquipoDeportistaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EquipoDeportistaService {

    private final Logger log = LoggerFactory.getLogger(EquipoDeportistaService.class);

    @Autowired
    EquipoDeportistaRepository equipoDeportistaRepository;

    public EquipoDeportista save(EquipoDeportista equipo) {
        log.debug("Request to get all EquipoDeportistas");
        return equipoDeportistaRepository.save(equipo);
    }

    public List<EquipoDeportista> listarEquipoDeportistaByCampeonato(Campeonato campeonato, Deportista deportista) {
        log.debug("Request to get all Equipos");
        return equipoDeportistaRepository.findByIdCampeonatoAndIdDeportista(campeonato, deportista);
    }

    public List<EquipoDeportista> listarEquipoDeportistaByEquipo(Equipo equipo, Campeonato campeonato) {
        log.debug("Request to get all Equipos");
        return equipoDeportistaRepository.findByIdEquipoAndIdCampeonato(equipo,campeonato);
    }

    public List<EquipoDeportista> listarDeportistaAnotaciones(Integer torneo) {
        log.debug("Request to get all Deportistas");
        return equipoDeportistaRepository.getDeportistaAnotaciones(torneo);
    }

    public List<EquipoDeportista> listarVallaMenosVencida(Integer torneo) {
        log.debug("Request to get all Deportistas");
        return equipoDeportistaRepository.getVallaMenosVencida(torneo);
    }

    public void delete(int id) {
        equipoDeportistaRepository.deleteById(id);
    }

}
