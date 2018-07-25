
public class Onion extends ToppingPizza {

	protected Pizza pizza;

	public Onion(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public int cost() {
		return 2 + pizza.cost();
	}

	@Override
	public String content() {
		return pizza.content() + " Onion";
	}

}
