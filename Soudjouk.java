
public class Soudjouk extends ToppingPizza {

	protected Pizza pizza;

	public Soudjouk(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public int cost() {
		return 3 + pizza.cost();
	}

	@Override
	public String content() {
		return pizza.content() + " Soudjouk";
	}

}
