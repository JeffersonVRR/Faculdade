package projeto;

public class Funcionario 
{
	private long cpf = 0;
			
	private int telefone = 0;
	
	private String nome = null,
				   endereco = null,
				   login = null,
				   senha= null,
				   gerente = null;

	public Funcionario()
	{
		
	}
	
	public String getGerente() 
	{
		return gerente;
	}

	public void setGerente(String gerente) 
	{
		this.gerente = gerente;
	}

	public long getCpf()
	{
		return cpf;
	}

	public void setCpf(long cpf)
	{
		this.cpf = cpf;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public int getTelefone()
	{
		return telefone;
	}

	public void setTelefone(int telefone)
{
		this.telefone = telefone;
	}

	public String getEndereco() 
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}
	
	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

}
