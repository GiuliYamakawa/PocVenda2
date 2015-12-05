package br.univel.model;
/**
 * Produto (Entidade produto com as informações dos produtos do sistema)
 * Data: 04-11-2015 23:48
 * @author ggsgyamakawa
 */
import java.math.BigDecimal;

public class ProdutoVenda {
	
	private Produto produto;
	private Venda venda;
	private BigDecimal valor;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
