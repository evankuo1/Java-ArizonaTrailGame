/**
 * Location
 *
 * subclass landmark, city/town, wilderness
 * 		river, lake???
 *
 * @author John Yang
 *
 */
import java.io.Serializable;

public class Location implements Serializable{
	// region info
	private String name;
	private double longitude;
	private double latitude;

	private Region region;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

	public Location(String n, double lon, double lat) {
		name = n;
		longitude = lon;
		latitude = lat;

		region = Region.calcRegion(longitude, latitude);
	}

	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public Region getRegion()
	{
		return region;
	}

	public double getBaseTemperature() {
		return region.getBaseTemperature();
	}
}
