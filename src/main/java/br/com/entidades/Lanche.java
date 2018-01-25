package br.com.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Lanche implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String nome;
	
	private List<Ingrediente> ingredientes;

	public Lanche(String nome, List<Ingrediente> ingredientes) {
		this.nome = nome;
		this.ingredientes = new ArrayList<>(ingredientes);
	}

	public Lanche(String nome) {
		this.nome = nome;
	}

	public Lanche() {
	}

	public List<Ingrediente> getIngredientes() {
		if (ingredientes == null)
			ingredientes = new ArrayList<>(0);
		return ingredientes;
	}

	public BigDecimal getValorSemDesconto() {
		BigDecimal soma = BigDecimal.ZERO;
		for (Ingrediente item : ingredientes) {
			soma = soma.add(item.getValorTotal());
		}
		return soma.setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal getValorDesconto() {
		return descontoLight().add(descontoMuitaCarne()).add(descontoMuitoQueijo()).setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal descontoLight() {

		BigDecimal total = getValorSemDesconto();

		boolean temAlface = false;
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.ALFACE) && ingrediente.getQuantidade() > 0)
				temAlface = true;
			if (ingrediente.getTipo().equals(TipoIngrediente.BACON) && ingrediente.getQuantidade() > 0l)
				return BigDecimal.ZERO;
		}

		if (temAlface)
			return (total.multiply(BigDecimal.TEN)).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN);
		else
			return BigDecimal.ZERO;
	}

	public BigDecimal descontoMuitaCarne() {
		BigDecimal valor = BigDecimal.ZERO;
		Long quantidade = 0l;
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.HAMBURGUER_DE_CARNE)) {

				if (ingrediente.getQuantidade() < 3)
					return BigDecimal.ZERO;
				valor = ingrediente.getValorUnidade();
				quantidade = ingrediente.getQuantidade();
				break;
			}
		}

		Long qtdDesc = 0l;
		if (quantidade % 3 == 0)
			qtdDesc = quantidade / 3;
		else if (quantidade % 3 == 1)
			qtdDesc = (quantidade - 1) / 3;
		else if (quantidade % 3 == 2)
			qtdDesc = (quantidade - 2) / 3;

		return valor.multiply(new BigDecimal(qtdDesc)).setScale(2, RoundingMode.HALF_EVEN);
	}

	public BigDecimal descontoMuitoQueijo() {

		BigDecimal valor = BigDecimal.ZERO;
		Long quantidade = 0l;
		for (Ingrediente ingrediente : ingredientes) {
			if (ingrediente.getTipo().equals(TipoIngrediente.QUEIJO)) {

				if (ingrediente.getQuantidade() < 3)
					return BigDecimal.ZERO;
				valor = ingrediente.getValorUnidade();
				quantidade = ingrediente.getQuantidade();
				break;

			}
		}

		Long qtdDesc = 0l;
		if (quantidade % 3 == 0)
			qtdDesc = quantidade / 3;
		else if (quantidade % 3 == 1)
			qtdDesc = (quantidade - 1) / 3;
		else if (quantidade % 3 == 2)
			qtdDesc = (quantidade - 2) / 3;

		return valor.multiply(new BigDecimal(qtdDesc)).setScale(2, RoundingMode.HALF_EVEN);

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return getValorSemDesconto().subtract(getValorDesconto()).setScale(2, RoundingMode.HALF_EVEN);
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

}
