package projeto;


import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;


public class TelaLogin extends JFrame 
{
	private static JTextField login ;
	private static JPasswordField senhaU;
	private JButton ok;
	
	// Metodo coloca os componentes na GUI 
	public TelaLogin()
	{
		super("Login Funcionário");
		setLayout(null);
		
		
		Label llogin = new Label("Login: ");
		add(llogin);
		llogin.setBounds(10, 10, 40, 20);
		login = new JTextField();
		add(login);
		login.setBounds(55, 10, 100, 20);
		login.getText();
		
				
		Label  lsenha = new Label("Senha: ");
		add(lsenha);
		lsenha.setBounds(10, 40, 40, 20);
		senhaU = new JPasswordField();
		add(senhaU);
		senhaU.setBounds(55, 40, 100, 20);
		senhaU.getText();
		
		
		ok = new JButton("Entrar");
		add(ok);
		ok.setBounds(55, 70, 100, 20);

		TextoHandler textoHandler = new TextoHandler();
		login.addActionListener(textoHandler);
		senhaU.addActionListener(textoHandler);
		
		ButtonHandler buttonHandler = new ButtonHandler();
		ok.addActionListener(buttonHandler);
		
	}
	// Classe para eventos de texto
	public class TextoHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
	// Classe para eventos de botões
	public class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == ok){
				//logar();	    	
			}
		}
	}
	public static void main(String[] args) 
	{
		/*
		TelaLogin telaLogin = new TelaLogin();
		telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setMinimumSize(new Dimension(300,150));
        telaLogin.setVisible(true);
        telaLogin.setLocationRelativeTo(null);
		
		}*/
	//Metodo para logar no sistema
	/*public void logar() throws Exception
	{
		PreparedStatement canal = null;
		String consulta =null;
		try
		{
			Connection conexao = Conexao.abrir();
			consulta = "SELECT * FROM FUNCIONARIO WHERE Login=? and Senha=?";
			canal = (PreparedStatement) conexao.prepareStatement(consulta);
			canal.setString(1, login.getText());
			canal.setString(2, senhaU.getText());
			
			ResultSet rs = canal.executeQuery();
			
			if (rs.next())
            {
                JOptionPane.showMessageDialog(null, "Logado com sucesso!");*/
                TelaPrincipal telaprincipal = new TelaPrincipal();
                telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                telaprincipal.setMinimumSize(new Dimension(1366, 730));
                telaprincipal.setVisible(true);
                telaprincipal.setLocationRelativeTo(null);
                /*

				setVisible(false);    
            } 
            else
            {   
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!!");
                  
            }
			canal.close();
			conexao.close();
		}
		catch(SQLException sqlException)
		{
			System.out.println(sqlException);
		}
		catch(ClassNotFoundException classNotFoundException)
		{
			System.out.println(classNotFoundException);
		}*/
	}
}


