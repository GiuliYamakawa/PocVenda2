package br.univel.model;
/**
 * Produto (Entidade produto com as informações dos produtos do sistema)
 * Data: 04-11-2015 00:19
 * @author ggsgyamakawa
 */
import java.math.BigDecimal;
import java.util.List;

public class Venda {
	private int id;
	private Cliente cliente;
	private List<ProdutoVenda> itens;
	private BigDecimal total;
	
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
	public List<ProdutoVenda> getItens() {
		return itens;
	}
	public void setItens(List<ProdutoVenda> itens) {
		this.itens = itens;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
