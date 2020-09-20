package br.com.profile.doadores.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "doadores_de_para")
@SequenceGenerator(name = "doadores_de_para_seq", sequenceName = "doadores_de_para_seq", initialValue = 1, allocationSize = 1)
public class DoadoresDePara {

	@Id
	@GeneratedValue(generator = "doadores_de_para_seq", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@JoinColumn(name = "doador_para_id")
	@ManyToOne
	private DoadoresTipoSanguineo doadorTipoSanguineoDe;
	
	@JoinColumn(name = "receptor_id")
	@ManyToOne
	private DoadoresTipoSanguineo doadorTipoSanguineoPara;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DoadoresTipoSanguineo getDoadorTipoSanguineoDe() {
		return doadorTipoSanguineoDe;
	}

	public void setDoadorTipoSanguineoDe(DoadoresTipoSanguineo doadorTipoSanguineoDe) {
		this.doadorTipoSanguineoDe = doadorTipoSanguineoDe;
	}

	public DoadoresTipoSanguineo getDoadorTipoSanguineoPara() {
		return doadorTipoSanguineoPara;
	}

	public void setDoadorTipoSanguineoPara(DoadoresTipoSanguineo doadorTipoSanguineoPara) {
		this.doadorTipoSanguineoPara = doadorTipoSanguineoPara;
	}
}
