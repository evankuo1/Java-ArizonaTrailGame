import java.io.Serializable;

public class Inventory implements Serializable{
    private  double money;//arbitrary number for now.
    private int capacity = 20;//arbitrary number for now.
    private Item[] inventory;
    private int numItems = 0;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

    //Ideas for inventory:
    //The inventory has a certain number of slots, and each item has its own quantity.
    //For example, one inventory slot could hold 'food' which might have a quantity of 100(lbs).
    //The maximum quantity of an item could be controlled individually by each item, so that we could
    //carry 100lbs of food, 150 bullets, 100 gallons of water, etc.

    public Inventory(){
        this.numItems = 0;
        inventory = new Item[capacity];
    }

    public void setMoney(double amount) {
    	money = amount;
    }

    public double getMoney() {
    	return money;
    }

    public void changeMoney(double moneyLostorGained) {
    	money = money + moneyLostorGained;
    }

    /***
     *
     * @param item An item object to be added to inventory.
     */
    public void addItem(Item item){
        //This is assuming that each item name in the inventory is
        //unique. If an item with the same name already exists in
        //the inventory, then increment its quantity.
        //example: adding 20lbs of food to 100lbs food already in inventory.


        for(Item temp : inventory){
        	if (temp != null) {
            if(temp.getName().equals(item.getName())){
                item.incQuantity();
                return;
            }
        	}
        }


        if(numItems < capacity-1){
            inventory[numItems] = item;
            numItems++;
        }
    }

    /***
     *
     * @param item An item to be removed from inventory.
     */
    public void removeItem(Item item){
        Item[] temp = new Item[capacity];
        int j = 0;
        for(int i = 0; i < capacity; i++){
            if(inventory[i] != null){
                if(inventory[i].getName() != item.getName()){
                    temp[j] = inventory[i];
                    j++;
                }
            }
        }

        inventory = temp.clone();
    }

    public void useItem(Item item){
        item.decQuantity();

        // remove item from inventory if quantity reaches 0
        if (item.getQuantity() <= 0) {
        	removeItem(item);
        }
    }

    // get item by name
    //		if not found, return null
    //		probably should throw exception
    public Item getItem(String name) {
    	for (Item i : inventory) {
    		if (i.getName().equals(name)) {
    			return i;
    		}
    	}

    	return null;
    }

    public String getItems(){
        String invStr = "";
        for(int i = 0; i <= numItems; i++){
            invStr += inventory[i].toString() + "\n";
        }
        return invStr;
    }

}
