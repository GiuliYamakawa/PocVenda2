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
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.univel.dao.ProdutoDAO;
import br.univel.enums.Categoria;
import br.univel.enums.Unidade;
import br.univel.model.Produto;
import br.univel.tabelas.ModeloTabelaProdutos;

public class CadProduto extends JFrame {

	private static final long serialVersionUID = 2321049365040612471L;
	private JPanel contentPane;
	private JTextField txtCodigoBarra;
	private JTextField txtDescricao;
	private JTextField txtCusto;
	private JTextField txtId;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtMargemLucro;
	private JComboBox<String> cbUnidade;
	private JComboBox<String> cbCategoria;
	private ProdutoDAO dao = new ProdutoDAO();
	private ModeloTabelaProdutos modelo = new ModeloTabelaProdutos(dao.getProdutos());
	private Produto produtoSelecionado = null;
	private JPanel panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadProduto frame = new CadProduto();
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
	public CadProduto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{60, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
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
					if(buscarProduto(Integer.valueOf(txtId.getText().trim()))){
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
		
		JLabel lblCdigoDeBarra = new JLabel("C\u00F3digo de barra");
		GridBagConstraints gbc_lblCdigoDeBarra = new GridBagConstraints();
		gbc_lblCdigoDeBarra.anchor = GridBagConstraints.WEST;
		gbc_lblCdigoDeBarra.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdigoDeBarra.gridx = 0;
		gbc_lblCdigoDeBarra.gridy = 1;
		contentPane.add(lblCdigoDeBarra, gbc_lblCdigoDeBarra);
		
		txtCodigoBarra = new JTextField();
		GridBagConstraints gbc_txtCodigoBarra = new GridBagConstraints();
		gbc_txtCodigoBarra.insets = new Insets(0, 0, 5, 0);
		gbc_txtCodigoBarra.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigoBarra.gridx = 1;
		gbc_txtCodigoBarra.gridy = 1;
		contentPane.add(txtCodigoBarra, gbc_txtCodigoBarra);
		txtCodigoBarra.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
		gbc_lblCategoria.anchor = GridBagConstraints.WEST;
		gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoria.gridx = 0;
		gbc_lblCategoria.gridy = 2;
		contentPane.add(lblCategoria, gbc_lblCategoria);
		
		cbCategoria = new JComboBox<String>();
		cbCategoria.setModel(new DefaultComboBoxModel(Categoria.values()));
		GridBagConstraints gbc_cbCategoria = new GridBagConstraints();
		gbc_cbCategoria.insets = new Insets(0, 0, 5, 0);
		gbc_cbCategoria.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCategoria.gridx = 1;
		gbc_cbCategoria.gridy = 2;
		
		contentPane.add(cbCategoria, gbc_cbCategoria);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.anchor = GridBagConstraints.WEST;
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 3;
		contentPane.add(lblDescrio, gbc_lblDescrio);
		
		txtDescricao = new JTextField();
		GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
		gbc_txtDescricao.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescricao.gridx = 1;
		gbc_txtDescricao.gridy = 3;
		contentPane.add(txtDescricao, gbc_txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblUnidade = new JLabel("Unidade");
		GridBagConstraints gbc_lblUnidade = new GridBagConstraints();
		gbc_lblUnidade.anchor = GridBagConstraints.WEST;
		gbc_lblUnidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidade.gridx = 0;
		gbc_lblUnidade.gridy = 4;
		contentPane.add(lblUnidade, gbc_lblUnidade);
		
		cbUnidade = new JComboBox<String>();
		cbUnidade.setModel(new DefaultComboBoxModel(Unidade.values()));
		GridBagConstraints gbc_cbUnidade = new GridBagConstraints();
		gbc_cbUnidade.insets = new Insets(0, 0, 5, 0);
		gbc_cbUnidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbUnidade.gridx = 1;
		gbc_cbUnidade.gridy = 4;
		
		contentPane.add(cbUnidade, gbc_cbUnidade);
		
		JLabel lblCusto = new JLabel("Custo");
		GridBagConstraints gbc_lblCusto = new GridBagConstraints();
		gbc_lblCusto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCusto.anchor = GridBagConstraints.WEST;
		gbc_lblCusto.gridx = 0;
		gbc_lblCusto.gridy = 5;
		contentPane.add(lblCusto, gbc_lblCusto);
		
		txtCusto = new JTextField();
		GridBagConstraints gbc_txtCusto = new GridBagConstraints();
		gbc_txtCusto.insets = new Insets(0, 0, 5, 0);
		gbc_txtCusto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCusto.gridx = 1;
		gbc_txtCusto.gridy = 5;
		contentPane.add(txtCusto, gbc_txtCusto);
		txtCusto.setColumns(10);
		
		JLabel lblMargemLucro = new JLabel("Margem de Lucro");
		GridBagConstraints gbc_lblMargemLucro = new GridBagConstraints();
		gbc_lblMargemLucro.anchor = GridBagConstraints.WEST;
		gbc_lblMargemLucro.insets = new Insets(0, 0, 5, 5);
		gbc_lblMargemLucro.gridx = 0;
		gbc_lblMargemLucro.gridy = 6;
		contentPane.add(lblMargemLucro, gbc_lblMargemLucro);
		
		txtMargemLucro = new JTextField();
		txtMargemLucro.setColumns(10);
		GridBagConstraints gbc_txtMargemLucro = new GridBagConstraints();
		gbc_txtMargemLucro.insets = new Insets(0, 0, 5, 0);
		gbc_txtMargemLucro.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMargemLucro.gridx = 1;
		gbc_txtMargemLucro.gridy = 6;
		contentPane.add(txtMargemLucro, gbc_txtMargemLucro);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 7;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto p = new Produto();
				p.setCodigoBarras(txtCodigoBarra.getText());
				p.setCategoria(Categoria.valueOf(cbCategoria.getSelectedItem().toString()));
				p.setDescricao(txtDescricao.getText());
				p.setUnidade(Unidade.valueOf(cbUnidade.getSelectedItem().toString()));
				p.setCusto(new BigDecimal(txtCusto.getText().trim()));
				p.setMargemLucro(new BigDecimal(txtMargemLucro.getText().trim()));
				
				if(produtoSelecionado == null){
					dao.inserir(p);
					JOptionPane.showMessageDialog(null, "Registro salvo!");
					limpaForm();
				}else{
					p.setId(produtoSelecionado.getId());
					dao.atualizar(p);
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
				if(produtoSelecionado != null){		
					if(JOptionPane.showConfirmDialog(null,"Deseja realmente apagar?","ATENÇÃO",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						dao.deletar(produtoSelecionado.getId());
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
		gbc_scrollPane.gridy = 8;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(modelo);
		
		carregarTabela();
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	Object id = table.getValueAt(table.getSelectedRow(), 0);
	            if (buscarProduto(new Integer(id.toString()).intValue())){
	            	carregaForm();
	            }
	        }
	    });
	}

	protected void carregaForm() {
		txtCodigoBarra.setText(produtoSelecionado.getCodigoBarras());
		txtCusto.setText(produtoSelecionado.getCusto().toString());
		txtDescricao.setText(produtoSelecionado.getDescricao());
		txtMargemLucro.setText(produtoSelecionado.getMargemLucro().toString());
		cbCategoria.setSelectedItem(produtoSelecionado.getCategoria());
		cbUnidade.setSelectedItem(produtoSelecionado.getUnidade());
	}

	protected boolean buscarProduto(Integer id) {
		for(Produto p : modelo.getLista()){
			if(p.getId() == id){
				produtoSelecionado = p;
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Registro não existe!");
		limpaForm();
		return false;
	}

	private void limpaForm() {
		txtId.setText("");
		txtCodigoBarra.setText("");
		txtCusto.setText("");
		txtDescricao.setText("");
		txtMargemLucro.setText("");
		cbCategoria.setSelectedItem(Categoria.LIMPEZA);
		cbUnidade.setSelectedItem(Unidade.KG);
		
		produtoSelecionado = null;
	}

	private void carregarTabela() {
		modelo.setLista(dao.getProdutos());
		table.repaint();
		scrollPane.repaint();
		scrollPane.setViewportView(table);
	}

}
