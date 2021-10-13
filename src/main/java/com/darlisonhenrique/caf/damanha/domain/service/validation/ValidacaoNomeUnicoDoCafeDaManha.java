package com.darlisonhenrique.caf.damanha.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.domain.exceptions.ProjetomvExceptions;
import com.darlisonhenrique.caf.damanha.domain.model.CafeManha;
import com.darlisonhenrique.caf.damanha.domain.repository.CafeManhaRepository;

@Component
public class ValidacaoNomeUnicoDoCafeDaManha {
	@Autowired
	private CafeManhaRepository repository;

	public void ValidarNomeUnico(CafeManha cafeManha) {
		Optional<CafeManha> obj = repository.buscarPorNome(cafeManha.getNome());
		
		if(obj.isPresent() && !obj.get().equals(cafeManha)) {
			throw new ProjetomvExceptions(String.format("Já trouxeram o café da manhã %s", cafeManha.getNome()));
			
		}

	}

}
