import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		IDataAccessObject customerList= new CustomerDaoImpl();			/* holds all customers information */
		ArrayList <Order> orders = new ArrayList<Order>();				/* holds all orders information */
		BufferedReader br = null;
		String line = "";

		
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter("output.txt"));

			
			/* This code block until close, reads customers assign them to a list */
			br = new BufferedReader(new FileReader("customer.txt"));
			while ((line = br.readLine()) != null) {
				String[] columns = line.split(" ");
				String address = "";
				for (int i = 5; i < columns.length; i++) {
					address += columns[i] + " ";
				}
				Customer temp=new Customer(Integer.parseInt(columns[0]),columns[1],columns[2],columns[3],address);
				customerList.add(temp);
			}
			br.close();
			/* closing block */
			
			/* This code block until close, reads orders assign them to a list */			
			br = new BufferedReader(new FileReader("order.txt"));
			int orderId = -1;
			int customerId=0;
			ArrayList<Pizza> orderPizzas = new ArrayList<Pizza>();
			ArrayList<Drink> orderDrinks = new ArrayList<Drink>();

			while ((line = br.readLine()) != null) {
				String[] columns = line.split(" ");
				
				Pizza pizza=null;
				Drink drink=null;
				
				if (columns[0].equals("Order:")) {
					/* adding order operation */
					
					if (orderId != -1) {
						if(!orderDrinks.isEmpty()) {
							Order temp=new Order(orderId, customerId, orderPizzas, orderDrinks);
							orders.add(temp);
						}
						else {
							Order temp=new Order(orderId, customerId, orderPizzas);	
							orders.add(temp);
						}
					}
					orderPizzas.clear();
					orderDrinks.clear();
					orderId = Integer.parseInt(columns[1]);
					customerId = Integer.parseInt(columns[2]);
					break;
				}
				else if (columns[0].equals("Neapolitan")) {
					pizza = new NeapolitanPizza();
					
					
				}
				else if (columns[0].equals("AmericanPan")) {
					pizza = new AmericanPanPizza();
					
				}
				else if (columns[0].equals("Softdrink")) {
					drink = new Drink();	
					orderDrinks.add(drink);
				}
				for(int i=0;i<columns.length;i++) {
					if(columns[i].equals("Soudjouk")) {
						pizza = new Soudjouk(pizza);
						
					}else if(columns[i].equals("Salami")) {
						pizza = new Salami(pizza);
						
					}else if(columns[i].equals("HotPepper")) {
						pizza = new Pepper(pizza);
						
					}else if(columns[i].equals("Onion")) {
						pizza = new Onion(pizza);
					}
				}
				orderPizzas.add(pizza);

			}
			br.close();
			/* closing block */
			
			
			br = new BufferedReader(new FileReader("input.txt"));
			while ((line = br.readLine()) != null) {

				// use whitespace as separator
				String[] columns = line.split(" ");
				
				
				if (columns[0].equals("AddCustomer")) {
					String address = "";
					for (int i = 5; i < columns.length; i++) {
						address += columns[i] + " ";
					}
					Customer temp=new Customer(Integer.parseInt(columns[1]),columns[2],columns[3],columns[4],address);
					customerList.add(temp);
					output.write("Customer "+temp.getCustomerId()+" "+temp.getName()+" added\n");
					
				}
				else if (columns[0].equals("RemoveCustomer")) {
					boolean controlExist = false;
					int index = -1;
					
					for(int i=0;i<customerList.getALL().size();i++) {
						if(customerList.getALL().get(i).getCustomerId()==Integer.parseInt(columns[1])) {
							controlExist=true;
							index = i;
						}
					}
					if (controlExist == true) {
						output.write("Customer "+customerList.getALL().get(index).getCustomerId()+" "+customerList.getALL().get(index).getName()+" removed\n");
						customerList.deleteByID(Integer.parseInt(columns[1]));
					}
				}

				else if (columns[0].equals("CreateOrder")) {
					boolean controlExist = false;
				
					for(int i=0;i<customerList.getALL().size();i++) {
						if(customerList.getALL().get(i).getCustomerId()==Integer.parseInt(columns[2])) {
							controlExist=true;
						}
					}
					if (controlExist == true) {
						Order newOrder = new Order(Integer.parseInt(columns[1]),Integer.parseInt(columns[2]));
						orders.add(newOrder);
						output.write("Order "+columns[1]+" created\n");
					}
						
				}

				else if (columns[0].equals("AddPizza")) {
					int index=-1;
					for(int i=0;i<orders.size();i++) {
						if(orders.get(i).getOrderId()==Integer.parseInt(columns[1])) {
							index=i;
						}
					}
					if(index != -1) {
						Pizza pizza = null;
						
						if (columns[2].equals("Neapolitan")) {
							pizza = new NeapolitanPizza();
							output.write("Neapolitan pizza added to order "+columns[1]+"\n");
						}
						else if (columns[2].equals("AmericanPan")) {
							pizza = new AmericanPanPizza();
							output.write("AmericanPan pizza added to order "+columns[1]+"\n");							
						}
						
						for(int i=0;i<columns.length;i++) {
							if(columns[i].equals("Soudjouk")) {
								pizza = new Soudjouk(pizza);
								
							}else if(columns[i].equals("Salami")) {
								pizza = new Salami(pizza);
								
							}else if(columns[i].equals("HotPepper")) {
								pizza = new Pepper(pizza);
								
							}else if(columns[i].equals("Onion")) {
								pizza = new Onion(pizza);
							}
						}
						orders.get(index).updatePizzaOrder(pizza);					
					}
				}

				
				else if (columns[0].equals("AddDrink")) {
					int index=-1;
					for(int i=0;i<orders.size();i++) {
						if(orders.get(i).getOrderId()==Integer.parseInt(columns[1])) {
							index=i;
						}
					}
					if(index != -1) {
						Drink drink = new Drink();
						
						orders.get(index).updateDrinkOrder(drink);
						output.write("Drink added to order "+columns[1]+"\n");
					}
				}
				
				
				
				else if (columns[0].equals("PayCheck")) {
					int total = 0;
					output.write("PayCheck for order " + columns[1]+"\n");
					int index = -1;
					for (int i = 0; i < orders.size(); i++) {
						if (orders.get(i).getOrderId() == Integer.parseInt(columns[1])) {
							index = i;
						}
					}
					if (index != -1) {
						for (int i = 0; i < orders.get(index).getPizzas().size(); i++) {
							output.write("\t" + orders.get(index).getPizzas().get(i).content());
							output.write(" " + orders.get(index).getPizzas().get(i).cost()+"$\n");
							total += orders.get(index).getPizzas().get(i).cost();
						}
						if (!orders.get(index).getDrink().isEmpty()) {
							output.write("\tSoftDrink "+orders.get(index).getDrink().size()*2+"$\n");
							total += orders.get(index).getDrink().size() * 2;
						}
						output.write("\tTotal: "+total+"$\n");
						
					}

				}		
				

				else if (columns[0].equals("List")) {
					Collections.sort(customerList.getALL(), Comparator.comparing(Customer::getName).thenComparing(Customer::getSurname));
					output.write("Customer List:\n");
					for(int i=0;i<customerList.getALL().size(); i++) {
						output.write(customerList.getALL().get(i).getCustomerId()+" ");
						output.write(customerList.getALL().get(i).getName()+" ");
						output.write(customerList.getALL().get(i).getSurname()+" ");
						output.write(customerList.getALL().get(i).getPhoneNumber()+" ");
						output.write("Address: "+customerList.getALL().get(i).getAddress()+"\n");
					}
				}

			}
			output.close();
			
			BufferedWriter order = new BufferedWriter(new FileWriter("order1.txt"));
			order.close();
			
			BufferedWriter customer = new BufferedWriter(new FileWriter("customer1.txt"));
			
			Collections.sort(customerList.getALL(), Comparator.comparing(Customer::getCustomerId).thenComparing(Customer::getName));
			for(int i=0;i<customerList.getALL().size(); i++) {
				customer.write(customerList.getALL().get(i).getCustomerId()+" ");
				customer.write(customerList.getALL().get(i).getName()+" ");
				customer.write(customerList.getALL().get(i).getSurname()+" ");
				customer.write(customerList.getALL().get(i).getPhoneNumber()+" ");
				customer.write("Address: "+customerList.getALL().get(i).getAddress()+"\n");
			}
			customer.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
