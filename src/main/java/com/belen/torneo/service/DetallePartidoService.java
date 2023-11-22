package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.DetallePartido;
import com.belen.torneo.repository.DetallePartidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetallePartidoService {

    private final Logger log = LoggerFactory.getLogger(DetallePartidoService.class);

    @Autowired
    DetallePartidoRepository detallePartidoRepository;

    public List<DetallePartido> listar() {
        log.debug("Request to get all detallePartido");
        return detallePartidoRepository.findAll();
    }

    public DetallePartido save(DetallePartido detallePartido) {
        log.debug("Request to get all detallePartido");
        return detallePartidoRepository.save(detallePartido);
    }

    public void delete(int id) {
        detallePartidoRepository.deleteById(id);
    }

    public List<DetallePartido> listarDetallePartidoByTorneo(Campeonato torneo) {
        return detallePartidoRepository.findByCampeonato(torneo);
    }
}
