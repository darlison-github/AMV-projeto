package com.darlisonhenrique.caf.damanha.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CafeDaManhaInput {
	
	@Size(max = 255)
	@NotBlank
	private String nome;
	
	public CafeDaManhaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
