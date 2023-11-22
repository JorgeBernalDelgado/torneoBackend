package com.belen.torneo.service;

import com.belen.torneo.domain.*;
import com.belen.torneo.repository.CampeonatoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CampeonatoService {
	
	private final Logger log = LoggerFactory.getLogger(CampeonatoService.class);
	
	@Autowired
    CampeonatoRepository campeonatoRepository;

	@Autowired
    EquipoService equipoService;

	@Autowired
    EquipoDeportistaService equipoDeportistaService;

	@Autowired
    DetallePartidoService detallePartidoService;

	@Autowired
    GrupoService grupoService;
    
    public List<Campeonato> listar() {
        log.debug("Request to get all Campeonatos");
        return campeonatoRepository.findAll();
    }
    
    public Campeonato save(Campeonato campeonato) {
        log.debug("Request to get all Campeonatos");
        return campeonatoRepository.save(campeonato);
    }
    
    public Optional<Campeonato> listarCampeonato(Integer campeonato) {
        log.debug("Request to get all Campeonatos");
        return campeonatoRepository.findById(campeonato);
    }

    public void delete(int id) {
        Optional<Campeonato> campeonato = campeonatoRepository.findById(id);
        List<Equipo> listaEquipos = equipoService.listarEquipoByTorneo(campeonato.get());
        for (Equipo equipo:listaEquipos) {
            List<EquipoDeportista> listaDeportista =
                    equipoDeportistaService.listarEquipoDeportistaByEquipo(equipo, campeonato.get());
            for (EquipoDeportista deportista:listaDeportista) {
                equipoDeportistaService.delete(deportista.getId());
            }
            equipoService.delete(equipo.getId());
        }
        List<DetallePartido> detallePartidos = detallePartidoService.listarDetallePartidoByTorneo(campeonato.get());
        for (DetallePartido detalle:detallePartidos) {
            detallePartidoService.delete(detalle.getId());
        }
        List<Grupo> grupos = grupoService.listarGrupoByTorneo(campeonato.get().getId());
        for (Grupo grupo:grupos) {
            grupoService.delete(grupo.getId());
        }
        campeonatoRepository.deleteById(id);
    }


}
