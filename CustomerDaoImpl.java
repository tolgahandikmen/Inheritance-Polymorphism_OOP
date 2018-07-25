import java.util.ArrayList;

public class CustomerDaoImpl implements IDataAccessObject{

	private ArrayList<Customer> customers=new ArrayList<Customer>();
	
	@Override
	public Customer getByID(int ID) {
		return customers.get(ID);
	}

	@Override
	public boolean deleteByID(int ID) {
		int contain=0;
		int index=0;
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getCustomerId()==ID) {
				index=i;
				contain++;
				break;
			}
		}
		if(contain!=0) {
			customers.remove(index);
			return true;
		}
		return false;
	}

	@Override
	public void add(Customer object) {
		customers.add(object);
	}

	@Override
	public void update(Customer object) {
		
	}

	@Override
	public ArrayList<Customer> getALL() {
		return customers;
	}
	
}
