package br.univel.tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;

import br.univel.dao.ClienteDAO;
import br.univel.dao.UsuarioDAO;
import br.univel.model.Cliente;
import br.univel.model.Usuario;
import br.univel.tabelas.ModeloTabelaUsuarios;

public class CadUsuario extends JFrame {

	private static final long serialVersionUID = -1905672472538040173L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtSenha;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox<String> cbClientes;
	private ClienteDAO cdao = new ClienteDAO();
	private UsuarioDAO udao = new UsuarioDAO();
	private ModeloTabelaUsuarios modelo = new ModeloTabelaUsuarios(udao.getUsuarios());
	private Usuario usuarioSelecionado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadUsuario frame = new CadUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadUsuario() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		contentPane.add(lblId, gbc_lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					if(buscarUsuario(Integer.valueOf(txtId.getText().trim()))){
						carregaForm();
					}
				}
			}
		});
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		contentPane.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JLabel lblIdDoCliente = new JLabel("Cliente");
		GridBagConstraints gbc_lblIdDoCliente = new GridBagConstraints();
		gbc_lblIdDoCliente.anchor = GridBagConstraints.EAST;
		gbc_lblIdDoCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdDoCliente.gridx = 0;
		gbc_lblIdDoCliente.gridy = 1;
		contentPane.add(lblIdDoCliente, gbc_lblIdDoCliente);
		
		List<Cliente> clientes = cdao.getClientes();
		Vector<String> vetor = new Vector<String>(clientes.size());
		for(Cliente c : clientes)
			vetor.add(c.getId()+"-"+c.getNome());
		
		cbClientes = new JComboBox<String>(vetor);
		GridBagConstraints gbc_cbCliente = new GridBagConstraints();
		gbc_cbCliente.insets = new Insets(0, 0, 5, 0);
		gbc_cbCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCliente.gridx = 1;
		gbc_cbCliente.gridy = 1;
		contentPane.add(cbClientes, gbc_cbCliente);
		
		JLabel lblSenha = new JLabel("Senha");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 2;
		contentPane.add(lblSenha, gbc_lblSenha);
		
		txtSenha = new JTextField();
		GridBagConstraints gbc_txtSenha = new GridBagConstraints();
		gbc_txtSenha.insets = new Insets(0, 0, 5, 0);
		gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSenha.gridx = 1;
		gbc_txtSenha.gridy = 2;
		contentPane.add(txtSenha, gbc_txtSenha);
		txtSenha.setColumns(10);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idCliente = Integer.valueOf(((String) cbClientes.getSelectedItem()).split("-")[0]);
				Cliente c = cdao.buscar(idCliente);
				Usuario u = new Usuario();
				u.setCliente(c);
				u.setSenha(txtSenha.getText().trim());
				
				if(usuarioSelecionado == null){
					udao.inserir(u);
					JOptionPane.showMessageDialog(null, "Registro gravado!");
					limpaForm();
				}else{
					u.setId(usuarioSelecionado.getId());
					udao.atualizar(u);
					JOptionPane.showMessageDialog(null, "Registro atualizado!");
					limpaForm();
				}
				carregarTabela();
			}
		});
		panel.add(btnSalvar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usuarioSelecionado != null){		
					if(JOptionPane.showConfirmDialog(null,"Deseja realmente apagar?","ATENÇÃO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						udao.deletar(Integer.valueOf(usuarioSelecionado.getId()));
						limpaForm();
						carregarTabela();
					}
				}
			}
		});
		panel.add(btnDeletar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(modelo);
		
		carregarTabela();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	Object id = table.getValueAt(table.getSelectedRow(), 0);
	            if (buscarUsuario(new Integer(id.toString()).intValue())){
	            	carregaForm();
	            }
	        }
	    });
	}

	private void carregarTabela() {
		modelo.setLista(udao.getUsuarios());
		table.repaint();
		scrollPane.repaint();
		scrollPane.setViewportView(table);
	}

	protected void carregaForm() {
		txtSenha.setText(usuarioSelecionado.getSenha());
		int idx = 0;
		for(int i = 0; i < cbClientes.getItemCount(); i++){
			String selecao = cbClientes.getItemAt(i);
			if(Integer.valueOf(selecao.split("-")[0]) == usuarioSelecionado.getCliente().getId()){
				idx = i;
			}
		}
		cbClientes.setSelectedIndex(idx);
	}

	protected boolean buscarUsuario(Integer id) {
		for(Usuario u : modelo.getLista()){
			if(u.getId() == id){
				usuarioSelecionado = u;
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Registro não existe!");
		limpaForm();
		return false;
	}

	private void limpaForm() {
		txtId.setText("");
		txtSenha.setText("");
		cbClientes.setSelectedIndex(0);
		usuarioSelecionado = null;
	}

}
