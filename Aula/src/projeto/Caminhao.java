package projeto;

public class Caminhao 
{
	private int placa = 0,
				ano,
				cpf_Funcionario = 0,
				id_gps = 0;
	
	private String modelo = null,
				   marca =null;
	
	public Caminhao()
	{	
		
	}
	public int getPlaca() 
	{
		return placa;
	}
	public void setPlaca(int placa) 
	{
		this.placa = placa;
	}
	public String getModelo() 
	{
		return modelo;
	}
	public void setModelo(String modelo) 
	{
		this.modelo = modelo;
	}
	public int getAno() 
	{
		return ano;
	}
	public void setAno(int ano) 
	{
		this.ano = ano;
	}
	public String getMarca() 
	{
		return marca;
	}
	public void setMarca(String marca)
	{
		this.marca = marca;
	}
	public int getCpf_Funcionario() 
	{
		return cpf_Funcionario;
	}
	public void setCpf_Funcionario(int cpf_Funcionario) 
	{
		this.cpf_Funcionario = cpf_Funcionario;
	}
	public int getId_gps() 
	{
		return id_gps;
	}
	public void setId_gps(int id_gps) 
	{
		this.id_gps = id_gps;
	}

}
