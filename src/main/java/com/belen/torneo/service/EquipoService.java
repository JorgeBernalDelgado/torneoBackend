package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Equipo;
import com.belen.torneo.domain.Usuario;
import com.belen.torneo.repository.EquipoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipoService {
	
	private final Logger log = LoggerFactory.getLogger(EquipoService.class);
	
	@Autowired
    EquipoRepository equipoRepository;
	
	public List<Equipo> listar() {
        log.debug("Request to get all Campeonatos");
        return equipoRepository.findAll();
    }
    
    public Equipo save(Equipo equipo) {
        log.debug("Request to get all Campeonatos");
        return equipoRepository.save(equipo);
    }

    public Equipo update(Equipo equipo) {
        log.debug("Request to get all Equipos");
        return equipoRepository.save(equipo);
    }

    public void delete(int id) {
        log.debug("Request to get all Equipos");
        equipoRepository.deleteById(id);
    }

    public Optional<Equipo> listarEquipo(Integer equipo) {
        log.debug("Request to get all Equipos");
        return equipoRepository.findById(equipo);
    }

    public List<Equipo> listarEquipoByTorneo(Campeonato campeonato) {
        log.debug("Request to get all Equipos");
        return equipoRepository.findByIdCampeonato(campeonato);
    }

    public List<Equipo> listarEquipoByName(String nombre) {
        log.debug("Request to get all Equipos");
        return equipoRepository.findByNombre(nombre);
    }

    public List<Equipo> listarEquipoByDelegado(Usuario usuario, Campeonato campeonato) {
        log.debug("Request to get all Equipos");
        return equipoRepository.findByDelegadoAndIdCampeonato(usuario, campeonato);
    }

    public List<Equipo> listarPosicionEquipos(Integer torneo, Integer grupo) {
        log.debug("Request to get all Deportistas");
        return equipoRepository.getPosicionEquipos(torneo, grupo);
    }

}
