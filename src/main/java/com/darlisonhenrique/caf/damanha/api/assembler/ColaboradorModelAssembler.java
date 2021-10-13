package com.darlisonhenrique.caf.damanha.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.api.model.ColaboradorModel;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;

@Component
public class ColaboradorModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ColaboradorModel toModel(Colaborador colaborador) {
		return modelMapper.map(colaborador, ColaboradorModel.class);
	}
	
	public List<ColaboradorModel> toColaboradorModels(List<Colaborador> lista) {
		return lista.stream().map(colaborador -> toModel(colaborador)).collect(Collectors.toList());
	}
}
