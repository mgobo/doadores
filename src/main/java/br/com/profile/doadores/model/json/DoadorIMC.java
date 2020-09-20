package br.com.profile.doadores.model.json;

public class DoadorIMC {

	private String faixaIdade;
	private Double imcMedioFaixaIdade;
	private Integer total;
	
	public DoadorIMC(String faixaIdade, Double imcMedioFaixaIdade, Integer total) {
		super();
		this.faixaIdade = faixaIdade;
		this.imcMedioFaixaIdade = imcMedioFaixaIdade;
		this.total = total;
	}
	
	public String getFaixaIdade() {
		return faixaIdade;
	}

	public void setFaixaIdade(String faixaIdade) {
		this.faixaIdade = faixaIdade;
	}

	public Double getImcMedioFaixaIdade() {
		return imcMedioFaixaIdade;
	}

	public void setImcMedioFaixaIdade(Double imcMedioFaixaIdade) {
		this.imcMedioFaixaIdade = imcMedioFaixaIdade;
	}

	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
