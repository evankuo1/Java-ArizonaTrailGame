/**
 * Travel Speed
 *
 * create travel speed using constants
 * 	or makeTravelSpeed
 *
 * @author John Yang
 *
 */

import java.io.Serializable;

public class TravelSpeed implements Serializable{
	// numbers may need adjusting
	public static final TravelSpeed RESTING = new TravelSpeed("Resting", 0);
	public static final TravelSpeed SLOW = new TravelSpeed("Slow", .5);
	public static final TravelSpeed STEADY = new TravelSpeed("Steady", 1);
	public static final TravelSpeed FAST = new TravelSpeed("Fast", 1.5);
	public static final TravelSpeed GRUELING = new TravelSpeed("Grueling", 2);

	private String speedName;
	private double speed;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

	private TravelSpeed(String speedName, double speed) {
		this.speedName = speedName;
		this.speed = speed;
	}

	// returns null if invalid speed
	public static TravelSpeed makeTravelSpeed(int speed) {
		switch (speed) {
			case(1):{
				return TravelSpeed.RESTING;
			}
			case (2):{
				return TravelSpeed.SLOW;
			}
			case(3):{
				return TravelSpeed.STEADY;
			}
			case (4):{
				return TravelSpeed.FAST;
			}
			case (5):{
				return TravelSpeed.GRUELING;
			}
			default:
				return null;
		}
	}

	// returns null if invalid speed
	public static TravelSpeed makeTravelSpeed(String speedName) {
		switch (speedName) {
			case("Resting"):{
				return TravelSpeed.RESTING;
			}
			case ("Slow"):{
				return TravelSpeed.SLOW;
			}
			case("Steady"):{
				return TravelSpeed.STEADY;
			}
			case ("Fast"):{
				return TravelSpeed.FAST;
			}
			case ("Grueling"):{
				return TravelSpeed.GRUELING;
			}
			default:
				return null;
		}
	}

	public String getSpeedName() {
		return this.speedName;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}
}
