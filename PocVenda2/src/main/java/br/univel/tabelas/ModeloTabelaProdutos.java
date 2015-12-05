package br.univel.tabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.Produto;

public class ModeloTabelaProdutos extends AbstractTableModel {

	private static final long serialVersionUID = -2852110972924777966L;
	private List<Produto> lista;

	public ModeloTabelaProdutos(List<Produto> lista) {
		this.lista = lista;
		this.fireTableDataChanged();
	}

	public List<Produto> getLista() {
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public int getColumnCount() {
		return 7;
	}

	public int getRowCount() {
		return lista.size();
	}

	public Object getValueAt(int row, int column) {
		switch(column){
		case 0:
			return lista.get(row).getId();
		case 1:
			return lista.get(row).getCodigoBarras();
		case 2:
			return lista.get(row).getCategoria();
		case 3:
			return lista.get(row).getDescricao();
		case 4:
			return lista.get(row).getUnidade();
		case 5:
			return lista.get(row).getCusto();
		default:
			return lista.get(row).getMargemLucro();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0:
				return "ID";
			case 1:
				return "Código de barra";
			case 2:
				return "Categoria";
			case 3:
				return "Descrição";
			case 4:
				return "Unidade";
			case 5:
				return "Custo";
			default:
				return "Margem Lucro";
		}
	}
}
