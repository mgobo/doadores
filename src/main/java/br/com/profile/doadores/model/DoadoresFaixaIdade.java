package br.com.profile.doadores.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "doadores_faixa_idade")
@SequenceGenerator(name = "faixa_seq", sequenceName = "faixa_seq", initialValue = 1, allocationSize = 1)
public class DoadoresFaixaIdade {

	@Id
	@GeneratedValue(generator = "faixa_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "faixa_description")
	private String faixaDescription;
	
	@Column(name = "codigo_faixa")
	private String codigo;
	
	@Column(name = "somatoria_peso")
	private Double somatoriaPeso;
	
	@Column(name = "somatoria_altura")
	private Double somatoriaAltura;
	
	@OneToMany(mappedBy = "doadoresFaixaIdade", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doadores> doadoresCollection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFaixaDescription() {
		return faixaDescription;
	}

	public void setFaixaDescription(String faixaDescription) {
		this.faixaDescription = faixaDescription;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getSomatoriaPeso() {
		return somatoriaPeso;
	}

	public void setSomatoriaPeso(Double somatoriaPeso) {
		this.somatoriaPeso = somatoriaPeso;
	}

	public Double getSomatoriaAltura() {
		return somatoriaAltura;
	}

	public void setSomatoriaAltura(Double somatoriaAltura) {
		this.somatoriaAltura = somatoriaAltura;
	}

	public Set<Doadores> getDoadoresCollection() {
		return doadoresCollection;
	}

	public void setDoadoresCollection(Set<Doadores> doadoresCollection) {
		this.doadoresCollection = doadoresCollection;
	}
}
