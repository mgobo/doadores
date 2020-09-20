package br.com.profile.doadores.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = "doadores_tipo_sanguineo")
@Entity
@SequenceGenerator(name = "tp_sangue_seq", sequenceName = "tp_sangue_seq", initialValue = 1, allocationSize = 1)
public class DoadoresTipoSanguineo {

	@Id
	@GeneratedValue(generator = "tp_sangue_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String tipo_sanguineo;
	private Long somaIdades;
	private Long somaDoadores;
	
	@OneToMany(mappedBy = "doadoresTipoSanguineo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doadores> doadoresCollection;
	
	@OneToMany(mappedBy = "doadorTipoSanguineoDe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DoadoresDePara> doadoresReceptorCollection;
	
	@OneToMany(mappedBy = "doadorTipoSanguineoPara", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<DoadoresDePara> doadoresDoadorCollection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_sanguineo() {
		return tipo_sanguineo;
	}

	public void setTipo_sanguineo(String tipo_sanguineo) {
		this.tipo_sanguineo = tipo_sanguineo;
	}

	public Long getSomaIdades() {
		return somaIdades;
	}

	public void setSomaIdades(Long somaIdades) {
		this.somaIdades = somaIdades;
	}

	public Long getSomaDoadores() {
		return somaDoadores;
	}

	public void setSomaDoadores(Long somaDoadores) {
		this.somaDoadores = somaDoadores;
	}

	public Set<Doadores> getDoadoresCollection() {
		return doadoresCollection;
	}

	public void setDoadoresCollection(Set<Doadores> doadoresCollection) {
		this.doadoresCollection = doadoresCollection;
	}

	public Set<DoadoresDePara> getDoadoresReceptorCollection() {
		return doadoresReceptorCollection;
	}

	public void setDoadoresReceptorCollection(Set<DoadoresDePara> doadoresReceptorCollection) {
		this.doadoresReceptorCollection = doadoresReceptorCollection;
	}

	public Set<DoadoresDePara> getDoadoresDoadorCollection() {
		return doadoresDoadorCollection;
	}

	public void setDoadoresDoadorCollection(Set<DoadoresDePara> doadoresDoadorCollection) {
		this.doadoresDoadorCollection = doadoresDoadorCollection;
	}
}
