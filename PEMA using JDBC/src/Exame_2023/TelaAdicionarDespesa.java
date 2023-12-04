package Exame_2023;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class TelaAdicionarDespesa extends JFrame implements ActionListener{

	private JLabel lblDescricao,lblCategoria,lblValor,lblData;
	private JTextField txtDescricao,txtValor,txtData;
	private JComboBox comboBox;
	private JPanel panel,panel1,panel2,panel3,panelBotoes,panelPrincipal;
	private JButton btAdicionar ,btVoltar;
	
	public TelaAdicionarDespesa(){
		this.setTitle("Tela de adicionar despesas");
		this.setLocation(500,500);
		this.setSize(300,335);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblDescricao = new JLabel("Descricao");
		txtDescricao = new JTextField(10);
		panel.add(lblDescricao);
		panel.add(txtDescricao);
		
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblCategoria = new JLabel("Categoria");
		String[] categorias = {"Alimentacao","Transporte","Lazer","Outros"};
		comboBox = new JComboBox(categorias);
		comboBox.addActionListener(this);
		panel1.add(lblCategoria);
		panel1.add(comboBox);
		
		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblValor = new JLabel("Valor");
		txtValor = new JTextField(10);
		panel2.add(lblValor);
		panel2.add(txtValor);
		
		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		lblData = new JLabel("Data");
		txtData = new JTextField(10);
		panel3.add(lblData);
		panel3.add(txtData);
		
		panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btVoltar = new JButton("Voltar");
		btVoltar.addActionListener(this);
		
		btAdicionar = new JButton("Adicionar");
		btAdicionar.addActionListener(this);

		panelBotoes.add(btVoltar);
		
		
		panelBotoes.add(btAdicionar);
		
		panelPrincipal = new JPanel();
		panelPrincipal.add(panel);
		panelPrincipal.add(panel1);
		panelPrincipal.add(panel2);
		panelPrincipal.add(panel3);
		panelPrincipal.add(panelBotoes);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
		
		this.add(panelPrincipal);
		this.setVisible(true);
	}
	
	public void adicionarDespesa(String descricao,String categoria,double valor,Date data) {
		try {
			Connection connection = Criador_Conexao.getConnection();
			
			String insertQuery = "INSERT INTO Despesa (descricao,categoria,valor,dataa) VALUES (?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, descricao);
			statement.setString(2, categoria);
			statement.setDouble(3, valor);
			statement.setDate(4, data);
			statement.execute();
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		new TelaAdicionarDespesa();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btAdicionar) {
			String descricao = txtDescricao.getText();
			String categoria = comboBox.getSelectedItem().toString();
			double valor = Double.valueOf(txtValor.getText());
			Date data = Date.valueOf(txtData.getText());
			
			if(descricao==null || categoria==null || valor < 0 || data == null) {
				JOptionPane.showMessageDialog(this, "Um dos campos esta vazio!","erro",JOptionPane.ERROR_MESSAGE);
			}else {
				adicionarDespesa(descricao,categoria,valor,data);
				JOptionPane.showMessageDialog(this,"Despesa adicionada com sucesso!");
			}
		}
		if(e.getSource() == btVoltar) {
			TelaDeGestaoDespesas telaGestaoDespesas = new TelaDeGestaoDespesas();
			telaGestaoDespesas.setVisible(true);
			this.dispose();
		}
	}
}
