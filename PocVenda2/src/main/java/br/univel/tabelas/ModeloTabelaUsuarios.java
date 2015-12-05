package br.univel.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.Usuario;

public class ModeloTabelaUsuarios extends AbstractTableModel {

	private static final long serialVersionUID = -2852110972924777966L;
	private List<Usuario> lista = new ArrayList<Usuario>();

	public ModeloTabelaUsuarios(List<Usuario> lista) {
		this.lista = lista;
		this.fireTableDataChanged();
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return lista.size();
	}

	public Object getValueAt(int row, int column) {
		switch(column){
		case 0:
			return lista.get(row).getId();
		case 1:
			return lista.get(row).getCliente().getNome();
		default:
			return lista.get(row).getSenha();
	}
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0:
				return "ID";
			case 1:
				return "Nome do Cliente";
			default:
				return "Senha";
		}
	}

}
