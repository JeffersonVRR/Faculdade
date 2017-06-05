package projeto;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CadastrarFuncionario extends JFrame 
{
	private JTextField cpf,
					   nome,
					   telefone,
					   endereco,
					   loginU,
					   senhaU,
					   gerente;
	
	private JButton cadastrar,
					voltar,
					editar,
					excluir,
					atualizar;
	
	private JTable tabela; 
	DefaultTableModel modelo = new DefaultTableModel();
	// Metodo que coloca os componentes na GUI
	public void Componentes ()
	{
		Label lcpf = new Label("CPF: ");
		lcpf.setBounds(10, 10, 30, 20);
		add(lcpf);
		cpf = new JTextField();
		cpf.setBounds(40, 10, 170, 20);
		this.add(cpf);
		cpf.getText();
		
		Label lnome = new Label("Nome: ");
		lnome.setBounds(10, 40, 40, 20);
		add(lnome);
		nome = new JTextField();
		nome.setBounds(50, 40, 160, 20);
		this.add(nome);
		nome.getText();
		
		Label ltelefone = new Label("Telefone: ");
		ltelefone.setBounds(10, 70, 55, 20);
		add(ltelefone);
		telefone = new JTextField();
		telefone.setBounds(65, 70, 145, 20);
		this.add(telefone);
		telefone.getText();
		
		Label lendereco = new Label("Endereço: ");
		lendereco.setBounds(10, 100, 60, 20);
		add(lendereco);
		endereco = new JTextField();
		endereco.setBounds(70, 100, 140, 20);
		this.add(endereco);
		endereco.getText();
		
		Label llogin = new Label("Login: ");
		llogin.setBounds(10, 130, 40, 20);
		add(llogin);
		loginU = new JTextField();
		loginU.setBounds(50, 130, 160, 20);
		this.add(loginU);
		loginU.getText();
		
		Label lsenha = new Label("Senha: ");
		lsenha.setBounds(10, 160, 45, 20);
		add(lsenha);
		senhaU = new JTextField();
		senhaU.setBounds(55, 160, 155, 20);
		this.add(senhaU);
		senhaU.getText();
		
		Label lgerente = new Label("Gerente: ");
		lgerente.setBounds(10, 190, 50, 20);
		add(lgerente);
		gerente = new JTextField();
		gerente.setBounds(63, 190, 147, 20);
		this.add(gerente);
		gerente.getText();	
		
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
		
		TextoHandler textohandler= new TextoHandler();
		cpf.addActionListener(textohandler);
		nome.addActionListener(textohandler);
		telefone.addActionListener(textohandler);
		endereco.addActionListener(textohandler);
		loginU.addActionListener(textohandler);
		senhaU.addActionListener(textohandler);
		gerente.addActionListener(textohandler);
		
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
		
		modelo.addColumn("CPF");
		modelo.addColumn("Nome");
		modelo.addColumn("Telefone");
		modelo.addColumn("ENDEREÇO");
		modelo.addColumn("LOGIN");
		modelo.addColumn("SENHA");
		modelo.addColumn("GERENTE");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(80);
		
		modelo.setNumRows(0);
		FuncionarioDAO dao = new FuncionarioDAO();
		try 
		{
			for (Funcionario f : dao.buscar()) 
			{
				modelo.addRow(new Object[]{f.getCpf(), f.getNome(), f.getTelefone(),f.getEndereco(),f.getLogin(),f.getSenha(),f.getGerente()});
			}	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
	// Metodo que cria a GUI	
	public CadastrarFuncionario()
	{
		super("Cadastrar Funcionario");
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
				if(cpf.getText().isEmpty() || nome.getText().isEmpty() || telefone.getText().isEmpty() || endereco.getText().isEmpty()
				|| loginU.getText().isEmpty() || senhaU.getText().isEmpty() || gerente.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Algum campo está vazio");
				}
				else
				{
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf.getText()) == true)
					{
						Funcionario f = new Funcionario();
						f.setCpf(Long.parseLong((cpf.getText())));
						f.setNome(nome.getText());
						f.setTelefone(Integer.parseInt(telefone.getText()));
						f.setEndereco(endereco.getText());
						f.setLogin(loginU.getText());
						f.setSenha(senhaU.getText());
						f.setGerente(gerente.getText());
						FuncionarioDAO d = new FuncionarioDAO();
						try {
							d.cadastrar(f);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
						}
						modelo.addRow(new Object[]{f.getCpf(), f.getNome(), f.getTelefone(),f.getEndereco(),f.getLogin(),
								f.getSenha(),f.getGerente()});
					}
					else
					{
					JOptionPane.showMessageDialog(null, "CPF Invalido!");	
					}
				}
			}
			// Evento do botão editar
			if(e.getSource() == editar)
			{
				int x = tabela.getSelectedRow();	
				DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
						
				String cpft = modelo.getValueAt(x, 0).toString();
				String nomet = modelo.getValueAt(x, 1).toString();
				String telefonet = modelo.getValueAt(x, 2).toString();
				String enderecot = modelo.getValueAt(x, 3).toString();
				String loginut = modelo.getValueAt(x, 4).toString();
				String senhaut = modelo.getValueAt(x, 5).toString();
				String gerentet = modelo.getValueAt(x, 6).toString();
						
				cpf.setText(cpft);
				cpf.disable();
				nome.setText(nomet);
				telefone.setText(telefonet);
				endereco.setText(enderecot);
				loginU.setText(loginut);
				senhaU.setText(senhaut);
				gerente.setText(gerentet);
			}
			// Evento do botão atualizar
			if(e.getSource() == atualizar)
			{
				if(cpf.getText().isEmpty() || nome.getText().isEmpty() || telefone.getText().isEmpty() || endereco.getText().isEmpty()
						|| loginU.getText().isEmpty() || senhaU.getText().isEmpty() || gerente.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Algum campo está vazio");
				}
				else{
					Funcionario f = new Funcionario();
					f.setCpf(Long.parseLong(cpf.getText()));
					f.setNome(nome.getText());
					f.setTelefone(Integer.parseInt(telefone.getText()));
					f.setEndereco(endereco.getText());
					f.setLogin(loginU.getText());
					f.setSenha(senhaU.getText());
					f.setGerente(gerente.getText());
					FuncionarioDAO d = new FuncionarioDAO();
					try{
						d.atualizar(f);
					}catch (Exception e1)
					{
						e1.printStackTrace();
					}
						modelo.addRow(new Object[]{f.getCpf(), f.getNome(), f.getTelefone(),f.getEndereco(),f.getLogin(),f.getSenha(),
								f.getGerente()});
					}
			}
			// Evento do botão excluir
			if(e.getSource() == excluir)
			{
				Funcionario f = new Funcionario();
				f.setCpf(Long.parseLong(cpf.getText()));
				
				FuncionarioDAO d = new FuncionarioDAO();
				try
				{
					d.remover(f);	
				} catch (Exception e1)
				{
					
				}
			}
			// Evento do botão voltar
			if(e.getSource() == voltar)
			{
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

