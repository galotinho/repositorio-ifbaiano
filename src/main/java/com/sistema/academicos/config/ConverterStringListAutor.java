package com.sistema.academicos.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sistema.academicos.model.Autor;

@Component
public class ConverterStringListAutor implements Converter<String, List<Autor>>{

	public List<Autor> convert(final String dados) {
	    
		String[] lista = dados.split(",");
		List<Autor> retorno = new ArrayList<Autor>();
				
		for(String l : lista) {
			Autor a = new Autor();
			a.setNome(l);
			retorno.add(a);
		}
		return retorno;	
		
	 }
}
