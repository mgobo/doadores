package br.com.profile.doadores.model.json;

public class DoadorTpSangue {

	private String tipoSangue;
	private Double mediaIdade;
	private Integer totalDoadores;
	
	public DoadorTpSangue(String tipoSangue, Double mediaIdade, Integer totalDoadores) {
		super();
		this.tipoSangue = tipoSangue;
		this.mediaIdade = mediaIdade;
		this.totalDoadores = totalDoadores;
	}
	
	public String getTipoSangue() {
		return tipoSangue;
	}
	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}
	public Double getMediaIdade() {
		return mediaIdade;
	}
	public void setMediaIdade(Double mediaIdade) {
		this.mediaIdade = mediaIdade;
	}
	public Integer getTotalDoadores() {
		return totalDoadores;
	}
	public void setTotalDoadores(Integer totalDoadores) {
		this.totalDoadores = totalDoadores;
	}
}
