package br.univel.tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ClienteDAO;
import br.univel.dao.ProdutoDAO;
import br.univel.model.Cliente;
import br.univel.model.Produto;
import br.univel.util.RelatorioProduto;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class RelProduto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtcategoria;
	private JTextField txtmargem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RelProduto dialog = new RelProduto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelProduto() {
		setBounds(100, 100, 452, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel Categoria = new JLabel("Categoria");
			GridBagConstraints gbc_Categoria = new GridBagConstraints();
			gbc_Categoria.insets = new Insets(0, 0, 5, 5);
			gbc_Categoria.anchor = GridBagConstraints.EAST;
			gbc_Categoria.gridx = 0;
			gbc_Categoria.gridy = 0;
			contentPanel.add(Categoria, gbc_Categoria);
		}
		{
			txtcategoria = new JTextField();
			GridBagConstraints gbc_txtcategoria = new GridBagConstraints();
			gbc_txtcategoria.insets = new Insets(0, 0, 5, 0);
			gbc_txtcategoria.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtcategoria.gridx = 1;
			gbc_txtcategoria.gridy = 0;
			contentPanel.add(txtcategoria, gbc_txtcategoria);
			txtcategoria.setColumns(10);
		}
		{
			JLabel lblMargemDeLucro = new JLabel("Margem de lucro");
			GridBagConstraints gbc_lblMargemDeLucro = new GridBagConstraints();
			gbc_lblMargemDeLucro.anchor = GridBagConstraints.EAST;
			gbc_lblMargemDeLucro.insets = new Insets(0, 0, 0, 5);
			gbc_lblMargemDeLucro.gridx = 0;
			gbc_lblMargemDeLucro.gridy = 1;
			contentPanel.add(lblMargemDeLucro, gbc_lblMargemDeLucro);
		}
		{
			txtmargem = new JTextField();
			GridBagConstraints gbc_txtmargem = new GridBagConstraints();
			gbc_txtmargem.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtmargem.gridx = 1;
			gbc_txtmargem.gridy = 1;
			contentPanel.add(txtmargem, gbc_txtmargem);
			txtmargem.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Imprimir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Imprimir();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	protected void Imprimir() {
		List<Produto> produtosrel = new ArrayList<Produto>();
		ProdutoDAO dao = new ProdutoDAO();
		List<Produto> produtos = dao.getProdutos();
		for (Produto produto : produtos) {
			if (produto.getCategoria().toString().equals(txtcategoria.getText())) // Se a categoria do produto for igual a categoria selecionada
				produtosrel.add(produto);
			if (produto.getMargemLucro().toString().equals(txtmargem.getText())) // Se o lucro for igual
				produtosrel.add(produto);// TODO Auto-generated method stub

		
		}
		try {
			new RelatorioProduto().imprimir(produtosrel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
