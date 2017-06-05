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

public class CadastrarGPS extends JFrame
{
	private JTextField id_GPS,
					   modelo,
					   nome;
	
	private JButton cadastrar,
					voltar,
					editar,
					excluir,
					atualizar;
	
	private JTable tabela;
	DefaultTableModel modelos = new DefaultTableModel();
	// Metodo que coloca os componentes na GUI
	public void Componentes()
	{
		Label lid_gps = new Label("ID do GPS: ");
		lid_gps.setBounds(10, 10, 70, 20);
		add(lid_gps);
		id_GPS = new JTextField(10);
		id_GPS.setBounds(80, 10, 130, 20);
		add(id_GPS);
		id_GPS.getText();
		
		Label lmodelo = new Label("Modelo: ");
		lmodelo.setBounds(10, 40, 50, 20);
		add(lmodelo);
		modelo = new JTextField(10);
		modelo.setBounds(60, 40, 150, 20);
		add(modelo);
		modelo.getText();
		
		Label lnome = new Label("Nome: ");
		lnome.setBounds(10, 70, 40, 20);
		add(lnome);
		nome = new JTextField(10);
		nome.setBounds(55, 70, 155, 20);
		add(nome);
		nome.getText();
		
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
		
		TextoHandler textoHandler = new TextoHandler();
		id_GPS.addActionListener(textoHandler);
		modelo.addActionListener(textoHandler);
		nome.addActionListener(textoHandler);
		
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
		add(tabelas);
		
		modelos.addColumn("ID DO GPS");
		modelos.addColumn("MODELO");
		modelos.addColumn("NOME");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		
		modelos.setNumRows(0);
		GPSDAO gd = new GPSDAO();
		try
		{
			for(GPS g : gd.buscar())
			{
				modelos.addRow(new Object[]{g.getId_GPS(),g.getModelo(),g.getNome()});
			}
		}
		catch(Exception e)
		{
			
		}
	}
	// Metodo que cria a GUI
	public CadastrarGPS()
	{
		super("Cadastrar GPS");
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
				if(id_GPS.getText().isEmpty() || modelo.getText().isEmpty() || nome.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Algum campo esta vazio");
				}
				else
				{
					GPS g = new GPS();
					g.setId_GPS(Integer.parseInt(id_GPS.getText()));
					g.setModelo(modelo.getText());
					g.setNome(nome.getText());
				
					GPSDAO gd = new GPSDAO();
					try
					{
						gd.cadastrar(g);
					}catch (Exception e1) 
					{
						e1.printStackTrace();
					}
					modelos.addRow(new Object[]{g.getId_GPS(),g.getModelo(),g.getNome()});
				}
			}
			// Evento do botão editar
			if(e.getSource() == editar)
			{
				int x =  tabela.getSelectedRow();
				DefaultTableModel modelos = (DefaultTableModel) tabela.getModel();
				
				String id_gpst = modelos.getValueAt(x, 0).toString();
				String modelot =  modelos.getValueAt(x, 1).toString();
				String nomet = modelos.getValueAt(x, 2).toString();
				
				id_GPS.setText(id_gpst);
				id_GPS.disable();
				modelo.setText(modelot);
				nome.setText(nomet);
			}
			// Evento do botão atualizar
			if(e.getSource() == atualizar)
			{
				if(id_GPS.getText().isEmpty() || modelo.getText().isEmpty() || nome.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Algum campo está vazio");
				}
				else
				{
					GPS g = new GPS();
					g.setId_GPS(Integer.parseInt(id_GPS.getText()));
					g.setModelo(modelo.getText());
					g.setNome(nome.getText());
				
					GPSDAO gd = new GPSDAO();
					gd.atualizar(g);
					modelos.addRow(new Object[]{g.getId_GPS(),g.getModelo(),g.getNome()});
				}
			}
			// Evento do botão excluir
			if(e.getSource() == excluir)
			{
				GPS g = new GPS();
				g.setId_GPS(Integer.parseInt(id_GPS.getText()));
				
				GPSDAO gd = new GPSDAO();
				gd.remover(g);
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