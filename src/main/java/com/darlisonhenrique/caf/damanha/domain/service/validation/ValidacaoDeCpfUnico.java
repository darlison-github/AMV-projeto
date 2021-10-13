package com.darlisonhenrique.caf.damanha.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.domain.exceptions.ProjetomvExceptions;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;
import com.darlisonhenrique.caf.damanha.domain.repository.ColaboradorRepository;

@Component
public class ValidacaoDeCpfUnico {
	
	@Autowired
	private ColaboradorRepository repository;
	
	public void validarCpfUnico(Colaborador colaborador) {
		Optional<Colaborador> obj = repository.findByCpf(colaborador.getCpf());
		
		if(obj.isPresent() && !obj.get().equals(colaborador)) {
			throw new ProjetomvExceptions(String.format("O cpf '%s' é existente!", colaborador.getCpf()));
		}
	}

}
