package br.com.profile.doadores.model.json;

public class DoadorObesos {

	private Long totalMasculinosObesos;
	private Double percentualMasculinosObesos;
	private Long totalFemininosObesos;
	private Double percentualFemininosObesos;
	private Integer totalDoadores;
	
	public DoadorObesos(Long totalMasculinosObesos, Double percentualMasculinosObesos,
			Long totalFemininosObesos, Double percentualFemininosObesos, Integer totalDoadores) {
		super();
		this.totalMasculinosObesos = totalMasculinosObesos;
		this.percentualMasculinosObesos = percentualMasculinosObesos;
		this.totalFemininosObesos = totalFemininosObesos;
		this.percentualFemininosObesos = percentualFemininosObesos;
		this.totalDoadores = totalDoadores;
	}

	public Long getTotalMasculinosObesos() {
		return totalMasculinosObesos;
	}

	public void setTotalMasculinosObesos(Long totalMasculinosObesos) {
		this.totalMasculinosObesos = totalMasculinosObesos;
	}

	public Double getPercentualMasculinosObesos() {
		return percentualMasculinosObesos;
	}

	public void setPercentualMasculinosObesos(Double percentualMasculinosObesos) {
		this.percentualMasculinosObesos = percentualMasculinosObesos;
	}

	public Long getTotalFemininosObesos() {
		return totalFemininosObesos;
	}

	public void setTotalFemininosObesos(Long totalFemininosObesos) {
		this.totalFemininosObesos = totalFemininosObesos;
	}

	public Double getPercentualFemininosObesos() {
		return percentualFemininosObesos;
	}

	public void setPercentualFemininosObesos(Double percentualFemininosObesos) {
		this.percentualFemininosObesos = percentualFemininosObesos;
	}

	public Integer getTotalDoadores() {
		return totalDoadores;
	}

	public void setTotalDoadores(Integer totalDoadores) {
		this.totalDoadores = totalDoadores;
	}
}
