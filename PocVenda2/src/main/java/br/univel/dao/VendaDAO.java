package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.univel.model.Venda;
import br.univel.util.Banco;

public class VendaDAO {

	private Connection con;
	private PreparedStatement stmt;
	private List<Venda> lista = new ArrayList<Venda>();

	public VendaDAO() {
		try{
			con = Banco.conectar();
			listar();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
			e.printStackTrace();
		}
	}
	
	public void inserir(Venda v) {
		String query = "INSERT INTO venda (idcliente, total) "+
						"VALUES (?,?);";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, v.getCliente().getId());
			stmt.setDouble(2, v.getTotal().doubleValue());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			e.printStackTrace();
		}
		listar();
	}

	public void deletar(int id) {
		String query = "UPDATE venda SET ativo = '0' WHERE idvenda = ?";
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

	public void atualizar(Venda v) {
		String query = "UPDATE venda SET idcliente =?, total =? WHERE idvenda =?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,v.getCliente().getId());
			stmt.setDouble(2, v.getTotal().doubleValue());
			stmt.setInt(3, v.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
			e.printStackTrace();
		}
		listar();
	}

	public Venda buscar(int id) {
		listar();
		for(Venda v : lista){
			if(v.getId() == id)
				return v;
		}
		JOptionPane.showMessageDialog(null, "Erro ao buscar");
		return null;
	}
	
	public List<Venda> getVendas(){
		listar();
		if(!lista.isEmpty())
			return lista;
		else
			JOptionPane.showMessageDialog(null, "Lista vazia");
		return lista;
	}
	
	private void listar(){
		lista = new ArrayList<Venda>();
		String query = "SELECT * FROM venda WHERE ativo = '1'";
		ClienteDAO cdao = new ClienteDAO();
		try {
			stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				Venda v = new Venda();
				System.out.println(result.getInt("idVenda"));
				System.out.println(cdao.buscar(result.getInt("idcliente")));
				v.setId(result.getInt("idVenda"));
				v.setCliente(cdao.buscar(result.getInt("idcliente")));
				v.setTotal(result.getBigDecimal("total"));
				lista.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Venda getUltimaVenda(){
		listar();
		return lista.get(lista.size()-1);
	}

}
