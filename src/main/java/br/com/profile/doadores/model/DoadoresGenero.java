package br.com.profile.doadores.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="doadores_genero")
@SequenceGenerator(sequenceName = "doadores_genero_seq", name = "doadores_genero_seq", initialValue = 1, allocationSize = 1)
public class DoadoresGenero {
	
	@Id
	@GeneratedValue(generator = "doadores_genero_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="sexo")
	private String sexo;
	
	@Column(name="idade")
	private Integer idade;
	
	@Column(name="peso")
	private Double peso;
	
	@Column(name="altura")
	private Double altura;
	
	@Column(name="imc")
	private Double imc;
	
	@JoinColumn(name = "doadores_id")
	@OneToOne()
	private Doadores doadores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public Doadores getDoadores() {
		return doadores;
	}

	public void setDoadores(Doadores doadores) {
		this.doadores = doadores;
	}
}
