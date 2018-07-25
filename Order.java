import java.util.ArrayList;

public class Order {
	private int orderId;
	private int customerId;
	private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private ArrayList<Drink> drink = new ArrayList<Drink>();

	
	public Order(int orderId, int customerId, ArrayList<Pizza> pizzas) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.pizzas = pizzas;
		this.drink = new ArrayList<Drink>();
	}
	
	public Order(int orderId, int customerId, ArrayList<Pizza> pizzas, ArrayList<Drink> drink) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.pizzas = pizzas;
		this.drink = drink;
	}
	public Order(int orderId, int customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.pizzas = new ArrayList<Pizza>();
		this.drink = new ArrayList<Drink>();
	}
	
	public void updatePizzaOrder(Pizza pizza) {
		this.pizzas.add(pizza);
	}
	
	public void updateDrinkOrder(Drink drink) {
		this.drink.add(drink);
	}
	
	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}


	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	public ArrayList<Drink> getDrink(){
		return drink;
	}
	
	public void setDrink(ArrayList<Drink> drink) {
		this.drink=drink;
	}



	

}
