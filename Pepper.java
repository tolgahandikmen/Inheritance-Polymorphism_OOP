
public class Pepper extends ToppingPizza {

	protected Pizza pizza;

	public Pepper(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public int cost() {
		return 1 + pizza.cost();
	}

	@Override
	public String content() {
		return pizza.content() + " HotPepper" ;
	}

}
