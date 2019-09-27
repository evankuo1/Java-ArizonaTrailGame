/**
 * Store
 *
 *
 *
 * @author John Yang
 *
 */
import java.io.Serializable;

public class Store implements Serializable{
	private String name;
	private Person owner;

	private Inventory stock;		// consider making inventory capacity setable

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;
    
	public Store() {
		name = "General Store";
		owner = new Person("Nobody");
		stock = new Inventory();
	}

	public Store(String name, Person owner) {
		this.name = "General Store";
		this.owner = owner;
		this.stock = new Inventory();
	}




	// as String
	//		probably better as array
	public String getItems() {
		return stock.getItems();
	}

	// may need to change to return String
	public Item getItem(String name) {
		return stock.getItem(name);
	}

	public Item getItem(Item item) {
		return stock.getItem(item.getName());
	}
	//^^^

	public boolean itemInStock(String name) {
		return getItem(name) != null;
	}

	public boolean itemInStock(Item item) {
		return getItem(item) != null;
	}

	public void addItem(Item item) {
		stock.addItem(item);
	}

	public void useItem(Item item) {
		stock.useItem(item);
	}


	//		!!!! NEEED TO IMPLEMENT USING MONEY
	// consider moving buy/sell to model or controller
	// buy 1 item from store
	public void buyItemFromStore(Inventory inv, String name) {
		Item temp = stock.getItem(name);
		stock.useItem(temp);
		inv.addItem(temp);
	}

	// sell 1 item to store
	public void sellItemToStore(Inventory inv, String name) {
		Item temp = inv.getItem(name);
		inv.useItem(temp);
		stock.addItem(temp);
	}
}
