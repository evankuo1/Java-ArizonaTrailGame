/**
 * Settlement
 *
 * city/town
 * 	also holds shop
 *
 * @author John Yang
 *
 */
import java.io.Serializable;

public class Settlement extends Location implements Serializable{
	private Store store;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

	public Settlement(String n, double lon, double lat) {
		super(n, lon, lat);

		store = new Store();
	}

	public Settlement(String n, double lon, double lat, Person p) {
		super(n, lon, lat);

		store = new Store(n, p);
	}

	public Store getStore() {
		return store;
	}
}
