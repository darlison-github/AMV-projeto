package com.darlisonhenrique.caf.damanha.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.api.input.ColaboradorInput;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;

@Component
public class ColaboradorInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Colaborador toDomainModel(ColaboradorInput colaboradorInput) {
		return modelMapper.map(colaboradorInput, Colaborador.class);
	}
	
	public void copiarParaDomainModel(ColaboradorInput colaboradorInput, Colaborador colaborador) {
		modelMapper.map(colaboradorInput, colaborador);
	}
}
