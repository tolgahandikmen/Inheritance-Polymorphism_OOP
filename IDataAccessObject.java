import java.util.ArrayList;

public interface IDataAccessObject {
	
    /**
     *  Read a single entry from file
     * @param ID id of the customer
     * @return  Object customer with given ID
     */
            Customer getByID(int ID);

    /**
     *   Delete a single entry from file
     * @param ID  id of customer
     * @return result of operation
     */
            boolean deleteByID(int ID);

    /**
     *  Add a new object
     * @param object  object to be added
     */
            void 	add(Customer object);

    /**
     *  Update an entry
     * @param object   entry to be updated
     */
            void 	update(Customer object);

    /**
     * Returns all entries  in a list
     * @return   List list of all entries
     */
            ArrayList<Customer> getALL(); // get all entries

}
