package projeto;

public class GPS {
	private int id_GPS = 0;
	
	private String modelo = null,
				   nome = null;
	
	public GPS()
	{	
		
	}

	public int getId_GPS() 
	{
		return id_GPS;
	}

	public void setId_GPS(int id_GPS) 
	{
		this.id_GPS = id_GPS;
	}

	public String getModelo() 
	{
		return modelo;
	}

	public void setModelo(String modelo) 
	{
		this.modelo = modelo;
	}

	public String getNome() 
	{
		return nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}
}
