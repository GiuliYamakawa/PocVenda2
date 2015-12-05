package br.univel.tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ClienteDAO;
import br.univel.model.Cliente;
import br.univel.util.RelatorioCliente;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import br.univel.enums.Estados;

public class RelCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCidade;
	private JComboBox cbxEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RelCliente dialog = new RelCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RelCliente() {
		setBounds(100, 100, 453, 197);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCidade = new JLabel("Cidade");
			GridBagConstraints gbc_lblCidade = new GridBagConstraints();
			gbc_lblCidade.anchor = GridBagConstraints.EAST;
			gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
			gbc_lblCidade.gridx = 0;
			gbc_lblCidade.gridy = 0;
			contentPanel.add(lblCidade, gbc_lblCidade);
		}
		{
			txtCidade = new JTextField();
			GridBagConstraints gbc_txtCidade = new GridBagConstraints();
			gbc_txtCidade.insets = new Insets(0, 0, 5, 0);
			gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCidade.gridx = 1;
			gbc_txtCidade.gridy = 0;
			contentPanel.add(txtCidade, gbc_txtCidade);
			txtCidade.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Estado");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			cbxEstado = new JComboBox();
			cbxEstado.setModel(new DefaultComboBoxModel(Estados.values()));
			GridBagConstraints gbc_cbxEstado = new GridBagConstraints();
			gbc_cbxEstado.insets = new Insets(0, 0, 5, 0);
			gbc_cbxEstado.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxEstado.gridx = 1;
			gbc_cbxEstado.gridy = 1;
			contentPanel.add(cbxEstado, gbc_cbxEstado);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Imprimir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						imprimir();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	
	protected void imprimir() { 
		List<Cliente> clientesrel = new ArrayList<Cliente>(); // lista de clientes
		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = dao.getClientes();// pega lista de cliente do banco
		for (Cliente cliente : clientes) { // percorre toda a lista
			if(cliente.getCidade().equals(txtCidade.getText())) // se a cidade é igual a cidade informada pelo usuario
			clientesrel.add(cliente); 
			if(cliente.getEstado().equals(cbxEstado.getSelectedItem())) //verifica o estado 
				clientesrel.add(cliente);
			
		}
		
		RelatorioCliente RelCli = new RelatorioCliente();
		try {
			RelCli.imprimir(clientesrel); // gerar relatorio
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
