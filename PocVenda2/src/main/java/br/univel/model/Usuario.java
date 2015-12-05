package br.univel.model;

/**
 * Usuario (Entidade usuariio com as informações dos usuarios do sistema)
 * Data: 04-11-2015 21:50
 * @author ggsgyamakawa
 */
public class Usuario {
	
	private int id;
	private Cliente cliente;
	private String senha;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	

}
