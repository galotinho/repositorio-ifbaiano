package com.sistema.academicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.academicos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

}
