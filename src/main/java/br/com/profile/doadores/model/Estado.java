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

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
public class Estado {

	@Id
	@GeneratedValue(generator = "estado_seq", strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	
	@OneToMany(mappedBy = "estado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Doadores> pacienteCollection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Doadores> getProfileCollection() {
		return pacienteCollection;
	}

	public void setProfileCollection(Set<Doadores> pacienteCollection) {
		this.pacienteCollection = pacienteCollection;
	}
}
