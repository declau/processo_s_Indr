package br.com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	protected BigDecimal valorDesconto;
	protected BigDecimal valorSemDesconto;
	protected BigDecimal valor;
	
	List<Lanche> itens;

	public List<Lanche> getItens() {
		if(itens == null)
			itens = new ArrayList<Lanche>();
		return itens;
	}

	public void setItens(List<Lanche> itens) {
		this.itens = itens;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor(){
		return getValorSemDesconto().subtract(getValorDesconto()).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public BigDecimal getValorSemDesconto() {
		BigDecimal b = BigDecimal.ZERO;
		for (Lanche lanche : itens) {
			b = b.add(lanche.getValorSemDesconto());
		}
		return b.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public BigDecimal getValorDesconto() {
		BigDecimal b = BigDecimal.ZERO;
		for (Lanche lanche : itens) {
			b = b.add(lanche.getValorDesconto());
		}
		return b.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
