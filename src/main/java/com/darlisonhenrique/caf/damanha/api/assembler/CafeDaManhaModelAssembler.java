package com.darlisonhenrique.caf.damanha.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.api.model.CafeDaManhaModel;
import com.darlisonhenrique.caf.damanha.domain.model.CafeManha;

@Component
public class CafeDaManhaModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CafeDaManhaModel toModel(CafeManha cafeDaManha) {
		return modelMapper.map(cafeDaManha, CafeDaManhaModel.class);
	}
	
	public List<CafeDaManhaModel> toCafeDaManhaModels(List<CafeManha> lista) {
		return lista.stream().map(cafeDaManha -> toModel(cafeDaManha)).collect(Collectors.toList());
	}
}
