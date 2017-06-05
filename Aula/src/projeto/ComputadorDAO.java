package projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ComputadorDAO {
	private final String INSERT = "INSERT INTO `banco_projeto`.`computador` (`ID_Computador`, `Modelo`, `Marca`, `Configuracao`,"
			+ " `ID_GPS`, `CPF_funcionario`) VALUES ( ? , ? , ? , ? , ? , ?);";
	private final String UPDATE = "UPDATE `banco_projeto`.`computador` SET `Modelo`= ? , `Marca`= ? , `Configuracao`= ? ,"
			+ " `ID_GPS`= ? , `CPF_funcionario`= ?  WHERE `ID_Computador`= ?;";
	private final String DELETE = "DELETE FROM `banco_projeto`.`computador` WHERE `ID_Computador`= ? ;";
	private final String LIST = "SELECT * FROM banco_projeto.computador;";
	// Listar todos os computador
	public List<Computador> buscar() throws Exception
	{
		Connection conn = Conexao.abrir();
		
		PreparedStatement stmt = conn.prepareStatement(LIST);
		
		ResultSet rs = stmt.executeQuery();
		
		List<Computador> lista = new ArrayList<Computador>();
		
		while(rs.next())
		{
			Computador c = new Computador();
			c.setId_Computador(rs.getInt("ID_COMPUTADOR"));
			c.setModelo(rs.getString("MODELO"));
			c.setMarca(rs.getString("MARCA"));
			c.setConfiguracao(rs.getString("CONFIGURACAO"));
			c.setId_GPS(rs.getInt("ID_GPS"));
			c.setCpf_Funcionario(rs.getInt("CPF_FUNCIONARIO"));
			
			lista.add(c);
		}
		
		conn.close();
		stmt.close();
		rs.close();
		return lista;
	}
	// Cadastra o computador
	public void cadastrar(Computador c)
	{
		try
		{
			Connection conexao = Conexao.abrir();

			PreparedStatement canal = conexao.prepareStatement(INSERT);
			canal.setInt(1, c.getId_Computador());
			canal.setString(2, c.getModelo());
			canal.setString(3, c.getMarca());
			canal.setString(4, c.getConfiguracao());
			canal.setInt(5, c.getId_GPS());
			canal.setInt(6, c.getCpf_Funcionario());
			
			int rs = canal.executeUpdate();
			
			
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
               
            canal.close();
			conexao.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
		}
	}
	// Remove o computador
	public void remover (Computador c) 
	{
		try
		{
			Connection conn = Conexao.abrir();
			
			PreparedStatement stmt = conn.prepareStatement(DELETE);
			
			stmt.setInt(1, c.getId_Computador());
			
			int rs = stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Sucessor ao remover");
			conn.close();
			stmt.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao excluir os dados");
		}
	}
	// Atualizar o computador
	public void atualizar (Computador c)
	{
		try
		{
			Connection conn = Conexao.abrir();
			
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, c.getModelo());
			stmt.setString(2, c.getMarca());
			stmt.setString(3, c.getConfiguracao());
			stmt.setInt(4, c.getId_GPS());
			stmt.setInt(5, c.getCpf_Funcionario());
			stmt.setInt(6, c.getId_Computador());
			
			int rs = stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Dados alterado com sucesso");
			
			conn.close();
			stmt.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao atualizar os dados");	
		}
	}
}
