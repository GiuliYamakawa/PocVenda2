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
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.dao.ClienteDAO;
import br.univel.dao.ProdutoDAO;
import br.univel.dao.ProdutoVendaDAO;
import br.univel.dao.VendaDAO;
import br.univel.model.Cliente;
import br.univel.model.Produto;
import br.univel.model.ProdutoVenda;
import br.univel.model.Venda;

public class CadVenda extends JFrame {

	private static final long serialVersionUID = -6364715238148233355L;
	private JPanel contentPane;
	private JTextField txtValorCompra;
	private JTextField txtValorPago;
	private JTextField txtTroco;
	private List<Produto> carrinho = new ArrayList<Produto>();
	private JList<String> listaProdutos;
	private JList<String> listaCarrinho;
	private double total = 0;
	private JComboBox<String> cbClientes;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadVenda frame = new CadVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadVenda() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 130, 0, 29, 28, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblCliente = new JLabel("Cliente");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.WEST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 0;
		contentPane.add(lblCliente, gbc_lblCliente);
		
		List<Cliente> clientes = new ClienteDAO().getClientes();
		Vector<String> vetor = new Vector<String>(clientes.size());
		for(Cliente c : clientes)
			vetor.add(c.getId()+"-"+c.getNome());
		
		cbClientes = new JComboBox<String>(vetor);
		GridBagConstraints gbc_cbClientes = new GridBagConstraints();
		gbc_cbClientes.insets = new Insets(0, 0, 5, 5);
		gbc_cbClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbClientes.gridx = 1;
		gbc_cbClientes.gridy = 0;
		contentPane.add(cbClientes, gbc_cbClientes);
		
		JLabel lblCarrinho = new JLabel("Carrinho");
		GridBagConstraints gbc_lblCarrinho = new GridBagConstraints();
		gbc_lblCarrinho.insets = new Insets(0, 0, 5, 0);
		gbc_lblCarrinho.gridx = 3;
		gbc_lblCarrinho.gridy = 0;
		contentPane.add(lblCarrinho, gbc_lblCarrinho);
		
		JLabel lblProdutos = new JLabel("Produtos");
		GridBagConstraints gbc_lblProdutos = new GridBagConstraints();
		gbc_lblProdutos.anchor = GridBagConstraints.WEST;
		gbc_lblProdutos.insets = new Insets(0, 0, 5, 5);
		gbc_lblProdutos.gridx = 0;
		gbc_lblProdutos.gridy = 1;
		contentPane.add(lblProdutos, gbc_lblProdutos);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		List<Produto> produtos = new ProdutoDAO().getProdutos();
		Vector<String> vetorProdutos = new Vector<String>(produtos.size());
		for(Produto p: produtos)
			vetorProdutos.add(p.getId()+"-"+p.getDescricao());
		
		listaProdutos = new JList<String>(vetorProdutos);
		listaProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(evt.getClickCount() == 2){
					adicionarCarrinho(Integer.valueOf(listaProdutos.getSelectedValue().split("-")[0]));
				}
			}
		});
		scrollPane.setViewportView(listaProdutos);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		listaCarrinho = new JList<String>();
		scrollPane_1.setViewportView(listaCarrinho);
		
		JLabel lblValorDaCompra = new JLabel("Valor da compra");
		GridBagConstraints gbc_lblValorDaCompra = new GridBagConstraints();
		gbc_lblValorDaCompra.anchor = GridBagConstraints.EAST;
		gbc_lblValorDaCompra.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorDaCompra.gridx = 0;
		gbc_lblValorDaCompra.gridy = 2;
		contentPane.add(lblValorDaCompra, gbc_lblValorDaCompra);
		
		txtValorCompra = new JTextField();
		txtValorCompra.setEnabled(false);
		GridBagConstraints gbc_txtValorCompra = new GridBagConstraints();
		gbc_txtValorCompra.insets = new Insets(0, 0, 5, 5);
		gbc_txtValorCompra.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValorCompra.gridx = 1;
		gbc_txtValorCompra.gridy = 2;
		contentPane.add(txtValorCompra, gbc_txtValorCompra);
		txtValorCompra.setColumns(10);
		
		JLabel lblValorPago = new JLabel("Valor pago");
		GridBagConstraints gbc_lblValorPago = new GridBagConstraints();
		gbc_lblValorPago.anchor = GridBagConstraints.WEST;
		gbc_lblValorPago.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorPago.gridx = 0;
		gbc_lblValorPago.gridy = 3;
		contentPane.add(lblValorPago, gbc_lblValorPago);
	
		txtValorPago = new JTextField();
		txtValorPago.setText("0");
		txtValorPago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					calcular();
			}
		});
		
		GridBagConstraints gbc_txtValorPago = new GridBagConstraints();
		gbc_txtValorPago.insets = new Insets(0, 0, 5, 5);
		gbc_txtValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValorPago.gridx = 1;
		gbc_txtValorPago.gridy = 3;
		contentPane.add(txtValorPago, gbc_txtValorPago);
		txtValorPago.setColumns(10);
		
		JLabel lblTroco = new JLabel("Troco");
		GridBagConstraints gbc_lblTroco = new GridBagConstraints();
		gbc_lblTroco.anchor = GridBagConstraints.WEST;
		gbc_lblTroco.insets = new Insets(0, 0, 5, 5);
		gbc_lblTroco.gridx = 0;
		gbc_lblTroco.gridy = 4;
		contentPane.add(lblTroco, gbc_lblTroco);
		
		txtTroco = new JTextField();
		txtTroco.setEnabled(false);
		txtTroco.setColumns(10);
		GridBagConstraints gbc_txtTroco = new GridBagConstraints();
		gbc_txtTroco.insets = new Insets(0, 0, 5, 5);
		gbc_txtTroco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTroco.gridx = 1;
		gbc_txtTroco.gridy = 4;
		contentPane.add(txtTroco, gbc_txtTroco);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarVenda();
			}
		});
		panel.add(btnSalvar);
		
		final JFrame that = this;
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				that.dispose();
			}
		});
		panel.add(btnCancelar);
		
		JButton btnRemover = new JButton("Remover selecionado");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removerCarrinho(listaCarrinho.getSelectedIndex());
			}
		});
		GridBagConstraints gbc_btnRemover = new GridBagConstraints();
		gbc_btnRemover.gridx = 3;
		gbc_btnRemover.gridy = 5;
		contentPane.add(btnRemover, gbc_btnRemover);
	}

	protected void gerarVenda() {
		String clienteSelecionado = cbClientes.getSelectedItem().toString();						//pega a string do combobox
		Cliente c = new ClienteDAO().buscar(Integer.valueOf(clienteSelecionado.split("-")[0]));		//separa o nome do id e puxa do banco o cliente pelo dao
		
		Venda v = new Venda();
		v.setCliente(c);
		v.setTotal(new BigDecimal(this.total));
		
		VendaDAO vdao = new VendaDAO();
		vdao.inserir(v); 					//grava no banco a venda "vazia", só com o Cliente e o Total
		
		v = vdao.getUltimaVenda();			//pega do banco a última venda inserida
		List<ProdutoVenda> itens = new ArrayList<ProdutoVenda>();		//lista para armazenar os ProdutoVenda dos Produtos que estão no carrinho
		
		for(Produto p: carrinho){ 			//percorre o carrinho para criar os ProdutoVenda
			ProdutoVenda pv = new ProdutoVenda();
			pv.setProduto(p);
			pv.setVenda(v);
			pv.setValor(p.getValor());
			itens.add(pv);
		}
		v.setItens(itens);					//atribui a lista de ProdutoVenda à ultima venda inserida
		vdao.atualizar(v);					//atualiza a última venda, agora com os ProdutoVenda que estavam no carrinho
		
		ProdutoVendaDAO pvdao = new ProdutoVendaDAO();
		for(ProdutoVenda pv : itens)
			pvdao.inserir(pv);
	}

	protected void calcular() {
		double pago = Double.valueOf(txtValorPago.getText()); 
		txtTroco.setText(NumberFormat.getCurrencyInstance().format(total-pago));
	}

	protected void removerCarrinho(int index) {
		carrinho.remove(index);
		atualizarCarrinho();
	}

	protected void adicionarCarrinho(Integer idProduto) {
		Produto p = new ProdutoDAO().buscar(idProduto);
		carrinho.add(p);
		
		atualizarCarrinho();
	}
	
	protected void atualizarCarrinho(){
		listaCarrinho.removeAll();
		total  = 0;
		Vector<String> v = new Vector<String>(carrinho.size());
		for(Produto p: carrinho){
			v.add(p.getId()+"-"+p.getDescricao());
			total += p.getValor().doubleValue();
		}
		listaCarrinho.setListData(v);
		listaCarrinho.repaint();
		txtValorCompra.setText(NumberFormat.getCurrencyInstance().format(total));

	}

}
