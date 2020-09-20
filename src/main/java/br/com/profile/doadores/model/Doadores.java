package br.com.profile.doadores.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="doadores")
@SequenceGenerator(name = "doadores_seq", sequenceName = "doadores_seq", initialValue = 1, allocationSize = 1)
public class Doadores {

	@Id
	@GeneratedValue(generator = "doadores_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String cpf;
	private String nome;
	private String rg;
	private String data_nasc;
	private String mae;
	private String pai;
	
	@Column(name="email",unique = true)
	private String email;
	private String cep;
	private String endereco;
	private Long numero;
	private String bairro;
	private String cidade;
	
	
	@JoinColumn(name = "estado_id")
	@ManyToOne
	private Estado estado;
	
	@JoinColumn(name = "faixa_idade")
	@ManyToOne
	private DoadoresFaixaIdade doadoresFaixaIdade;
	
	@JoinColumn(name = "tp_sangue_id")
	@ManyToOne
	private DoadoresTipoSanguineo doadoresTipoSanguineo;
	
	@OneToOne(mappedBy = "doadores", cascade = CascadeType.ALL)
	private DoadoresGenero doadoresGenero;
	
	private String telefone_fixo;
	private String celular;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getMae() {
		return mae;
	}
	public void setMae(String mae) {
		this.mae = mae;
	}
	public String getPai() {
		return pai;
	}
	public void setPai(String pai) {
		this.pai = pai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public DoadoresFaixaIdade getDoadoresFaixaIdade() {
		return doadoresFaixaIdade;
	}
	public void setDoadoresFaixaIdade(DoadoresFaixaIdade doadoresFaixaIdade) {
		this.doadoresFaixaIdade = doadoresFaixaIdade;
	}
	public DoadoresTipoSanguineo getDoadoresTipoSanguineo() {
		return doadoresTipoSanguineo;
	}
	public void setDoadoresTipoSanguineo(DoadoresTipoSanguineo doadoresTipoSanguineo) {
		this.doadoresTipoSanguineo = doadoresTipoSanguineo;
	}
	public DoadoresGenero getDoadoresGenero() {
		return doadoresGenero;
	}
	public void setDoadoresGenero(DoadoresGenero doadoresGenero) {
		this.doadoresGenero = doadoresGenero;
	}
	public String getTelefone_fixo() {
		return telefone_fixo;
	}
	public void setTelefone_fixo(String telefone_fixo) {
		this.telefone_fixo = telefone_fixo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}
