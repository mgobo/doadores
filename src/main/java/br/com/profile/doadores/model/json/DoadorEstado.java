package br.com.profile.doadores.model.json;

import java.util.List;

public class DoadorEstado {

	private String siglaEstado;
	private List<DoadorJson> doadoresJsonCollection;
	private Integer totalNoEstado;
	
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public List<DoadorJson> getDoadoresJsonCollection() {
		return doadoresJsonCollection;
	}
	public void setDoadoresJsonCollection(List<DoadorJson> doadoresJsonCollection) {
		this.doadoresJsonCollection = doadoresJsonCollection;
	}
	public Integer getTotalNoEstado() {
		return totalNoEstado;
	}
	public void setTotalNoEstado(Integer totalNoEstado) {
		this.totalNoEstado = totalNoEstado;
	}
}
