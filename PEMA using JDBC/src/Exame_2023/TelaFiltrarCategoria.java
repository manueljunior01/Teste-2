package Exame_2023;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TelaFiltrarCategoria extends JFrame implements ActionListener{

	private JLabel lblSelecaoCategoria;
	private JComboBox comboBox;
	private JButton btFiltrar;
	private JPanel panel,panel1,panelPrincipal,panelBotao;
	
	public TelaFiltrarCategoria(){
		this.setTitle("Filtrar por Categoria");
		this.setLocation(500,500);
		this.setSize(350,150);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblSelecaoCategoria = new JLabel("Selecione a categoria:");
		String[] categorias = {"Alimentacao","Transporte","Lazer","Outros"};
		comboBox = new JComboBox(categorias);
		
		btFiltrar = new JButton("Filtrar");
		btFiltrar.addActionListener(this);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(lblSelecaoCategoria);
		panel.add(comboBox);
		
		panelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBotao.add(btFiltrar);
		
		panelPrincipal = new JPanel();
		panelPrincipal.add(panel);
		panelPrincipal.add(panelBotao);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
		
		this.add(panelPrincipal);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaFiltrarCategoria();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btFiltrar) {
			String filtro = comboBox.getSelectedItem().toString();
			TelaDeGestaoDespesas telaGestaoDespesas = new TelaDeGestaoDespesas();
			telaGestaoDespesas.filtrarDespesas(filtro);
			telaGestaoDespesas.setVisible(true);
			this.dispose();
		}
	}
}
