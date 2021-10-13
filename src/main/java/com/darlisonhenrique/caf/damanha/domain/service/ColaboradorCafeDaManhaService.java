package com.darlisonhenrique.caf.damanha.domain.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darlisonhenrique.caf.damanha.domain.exceptions.CafeDaManhaNaoEncontradoExceptions;
import com.darlisonhenrique.caf.damanha.domain.exceptions.ProjetomvExceptions;
import com.darlisonhenrique.caf.damanha.domain.model.CafeManha;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;
import com.darlisonhenrique.caf.damanha.domain.repository.CafeManhaRepository;
import com.darlisonhenrique.caf.damanha.domain.service.validation.ValidacaoNomeUnicoDoCafeDaManha;

@Service
public class ColaboradorCafeDaManhaService {

	@Autowired
	private ColaboradorService colaboradorService;

	@Autowired
	private CafeManhaRepository cafeManhaRepository;
	
	@Autowired
	private ValidacaoNomeUnicoDoCafeDaManha validacaoNomeUnicoDoCafeDaManha;
	
	@Autowired
	private EntityManager entityManager;
	        
 
	public CafeManha buscarPorId(Long colaboradorId, Long cafeManhaId) {
		colaboradorService.buscarPorId(colaboradorId);
		cafeManhaRepository.findById(cafeManhaId)
				.orElseThrow(() -> new CafeDaManhaNaoEncontradoExceptions(cafeManhaId));

		return cafeManhaRepository.BuscarCafeDoColaborador(colaboradorId, cafeManhaId)
				.orElseThrow(() -> new ProjetomvExceptions(
						String.format("O café da manhã de id %d n]ao está associado com o colaborador de id %d",
								cafeManhaId, colaboradorId)));
	}

	@Transactional
	public CafeManha inserir(CafeManha domainModel, Long colaboradorId) {
		Colaborador colaborador = colaboradorService.buscarPorId(colaboradorId);
		domainModel.setColaborador(colaborador);
		validacaoNomeUnicoDoCafeDaManha.ValidarNomeUnico(domainModel);

		return cafeManhaRepository.save(domainModel);
	}

	@Transactional
	public CafeManha atualizar(CafeManha cafeManha) {
		entityManager.detach(cafeManha);
		validacaoNomeUnicoDoCafeDaManha.ValidarNomeUnico(cafeManha);
		
		return cafeManhaRepository.save(cafeManha);
		
	}

	@Transactional
	public void deletar(Long colaboradorId, Long cafeManhaId) {
		buscarPorId(colaboradorId, cafeManhaId);
		
		cafeManhaRepository.deleteById(cafeManhaId);
	}

}
