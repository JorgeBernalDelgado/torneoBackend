package com.belen.torneo.service;

import com.belen.torneo.domain.Usuario;
import com.belen.torneo.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@EnableJpaRepositories(basePackages = {"com.belen.torneo.repository","org.springframework.security.crypto.password"})
public class UsuarioService {

	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
    UsuarioRepository usuarioRepository;



    public List<Usuario> listar() {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.findAll();
    }
    
    public Usuario save(Usuario usuario) {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.save(usuario);
    }
    
    public Usuario update(Usuario usuario) {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.save(usuario);
    }
    
    public void delete(int id) {
        log.debug("Request to get all Usuarios");
        usuarioRepository.deleteById(id);
    }
    
    public List<Usuario> listarDelegados(Integer delegado, Integer torneo) {
        log.debug("Request to get all Usuarios");
        if(torneo == 0){
            return usuarioRepository.listarPlanillero(delegado);
        }else{
            return usuarioRepository.listarDelegado(delegado, torneo);
        }
    }

    public List<Usuario> listarUsuariosAdmin(Integer admin) {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.listarUsuarioAdmin(admin);
    }
    
    public Optional<Usuario> listarUsuario(Integer usuario) {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.findById(usuario);
    }

    public Optional<Usuario> getByUsuario(String nombreUsuario){
        return usuarioRepository.findByUsuario(nombreUsuario);
    }

    public Usuario validarExisteUsuario(String usuario, String identificacion){
        return usuarioRepository.validarExisteUsuario(usuario, identificacion);
    }
}
