package projeto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.activation.ActivationDataFlavor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class CadastrarComputador extends JFrame 
{
		private JTextField ID_Computador,
						   modelo,
						   marca,
						   configuracao,
						   cpf_Funcionario,
						   id_gps;
		
		private JButton cadastrar,
						voltar,
						editar,
						excluir,
						atualizar;
		
		private JTable tabela;
		DefaultTableModel modelos = new DefaultTableModel();
	// Metodo que coloca os componentes na GUI
	public void Componente()
	{
		Label lid_computador = new Label("Id do computador: ");
		lid_computador.setBounds(10, 10, 100, 20);
		add(lid_computador);
		ID_Computador = new JTextField(10);
		ID_Computador.setBounds(115, 10, 95, 20);
		this.add(ID_Computador);
		ID_Computador.getText();
		
		Label lmodelo = new Label("Modelo: ");
		lmodelo.setBounds(10, 40, 50, 20);
		add(lmodelo);
		modelo =  new JTextField(10);
		modelo.setBounds(60, 40, 150, 20);
		this.add(modelo);
		modelo.getText();
		
		Label lmarca = new Label("Marca: ");
		lmarca.setBounds(10, 70, 40, 20);
		add(lmarca);
		marca = new JTextField(10);
		marca.setBounds(50, 70, 160, 20);
		this.add(marca);
		marca.getText();
		
		Label lconfiguracao = new Label("Configuração: ");
		lconfiguracao.setBounds(10, 100, 80, 20);
		add(lconfiguracao);
		configuracao = new JTextField(10);
		configuracao.setBounds(90, 100, 120, 20);
		this.add(configuracao);
		configuracao.getText();
		
		Label lgps = new Label("ID do GPS: ");
		lgps.setBounds(10, 130, 65, 20);
		add(lgps);
		id_gps = new JTextField(10);
		id_gps.setBounds(75, 130, 135, 20);
		this.add(id_gps);
		id_gps.getText();
		
		Label lcpf_funcionario = new Label("CPF do Funcionário: ");
		lcpf_funcionario.setBounds(10, 160, 115, 20);
		add(lcpf_funcionario);
		cpf_Funcionario = new JTextField(10);
		cpf_Funcionario.setBounds(125, 160, 85, 20);
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
		ID_Computador.addActionListener(textohandler);
		modelo.addActionListener(textohandler);
		marca.addActionListener(textohandler);
		configuracao.addActionListener(textohandler);
		cpf_Funcionario.addActionListener(textohandler);
		id_gps.addActionListener(textohandler);
		
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
		tabela = new JTable(modelos);
		JScrollPane tabelas = new JScrollPane(tabela);
		tabelas.setBounds(220, 10,560,275);
		
		modelos.addColumn("ID DO COMPUTADOR");
		modelos.addColumn("MODELO");
		modelos.addColumn("MARCA");
		modelos.addColumn("CONFIGURAÇÃO");
		modelos.addColumn("ID DO GPS");
		modelos.addColumn("CPF DO FUNCIONARIO");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(180);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(70);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(130);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(200);
		modelos.setNumRows(0);
		
		ComputadorDAO cd = new ComputadorDAO();
		try {
			for(Computador c : cd.buscar()){
				modelos.addRow(new Object[]{c.getId_Computador(),c.getModelo(),c.getMarca(),c.getConfiguracao(),c.getId_GPS(),c.getCpf_Funcionario()});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		add(tabelas);
	}
	// Metodo que cria a GUI
	public CadastrarComputador()
	{
		super("Cadastrar Computador");
		setLayout(null);
		
		Componente();
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
			if(e.getSource() ==cadastrar)
			{
				if(ID_Computador.getText().isEmpty() || modelo.getText().isEmpty() || marca.getText().isEmpty() 
						|| configuracao.getText().isEmpty() || cpf_Funcionario.getText().isEmpty() || id_gps.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Algum campo esta vazio");
				}
				else
				{
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf_Funcionario.getText()) == true)
					{
						Computador c = new Computador();
						c.setId_Computador(Integer.parseInt(ID_Computador.getText()));
						c.setModelo(modelo.getText());
						c.setMarca(marca.getText());
						c.setConfiguracao(configuracao.getText());
						c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
						c.setId_GPS(Integer.parseInt(id_gps.getText()));
						
						ComputadorDAO cd = new ComputadorDAO();
						cd.cadastrar(c);
						modelos.addRow(new Object[]{c.getId_Computador(),c.getModelo(),c.getMarca(),c.getConfiguracao(),
								c.getCpf_Funcionario(),c.getId_GPS()});
					}
					else
					{
						JOptionPane.showMessageDialog(null, "CPF Inválido");
					}
				}
			}
			// Evento do botão atualizar
			if(e.getSource() == atualizar)
			{
				if(ID_Computador.getText().isEmpty() || modelo.getText().isEmpty() || marca.getText().isEmpty() 
						|| configuracao.getText().isEmpty() || cpf_Funcionario.getText().isEmpty() || id_gps.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Algum campo esta vazio");
				}
				else
				{
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf_Funcionario.getText()) == true)
					{
						Computador c = new Computador();
						c.setId_Computador(Integer.parseInt(ID_Computador.getText()));
						c.setModelo(modelo.getText());
						c.setMarca(marca.getText());
						c.setConfiguracao(configuracao.getText());
						c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
						c.setId_GPS(Integer.parseInt(id_gps.getText()));
						
						ComputadorDAO cd = new ComputadorDAO();
						cd.atualizar(c);
						modelos.addRow(new Object[]{c.getId_Computador(),c.getModelo(),c.getMarca(),c.getConfiguracao(),c.getCpf_Funcionario(),c.getId_GPS()});
					}
					else
					{
						JOptionPane.showMessageDialog(null, "CPF Inválido");
					}
				}
			}
			// Evento do botão excluir
			if(e.getSource() == excluir)
			{
				Computador c = new Computador();
				c.setId_Computador(Integer.parseInt(ID_Computador.getText()));
				
				ComputadorDAO cd = new ComputadorDAO();
				cd.remover(c);
			}
			// Evento do botão editar
			if(e.getSource() == editar)
			{
				
				int x = tabela.getSelectedRow();
				
				DefaultTableModel modelos = (DefaultTableModel) tabela.getModel();
				
				String id_computadort = modelos.getValueAt(x, 0).toString();
				String modelot = modelos.getValueAt(x, 1).toString();
				String marcat = modelos.getValueAt(x, 2).toString();
				String configuracaot = modelos.getValueAt(x, 3).toString();
				String cpf_funcionariot = modelos.getValueAt(x, 4).toString();
				String id_gpst = modelos.getValueAt(x, 5).toString();
				
				ID_Computador.setText(id_computadort);
				ID_Computador.disable();
				modelo.setText(modelot);
				marca.setText(marcat);
				configuracao.setText(configuracaot);
				cpf_Funcionario.setText(cpf_funcionariot);
				id_gps.setText(id_gpst);
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
