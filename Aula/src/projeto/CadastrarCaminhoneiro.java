package projeto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class CadastrarCaminhoneiro extends JFrame 
{	
	private JTextField CNH,
					   nome,
					   telefone,
					   endereco,
					   cpf_Funcionario;
	
	private JButton cadastrar,
					voltar,
					editar,
					atualizar,
					excluir;
	
	private JTable tabela;
	
	DefaultTableModel modelo = new DefaultTableModel();
	
	// Metodo que coloca os componentes na GUI
	public void Componentes()
	{
		Label lcnh = new Label("CNH: ");
		lcnh.setBounds(10, 10, 40, 20);
		add(lcnh);
		CNH = new JTextField(10);
		CNH.setBounds(50, 10, 160, 20);
		this.add(CNH);
		CNH.getText();
	
		Label lnome = new Label("Nome: ");
		lnome.setBounds(10, 40, 40, 20);
		add(lnome);
		nome =  new JTextField(10);
		nome.setBounds(50, 40, 160, 20);
		this.add(nome);
		nome.getText();
		
		Label ltelefone = new Label("Telefone: ");
		ltelefone.setBounds(10, 70, 55, 20);
		add(ltelefone);
		telefone = new JTextField(10);
		telefone.setBounds(65, 70, 145, 20);
		this.add(telefone);
		telefone.getText();
	
		Label lendereco = new Label("Endereço: ");
		lendereco.setBounds(10, 100, 60, 20);
		add(lendereco);
		endereco = new JTextField(10);
		endereco.setBounds(70, 100, 140, 20);
		this.add(endereco);
		endereco.getText();
	
		Label lcpf_funcionario = new Label("CPF do Funcionário: ");
		lcpf_funcionario.setBounds(10, 130, 115, 20);
		add(lcpf_funcionario);
		cpf_Funcionario = new JTextField(10);
		cpf_Funcionario.setBounds(125, 130, 85, 20);
		this.add(cpf_Funcionario);
		cpf_Funcionario.getText();
	
		cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(5, 215, 100, 20);
		this.add(cadastrar);
		
		atualizar = new JButton("Atualizar");
		atualizar.setBounds(110, 215, 100, 20);
		this.add(atualizar);
		
		editar = new JButton("Editar");
		editar.setBounds(5,239,100,20);
		add(editar);
		
		excluir = new JButton("Excluir");
		excluir.setBounds(110, 239, 100, 20);
		add(excluir);
		
		voltar = new JButton("Voltar");
		voltar.setBounds(5, 263, 205, 20);
		add(voltar);
		
		TextoHandler textohandler = new TextoHandler();
		CNH.addActionListener(textohandler);
		nome.addActionListener(textohandler);
		telefone.addActionListener(textohandler);
		endereco.addActionListener(textohandler);
		cpf_Funcionario.addActionListener(textohandler);
		
		ButtonHandler buttonhandler = new ButtonHandler();
		cadastrar.addActionListener(buttonhandler);
		atualizar.addActionListener(buttonhandler);
		voltar.addActionListener(buttonhandler);
		editar.addActionListener(buttonhandler);
		excluir.addActionListener(buttonhandler);
	}
	// Metodo que cria a tabela com os dados na GUI
	public void Tabela()
	{
		tabela = new JTable(modelo);
		JScrollPane tabelas = new JScrollPane(tabela);
		tabelas.setBounds(220, 10,560,275);
		add(tabelas);

		modelo.addColumn("CNH");
		modelo.addColumn("NOME");
		modelo.addColumn("TELEFONE");
		modelo.addColumn("ENDEREÇO");
		modelo.addColumn("CPF_FUNCIONARIO");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(150);
		
		modelo.setRowCount(0);
		CaminhoneiroDAO cd = new CaminhoneiroDAO();
		try {
			for(Caminhoneiro c : cd.buscar()){
				modelo.addRow(new Object[]{c.getCnh(),c.getNome(),c.getTelefone(),c.getEndereco(),c.getCpf_Funcionario()});
			}
		} catch (Exception e) {    
			e.printStackTrace();
		}
	}
	// Metodo que cria a GUI
	public CadastrarCaminhoneiro()
	{
		super("Cadastrar Caminhoneiro");
		setLayout(null);
		Componentes();
		Tabela();
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
			// Evento do botão cadastrar
			if(e.getSource() == cadastrar)
			{	
				if(CNH.getText().isEmpty() || nome.getText().isEmpty() || telefone.getText().isEmpty() || endereco.getText().isEmpty()
						|| cpf_Funcionario.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Algum campo está vazio");
				}
				else
				{
					ValidaCPF v = new ValidaCPF(); 
					
					if(v.isCPF(cpf_Funcionario.getText()) == true)
					{	
						JOptionPane.showMessageDialog(null, "CPF Valido!");
						Caminhoneiro c = new Caminhoneiro();
						c.setCnh(Integer.parseInt(CNH.getText()));
						c.setNome(nome.getText());
						c.setTelefone(Integer.parseInt(telefone.getText()));
						c.setEndereco(endereco.getText());
						c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
				
						CaminhoneiroDAO cd = new CaminhoneiroDAO();
						cd.Cadastrar(c);
						modelo.addRow(new Object[]{c.getCnh(),c.getNome(),c.getTelefone(),c.getEndereco(),c.getCpf_Funcionario()});
					}
					else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");
					}
				}
			}
			// Evento do botão editar
			if(e.getSource() == editar)
			{
				int x = tabela.getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
				
				String cnht = modelo.getValueAt(x, 0).toString();
				String nomet = modelo.getValueAt(x, 1).toString();
				String telefonet = modelo.getValueAt(x, 2).toString();
				String enderecot = modelo.getValueAt(x, 3).toString();
				String cpft = modelo.getValueAt(x, 4).toString();
				
				CNH.setText(cnht);
				CNH.disable();
				nome.setText(nomet);
				telefone.setText(telefonet);
				endereco.setText(enderecot);
				cpf_Funcionario.setText(cpft);
				
			}
			// Evento do botão atualizar
			if(e.getSource() == atualizar)
			{
				
				if(CNH.getText().isEmpty() || nome.getText().isEmpty() || telefone.getText().isEmpty() || endereco.getText().isEmpty()
						|| cpf_Funcionario.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Algum campo está vazio");
				}
				else
				{
					
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf_Funcionario.getText()) == true)
					{
						JOptionPane.showMessageDialog(null, "CPF Valido!");	
						
						Caminhoneiro c = new Caminhoneiro();
						c.setCnh(Integer.parseInt(CNH.getText()));
						c.setNome(nome.getText());
						c.setTelefone(Integer.parseInt(telefone.getText()));
						c.setEndereco(endereco.getText());
						c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
				
						CaminhoneiroDAO cd = new CaminhoneiroDAO();
						cd.atualizar(c);
						modelo.addRow(new Object[]{c.getCnh(),c.getNome(),c.getTelefone(),c.getEndereco(),c.getCpf_Funcionario()});
					}
					else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");	
					}
				}
			}
			// Evento do botão excluir
			if(e.getSource() == excluir)
			{
				Caminhoneiro c = new Caminhoneiro();
				c.setCnh(Integer.parseInt(CNH.getText()));
				
				CaminhoneiroDAO cd = new CaminhoneiroDAO();
				cd.remover(c);
			}
			// Evento do botão voltar
			if(e.getSource() == voltar){
				TelaPrincipal telaprincipal = new TelaPrincipal();
	            telaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            telaprincipal.setMinimumSize(new Dimension(1366, 730));
	            telaprincipal.setVisible(true);
	            telaprincipal.setLocationRelativeTo(null);
		         setVisible(false);
			}
		}
	}
}
