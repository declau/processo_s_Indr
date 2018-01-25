package br.com.entidades;

public enum TipoIngrediente {
	
	ALFACE("Alface"), BACON("Bacon"), HAMBURGUER_DE_CARNE("Hamburguer de carne"), OVO("Ovo"), QUEIJO("Queijo");
	
	private String descricao;
	
	private TipoIngrediente(String descricao) {
        this.descricao = descricao;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
