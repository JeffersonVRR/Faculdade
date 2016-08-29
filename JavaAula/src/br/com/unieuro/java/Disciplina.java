package br.com.unieuro.java;

public class Disciplina {
	private String nome = null;
	private String id = null;
	private int credito = 0;
	private String departamento = null; 
	
	public Disciplina(String nome, String id, int credito, String departamento){
		super();
		this.nome = nome;
		this.id = id;
		this.credito = credito;
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public String toString(){
		String resultado = "Nome do departamento: " + nome + "ID da disciplina: " + id + "Crédito: " + credito + "Departamento: " + departamento;  
		return resultado;
	}
	
}
