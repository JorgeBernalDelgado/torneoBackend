package com.belen.torneo.service;

import com.belen.torneo.domain.Datos;
import com.belen.torneo.repository.DatosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DatosService {

    private final Logger log = LoggerFactory.getLogger(DeportistaService.class);

    @Autowired
    DatosRepository datosRepository;

    public List<Datos> listar() {
        log.debug("Request to get all datos");
        return datosRepository.findAll();
    }

    public Datos save(Datos datos) {
        log.debug("Request to get all datos");
        return datosRepository.save(datos);
    }

    public void delete(int id) {
        log.debug("Request to get all datos");
        datosRepository.deleteById(id);
    }

    public List<Datos> listarDatosByCategoria(String categoria) {
        log.debug("Request to get all datos");
        return datosRepository.findByCategoria(categoria);
    }
}
