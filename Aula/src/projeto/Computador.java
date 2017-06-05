package projeto;


public class Computador 
{
	private int id_Computador = 0,
				id_GPS = 0,
				cpf_Funcionario = 0;
	
	private String modelo = null,
				   marca = null,
				   configuracao = null;
	
	
	public int getCpf_Funcionario() 
	{
		return cpf_Funcionario;
	}

	public void setCpf_Funcionario(int cpf_Funcionario) 
	{
		this.cpf_Funcionario = cpf_Funcionario;
	}

	public Computador()
	{
		
	}

	public int getId_Computador() 
	{
		return id_Computador;
	}

	public void setId_Computador(int id_Computador) 
	{
		this.id_Computador = id_Computador;
	}

	public String getModelo() 
	{
		return modelo;
	}

	public void setModelo(String modelo) 
	{
		this.modelo = modelo;
	}

	public String getMarca() 
	{
		return marca;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public String getConfiguracao()
	{
		return configuracao;
	}

	public void setConfiguracao(String configuracao) 
	{
		this.configuracao = configuracao;
	}

	public int getId_GPS() 
	{
		return id_GPS;
	}

	public void setId_GPS(int id_GPS) 
	{
		this.id_GPS = id_GPS;
	}
}
