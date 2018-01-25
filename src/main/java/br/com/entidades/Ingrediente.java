package br.com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

public class Ingrediente extends Lanche implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal valorUnidade;
	private Long quantidade;
	private TipoIngrediente tipo;

	public Ingrediente(){
	}
	
	public Ingrediente(Ingrediente ingrediente){
		this.nome = ingrediente.getNome();
		this.valorUnidade = ingrediente.getValorUnidade();
		this.quantidade = ingrediente.getQuantidade();
		this.tipo = ingrediente.getTipo();
	}
	
	public Ingrediente(TipoIngrediente tipo, BigDecimal valorUnidade, Long quantidade){
		this.nome = tipo.getDescricao();
		this.valorUnidade = valorUnidade;
		this.quantidade = quantidade;
		this.tipo = tipo;
	}
	
	public BigDecimal getValorTotal(){
		return getValorUnidade().multiply(new BigDecimal(quantidade));
	}
	
	public BigDecimal getValorUnidade() {
		return valorUnidade;
	}

	public void setValorUnidade(BigDecimal valorUnidade) {
		this.valorUnidade = valorUnidade;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public TipoIngrediente getTipo() {
		return tipo;
	}

	public void setTipo(TipoIngrediente tipo) {
		this.tipo = tipo;
	}
	
}
