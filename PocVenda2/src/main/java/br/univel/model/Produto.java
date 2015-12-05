package br.univel.model;

import java.math.BigDecimal;

import br.univel.enums.Categoria;
import br.univel.enums.Unidade;

/**
 * Produto (Entidade produto com as informações dos produtos do sistema)
 * Data: 04-11-2015 22:10
 * @author ggsgyamakawa
 */
public class Produto {

	private int id;
	private String codigoBarras;
	private Categoria categoria;
	private String descricao;
	private Unidade unidade;
	private BigDecimal custo;
	private BigDecimal margemLucro;
	
	
	public BigDecimal getValor(){
		double custo = this.custo.doubleValue();
		double margem = this.margemLucro.doubleValue() / 100;
		return new BigDecimal(custo + (custo * margem));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public String getDescCategoria() {
		return categoria.toString();
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public BigDecimal getCusto() {
		return custo;
	}
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}
	public BigDecimal getMargemLucro() {
		return margemLucro;
	}
	public void setMargemLucro(BigDecimal margemLucro) {
		this.margemLucro = margemLucro;
	}
	
	
	
	
	
}
