/**
 * Region
 *
 * Plateau, Mountain, Desert
 *
 * create with constants
 *
 * @author John Yang
 *
 */
import java.io.Serializable;

public class Region implements Serializable{

	// baseTemperature is based on average temperature in October
	public static Region PLATEAU = new Region("Plateau", 47.0);		// based on Flagstaff
	public static Region MOUNTAIN = new Region("Mountain", 56.0);	// based on Prescott
	public static Region DESERT = new Region("Desert", 71.5);		// based on Tuscon

	private final int forestTempMod = 10;

	private String name;
	private boolean isForest;
	private double baseTemperature;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;
	// slope (speed modifier, may not implement)

	private Region(String name, double temp) {
		this.name = name;
		isForest = false;
		baseTemperature = temp;
	}

	/**
	 *
	 * latitude and longitudes are approximated
	 *
	 * @param lon
	 * @param lat
	 * @return
	 */
	public static Region calcRegion(double lon, double lat) {
		Region thisRegion;

		if (lon < -114.0) {
			thisRegion = Region.DESERT;
		}
		else if (lat > 35.0) {
			thisRegion = Region.PLATEAU;
		}
		else if (lat > 34.0) {
			thisRegion = Region.MOUNTAIN;
		}
		else {
			thisRegion = Region.DESERT;
		}

		thisRegion.setForest(lon, lat);

		return thisRegion;
	}

	public String getName() {
		return this.name;
	}

	private void setForest(double lon, double lat) {
		if (-113.0 < lon && lon < -110.0 && 33.8 < lat && lat < 35.5) {
			isForest = true;
			baseTemperature -= forestTempMod;
		}
	}

	public boolean getIsForest() {
		return isForest;
	}

	public double getBaseTemperature() {
		return baseTemperature;
	}
}
