package Exame_2023;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TelaDeGestaoDespesas extends JFrame implements ActionListener{

	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private JButton btAdicionarDespesa,btVisualizarDespesas,btFiltrarDespesas;
	private JPanel panelBotoes;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem adicionarItem,visualizarItem,filtrarItem,sairItem;

	public TelaDeGestaoDespesas() {
		this.setTitle("Tela de Gestao de Despesas");
		this.setLocation(500,500);
		this.setSize(650,525);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();

		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		adicionarItem = new JMenuItem("Adicionar");
		adicionarItem.setMnemonic(KeyEvent.VK_A);

		visualizarItem = new JMenuItem("Visualizar");
		visualizarItem.setMnemonic(KeyEvent.VK_V);

		filtrarItem = new JMenuItem("Filtar");
		filtrarItem.setMnemonic(KeyEvent.VK_F);

		sairItem = new JMenuItem("Sair");
		sairItem.addActionListener(this);
		sairItem.setMnemonic(KeyEvent.VK_S);

		menu.add(adicionarItem);
		menu.add(visualizarItem);
		menu.add(filtrarItem);
		menu.addSeparator();
		menu.add(sairItem);

		menuBar.add(menu);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Descricao");
		tableModel.addColumn("Categoria");
		tableModel.addColumn("valor");
		tableModel.addColumn("Data");

		table = new JTable(tableModel);

		scrollPane = new JScrollPane(table);

		panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));

		btAdicionarDespesa = new JButton("Adicionar Despesa");
		btAdicionarDespesa.addActionListener(this);

		btVisualizarDespesas = new JButton("Visualizar despesas");
		btVisualizarDespesas.addActionListener(this);

		btFiltrarDespesas = new JButton("Filtrar por categoria");
		btFiltrarDespesas.addActionListener(this);

		panelBotoes.add(btAdicionarDespesa);
		panelBotoes.add(btVisualizarDespesas);
		panelBotoes.add(btFiltrarDespesas);


		this.setJMenuBar(menuBar);
		this.add(scrollPane,"North");
		this.add(panelBotoes,"South");
		this.setVisible(true);
	}

	public void filtrarDespesas(String filtro) {
		try{
			Connection connection = Criador_Conexao.getConnection();
			
			String filterQuery = "SELECT * FROM Despesa WHERE categoria= ? ";
			PreparedStatement statement = connection.prepareStatement(filterQuery);
			statement.setString(1,filtro);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String descricao = resultSet.getString("descricao");
				String categoria = resultSet.getString("categoria");
				double valor = resultSet.getDouble("valor");
				Date data = resultSet.getDate("dataa");
				
				tableModel.addRow(new Object[] {descricao,categoria,valor,data});
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void visualizarDespesas() {
		try {
			Connection connection = Criador_Conexao.getConnection();
			
			String querySQl = "SELECT * FROM Despesa";
			
			PreparedStatement statement = connection.prepareStatement(querySQl);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String descricao = resultSet.getString("descricao");
				String categoria = resultSet.getString("categoria");
				double valor = resultSet.getDouble("valor");
				Date data = resultSet.getDate("dataa");
				
				tableModel.addRow(new Object[] {descricao,categoria,valor,data});
				
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
		new TelaDeGestaoDespesas();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btAdicionarDespesa) {
			TelaAdicionarDespesa telaAdicionarDespesa = new TelaAdicionarDespesa();
			telaAdicionarDespesa.setVisible(true);
			this.dispose();
		}
		if(e.getSource() == btVisualizarDespesas) {
			visualizarDespesas();
		}
		if(e.getSource() == btFiltrarDespesas) {
			TelaFiltrarCategoria telaCategoria = new TelaFiltrarCategoria();
			telaCategoria.setVisible(true);
			this.dispose();
		}
		if(e.getSource()==sairItem) {
			System.exit(0);
		}
	}
}
