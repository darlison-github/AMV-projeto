package com.darlisonhenrique.caf.damanha.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.darlisonhenrique.caf.damanha.api.input.CafeDaManhaInput;
import com.darlisonhenrique.caf.damanha.domain.model.CafeManha;

@Component
public class CafeDaManhaInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CafeManha toDomainModel(CafeDaManhaInput cafeDaManhaInput) {
		return modelMapper.map(cafeDaManhaInput, CafeManha.class);
	}
	
	public void copiarParaDomainModel(CafeDaManhaInput cafeDaManhaInput, CafeManha cafeDaManha) {
		modelMapper.map(cafeDaManhaInput, cafeDaManha);
	}
}
