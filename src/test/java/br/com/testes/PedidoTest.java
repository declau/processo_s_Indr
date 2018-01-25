package br.com.testes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.entidades.Ingrediente;
import br.com.entidades.Lanche;
import br.com.entidades.Pedido;
import br.com.entidades.TipoIngrediente;

public class PedidoTest {
	
	
	private Pedido pedido;
	
	@Before
	public void inicializacao(){
		pedido = new Pedido();
	}
	
	@Test
	public void pedidoLancheXBaconSemDesconto(){
		
		Ingrediente bacon = new Ingrediente(TipoIngrediente.BACON, new BigDecimal(2), 1l);
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		Lanche xBacon = new Lanche("xBacon");
		xBacon.getIngredientes().add(bacon);
		xBacon.getIngredientes().add(hambuguerCarne);
		xBacon.getIngredientes().add(queijo);
		pedido.getItens().add(xBacon);
		BigDecimal total = new BigDecimal(6.50).setScale(2, RoundingMode.HALF_EVEN);
		Assert.assertTrue(total.compareTo(pedido.getValor()) == 0);
		
	}
	
	@Test
	public void pedidoLancheXBurgerSemDesconto(){
		
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		Lanche xBurger = new Lanche("xBurger");
		xBurger.getIngredientes().add(hambuguerCarne);
		xBurger.getIngredientes().add(queijo);
		pedido.getItens().add(xBurger);
		BigDecimal total = new BigDecimal(4.50).setScale(2, RoundingMode.HALF_EVEN);
		Assert.assertTrue(total.compareTo(pedido.getValor()) == 0);
		
	}
	
	@Test
	public void pedidoLancheXEggSemDesconto(){
		
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		Lanche xEgg = new Lanche("xEgg");
		xEgg.getIngredientes().add(hambuguerCarne);
		xEgg.getIngredientes().add(queijo);
		xEgg.getIngredientes().add(ovo);
		pedido.getItens().add(xEgg);
		BigDecimal total = new BigDecimal(5.30).setScale(2, RoundingMode.HALF_EVEN);
		Assert.assertTrue(total.compareTo(pedido.getValor()) == 0);
	}
	
	@Test
	public void pedidoLancheXEggBaconSemDesconto(){
		Ingrediente bacon = new Ingrediente(TipoIngrediente.BACON, new BigDecimal(2), 1l);
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		
		Lanche xEggBacon = new Lanche("xEggBacon");
		xEggBacon.getIngredientes().add(bacon);
		xEggBacon.getIngredientes().add(hambuguerCarne);
		xEggBacon.getIngredientes().add(queijo);
		xEggBacon.getIngredientes().add(ovo);
		pedido.getItens().add(xEggBacon);
		BigDecimal total = new BigDecimal(7.30).setScale(2, RoundingMode.HALF_EVEN);
		Assert.assertTrue(total.compareTo(pedido.getValor()) == 0);
	}
	
	@Test
	public void pedidoLancheComDescontoLight(){
		
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		Ingrediente alface =  new Ingrediente(TipoIngrediente.ALFACE, new BigDecimal(0.40), 1l);
		Lanche xEgg = new Lanche("xEgg");
		xEgg.getIngredientes().add(hambuguerCarne);
		xEgg.getIngredientes().add(queijo);
		xEgg.getIngredientes().add(ovo);
		xEgg.getIngredientes().add(alface);
		pedido.getItens().add(xEgg);
		
		BigDecimal valorDesconto = new BigDecimal(0.57).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal totalSemDesconto = new BigDecimal(5.70).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal valor = new BigDecimal(5.13).setScale(2, RoundingMode.HALF_EVEN);
		
		Assert.assertTrue(valorDesconto.compareTo(pedido.getValorDesconto()) == 0);
		Assert.assertTrue(totalSemDesconto.compareTo(pedido.getValorSemDesconto()) == 0);
		Assert.assertTrue(valor.compareTo(pedido.getValor()) == 0);
		
	}
	
	@Test
	public void pedidoDoisLanchesUmComDescontoLightEComOutroDescontoMuitoQueijo(){
		
		Ingrediente bacon = new Ingrediente(TipoIngrediente.BACON, new BigDecimal(2), 1l);
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);
		Ingrediente alface =  new Ingrediente(TipoIngrediente.ALFACE, new BigDecimal(0.40), 1l);
		
		Lanche xEgg = new Lanche("xEgg");
		xEgg.getIngredientes().add(hambuguerCarne);
		xEgg.getIngredientes().add(queijo);
		xEgg.getIngredientes().add(ovo);
		xEgg.getIngredientes().add(alface);
		pedido.getItens().add(xEgg);
		
		Assert.assertTrue(xEgg.getValorDesconto().equals(new BigDecimal(0.57).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEgg.getValorSemDesconto().equals(new BigDecimal(5.70).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEgg.getValor().equals(new BigDecimal(5.13).setScale(2, RoundingMode.HALF_EVEN)));
		
		//Adicionando Mais Queijo ao xEggBacon
		queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 5l);
		Lanche xEggBacon = new Lanche("xEggBacon");
		xEggBacon.getIngredientes().add(bacon);
		xEggBacon.getIngredientes().add(hambuguerCarne);
		xEggBacon.getIngredientes().add(queijo);
		xEggBacon.getIngredientes().add(ovo);
		pedido.getItens().add(xEggBacon);
		
		Assert.assertTrue(xEggBacon.getValorDesconto().equals(new BigDecimal(1.50).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEggBacon.getValorSemDesconto().equals(new BigDecimal(13.30).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEggBacon.getValor().equals(new BigDecimal(11.80).setScale(2, RoundingMode.HALF_EVEN)));
		
		//Valor total pedido
		Assert.assertTrue(pedido.getValor().equals(new BigDecimal(16.93).setScale(2, RoundingMode.HALF_EVEN)));
		
	}
	
	@Test
	public void pedidoDeUmLancheComTodosOsDesconto(){
		
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 6l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 6l);
		Ingrediente alface =  new Ingrediente(TipoIngrediente.ALFACE, new BigDecimal(0.40), 1l);

		Lanche xEgg = new Lanche("xEgg");
		xEgg.getIngredientes().add(hambuguerCarne);
		xEgg.getIngredientes().add(queijo);
		xEgg.getIngredientes().add(ovo);
		xEgg.getIngredientes().add(alface);
		
		Assert.assertTrue(xEgg.descontoLight().equals(new BigDecimal(2.82).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEgg.descontoMuitaCarne().equals(new BigDecimal(6).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(xEgg.descontoMuitoQueijo().equals(new BigDecimal(3).setScale(2, RoundingMode.HALF_EVEN)));
		
		pedido.getItens().add(xEgg);
		Assert.assertTrue(pedido.getValor().equals(new BigDecimal(16.38).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(pedido.getValorDesconto().equals(new BigDecimal(11.82).setScale(2, RoundingMode.HALF_EVEN)));
		Assert.assertTrue(pedido.getValorSemDesconto().equals(new BigDecimal(28.20).setScale(2, RoundingMode.HALF_EVEN)));
		
	}
			

}
