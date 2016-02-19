package br.com.model;

public class Informacoes implements Comparable<Informacoes> {

	private String usuario;
	private String morte;
	private String outroUsuario;
	private String arma;
	private Integer quantidade;
	
	@Override
	public int compareTo(Informacoes o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getQuantidade(), o.getQuantidade());
	}
	
	@Override
	public boolean equals(Object obj) {
		final Informacoes other = (Informacoes) obj;
		return getUsuario().equalsIgnoreCase(other.getUsuario());
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getMorte() {
		return morte;
	}
	public void setMorte(String morte) {
		this.morte = morte;
	}
	public String getOutroUsuario() {
		return outroUsuario;
	}
	public void setOutroUsuario(String outroUsuario) {
		this.outroUsuario = outroUsuario;
	}
	public String getArma() {
		return arma;
	}
	public void setArma(String arma) {
		this.arma = arma;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
