package br.univel.teste;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import br.univel.dao.ProdutoDAO;
import br.univel.enums.Categoria;
import br.univel.enums.Unidade;
import br.univel.model.Produto;

public class TestesProduto {

	ProdutoDAO pdao = new ProdutoDAO();
	int produtoTeste = 6;

	@Test
	public void testeInsertProdutos() {

		boolean flag = false;

		for (int i = 1; i < 51; i++) { // inserindo 50 produtos aleatórios
			Produto p = new Produto();
		
			p.setCategoria(Categoria.PEÇAS); 
			p.setCusto(new BigDecimal(i * 3d));
			p.setMargemLucro(new BigDecimal(i * 1.5d));
			p.setDescricao("ProdPeça " + i);
			p.setUnidade(Unidade.UN);
			p.setCodigoBarras(String.valueOf(i * 23534));

			try {
				pdao.inserir(p);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		assertEquals(true, flag);
	}

	@Test
	public void testeGetProdutoNotNull() {
		Produto teste = null;
		teste = pdao.buscar(produtoTeste);
		assertEquals(true, teste != null);
	}

	@Test
	public void testeUpdateProduto() {
		Produto teste = pdao.buscar(produtoTeste);
		teste.setMargemLucro(new BigDecimal(0));

		pdao.atualizar(teste);
		Produto atualizado = pdao.buscar(teste.getId());
		BigDecimal valorDepois = new BigDecimal(atualizado.getCusto().doubleValue()
				+ (atualizado.getCusto().doubleValue() * atualizado.getMargemLucro().doubleValue()));
		assertEquals(teste.getCusto(), valorDepois);
	}

	@Test
	public void testeDeleteProduto() {
		List<Produto> teste = pdao.getProdutos(); // recupera todos os produtos
		int deletado = 0; // qnts q foram deletados
		for (Produto produto : teste) {
			if (produto.getId() == produtoTeste)
				continue; // nao deleta o id especifico
			if (deletado >= 50)
				break; // deleta apenas 50
			pdao.deletar(produto.getId());
			deletado++;
		}
		assertEquals(deletado, 50);
	}
}
