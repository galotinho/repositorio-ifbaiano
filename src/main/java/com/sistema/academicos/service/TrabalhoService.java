package com.sistema.academicos.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.academicos.datatables.Datatables;
import com.sistema.academicos.datatables.DatatablesColunas;
import com.sistema.academicos.model.Curso;
import com.sistema.academicos.model.Trabalho;
import com.sistema.academicos.repository.CursoRepository;
import com.sistema.academicos.repository.TrabalhoRepository;

@Service
public class TrabalhoService {
	
	@Autowired
	private TrabalhoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private Datatables dataTables;
	
	@Transactional(readOnly = false)
	public void salvar(Trabalho trabalho) {
		repository.save(trabalho);		
	}
	
	@Transactional(readOnly=true)
	public Map<String,Object> buscarTrabalhos(HttpServletRequest http) {
		
		dataTables.setRequest(http);
		dataTables.setColunas(DatatablesColunas.TRABALHOS);
		
		Page<?> page;
		
		if(dataTables.getSearch().isEmpty()) {
			page = repository.findAll(dataTables.getPageable());
		}else {
			page = repository.findAllByTituloAutorPalavra(dataTables.getSearch(), dataTables.getPageable());
		}
		
		return dataTables.getResponse(page);
	}
	
	@Transactional(readOnly=true)
	public Trabalho buscarPorId(Long id) {
		return repository.findById(id).get();
	}

	@Transactional(readOnly=false)
	public void remover(Long id) {
		repository.deleteById(id);		
	}
	
	@Transactional(readOnly = true)
	public List<Curso> buscarCursos() {
		return cursoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarTrabalhosHome(HttpServletRequest http) {
		dataTables.setRequest(http);
		dataTables.setColunas(DatatablesColunas.TRABALHOS);
		
		Page<?> page;
		
		if(dataTables.getSearch().isEmpty()) {
			page = repository.findAll(dataTables.getPageable());
		}else {
			page = repository.findAllByTituloAutorPalavra(dataTables.getSearch(), dataTables.getPageable());
		}
		
		return dataTables.getResponse(page);
	}

}
