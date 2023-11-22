package com.belen.torneo.service;

import com.belen.torneo.domain.Rol;
import com.belen.torneo.enums.ERol;
import com.belen.torneo.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(ERol nombre){
        return rolRepository.findByNombre(nombre);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
