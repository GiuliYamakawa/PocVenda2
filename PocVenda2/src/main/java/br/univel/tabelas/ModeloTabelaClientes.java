package br.univel.tabelas;
/**
 * TabCliente (TabCliente modelo de tabela cliente)
 * Data: 28-11-2015 23:57
 * @author ggsgyamakawa
 */
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.Cliente;

public class ModeloTabelaClientes extends AbstractTableModel {

	private static final long serialVersionUID = -2852110972924777966L;
	private List<Cliente> lista;

	public ModeloTabelaClientes(List<Cliente> lista) {
		this.lista = lista;
		this.fireTableDataChanged();
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public int getColumnCount() {
		return 8;
	}

	public int getRowCount() {
		return lista.size();
	}

	public String getValueAt(int row, int column) {
		switch(column){
		case 0:
			return String.valueOf(lista.get(row).getId());
		case 1:
			return lista.get(row).getNome();
		case 2:
			return lista.get(row).getTelefone();
		case 3:
			return lista.get(row).getEndereco();
		case 4:
			return lista.get(row).getCidade();
		case 5:
			return lista.get(row).getEstado().name();
		case 6:
			return lista.get(row).getEmail();
		default:
			return lista.get(row).getGenero().name();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0:
				return "ID";
			case 1:
				return "Nome";
			case 2:
				return "Telefone";
			case 3:
				return "Endereço";
			case 4:
				return "Cidade";
			case 5:
				return "Estado";
			case 6:
				return "E-mail";
			default:
				return "Genero";
		}
	}

}
