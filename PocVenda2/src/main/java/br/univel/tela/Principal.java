package br.univel.tela;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	private static final long serialVersionUID = -2999372076212891622L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public Principal() {
		//setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		JMenuItem mntmCadastro_1 = new JMenuItem("Cadastro");
		mntmCadastro_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadUsuario frame = new CadUsuario();
				frame.setVisible(true);
			}
		});
		mnUsuario.add(mntmCadastro_1);
		
//		JMenuItem mntmRelatrios_1 = new JMenuItem("Relat\u00F3rios");
//		mntmRelatrios_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				RelCliente recli = new RelCliente();
//				recli.setVisible(true);		
//				
//			}
//		});
//		mnUsuario.add(mntmRelatrios_1);
		
		JMenu mnClientes = new JMenu("Cliente");
		menuBar.add(mnClientes);
		
		JMenuItem mntmCadastro = new JMenuItem("Cadastro");
		mntmCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadCliente frame = new CadCliente();
				frame.setVisible(true);
			}
		});
		mnClientes.add(mntmCadastro);
		
		JMenuItem mntmRelatrios = new JMenuItem("Relat\u00F3rios");
		mntmRelatrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelCliente recli = new RelCliente();
				recli.setVisible(true);		
			}
		});
		mnClientes.add(mntmRelatrios);
		
		JMenu mnProdutos = new JMenu("Produto");
		menuBar.add(mnProdutos);
		
		JMenuItem mntmCadastro_2 = new JMenuItem("Cadastro");
		mntmCadastro_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadProduto frame = new CadProduto();
				frame.setVisible(true);
			}
		});
		mnProdutos.add(mntmCadastro_2);
		
		JMenuItem mntmRelatrios_2 = new JMenuItem("Relat\u00F3rios");
		mntmRelatrios_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelProduto rel = new RelProduto();
				rel.setVisible(true);		
			}
		});
		mnProdutos.add(mntmRelatrios_2);
		
		JMenu mnVenda = new JMenu("Venda");
		menuBar.add(mnVenda);
		
		JMenuItem mntmCadastro_3 = new JMenuItem("Cadastro");
		mntmCadastro_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadVenda frame = new CadVenda();
				frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		mnVenda.add(mntmCadastro_3);
		
		JMenuItem mntmRelatrios_3 = new JMenuItem("Relat\u00F3rios");
		mnVenda.add(mntmRelatrios_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}

}
