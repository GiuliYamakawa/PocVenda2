package br.univel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.univel.model.Usuario;
import br.univel.util.Banco;

public class UsuarioDAO {

	private Connection con;
	private PreparedStatement stmt;
	private List<Usuario> lista = new ArrayList<Usuario>();

	public UsuarioDAO() {
		try{
			con = Banco.conectar();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao conectar no banco");
			e.printStackTrace();
		}
	}
	
	public void inserir(Usuario t) {
		String query = "INSERT INTO Usuario (idCliente, senha) "+
						"VALUES (?,?);";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,t.getCliente().getId());
			stmt.setString(2, t.getSenha());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
			e.printStackTrace();
		}
		listar();
	}

	public void deletar(int id) {
		String query = "DELETE FROM Usuario WHERE idUsuario = ?";
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

	public void atualizar(Usuario t) {
		String query = "UPDATE Usuario SET idCliente =?, senha=? WHERE idUsuario =?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,t.getCliente().getId());
			stmt.setString(2, t.getSenha());
			stmt.setInt(3, t.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar");
			e.printStackTrace();
		}
		listar();
	}

	public Usuario buscar(int id) {
		listar();
		for(Usuario u : lista){
			if(u.getId() == id)
				return u;
		}
		JOptionPane.showMessageDialog(null, "Erro ao buscar");
		return null;
	}
	
	public List<Usuario> getUsuarios(){
		listar();
		if(!lista.isEmpty())
			return lista;
		else
			JOptionPane.showMessageDialog(null, "Lista vazia");
		return lista;
	}
	
	private void listar(){
		lista = new ArrayList<Usuario>();
		String query = "SELECT * FROM Usuario AS u INNER JOIN Cliente AS c ON u.idCliente = c.idCliente WHERE c.ativo = '1'";
		ClienteDAO cdao = new ClienteDAO();
		try {
			stmt = con.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()){
				Usuario u = new Usuario();
				u.setId(result.getInt("idUsuario"));
				u.setCliente(cdao.buscar(result.getInt("idCliente")));
				u.setSenha(result.getString("senha"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
