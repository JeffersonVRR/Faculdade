package projeto;


public class Caminhoneiro 
{
	private int cnh = 0,
				telefone = 0,
				cpf_Funcionario = 0;
	
	private String nome = null,
				   endereco = null;
	
	public Caminhoneiro()
	{
		
	}

	public int getCnh() 
	{
		return cnh;
	}

	public void setCnh(int cnh)
	{
		this.cnh = cnh;
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

	public int getCpf_Funcionario() 
	{
		return cpf_Funcionario;
	}

	public void setCpf_Funcionario(int cpf_Funcionario)
	{
		this.cpf_Funcionario = cpf_Funcionario;
	}
}
