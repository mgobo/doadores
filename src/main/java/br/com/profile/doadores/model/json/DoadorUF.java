package br.com.profile.doadores.model.json;

public class DoadorUF {
	
	private String uf;
	private Integer total;
	
	public DoadorUF(String uf, Integer total) {
		super();
		this.uf = uf;
		this.total = total;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
