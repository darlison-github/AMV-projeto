package com.darlisonhenrique.caf.damanha.api.exceptionhandler;

public class CampoDeMenssagem {
	private final String campo;
	private final String usarMensagem;
	
	public CampoDeMenssagem(String campo, String usarMensagem) {
		super();
		this.campo = campo;
		this.usarMensagem = usarMensagem;
	}

	public String getCampo() {
		return campo;
	}

	public String getUsarMensagem() {
		return usarMensagem;
	} 
	

}
