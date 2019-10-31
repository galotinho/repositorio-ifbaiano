package com.sistema.academicos.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sistema.academicos.model.Palavra;

@Component
public class ConverterStringListPalavra implements Converter<String, List<Palavra>>{

	public List<Palavra> convert(final String dados) {
	    System.out.println(dados+"\n\n\n\n\n\n\n");
		String[] lista = dados.split(",");
		List<Palavra> retorno = new ArrayList<Palavra>();
		
		
		for(String l : lista) {
			Palavra a = new Palavra();
			a.setPalavra(l);
			retorno.add(a);
		}
		return retorno;	
		
	 }
}
