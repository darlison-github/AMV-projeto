package com.darlisonhenrique.caf.damanha.domain.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darlisonhenrique.caf.damanha.domain.exceptions.ColaboradorNaoEncontradoExceptions;
import com.darlisonhenrique.caf.damanha.domain.model.Colaborador;
import com.darlisonhenrique.caf.damanha.domain.repository.ColaboradorRepository;
import com.darlisonhenrique.caf.damanha.domain.service.validation.ValidacaoDeCpfUnico;

@Service
public class ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private ValidacaoDeCpfUnico validacaoDeCpfUnico;
	
	@Autowired
	private EntityManager entityManager;
	
	public Page<Colaborador> buscarTodos(Pageable pageable) {
		return colaboradorRepository.findAll(pageable);
	}

	public Colaborador buscarPorId(Long id) {
		return colaboradorRepository.findById(id).orElseThrow(()-> new ColaboradorNaoEncontradoExceptions(id));
	}

	@Transactional
	public Colaborador inserir(Colaborador domainModel) {
		validacaoDeCpfUnico.validarCpfUnico(domainModel);
		
		return colaboradorRepository.save(domainModel);
	}
	
	@Transactional
	public Colaborador atualizar(Colaborador colaborador) {
		entityManager.detach(colaborador);
		validacaoDeCpfUnico.validarCpfUnico(colaborador);
		
		return colaboradorRepository.save(colaborador);
	}
	
	@Transactional
	public void deletarPorId(Long id) {
		buscarPorId(id);
		
		colaboradorRepository.deleteById(id);
	}

}
