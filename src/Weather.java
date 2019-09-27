/**
 * Weather
 *
 * @author John Yang
 *
 */

import java.util.Random;

import javafx.scene.paint.Color;
import java.io.Serializable;

public class Weather implements Serializable{
	private final int percent = 100;
    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

	//ground type		- depends on precipitation
	// private final Color GRASS = Color.GREEN;
	// private final Color DIRT = Color.SANDYBROWN;
	// private final Color MUD = Color.SADDLEBROWN;
	// private final Color HAILGROUND = Color.GRAY;
	//private final Color SNOWGROUND = Color.WHITE;
    private final String GRASS = "GREEN";
	private final String DIRT = "SANDYBROWN";
	private final String MUD = "SADDLEBROWN";
	private final String HAILGROUND = "GRAY";
	private final String SNOWGROUND = "LIGHTGRAY";

	private final int NONE = 0;
	private final int RAINING = 1;
	private final int HAILING = 2;
	private final int SNOWING = 3;

	private final int SUNNYMOD = 10;	// add 10 degrees if sunny
	// None, rain, hail, snow
	private final int[] PRECIPITATIONMOD = new int[]{0, -10, -15, -30};

	// none, wind, rain, hail, snow
	private final double[] SPEEDMOD = new double[] {1, 0.9, 0.6, 0.8, 0.4};

	private final int WINDTEMP = 70;	// subtract temperature (after sun, precip) by WINDTEMP, multiply by WINDMOD
	private final double WINDYMOD = 0.2; //

	private final int MAXHAILTEMP = 90;
	private final int MAXSNOWTEMP = 50;

	private final int CHANCESUNNY = 70;	// percent chance of sun (else cloudy)
	private final int CHANCEWINDY = 50;	// percent chance windy

	// if cloudy, chance of precipitation
	private final int CHANCERAIN = 40;
	private final int CHANCEHAIL = 5;
	private final int CHANCESNOW = 30;

	private boolean sunny;		// sunny or cloudy
	private boolean windy;		// windy or not windy
	private int precipitation;	// rain, hail, snow

	// temperature
	private double currTemperature;

	public Weather(double temp) {
		currTemperature = temp;
	}

	public void setTemperature(double temperature, Season s) {
		currTemperature = temperature + s.getTempMod();

		setSunny();
		if (sunny) {
			currTemperature += SUNNYMOD;
		}

		setPrecipitation();
		currTemperature += PRECIPITATIONMOD[precipitation];

		setWindy();
		if (windy) {
			double temp = currTemperature - WINDTEMP;
			temp *= WINDYMOD;
			currTemperature += temp;
		}
	}
	public double getTemperature() {
		return currTemperature;
	}

	private void setSunny() { 
		Random r = new Random();

		if (r.nextInt(percent) < CHANCESUNNY) {
			sunny = true;
		}
		else {
			sunny = false;
		}
	}

	private void setWindy() {
		Random r = new Random();

		if (r.nextInt(percent) < CHANCEWINDY) {
			windy = true;
		}
		else {
			windy = false;
		}
	}

	private void setPrecipitation() {
		Random r = new Random();
		if (!sunny) {
			if (currTemperature < MAXSNOWTEMP) {
				if (r.nextInt(percent) < CHANCESNOW) {
					precipitation = SNOWING;
					return;
				}
			}

			if(currTemperature < MAXHAILTEMP) {
				if (r.nextInt(percent) < CHANCEHAIL) {
					precipitation = HAILING;
					return;
				}
			}

			if (r.nextInt(percent) < CHANCERAIN) {
				precipitation = RAINING;
				return;
			}

		}
		precipitation = NONE;
	}

	public Color getGround(Location l) {
        Color color;
		switch (precipitation) {
			case SNOWING:
                color = Color.web(SNOWGROUND);
				return color;
			case HAILING:
                color = Color.web(HAILGROUND);
				return color;
			case RAINING:
                color = Color.web(MUD);
				return color;
		}

		if (l.getRegion().getIsForest()) {
            color = Color.web(GRASS);
			return color;
		}
        color = Color.web(DIRT);
		return color;
	}

	public String getWeatherConditionString() {
		String temp = "";

		if (sunny) {
			temp += "Sunny";
		}
		else {
			switch (precipitation) {
				case SNOWING:
					temp += "Snowing";
					break;
				case HAILING:
					temp += "Hailing";
					break;
				case RAINING:
					temp += "Raining";
					break;
				default:
					temp += "Cloudy";
			}
		}

		if (windy) {
			temp += ", Windy";
		}
		return temp;
	}

	public boolean getSunny() {
		return sunny;
	}

	public boolean getWindy() {
		return windy;
	}

	/**
	 * 0 = none
	 * 1 = rain
	 * 2 = hail
	 * 3 = snow
	 *
	 * @return
	 */
	public int getPrecipitation() {
		return precipitation;
	}

	public double getSpeedModifier() {
		if (windy) {
			return SPEEDMOD[1];
		}
		if (precipitation == 0)
		{
			return SPEEDMOD[0];
		}
		return SPEEDMOD[precipitation + 1];
	}
}
