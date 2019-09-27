/**
 * 
 * @author John Yang
 *
 */
public class Water extends Location {
	public static final int STILLWATER = 0;
	public static final int SLOWCURRENT = 1;
	public static final int MEDIUMCURRENT = 2;
	public static final int FASTCURRENT = 3;
	public static final int RAPIDS = 4;
	
	private int waterCurrent;
	
	public Water(String n, double lon, double lat, int c) {
		super(n, lon, lat);
		waterCurrent = c;
	}
	
	public int getCurrent() {
		return waterCurrent;
	}
}
