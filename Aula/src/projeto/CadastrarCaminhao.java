package projeto;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CadastrarCaminhao extends JFrame 
{
		private JTextField placa, 
						   modelo, 
						   ano, 
						   marca,
						   cpf_Funcionario, 
						   gps;
		
		private JButton cadastrar,
						voltar,
						editar,
						atualizar,
						excluir;
		
		private JTable tabela;
	
		DefaultTableModel modelotb = new DefaultTableModel();
		
	// Metodo que coloca os componentes na GUI
	public void Componentes()
	{
		Label lplaca = new Label("Placa:");
		lplaca.setBounds(10, 10, 35, 20);
		add(lplaca);
		placa = new JTextField(10);
		placa.setBounds(50, 10, 160, 20);
		this.add(placa);
		placa.getText();
		
		Label lmodelo = new Label("Modelo:");
		lmodelo.setBounds(10, 40, 50, 20);
		add(lmodelo);
		modelo =  new JTextField(10);
		modelo.setBounds(60, 40, 150, 20);
		this.add(modelo);
		modelo.getText();
		
		Label lano = new Label("Ano:");
		lano.setBounds(10, 70, 30, 20);
		add(lano);
		ano = new JTextField(10);
		ano.setBounds(40, 70, 170, 20);
		this.add(ano);
		ano.getText();
		
		Label lmarca = new Label("Marca:");
		lmarca.setBounds(10, 100, 40, 20);
		add(lmarca);
		marca = new JTextField(10);
		marca.setBounds(50, 100, 160, 20);
		this.add(marca);
		marca.getText();
		
		Label lcpf_funcionario = new Label("CPF do Funcionário:");
		lcpf_funcionario.setBounds(10, 130, 115, 20);
		add(lcpf_funcionario);
		cpf_Funcionario = new JTextField(10);
		cpf_Funcionario.setBounds(125, 130, 85, 20);
		this.add(cpf_Funcionario);
		cpf_Funcionario.getText();
		
		Label lgps = new Label("GPS:");
		lgps.setBounds(10, 160, 30, 20);
		add(lgps);
		gps = new JTextField(10);
		gps.setBounds(40, 160, 170, 20);
		this.add(gps);
		gps.getText();
		
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
		placa.addActionListener(textohandler);
		modelo.addActionListener(textohandler);
		ano.addActionListener(textohandler);
		marca.addActionListener(textohandler);
		cpf_Funcionario.addActionListener(textohandler);
		gps.addActionListener(textohandler);
		
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
		
		tabela = new JTable(modelotb);
		JScrollPane tabelas = new JScrollPane(tabela);
		tabelas.setBounds(220, 10,560,275);
		add(tabelas);
		
		modelotb.addColumn("PLACA");
		modelotb.addColumn("MODELO");
		modelotb.addColumn("ANO");
		modelotb.addColumn("MARCA");
		modelotb.addColumn("CPF DO FUNCIONÁRIO");
		modelotb.addColumn("ID DO GPS");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(140);
		tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		modelotb.setNumRows(0);
		CaminhaoDAO cd = new CaminhaoDAO();
		try 
		{
			for(Caminhao c :cd.buscar()){
				modelotb.addRow(new Object[]{c.getPlaca(),c.getModelo(),c.getAno(),c.getMarca(),c.getCpf_Funcionario(),c.getId_gps()});
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	// Metodo que cria a GUI
	public CadastrarCaminhao()
	{
		super("Cadastrar Caminhão");
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
				
				if(placa.getText().isEmpty() || modelo.getText().isEmpty() || ano.getText().isEmpty()|| marca.getText().isEmpty() 
						|| cpf_Funcionario.getText().isEmpty() || gps.getText().isEmpty())
				{	
					JOptionPane.showMessageDialog(null,"Algum campo está vazio");
				}else 
				{
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf_Funcionario.getText()) == true)
					{
						
						Caminhao c = new Caminhao();
						c.setPlaca(Integer.parseInt(placa.getText()));
						c.setModelo(modelo.getText());
						c.setAno(Integer.parseInt(ano.getText()));
						c.setMarca(marca.getText());
						c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
						c.setId_gps(Integer.parseInt(gps.getText()));
						
						CaminhaoDAO cd = new CaminhaoDAO();
						try 
						{
							cd.cadastrar(c);	
						}catch (Exception e1) 
						{
							e1.printStackTrace();
						}
							modelotb.addRow(new Object[]{c.getPlaca(),c.getModelo(),c.getAno(),c.getMarca(),c.getCpf_Funcionario(),
									c.getId_gps()});
					}
					else{
						JOptionPane.showMessageDialog(null, "CPF Invalido!");
					}
				}
			}
			// Evento do botão editar
			if(e.getSource() == editar){
				
				int x = tabela.getSelectedRow();
				DefaultTableModel modelot = (DefaultTableModel) tabela.getModel();
				String placat = modelot.getValueAt(x, 0).toString();
				String modelott = modelot.getValueAt(x, 1).toString();
				String anot = modelot.getValueAt(x, 2).toString();
				String marcat = modelot.getValueAt(x, 3).toString();
				String cpfft = modelot.getValueAt(x, 4).toString();
				String idgpst = modelot.getValueAt(x, 5).toString();
				
				placa.setText(placat);
				placa.disable();
				modelo.setText(modelott);
				ano.setText(anot);
				marca.setText(marcat);
				cpf_Funcionario.setText(cpfft);
				gps.setText(idgpst);
			}
			// Evento para atualizar os dados da tabela
			if(e.getSource() == atualizar){
				
				if(placa.getText().isEmpty() || modelo.getText().isEmpty() || ano.getText().isEmpty()|| marca.getText().isEmpty() ||
						cpf_Funcionario.getText().isEmpty() || gps.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Algum campo está vazio");
				}
				else{
					
					ValidaCPF v = new ValidaCPF();
					
					if(v.isCPF(cpf_Funcionario.getText()) == true){
						
					Caminhao c = new Caminhao();
					c.setPlaca(Integer.parseInt(placa.getText()));
					c.setModelo(modelo.getText());
					c.setAno(Integer.parseInt(ano.getText()));
					c.setMarca(marca.getText());
					c.setCpf_Funcionario(Integer.parseInt(cpf_Funcionario.getText()));
					c.setId_gps(Integer.parseInt(gps.getText()));
					
					CaminhaoDAO cd = new CaminhaoDAO();
					cd.atualizar(c);
					
					modelotb.addRow(new Object[]{c.getPlaca(),c.getModelo(),c.getAno(),c.getMarca(),c.getCpf_Funcionario(),c.getId_gps()});
					}
				}
			}
			// Evento do botão excluir
			if(e.getSource() == excluir){
				
				Caminhao c = new Caminhao();
				c.setPlaca(Integer.parseInt(placa.getText()));
				
				CaminhaoDAO cd = new CaminhaoDAO();
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