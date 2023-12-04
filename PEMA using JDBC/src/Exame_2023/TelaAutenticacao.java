package Exame_2023;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaAutenticacao extends JFrame implements ActionListener{

	private JLabel lblUsername,lblPassword;
	private JButton btLogin;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JPanel panel,panel1,panelBotao,panelPrincipal;
	
	public TelaAutenticacao() {
		this.setTitle("Tela de Autenticacao");
		this.setSize(450,300);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		lblUsername = new JLabel("Username");
		txtUsername = new JTextField(10);
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel.add(lblUsername);
		panel.add(txtUsername);
		
		lblPassword = new JLabel("Password");
		passwordField = new JPasswordField(10);
		
		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(lblPassword);
		panel1.add(passwordField);
		
		btLogin = new JButton("Login");
		btLogin.addActionListener(this);
		panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBotao.add(btLogin);
		
		panelPrincipal = new JPanel();
		panelPrincipal.add(panel);
		panelPrincipal.add(panel1);
		panelPrincipal.add(panelBotao);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
		
		this.add(panelPrincipal);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TelaAutenticacao();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btLogin) {
			String username = txtUsername.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			if(username.equals("admin") && password.equals("1234")) {
				JOptionPane.showMessageDialog(this,"Bem-Vindo!");
				TelaDeGestaoDespesas telaGestao = new TelaDeGestaoDespesas();
				telaGestao.setVisible(true);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this,"Credenciais invalidas!","erro",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
