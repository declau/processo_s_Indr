package lab.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DragDropEvent;

import br.com.entidades.Ingrediente;
import br.com.entidades.Lanche;
import br.com.entidades.Pedido;
import br.com.entidades.TipoIngrediente;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private List<Lanche> itensSelecionados;
	private List<Ingrediente> ingredientes;
	private Pedido pedido;
	private Lanche selectedLanche;

	@PostConstruct
	public void init() {
		itensSelecionados = new ArrayList<Lanche>(0);
		ingredientes = carregarIngredientes();
	}

	private List<Ingrediente> carregarIngredientes() {

		List<Ingrediente> retorno = new ArrayList<Ingrediente>(0);

		Ingrediente alface = new Ingrediente(TipoIngrediente.ALFACE, new BigDecimal(0.40), 1l);
		Ingrediente bacon = new Ingrediente(TipoIngrediente.BACON, new BigDecimal(2), 1l);
		Ingrediente hambuguerCarne = new Ingrediente(TipoIngrediente.HAMBURGUER_DE_CARNE, new BigDecimal(3), 1l);
		Ingrediente ovo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 1l);
		Ingrediente queijo = new Ingrediente(TipoIngrediente.QUEIJO, new BigDecimal(1.5), 1l);

		retorno.add(alface);
		retorno.add(bacon);
		retorno.add(hambuguerCarne);
		retorno.add(ovo);
		retorno.add(queijo);

		return retorno;

	}

	private List<Lanche> carregarLanches() {
		List<Lanche> retorno = new ArrayList<Lanche>(0);

		Ingrediente alface = new Ingrediente(ingredientes.get(0));
		Ingrediente bacon = new Ingrediente(ingredientes.get(1));
		Ingrediente hambuguerCarne = new Ingrediente(ingredientes.get(2));
		Ingrediente ovo = new Ingrediente(ingredientes.get(3));
		Ingrediente queijo = new Ingrediente(ingredientes.get(4));
		
		Ingrediente semAlface = new Ingrediente(TipoIngrediente.ALFACE, new BigDecimal(0.40), 0l);
		Ingrediente semBacon = new Ingrediente(TipoIngrediente.BACON, new BigDecimal(2), 0l);
		Ingrediente semOvo = new Ingrediente(TipoIngrediente.OVO, new BigDecimal(0.80), 0l);
		
		Lanche xBacon = new Lanche("xBacon");
		xBacon.getIngredientes().add(semAlface);
		xBacon.getIngredientes().add(bacon);
		xBacon.getIngredientes().add(hambuguerCarne);
		xBacon.getIngredientes().add(queijo);
		xBacon.getIngredientes().add(semOvo);

		Lanche xBurger = new Lanche("xBurger");
		xBurger.getIngredientes().add(semAlface);
		xBurger.getIngredientes().add(semBacon);
		xBurger.getIngredientes().add(hambuguerCarne);
		xBurger.getIngredientes().add(queijo);
		xBurger.getIngredientes().add(semOvo);

		Lanche xEgg = new Lanche("xEgg");
		xEgg.getIngredientes().add(semAlface);
		xEgg.getIngredientes().add(semBacon);
		xEgg.getIngredientes().add(hambuguerCarne);
		xEgg.getIngredientes().add(queijo);
		xEgg.getIngredientes().add(ovo);

		Lanche xEggBacon = new Lanche("xEggBacon");
		xEggBacon.getIngredientes().add(semAlface);
		xEggBacon.getIngredientes().add(bacon);
		xEggBacon.getIngredientes().add(hambuguerCarne);
		xEggBacon.getIngredientes().add(queijo);
		xEggBacon.getIngredientes().add(ovo);

		retorno.add(xBacon);
		retorno.add(xBurger);
		retorno.add(xEgg);
		retorno.add(xEggBacon);

		return retorno;

	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;

	}

	public List<Lanche> getItens() {
		return carregarLanches();
	}

	public List<Lanche> getItensSelecionados() {
		if (itensSelecionados == null)
			itensSelecionados = new ArrayList<Lanche>(0);
		return itensSelecionados;
	}

	public void onPedidoDrop(DragDropEvent ddEvent) throws CloneNotSupportedException {

		if (pedido == null)
			pedido = new Pedido();

		getItens().remove(((Lanche) ddEvent.getData()));
		getItensSelecionados().add(((Lanche) ddEvent.getData()));
		pedido.getItens().add((Lanche) ddEvent.getData());

	}

	public String avancar() {
		getItensSelecionados().clear();
		return "goVenda";
	}

	public String excluir() {
		getItensSelecionados().remove(selectedLanche);
		getItens().add(selectedLanche);
		pedido.getItens().remove(selectedLanche);

		if (getItensSelecionados().size() == 1)
			getItensSelecionados().clear();
		return null;
	}
	
	public String limpar() {
		getItensSelecionados().clear();
		pedido.getItens().clear();
		return null;
	}

	public String voltar() {
		return "goVoltar";
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Lanche getSelectedLanche() {
		return selectedLanche;
	}

	public void setSelectedLanche(Lanche selectedLanche) {
		this.selectedLanche = selectedLanche;
	}
}