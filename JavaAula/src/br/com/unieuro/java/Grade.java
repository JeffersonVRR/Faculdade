package br.com.unieuro.java;

public class Grade {
	private int id_grade = 0;
	private String id_disciplina =null ;
	private String semestre = null ; 
	private int ano  = 0 ;
	private String professor= null;
	
	public Grade(int id_grade,String id_disciplina, String semestre,int ano, String  professor){
		super();
		this.id_grade = id_grade;
		this.id_disciplina = id_disciplina;
		this.semestre = semestre;
		this.ano = ano;
		this.professor = professor;
	}

	public int getId_grade() {
		return id_grade;
	}

	public void setId_grade(int id_grade) {
		this.id_grade = id_grade;
	}

	public String getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(String id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String toString(){
		String resultado = "Id da Grade: " + id_grade + "Id da disciplina: " + id_disciplina + "Semestre: " + semestre + "Ano: "+ ano + "Professor: " + professor;
		return resultado;
	}
}
