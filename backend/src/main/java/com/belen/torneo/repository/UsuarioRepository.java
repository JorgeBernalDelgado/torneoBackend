package com.belen.torneo.repository;

import com.belen.torneo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByRoles(Integer delegado);
	Optional<Usuario> findByUsuario(String usuario);
	Boolean existsByUsuario(String usuario);

	@Query(value =
			"select usu.* from \n" +
					"bt_usuario_rol_tb url\n" +
					"join bt_usuario_tb usu \n" +
					"on usu.id = url.usuario_id \n" +
					"join bt_equipo_tb equ\n" +
					"on usu.id = equ.delegado_id \n" +
					"where url.rol_id = :delegado and equ.id_campeonato_id = :torneo",
			nativeQuery = true)
	List<Usuario> listarDelegado(@Param("delegado") Integer delegado, @Param("torneo") Integer torneo);

	@Query(value =
			"select usu.* from \n" +
					"bt_usuario_rol_tb url\n" +
					"join bt_usuario_tb usu \n" +
					"on usu.id = url.usuario_id \n" +
					"where url.rol_id = :planillero",
			nativeQuery = true)
	List<Usuario> listarPlanillero(@Param("planillero") Integer planillero);

	@Query(value =
			"select usu.* from \n" +
					"bt_usuario_rol_tb url \n" +
					"join bt_usuario_tb usu \n" +
					"on usu.id = url.usuario_id \n" +
					"where url.rol_id = :admin",
			nativeQuery = true)
	List<Usuario> listarUsuarioAdmin(@Param("admin") Integer admin);

	@Query(value =
			"select * from\n" +
					"bt_usuario_tb but \n" +
					"where but.usuario like %:usuario% or \n" +
					"but.identificacion like %:identificacion%",
			nativeQuery = true)
	Usuario validarExisteUsuario(@Param("usuario") String usuario, @Param("identificacion") String identificacion);

}
