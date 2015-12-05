package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.univel.model.ProdutoVenda;
import br.univel.util.Banco;

public class ProdutoVendaDAO {
	private Connection con;
	private PreparedStatement stmt;
	private List<ProdutoVenda> lista = new ArrayList<ProdutoVenda>();

	public ProdutoVendaDAO() {
		try{
			con = Banco.conectar();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
			e.printStackTrace();
		}
	}
	
	public void inserir(ProdutoVenda pv) {
		String query = "INSERT INTO produto_venda (idvenda, idproduto, valor) "+
						"VALUES (?,?,?);";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, pv.getVenda().getId());
			stmt.setInt(2, pv.getProduto().getId());
			stmt.setBigDecimal(3, pv.getValor());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			e.printStackTrace();
		}
		listar();
	}

	public void deletar(int idVenda, int idProduto) {
		String query = "DELETE FROM produto_venda WHERE idVenda = ? AND idProduto = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, idVenda);
			stmt.setInt(2, idProduto);
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao deletar");
			e.printStackTrace();
		}
		listar();
	}

	public void atualizar(ProdutoVenda pv) {
		String query = "UPDATE produto_venda SET idProduto =?, valor =? WHERE idVenda = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, pv.getProduto().getId());
			stmt.setBigDecimal(2, pv.getValor());
			stmt.setInt(3, pv.getVenda().getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
			e.printStackTrace();
		}
		listar();
	}

	public ProdutoVenda buscar(int idVenda, int idProduto) {
		listar();
		for(ProdutoVenda pv : lista){
			if(pv.getVenda().getId() == idVenda && pv.getProduto().getId() == idProduto)
				return pv;
		}
		JOptionPane.showMessageDialog(null, "Erro ao buscar");
		return null;
	}
	
	public List<ProdutoVenda> getPorVendas(int idVenda){
		listar();
		List<ProdutoVenda> temp = new ArrayList<ProdutoVenda>();
		if(!lista.isEmpty()){
			for(ProdutoVenda pv : lista){
				if(pv.getVenda().getId() == idVenda)
					temp.add(pv);
			}
			if(!temp.isEmpty())
				return temp;
		}else
			JOptionPane.showMessageDialog(null, "Lista vazia");
		return lista;
	}
	
	private void listar(){
		lista = new ArrayList<ProdutoVenda>();
		String query = "SELECT * FROM produto_venda";
		VendaDAO vdao = new VendaDAO();
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				ProdutoVenda pv = new ProdutoVenda();
				pv.setVenda(vdao.buscar(result.getInt("idvenda")));
				pv.setProduto(pdao.buscar(result.getInt("idproduto")));
				pv.setValor(result.getBigDecimal("valor"));
				lista.add(pv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
