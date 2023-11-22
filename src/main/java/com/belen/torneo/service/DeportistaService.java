package com.belen.torneo.service;

import com.belen.torneo.domain.Deportista;
import com.belen.torneo.repository.DeportistaRepository;
import com.belen.torneo.repository.EquipoDeportistaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeportistaService {

    private final Logger log = LoggerFactory.getLogger(DeportistaService.class);

    @Autowired
    DeportistaRepository deportistaRepository;

    @Autowired
    EquipoDeportistaRepository equipoDeportistaRepository;

    public List<Deportista> listar() {
        log.debug("Request to get all Deportistas");
        return deportistaRepository.findAll();
    }

    public Deportista save(Deportista deportista) {
        log.debug("Request to get all Deportistas");
        return deportistaRepository.save(deportista);
    }

    public Deportista update(Deportista deportista) {
        log.debug("Request to get all Deportistas");
        return deportistaRepository.save(deportista);
    }

    public void delete(int id) {
        log.debug("Request to get all Deportistas");
        Optional<Deportista> deportistaDelete = deportistaRepository.findById(id);
        equipoDeportistaRepository.deleteByIdDeportista(deportistaDelete.get());
        deportistaRepository.deleteById(id);
    }

    public Optional<Deportista> listarDeportista(Integer deportista) {
        log.debug("Request to get all Deportistas");
        return deportistaRepository.findById(deportista);
    }

}
