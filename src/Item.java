import java.io.Serializable;

public class Item implements Serializable{
    private String name;
    private double cost;
    private int quantity;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

    public Item(String name, double cost, int quantity){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public void incQuantity(){
        quantity++;
    }

    public void decQuantity(){
        quantity--;
    }

    public void decQuanityMultipleTimes(int amount) {
    	for (int i = 0; i < amount; i++) {
    		quantity--;
    	}
    }

    public String getName(){
        return name;
    }

    public double getCost(){
        return cost;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int amount){
        this.quantity = amount;
    }

    public String toString(){
        String description = this.getName() + ", " + this.getCost() + ", " + this.getQuantity();
        return description;
    }
}
