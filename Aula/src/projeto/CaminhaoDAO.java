package projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CaminhaoDAO 
{
	private final String INSERT = "INSERT INTO `banco_projeto`.`caminhão` (`Placa`, `Modelo`, `Ano`, `Marca`, `CPF_Funcionario`,"
			+ " `Id_GPS`) VALUES ( ? , ? , ? , ? , ? , ? );";
	private final String UPDATE = "UPDATE `banco_projeto`.`caminhão` SET `Placa`= ? , `Modelo`= ? , `Ano`= ? , `Marca`= ? ,"
			+ " `CPF_Funcionario`= ? , `Id_GPS`= ? WHERE `Placa`= ? ;";
	private final String DELETE = "DELETE FROM `banco_projeto`.`caminhão` WHERE `Placa`= ? ;";
	private final String LIST = "SELECT * FROM banco_projeto.caminhão;";
	
	// Lista todos os caminhoes
	public List<Caminhao> buscar() throws Exception 
	{
	   Connection conn = (Connection) Conexao.abrir();
   
       PreparedStatement comando = (PreparedStatement) conn.prepareStatement(LIST);

       ResultSet resultado = comando.executeQuery();

       List<Caminhao> lista = new ArrayList<Caminhao>();

       while (resultado.next()) 
       {
       	
           Caminhao c = new Caminhao();
           
           c.setPlaca(resultado.getInt("PLACA"));
           c.setModelo(resultado.getString("MODELO"));
           c.setMarca(resultado.getString("MARCA"));
           c.setAno(resultado.getInt("ANO"));
           c.setCpf_Funcionario(resultado.getInt("CPF_FUNCIONARIO"));
           c.setId_gps(resultado.getInt("ID_GPS"));
           
           lista.add(c);
       }

       resultado.close();
       comando.close();
       conn.close();

       return lista;
   }
	// Cadastra o caminhão
	public void cadastrar(Caminhao c) throws Exception
	{
		try
		{
			Connection conexao = Conexao.abrir();
			PreparedStatement canal = (PreparedStatement) conexao.prepareStatement(INSERT);
			canal.setInt(1, c.getPlaca());
			canal.setString(2, c.getModelo());
			canal.setInt(3, c.getAno());
			canal.setString(4, c.getMarca());
			canal.setInt(5, c.getCpf_Funcionario());
			canal.setInt(6, c.getId_gps());
			
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
	// Remove o caminhão
	public void remover(Caminhao c) 
	{
		try
		{
			Connection conexao = Conexao.abrir();
			PreparedStatement canal = conexao.prepareStatement(DELETE);
			canal.setInt(1, c.getPlaca());
			
			JOptionPane.showMessageDialog(null, "Exclusão com sucesso"); 
			
			int rs = canal.executeUpdate();
			
			conexao.close();
			canal.close();
		} 
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao excluir os dados");
		}
	}
	// Atualiza o caminhão
	public void atualizar(Caminhao c)
	{
			try 
			{
				Connection conexao = Conexao.abrir();
				PreparedStatement canal = conexao.prepareStatement(UPDATE);

				canal.setInt(1, c.getPlaca());
				canal.setString(2, c.getModelo());
				canal.setInt(3, c.getAno());
				canal.setString(4, c.getMarca());
				canal.setInt(5, c.getCpf_Funcionario());
				canal.setInt(6, c.getId_gps());
				canal.setInt(7, c.getPlaca());
				
				int rs = canal.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados alterado com sucesso");
				
				canal.close();
				conexao.close();
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Erro ao atualizar os dados");		
			}
		} 
}