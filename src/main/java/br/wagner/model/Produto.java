package model;

import java.text.DecimalFormat;

public class Produto {

	private int id;
	private String nome;
	private double valor;
	private int qtd;
	private int desconto;
	
	public void setId(int id) {
		this.id=id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setValor(double valor) {
		this.valor=valor;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	
	public int getId() {
		return this.id;
	}
	public String getNome() {
		return this.nome;
	}
	public double getValor() {
		return this.valor;
	}
	public int getQtd() {
		return this.qtd;
	}
	public int getDesconto() {
		return this.desconto;
	}
	
	public String getValorForm() {
		return new DecimalFormat("#,##0.00").format(this.valor);
	}
	
	public String getValorLiquido() {
		
		double valorLiquido = (this.valor - (this.valor * ((double)this.desconto/100))); 
		
		return new DecimalFormat("#,##0.00").format(valorLiquido);
	}
	public String getValorDesconto() {
		
		double valorDesconto = ((this.valor * ((double)this.desconto/100))); 
		
		return new DecimalFormat("#,##0.00").format(valorDesconto);
	}
	

}
