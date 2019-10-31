package com.sistema.academicos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.academicos.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.email like :email" )
	Optional<Usuario> findByEmail(@Param ("email") String email);
	
}
