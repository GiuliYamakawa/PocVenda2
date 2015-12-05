package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.univel.enums.Estados;
import br.univel.enums.Genero;
import br.univel.model.Cliente;
import br.univel.util.Banco;

public class ClienteDAO {

	private Connection con;
	private PreparedStatement stmt;
	private List<Cliente> lista = new ArrayList<Cliente>();

	public ClienteDAO() {
		try{
			con = Banco.conectar();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
			e.printStackTrace();
		}
	}
	
	public void inserir(Cliente t) {
		String query = "INSERT INTO Cliente (nome, telefone, endereco, cidade, estado, email, genero) "+
						"VALUES (?,?,?,?,?,?,?);";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1,t.getNome());
			stmt.setString(2, t.getTelefone());
			stmt.setString(3, t.getEndereco());
			stmt.setString(4, t.getCidade());
			stmt.setString(5, t.getEstado().name());
			stmt.setString(6, t.getEmail());
			stmt.setString(7, t.getGenero().name());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			e.printStackTrace();
		}
		listar();
	}

	public void deletar(int id) {
		String query = "UPDATE Cliente SET ativo = '0' WHERE idCliente = ?";
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

	public void atualizar(Cliente t) {
		String query = "UPDATE Cliente SET nome =?, telefone =?, endereco =?, cidade =?, estado =?, email =?, genero =? WHERE idCliente =?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1,t.getNome());
			stmt.setString(2, t.getTelefone());
			stmt.setString(3, t.getEndereco());
			stmt.setString(4, t.getCidade());
			stmt.setString(5, t.getEstado().name());
			stmt.setString(6, t.getEmail());
			stmt.setString(7, t.getGenero().name());
			stmt.setInt(8, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
			e.printStackTrace();
		}
		listar();
	}

	public Cliente buscar(int id) {
		listar();
		for(Cliente c : lista){
			if(c.getId() == id)
				return c;
		}
		JOptionPane.showMessageDialog(null, "Erro ao buscar");
		return null;
	}
	
	public List<Cliente> getClientes(){
		listar();
		if(!lista.isEmpty())
			return lista;
		else
			JOptionPane.showMessageDialog(null, "Lista vazia");
		return lista;
	}
	
	private void listar(){
		lista = new ArrayList<Cliente>();
		String query = "SELECT * FROM Cliente WHERE ativo = '1'";
		try {
			stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				Cliente c = new Cliente();
				c.setId(result.getInt("idCliente"));
				c.setNome(result.getString("nome"));
				c.setCidade(result.getString("cidade"));
				c.setEndereco(result.getString("endereco"));
				c.setEstado(Estados.valueOf(result.getString("estado")));
				c.setGenero(Genero.valueOf(result.getString("genero")));
				c.setEmail(result.getString("email"));
				c.setTelefone(result.getString("telefone"));
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
