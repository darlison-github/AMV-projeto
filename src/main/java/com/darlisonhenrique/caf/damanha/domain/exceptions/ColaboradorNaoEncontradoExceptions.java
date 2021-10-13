package com.darlisonhenrique.caf.damanha.domain.exceptions;

public class ColaboradorNaoEncontradoExceptions extends ObjetoNãoEncontradoExceptions{

	private static final long serialVersionUID = 1L;
	
	public ColaboradorNaoEncontradoExceptions(String mensagem) {
		super(mensagem);
	}
	
	public ColaboradorNaoEncontradoExceptions(Long id) {
		super(String.format("O id %d do colaborador não foi encontrado!", id));
	}
}
 
