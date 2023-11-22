package com.belen.torneo.service;

import com.belen.torneo.domain.Campeonato;
import com.belen.torneo.domain.Grupo;
import com.belen.torneo.repository.CampeonatoRepository;
import com.belen.torneo.repository.GrupoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrupoService {
	
	private final Logger log = LoggerFactory.getLogger(GrupoService.class);
	
	@Autowired
    GrupoRepository grupoRepository;
    @Autowired
    CampeonatoRepository campeonatoRepository;
    
    public List<Grupo> listar() {
        log.debug("Request to get all Grupos");
        return grupoRepository.findAll();
    }
    
    public Grupo save(Grupo grupo) {
        log.debug("Request to get all Grupos");
        return grupoRepository.save(grupo);
    }
    
    public Optional<Grupo> listarGrupo(Integer grupo) {
        log.debug("Request to get all Grupos");
        return grupoRepository.findById(grupo);
    }

    public void createGrupos(Integer grupo, Integer campeonato) {
        log.debug("Request to get all Grupos");
        Optional<Campeonato> campeonatoSave = campeonatoRepository.findById(campeonato);
        int valorBucle = 1;
        for(char c = 'A'; c <= 'Z'; ++c) {
            for(int i=valorBucle; i<=grupo; i++) {
                System.out.print(c + " ");
                Grupo grupoSave = new Grupo();
                grupoSave.setCodigo("GRUPO" + c);
                grupoSave.setIdCampeonato(campeonatoSave.get());
                grupoRepository.save(grupoSave);
                valorBucle++;
                break;
            }
            if(valorBucle > grupo){
                break;
            }
        }
    }

    public List<Grupo> listarGrupoByTorneo(Integer torneo) {
        log.debug("Request to get all Grupo");
        Optional<Campeonato> campeonatoSave = campeonatoRepository.findById(torneo);
        return grupoRepository.findByIdCampeonato(campeonatoSave.get());
    }

    public void delete(Integer grupo) {
        grupoRepository.deleteById(grupo);
    }

}
