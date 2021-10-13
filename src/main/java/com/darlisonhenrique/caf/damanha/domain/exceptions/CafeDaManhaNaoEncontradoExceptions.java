package com.darlisonhenrique.caf.damanha.domain.exceptions;

public class CafeDaManhaNaoEncontradoExceptions extends ObjetoNãoEncontradoExceptions{

	private static final long serialVersionUID = 1L;
	
	public CafeDaManhaNaoEncontradoExceptions(String mensagem) {
		super(mensagem);
	}
	
	public CafeDaManhaNaoEncontradoExceptions(Long id) {
		super(String.format("O id %d do café da manhã não foi encontrado!", id));
	}
}
 
