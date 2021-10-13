package com.darlisonhenrique.caf.damanha.api.model;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorFullModel {
	private Long id;
	private String nome;
	private String cpf;
	private List<CafeDaManhaModel> cafes = new ArrayList<>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<CafeDaManhaModel> getCafes() {
		return cafes;
	}
	public void setCafes(List<CafeDaManhaModel> cafes) {
		this.cafes = cafes;
	}
}
