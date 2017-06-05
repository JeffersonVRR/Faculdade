package projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class GPSDAO {
	private final String INSERT = "INSERT INTO `banco_projeto`.`gps` (`ID_GPS`, `Modelo`, `Nome`) VALUES ( ? , ? , ? );";
	private final String UPDATE = "UPDATE `banco_projeto`.`gps` SET `Modelo`= ? , `Nome`= ? WHERE `ID_GPS`= ? ;";
	private final String DELETE = "DELETE FROM `banco_projeto`.`gps` WHERE `ID_GPS`= ? ;";
	private final String LIST = "SELECT * FROM banco_projeto.gps;";
	// Lista todos os GPS
	public List<GPS> buscar() throws Exception
	{
		Connection conn = Conexao.abrir();
		
		PreparedStatement stmt = conn.prepareStatement(LIST);
		
		ResultSet rs = stmt.executeQuery();

		List<GPS> lista = new ArrayList<GPS>();
		
		while(rs.next())
		{
			GPS g = new GPS();
			g.setId_GPS(rs.getInt("ID_GPS"));
			g.setModelo(rs.getString("MODELO"));
			g.setNome(rs.getString("NOME"));
			
			lista.add(g);
		}
		conn.close();
		stmt.close();
		rs.close();
		return lista;
	}
	// Cadastra o GPS
	public void cadastrar(GPS g) throws Exception
	{
		try
		{
			Connection conexao = Conexao.abrir();
			PreparedStatement canal = (PreparedStatement) conexao.prepareStatement(INSERT);
			canal.setInt(1, g.getId_GPS());
			canal.setString(2, g.getModelo());
			canal.setString(3, g.getNome());
			
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
	// Remove o GPS
	public void remover(GPS g)
	{
		try
		{
		Connection conn = Conexao.abrir();
		
		PreparedStatement stmt = conn.prepareStatement(DELETE);
		stmt.setInt(1, g.getId_GPS());
		
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
	// Atualiza o GPS
	public void atualizar(GPS g)
	{
		try
		{
			Connection conn = Conexao.abrir();
			
			PreparedStatement stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, g.getModelo());
			stmt.setString(2, g.getNome());
			stmt.setInt(3, g.getId_GPS());
			
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
