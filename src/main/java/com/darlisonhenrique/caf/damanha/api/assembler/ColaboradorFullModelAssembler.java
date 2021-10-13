package com.darlisonhenrique.caf.damanha.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.api.model.ColaboradorFullModel;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;

@Component
public class ColaboradorFullModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ColaboradorFullModel toModelAssembler(Colaborador colaborador) {
		return modelMapper.map(colaborador, ColaboradorFullModel.class);
	}

	public ColaboradorFullModel toModel(Colaborador colaborador) {
		// TODO Auto-generated method stub
		return null;
	}

}
