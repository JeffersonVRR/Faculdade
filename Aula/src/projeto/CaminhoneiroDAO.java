package projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



public class CaminhoneiroDAO {
	private final String INSERT = "INSERT INTO `banco_projeto`.`caminhoneiro` (`Cnh`, `Nome`, `Telefone`, `Endereco`, `CPF_funcionario`) VALUES ( ? , ? , ? , ? , ? );";
	private final String UPDATE = "UPDATE `banco_projeto`.`caminhoneiro` SET `Nome`= ? , `Telefone`= ? , `Endereco`= ? ,"
			+ " `CPF_funcionario`= ? WHERE `Cnh`= ?;";
	private final String DELETE = "DELETE FROM `banco_projeto`.`caminhoneiro` WHERE `Cnh`= ? ;";
	private final String LIST = "SELECT * FROM banco_projeto.caminhoneiro;";
	// Lista todo os caminhoneiros
	public List<Caminhoneiro> buscar() throws Exception
	{
		Connection conn = Conexao.abrir();
		PreparedStatement stm = conn.prepareStatement(LIST);
		ResultSet rs = stm.executeQuery();
		
		List<Caminhoneiro> lista = new ArrayList<Caminhoneiro>();
		
		while(rs.next())
		{
			Caminhoneiro c = new Caminhoneiro();
			
			c.setCnh(rs.getInt("CNH"));
			c.setNome(rs.getString("NOME"));
			c.setTelefone(rs.getInt("TELEFONE"));
			c.setEndereco(rs.getString("ENDERECO"));
			c.setCpf_Funcionario(rs.getInt("CPF_FUNCIONARIO"));
			
			lista.add(c);
		}
		return lista;
	}
	// Cadastra o caminhoneiro
	public void Cadastrar(Caminhoneiro c)
	{
		try
		{
			Connection conn = Conexao.abrir();
			PreparedStatement stmt = conn.prepareStatement(INSERT);
			stmt.setInt(1, c.getCnh());
			stmt.setString(2, c.getNome());
			stmt.setInt(3, c.getTelefone());
			stmt.setString(4, c.getEndereco());
			stmt.setInt(5, c.getCpf_Funcionario());
			
			int rs = stmt.executeUpdate();
			
			conn.close();
			stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
		}
		
	}
	// Remove o caminhoneiro
	public void remover(Caminhoneiro c)
	{
		try
		{
			Connection conn = Conexao.abrir();
			PreparedStatement stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, c.getCnh());
			int rs = stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Exclusão com sucesso");
			
			conn.close();
			stmt.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao excluir os dados");
		}
	}
	// Atualiza o caminhoneiro
	public void atualizar(Caminhoneiro c)
	{
		try 
		{
			Connection conn = Conexao.abrir();
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, c.getNome());
			stmt.setInt(2, c.getTelefone());
			stmt.setString(3, c.getEndereco());
			stmt.setInt(4, c.getCpf_Funcionario());
			stmt.setInt(5, c.getCnh());
			
			int rs = stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Dados alterado com sucesso");
			
			conn.close();
			stmt.close();
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao atualizar os dados");	
		}
	}
}
