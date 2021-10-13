package com.darlisonhenrique.caf.damanha.api.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.darlisonhenrique.caf.damanha.api.assembler.ColaboradorFullModelAssembler;
import com.darlisonhenrique.caf.damanha.api.assembler.ColaboradorModelAssembler;
import com.darlisonhenrique.caf.damanha.api.disassembler.ColaboradorInputDisassembler;
import com.darlisonhenrique.caf.damanha.api.input.ColaboradorInput;
import com.darlisonhenrique.caf.damanha.api.model.ColaboradorFullModel;
import com.darlisonhenrique.caf.damanha.api.model.ColaboradorModel;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;
import com.darlisonhenrique.caf.damanha.domain.service.ColaboradorService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired	
	private ColaboradorService colaboradorService;
	
	@Autowired
	private ColaboradorModelAssembler colaboradorModelAssembler;
	
	@Autowired
	private ColaboradorFullModelAssembler colaboradorFullModelAssembler;
	
	@Autowired
	private ColaboradorInputDisassembler colaboradorInputDisassembler;
	
	@GetMapping
	public Page<ColaboradorModel> buscaTodos (Pageable pageable){
		Page<Colaborador> page = colaboradorService.buscarTodos(pageable);
		
		return page.map(colaborador -> colaboradorModelAssembler.toModel(colaborador));
	}
	
	@GetMapping("/{id}")
	public ColaboradorFullModel buscarPorId(@PathVariable Long id) {
		Colaborador colaborador = colaboradorService.buscarPorId(id);
		
		return colaboradorFullModelAssembler.toModelAssembler(colaborador);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ColaboradorFullModel inserir(@Valid @RequestBody ColaboradorInput colaboradorInput) {
		Colaborador colaborador = colaboradorService.inserir(colaboradorInputDisassembler.toDomainModel(colaboradorInput));
		
		return colaboradorFullModelAssembler.toModelAssembler(colaborador);
	}
	
	@PutMapping("/{id}")
	public ColaboradorFullModel atualizar(@Valid @RequestBody ColaboradorInput colaboradorInput, @PathVariable Long id) { 
		Colaborador colaborador = colaboradorService.buscarPorId(id);
		colaboradorInputDisassembler.copiarParaDomainModel(colaboradorInput, colaborador);
		colaborador = colaboradorService.atualizar(colaborador);
		
		return colaboradorFullModelAssembler.toModel(colaborador);
		
	}
	
	@DeleteMapping("/{id}")
	public void deletarPorId(@PathVariable Long id) {
		colaboradorService.deletarPorId(id);
	}
	

}
