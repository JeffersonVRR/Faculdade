package projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FuncionarioDAO {
	private final String INSERT = "INSERT INTO `banco_projeto`.`funcionario` (`CPF`, `Nome`, `Telefone`, `Endereco`, `Login`, `Senha`,"
			+ " `Gerente`) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private final String UPDATE = "UPDATE `banco_projeto`.`funcionario` SET `CPF`= ?, `Nome`= ?, `Telefone`= ?, `Endereco`= ?,"
			+ " `Login`= ?, `Senha`= ?, `Gerente`= ? WHERE `CPF`= ?;";
	private final String DELETE = "DELETE FROM `banco_projeto`.`funcionario` WHERE `CPF`= ?;";
	private final String LIST = "SELECT * FROM banco_projeto.funcionario";
	// Lista todos os funcionarios
	public List<Funcionario> buscar() throws Exception
	{
	   Connection conn = Conexao.abrir();
   
       PreparedStatement comando = (PreparedStatement) conn.prepareStatement(LIST);

       ResultSet resultado = comando.executeQuery();

       List<Funcionario> lista = new ArrayList<Funcionario>();

       while (resultado.next())
       {
       	
           Funcionario linha = new Funcionario();
       	
           linha.setCpf(resultado.getLong("CPF"));
           linha.setNome(resultado.getString("NOME"));
           linha.setTelefone(resultado.getInt("TELEFONE"));
           linha.setEndereco(resultado.getString("ENDERECO"));
           linha.setLogin(resultado.getString("LOGIN"));
           linha.setSenha(resultado.getString("SENHA"));
           linha.setGerente(resultado.getString("GERENTE"));
           
           lista.add(linha);
       }

       resultado.close();
       comando.close();
       conn.close();

       return lista;
   }
	// Cadastra o funcionario
	public void cadastrar(Funcionario f) throws Exception
	{
		PreparedStatement canal = null;
		int rs;
		
		try
		{
			Connection conexao = Conexao.abrir();
			canal = (PreparedStatement) conexao.prepareStatement(INSERT);
			canal.setLong(1, f.getCpf());
			canal.setString(2, f.getNome());
			canal.setInt(3, f.getTelefone());
			canal.setString(4, f.getEndereco());
			canal.setString(5, f.getLogin());
			canal.setString(6, f.getSenha());
			canal.setString(7, f.getGerente());
			
			rs = canal.executeUpdate();
			
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
    
            canal.close();
			conexao.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
		}
	}
	// Remove o funcionario
	public void remover(Funcionario f)
	{
		try
		{
			
			Connection conexao = Conexao.abrir();
			PreparedStatement canal = (PreparedStatement) conexao.prepareStatement(DELETE);
			canal.setLong(1, f.getCpf());
		
			int rs = canal.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Exclusão com sucesso");
			 
			conexao.close();
			canal.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Erro ao excluir os dados");
		}
	}
	// Atualiza o funcionario
	public void atualizar(Funcionario f)
	{
			try 
			{
				Connection conexao = Conexao.abrir();
				PreparedStatement pstm = conexao.prepareStatement(UPDATE);

				pstm.setLong(1, f.getCpf());
				pstm.setString(2, f.getNome());
				pstm.setInt(3, f.getTelefone());
				pstm.setString(4, f.getEndereco());
				pstm.setString(5, f.getLogin());
				pstm.setString(6, f.getSenha());
				pstm.setString(7, f.getGerente());
				pstm.setLong(8, f.getCpf());
				
				int rs = pstm.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Dados alterado com sucesso");
				
				pstm.close();
				conexao.close();
			}
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Erro ao atualizar os dados");	
			}
	}
}