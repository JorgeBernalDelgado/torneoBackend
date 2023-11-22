package com.belen.torneo.service;

import com.belen.torneo.domain.Calendario;
import com.belen.torneo.repository.CalendarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalendarioService {

    private final Logger log = LoggerFactory.getLogger(CalendarioService.class);

    @Autowired
    CalendarioRepository calendarioRepository;

    public Calendario save(Calendario calendario) {
        log.debug("Request to get all Calendarios");
        return calendarioRepository.save(calendario);
    }

    public List<Calendario> listarDia(Integer campeonato) {
        log.debug("Request to get all Campeonatos");
        return calendarioRepository.listarDia(campeonato);
    }

    public List<Calendario> listar() {
        log.debug("Request to get all Campeonatos");
        return calendarioRepository.findAll();
    }

    public void delete(int id) {
        log.debug("Request to get all Calendarios");
        calendarioRepository.deleteById(id);
    }

    public Calendario update(Calendario calendario) {
        log.debug("Request to get all Calendarios");
        return calendarioRepository.save(calendario);
    }
}
