package com.sistema.academicos.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity	
@Table(name="trabalhos")
public class Trabalho {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "trabalhos_tem_autores", 
        joinColumns = { @JoinColumn(name = "trabalho_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "autor_id", referencedColumnName = "id") }
	)
	private List<Autor> autores;
	
	private String orientador;
	private String coorientador;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "trabalhos_tem_palavras", 
        joinColumns = { @JoinColumn(name = "trabalho_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "palavra_id", referencedColumnName = "id") }
	)
	private List<Palavra> palavras;
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	private String resumo;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private DBFile file;
	
}
