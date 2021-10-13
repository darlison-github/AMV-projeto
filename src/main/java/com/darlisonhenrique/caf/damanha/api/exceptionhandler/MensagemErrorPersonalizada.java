package com.darlisonhenrique.caf.damanha.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class MensagemErrorPersonalizada {
	
	private String tipo;
	private String titulo;
	private String detalhe;
	private int status;
	private String mensagemUsuario;
	private List<CampoDeMenssagem> erros = new  ArrayList<>();
	
	public MensagemErrorPersonalizada(String tipo, String titulo, String detalhe, int status, String mensagemUsuario) {
		super();
		this.tipo = tipo;
		this.titulo = titulo;
		this.detalhe = detalhe;
		this.status = status;
		this.mensagemUsuario = mensagemUsuario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}
	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}
	public List<CampoDeMenssagem> getErros() {
		return erros;
	}
	public void setErros(List<CampoDeMenssagem> erros) {
		this.erros = erros;
	}
	
	public void adicionarError (String field, String userMenssagem) {
		this.erros.add(new CampoDeMenssagem(field, userMenssagem));
	}
}
