package com.sistema.academicos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.academicos.model.Trabalho;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>{

	@Query("select t from Trabalho t where "
			+ "t.titulo like %:search%")
	Page<Trabalho> findAllByTituloAutorPalavra(String search, Pageable pageable);

}