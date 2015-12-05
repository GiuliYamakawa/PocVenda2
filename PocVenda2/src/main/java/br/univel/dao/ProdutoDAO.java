package br.univel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.univel.enums.Categoria;
import br.univel.enums.Unidade;
import br.univel.model.Produto;
import br.univel.util.Banco;

public class ProdutoDAO {

	private Connection con;
	private PreparedStatement stmt;
	private List<Produto> lista = new ArrayList<Produto>();

	public ProdutoDAO() {
		try{
			con = Banco.conectar();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
			e.printStackTrace();
		}
	}
	
	public void inserir(Produto t) {
		String query = "INSERT INTO Produto (codigoBarras, descricao, categoria, unidade, custo, margemLucro) "+
						"VALUES (?,?,?,?,?,?);";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1,t.getCodigoBarras());
			stmt.setString(2, t.getDescricao());
			stmt.setString(3, t.getCategoria().name());
			stmt.setString(4, t.getUnidade().name());
			stmt.setDouble(5, t.getCusto().doubleValue());
			stmt.setDouble(6, t.getMargemLucro().doubleValue());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			e.printStackTrace();
		}
		listar();
	}

	public void deletar(int id) {
		String query = "UPDATE Produto SET ativo = '0' WHERE idProduto = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar");
			e.printStackTrace();
		}
		listar();
	}

	public void atualizar(Produto t) {
		String query = "UPDATE Produto SET codigoBarras =?, descricao =?, categoria =?, unidade =?, custo =?, margemLucro =? WHERE idProduto =?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1,t.getCodigoBarras());
			stmt.setString(2, t.getDescricao());
			stmt.setString(3, t.getCategoria().name());
			stmt.setString(4, t.getUnidade().name());
			stmt.setDouble(5, t.getCusto().doubleValue());
			stmt.setDouble(6, t.getMargemLucro().doubleValue());
			stmt.setInt(7, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
			e.printStackTrace();
		}
		listar();
	}

	public Produto buscar(int id) {
		listar();
		for(Produto c : lista){
			if(c.getId() == id)
				return c;
		}
		JOptionPane.showMessageDialog(null, "Erro ao buscar");
		return null;
	}
	
	public List<Produto> getProdutos(){
		listar();
		if(!lista.isEmpty())
			return lista;
		else
			JOptionPane.showMessageDialog(null, "Lista vazia");
		return lista;
	}
	
	private void listar(){
		lista = new ArrayList<Produto>();
		String query = "SELECT * FROM Produto WHERE ativo = '1'";
		try {
			stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				Produto p = new Produto();
				p.setId(result.getInt("idProduto"));
				p.setCodigoBarras(result.getString("codigoBarras"));
				p.setDescricao(result.getString("descricao"));
				p.setCategoria(Categoria.valueOf(result.getString("categoria")));
				p.setUnidade(Unidade.valueOf(result.getString("unidade")));
				p.setCusto(new BigDecimal(result.getDouble("custo")));
				p.setMargemLucro(new BigDecimal(result.getDouble("margemLucro")));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
