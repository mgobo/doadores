package br.com.profile.doadores.model.json;

public class DoadorJson {

	private String nome;
	private String cpf;
	private String tipoSanguineo;
	
	public DoadorJson() {
		super();
	}

	public DoadorJson(String nome, String cpf, String tipoSanguineo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.tipoSanguineo = tipoSanguineo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
}
