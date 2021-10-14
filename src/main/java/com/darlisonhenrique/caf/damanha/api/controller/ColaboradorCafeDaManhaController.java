package com.darlisonhenrique.caf.damanha.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darlisonhenrique.caf.damanha.api.assembler.CafeDaManhaModelAssembler;
import com.darlisonhenrique.caf.damanha.api.disassembler.CafeDaManhaInputDisassembler;
import com.darlisonhenrique.caf.damanha.api.input.CafeDaManhaInput;
import com.darlisonhenrique.caf.damanha.api.model.CafeDaManhaModel;
import com.darlisonhenrique.caf.damanha.domain.model.CafeManha;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;
import com.darlisonhenrique.caf.damanha.domain.service.ColaboradorCafeDaManhaService;
import com.darlisonhenrique.caf.damanha.domain.service.ColaboradorService;

@RestController
@RequestMapping("/colaboradores/{colaboradorId}/cafes")
public class ColaboradorCafeDaManhaController {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private CafeDaManhaModelAssembler cafeDaManhaModelAssembler;
	
	@Autowired
	private ColaboradorCafeDaManhaService colaboradorCafeDaManhaService;
	
	@Autowired
	private CafeDaManhaInputDisassembler cafeDaManhaInputDisassembler;
	
	@GetMapping
	public List<CafeDaManhaModel> buscarTodos(@PathVariable Long colaboradorId){
		Colaborador colaborador = colaboradorService.buscarPorId(colaboradorId);
		
		return cafeDaManhaModelAssembler.toCafeDaManhaModels(colaborador.getCafes());
		
	}
	
	@PostMapping
	public CafeDaManhaModel inserir(@Valid @RequestBody CafeDaManhaInput cafeDaManhaInput, @PathVariable Long colaboradorId) {
		CafeManha cafeManha = colaboradorCafeDaManhaService.inserir(cafeDaManhaInputDisassembler.toDomainModel(cafeDaManhaInput), colaboradorId);
		
	
	return cafeDaManhaModelAssembler.toModel(cafeManha);
	}
	
	@PutMapping("/{cafeManhaId}")
	public CafeDaManhaModel atualizar(@Valid @RequestBody CafeDaManhaInput cafeDaManhaInput, @PathVariable Long colaboradorId, 
		@PathVariable Long cafeManhaId) {
		
		CafeManha cafeManha = colaboradorCafeDaManhaService.buscarPorId(colaboradorId, cafeManhaId);
		cafeDaManhaInputDisassembler.copiarParaDomainModel(cafeDaManhaInput, cafeManha);
		cafeManha = colaboradorCafeDaManhaService.atualizar(cafeManha);
		
		return cafeDaManhaModelAssembler.toModel(cafeManha);
	}
	
	@DeleteMapping("/{cafeManhaId}")
	public void deletar(@PathVariable Long colaboradorId, @PathVariable Long cafeManhaId) {
		colaboradorCafeDaManhaService.deletar(colaboradorId, cafeManhaId);
	}
	
}