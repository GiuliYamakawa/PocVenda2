package br.univel.tela;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.univel.dao.ClienteDAO;
import br.univel.enums.Estados;
import br.univel.enums.Genero;
import br.univel.model.Cliente;
import br.univel.tabelas.ModeloTabelaClientes;

public class CadCliente extends JFrame {

	private static final long serialVersionUID = -413658732955104121L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtEmail;
	private JPanel panel;
	private JRadioButton rdbtnFeminino;
	private JRadioButton rdbtnMasculino;
	private JComboBox<String> cbEstado;
	private JPanel panel_1;
	private JButton btnSalvar;
	private JButton btnDeletar;
	private JScrollPane scrollPane;
	private JTable table;
	private ClienteDAO dao = new ClienteDAO();
	private ModeloTabelaClientes modelo = new ModeloTabelaClientes(dao.getClientes());
	private Cliente clienteSelecionado = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadCliente frame = new CadCliente();
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
	public CadCliente() {	
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 543, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 165, 202, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
					if(buscarCliente(Integer.valueOf(txtId.getText().trim()))){
						carregaForm();
					}
				}
			}
		});
		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.gridwidth = 2;
		gbc_txtId.insets = new Insets(0, 0, 5, 0);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 1;
		gbc_txtId.gridy = 0;
		contentPane.add(txtId, gbc_txtId);
		txtId.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 2;
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 1;
		contentPane.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.WEST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 2;
		contentPane.add(lblTelefone, gbc_lblTelefone);
		
		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.gridwidth = 2;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 2;
		contentPane.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblEn = new JLabel("Endere\u00E7o");
		GridBagConstraints gbc_lblEn = new GridBagConstraints();
		gbc_lblEn.anchor = GridBagConstraints.WEST;
		gbc_lblEn.insets = new Insets(0, 0, 5, 5);
		gbc_lblEn.gridx = 0;
		gbc_lblEn.gridy = 3;
		contentPane.add(lblEn, gbc_lblEn);
		
		txtEndereco = new JTextField();
		GridBagConstraints gbc_txtEndereco = new GridBagConstraints();
		gbc_txtEndereco.gridwidth = 2;
		gbc_txtEndereco.insets = new Insets(0, 0, 5, 0);
		gbc_txtEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndereco.gridx = 1;
		gbc_txtEndereco.gridy = 3;
		contentPane.add(txtEndereco, gbc_txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.WEST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 4;
		contentPane.add(lblCidade, gbc_lblCidade);
		
		txtCidade = new JTextField();
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.gridwidth = 2;
		gbc_txtCidade.insets = new Insets(0, 0, 5, 0);
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.gridx = 1;
		gbc_txtCidade.gridy = 4;
		contentPane.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
		gbc_lblEstado.anchor = GridBagConstraints.WEST;
		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstado.gridx = 0;
		gbc_lblEstado.gridy = 5;
		contentPane.add(lblEstado, gbc_lblEstado);
		
		cbEstado = new JComboBox<String>();
		cbEstado.setModel(new DefaultComboBoxModel(Estados.values()));
		GridBagConstraints gbc_cbEstado = new GridBagConstraints();
		gbc_cbEstado.gridwidth = 2;
		gbc_cbEstado.insets = new Insets(0, 0, 5, 0);
		gbc_cbEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbEstado.gridx = 1;
		gbc_cbEstado.gridy = 5;
		
		contentPane.add(cbEstado, gbc_cbEstado);
		
		JLabel lblEnail = new JLabel("E-mail");
		GridBagConstraints gbc_lblEnail = new GridBagConstraints();
		gbc_lblEnail.anchor = GridBagConstraints.WEST;
		gbc_lblEnail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnail.gridx = 0;
		gbc_lblEnail.gridy = 6;
		contentPane.add(lblEnail, gbc_lblEnail);
		
		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.gridwidth = 2;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 6;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblGnero = new JLabel("G\u00EAnero");
		GridBagConstraints gbc_lblGnero = new GridBagConstraints();
		gbc_lblGnero.anchor = GridBagConstraints.WEST;
		gbc_lblGnero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGnero.gridx = 0;
		gbc_lblGnero.gridy = 7;
		contentPane.add(lblGnero, gbc_lblGnero);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.WEST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setSelected(true);
		rdbtnFeminino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnMasculino.setSelected(false);
			}
		});
		panel.add(rdbtnFeminino);
		
		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnFeminino.setSelected(false);
			}
		});
		panel.add(rdbtnMasculino);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.anchor = GridBagConstraints.EAST;
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 7;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente c = new Cliente();
				c.setNome(txtNome.getText());
				c.setCidade(txtCidade.getText());
				c.setEndereco(txtEndereco.getText());
				c.setEmail(txtEmail.getText());
				if(rdbtnFeminino.isSelected()){
					c.setGenero(Genero.FEMININO);
				} else {
					c.setGenero(Genero.MASCULINO);
				}
				c.setEstado(Estados.valueOf(cbEstado.getSelectedItem().toString()));
				c.setTelefone(txtTelefone.getText());
				
				if(clienteSelecionado == null){ // se não estiver editando insere um novo
					dao.inserir(c);
					JOptionPane.showMessageDialog(null, "Registro gravado!");
					limpaForm();
				}else{ //se não ele da um update
					c.setId(clienteSelecionado.getId());
					dao.atualizar(c);
					JOptionPane.showMessageDialog(null, "Registro atualizado!");
					limpaForm();
				}
				carregarTabela();
			}
		});
		panel_1.add(btnSalvar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(clienteSelecionado != null){		
					if(JOptionPane.showConfirmDialog(null,"Deseja realmente apagar?","ATENÇÃO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						dao.deletar(clienteSelecionado.getId());
						limpaForm();
						carregarTabela();
					}
				}
			}
		});
		panel_1.add(btnDeletar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 8;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(modelo);
		carregarTabela();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ // pega a tabela adiciona um evento de click na tabela para editar
	        public void valueChanged(ListSelectionEvent event) {
	        	Object id = table.getValueAt(table.getSelectedRow(), 0);
	            if (buscarCliente(new Integer(id.toString()).intValue())){ //Se ele encontra o cliente carrega a form
	            	carregaForm();
	            }
	        }
	    });
	}
	
	protected void carregaForm() { // preenche o formulario com a informação do registro que esta sendo editado
		txtNome.setText(clienteSelecionado.getNome());
		txtTelefone.setText(clienteSelecionado.getTelefone());
		txtCidade.setText(clienteSelecionado.getCidade());
		txtEndereco.setText(clienteSelecionado.getEndereco());
		txtEmail.setText(clienteSelecionado.getEmail());
		cbEstado.setSelectedItem(clienteSelecionado.getEstado());
		if(clienteSelecionado.getGenero()==Genero.MASCULINO){
			rdbtnMasculino.setSelected(true);
			rdbtnFeminino.setSelected(false);
		}
		if(clienteSelecionado.getGenero()==Genero.FEMININO){
			rdbtnFeminino.setSelected(true);
			rdbtnMasculino.setSelected(false);
		}
	}

	protected boolean buscarCliente(Integer id) { //pega o cliente pelo id
		for(Cliente c : modelo.getLista()){
			if(c.getId() == id){
				clienteSelecionado = c;
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Registro não existe!");
		limpaForm();
		return false;
	}

	private void limpaForm() { // sempre que grava ou deleta deixa tudo vazio os campos
		txtId.setText("");
		txtNome.setText("");
		txtTelefone.setText("");
		txtCidade.setText("");
		txtEndereco.setText("");
		txtEmail.setText("");
		cbEstado.setSelectedItem(Estados.PR);
		clienteSelecionado = null;
	}

	private void carregarTabela() { // os dados da tabela quando cadastra novo atualiza a tabela
		modelo.setLista(dao.getClientes());
		table.repaint();
		scrollPane.repaint();
		scrollPane.setViewportView(table);
	}

}
