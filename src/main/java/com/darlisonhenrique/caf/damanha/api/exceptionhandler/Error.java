package com.darlisonhenrique.caf.damanha.api.exceptionhandler;

public enum Error {
	PROJETOMVEXCEPTIONS("error-client-side", "Aconteceu um erro do lado do front-end"),
	OBJETO_NÃO_ENCONTRADO("Objeto-nao-encontrado", "Objeto nao encontrado"),
	ERROR_VALIDACAO("error-validacao", "Error de validação");
	private String tipo;
	private String titulo;
	
	private Error(String tipo, String titulo) {
		this.tipo = "https://www.projeto-mv-darlisonhenrique.com.br/" + tipo;
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}


	public String getTitulo() {
		return titulo;
	}

}
