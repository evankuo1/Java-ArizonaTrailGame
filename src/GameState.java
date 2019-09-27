/**
 * GameState
 * Model
 *
 *
 * @author John Yang
 *
 */

import java.util.*;
import java.text.*;
import java.io.File;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.paint.Color;
import java.lang.reflect.Field;

public class GameState implements Serializable{
	private final double baseCoordinateDistanceToTravel = 0.03;
	private final double milesInOneDegree = 69;					// 1 degree distance ~ 69 miles

	// player class (Banker...)
	private Family fam;
	private Inventory inv;
	private TravelSpeed travelSpeed;
    private Rations numConsumed;

	private double milesTravelled;
	private double longitudeHeading;
	private double latitudeHeading;

	private Location currLocation;
	private Location destination;

	private final Location[] allLocations;
	private ArrayList<Location> visitedLocations;
	private ArrayList<Location> notVisitedLocations;

	private Weather weather;

	private Calendar currCalendar;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

	// score?

	/**
	 * Constructor
	 */
	public GameState() {
		fam = new Family();
		inv = new Inventory();

		travelSpeed = TravelSpeed.RESTING;
        //numConsumed = new Rations("Filling", 1);
        numConsumed = Rations.FILLING;

		milesTravelled = 0;
		longitudeHeading = 0;
		latitudeHeading = 0;

		allLocations = new Location[] {
				new Settlement("Nogales", -110.9381, 31.3012),
				new Settlement("Tubac", -111.0459, 31.6126),
				new Settlement("Tucson", -110.9747, 32.2226),
				new Settlement("Picacho", -111.4996, 32.7139),
				new Settlement("Phoenix", -112.0740, 33.4484),
				new Settlement("Prescott", -112.4685, 34.5400),
				new Settlement("Flagstaff", -111.6513, 35.1983),
				new Location("Grand Canyon", -112.1401, 36.0544),
				new Settlement("Kanab", -112.5263, 37.0475)
						};
		
		currLocation = allLocations[0];
		destination = currLocation;

		visitedLocations = new ArrayList<Location>();
		notVisitedLocations = new ArrayList<Location>();
		
		// add all but Nogales to not visited locations
		for (int i = 1; i < allLocations.length; i++) {
			notVisitedLocations.add(allLocations[i]);
		}

		weather = new Weather(currLocation.getBaseTemperature());
		currCalendar = new GregorianCalendar();
		setTemperature();

	}

	public GameState(int month) {
		this();
		this.setCurrMonth(month);
	}

	// people methods
	public void addMember(String name) {
		Person member = new Person(name);
		fam.addMember(member);
	}
	public void setHealth(String name, int health) {
		fam.getMember(name).setHealth(health);
	}
	// might not be needed, do through event
	public void incHealth(String name){
        fam.getMember(name).incHealth();
    }
	public void decHealth(String name){
		fam.getMember(name).decHealth();
	}

    // public void incPartyHealth(int amount){
    //     fam.incAllHealth(amount);
    // }
	// might not be needed

	public int getHealth(String name) {
		return fam.getMember(name).getHealth(); 
	}
	
	public Person[] getFamilyList() {
		return fam.getFamilyList();
	}

	// might not be needed, do through event
	public void setDead(String name) {
		fam.getMember(name).setDead();
	}
	public void incDead(){
		fam.incDead();
	}
	// might not be needed

	public int getNumDead(){
		return fam.getNumDead();
	}
	

	public String getNames(){
		return fam.getNames();
	}

	public String getFamilyStatus(){
        //fam.calcFamHealth();
		return fam.getFamilyStatus();
	}

    public void calcFamHealth(){
        fam.calcFamHealth();
    }

    public void setFamilyStatus(String status){
        fam.setFamHealth(status);
    }

    public String checkDead(){
        return fam.checkDead();
    }

	// item methods
	public void addItem(Item item) {
		inv.addItem(item);
	}
	// might want to change to String name
	public void removeItem(Item item) {
		inv.removeItem(item);
	}

	public void setMoney(int amount) {
		inv.setMoney(amount);
	}

	public double getMoney() {
		return inv.getMoney();
	}

	public void changeMoney(double change) {
		inv.changeMoney(change);
	}

	public double getCost(String anItem) {
		return inv.getItem(anItem).getCost();
	}

	@Deprecated
	public void decrItem(String name) {
		inv.getItem(name).decQuantity();
	}
	@Deprecated
	public void decrItemBy(String name, int amt) {
		Item item = inv.getItem(name);
		for (int i = 0; i < amt; i++) {
			item.decQuantity();
		}
	}
	public void useItem(String name) {
		inv.getItem(name).decQuantity();
	}
	public void useItemMultipleTimes(String name, int amt) {
		Item item = inv.getItem(name);
		for (int i = 0; i < amt; i++) {

			// only use an item if you have some of it
			if (item.getQuantity() > 0) {
			item.decQuantity();
			}
		}
	}
	public void incrItem(String name) {
		inv.getItem(name).incQuantity();
	}
	public void incrItemBy(String name, int amt) {
		Item item = inv.getItem(name);
		for (int i = 0; i < amt; i++) {
			item.incQuantity();
		}
	}
	public Item getItem(String name) {
		return inv.getItem(name);
	}
	public String getItems(){
		return inv.getItems();
	}

	// travel methods
	public void setTravelSpeed(int speed) {
		travelSpeed = TravelSpeed.makeTravelSpeed(speed);
	}
	public void setTravelSpeed(String speedName) {
		travelSpeed = TravelSpeed.makeTravelSpeed(speedName);
	}

	public double getSpeed() {
		return travelSpeed.getSpeed();
	}
	public String getSpeedName() {
		return travelSpeed.getSpeedName();
	}

	// location methods
	public ArrayList<Location> getVisitedLocations(){
		return notVisitedLocations;
	}
	
	public ArrayList<Location> getNotVisitedLocations(){
		return notVisitedLocations;
	}
	
	public Location[] getAllLocations(){
		Location[] temp = new Location[allLocations.length + 1];
		int i = 0;
		for (i = 0; i < allLocations.length; i++) {
			temp[i] = allLocations[i];
		}
		temp[i] = currLocation;
		return temp;
	}

	public void setDestination(String name) {
		for (Location l : notVisitedLocations) {
			if (l.getName().equals(name)) {
				this.setHeading(l);
				destination = l;
				break;
			}
		}

		// if name not found, do nothing
	}

	/**
	 * sets the heading as magnitude 0.1
	 * 	total distance= sqrt(lat^2 + lon^2)
	 * 	scalar = total distance / coordinate distance to travel
	 * 	latHeading = lat / scalar
	 * 	lonHeading = lon / scalar
	 *
	 * @param l
	 */
	public void setHeading(Location l) {
		double tempLonHeading = l.getLongitude() - currLocation.getLongitude();
		double tempLatHeading = l.getLatitude() - currLocation.getLatitude();
		double totalDistance = Math.sqrt((tempLonHeading * tempLonHeading) + (tempLatHeading * tempLatHeading));
		double scalar = totalDistance / baseCoordinateDistanceToTravel;

		longitudeHeading = tempLonHeading / scalar;
		latitudeHeading = tempLatHeading / scalar;
	}

	/**
	 * needs to include weather modifiers for travel speed
	 *
	 * @return true if destination reached
	 */
	public boolean travelOneDay() {
		Location afterTravel = new Location("Wilderness",
				currLocation.getLongitude() + longitudeHeading * travelSpeed.getSpeed() * weather.getSpeedModifier(),
				currLocation.getLatitude() + latitudeHeading * travelSpeed.getSpeed() * weather.getSpeedModifier()
				);
		
		for (int i = 0; i < allLocations.length; i++) {
			if (allLocations[i].equals(currLocation)) {
				visitedLocations.add(currLocation);
				break;
			}
		}

		//if (destination.getLongitude() < afterTravel.getLongitude() || destination.getLatitude() < afterTravel.getLatitude()) {
		if (Math.min(currLocation.getLongitude(), afterTravel.getLongitude()) < destination.getLongitude() &&
				destination.getLongitude() < Math.max(currLocation.getLongitude(), afterTravel.getLongitude()) &&
				Math.min(currLocation.getLatitude(), afterTravel.getLatitude()) < destination.getLatitude() &&
				destination.getLatitude() < Math.max(currLocation.getLatitude(), afterTravel.getLatitude())) {

			milesTravelled += calcMiles(currLocation, destination);
			currLocation = destination;
			notVisitedLocations.remove(destination);
			setTemperature();
			return true;
		}
		else {
			milesTravelled += calcMiles(currLocation, afterTravel);
			currLocation = afterTravel;
			setTemperature();
			return false;
		}
	}

	public Location getCurrLocation() {
		return currLocation;
	}

	public Location getDestination() {
		return destination;
	}

	public double getDistanceToDestination() {
		return calcMiles(currLocation, destination);
	}

	public double getDistanceTravelled() {
		return milesTravelled;
	}

	public double calcMiles(Location from, Location to) {
		double tempLonHeading = to.getLongitude() - from.getLongitude();
		double tempLatHeading = to.getLatitude() - from.getLatitude();
		double totalDistance = Math.sqrt((tempLonHeading * tempLonHeading) + (tempLatHeading * tempLatHeading));
		return totalDistance * milesInOneDegree;
	}

	public boolean visitedHere(String name) {
		for (Location l : visitedLocations) {
			if (l.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}

	// weather methods
	public void setTemperature() {
		weather.setTemperature(currLocation.getBaseTemperature(), this.getCurrSeason());
	}

	public double getTemperature() {
		return weather.getTemperature();
	}

	public String getWeatherConditions() {
		return weather.getWeatherConditionString();
	}

	public Color getGround() {
        // String colorStr = weather.getGround(currLocation);
        // Color color;
        // try {
        //     Field field = Class.forName("java.scene.paint.Color").getField(colorStr);
        //     color = (Color)field.get(null);
        // } catch (Exception e) {
        //     color = null; // Not defined
        // }
        // return color;
		return weather.getGround(currLocation);

	}

	// calendar methods
	public void setCurrMonth(int month) {
		currCalendar.set(1880,  month, 1);
	}

	public int getCurrYear() {
		return currCalendar.get(Calendar.YEAR);
	}

	// get month as int
	public int getCurrMonthInt() {
		return currCalendar.get(Calendar.MONTH);
	}

	// get month as string
	public String getCurrMonthStr() {
		return currCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}

	// get date in month
	public int getcurrDate() {
		return currCalendar.get(Calendar.DATE);
	}

	// get date in format MMMM dd, yyyy
	//		where MMMM means the month as a full name
	public String getDateMMMMDDYYYY() {
		DateFormat d = new SimpleDateFormat("MMMM dd, yyyy");
		return d.format(currCalendar.getTime());
	}

	// get the season as a season object
	//
	//	might be unnecessary
	//		might replace for events/environment with just calculating straight from month and location
	//
	// calculating as
	//		Spring:
	//			March, April, May
	//		Summer:
	//			June, July, August
	//		Fall/Autumn:
	//			September, October, November
	//		Winter:
	//			December, January, February
	public Season getCurrSeason() {
		int currMonth = getCurrMonthInt();

		if (Season.SPRING.getStartMonth() >= currMonth && currMonth >= Season.SPRING.getEndMonth()) {
			return Season.SPRING;
		}
		else if (Season.SUMMER.getStartMonth() >= currMonth && currMonth >= Season.SUMMER.getEndMonth()) {
			return Season.SUMMER;
		}
		else if (Season.FALL.getStartMonth() >= currMonth && currMonth >= Season.FALL.getEndMonth()) {
			return Season.FALL;
		}
		else {
			return Season.WINTER;
		}
	}

	public void incrDay() {
		currCalendar.add(Calendar.DATE, 1);
	}

    public void setRations(int type){
        numConsumed = Rations.makeRation(type);
    }

    public void setRations(String name){
        numConsumed = Rations.makeRation(name);
    }

    public int getRationsConsumed() {
    	return numConsumed.getAmountConsumed();
    }

    public void incAllHealth(){
        fam.incAllHealth(numConsumed.getAmountConsumed());
    }

    public void decAllHealth(int amount){
        fam.decAllHealth(amount);
    }

    public void dailyHealthDecrement(){
        fam.dailyHealthDecrement();
    }

    public void consumeRations() {
        // System.out.println("here");
        // if(inv.getItem("food").getQuantity() >= numConsumed.getAmountConsumed() && inv.getItem("water").getQuantity() >= numConsumed.getAmountConsumed()){
        //     useItemMultipleTimes("food", numConsumed.getAmountConsumed());
        //     useItemMultipleTimes("water", numConsumed.getAmountConsumed());
        //     fam.incAllHealth(numConsumed.getAmountConsumed());
        //     fam.dailyHealthDecrement();
        // } else {
        //     inv.getItem("food").setQuantity(0);
        //     inv.getItem("water").setQuantity(0);
        // }

    }

    public void writeSavedGame(File filename){
        if(filename.exists()){
            filename.delete();
        }
        try{
            FileOutputStream fileOutStream = new FileOutputStream(filename);
            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
            objOutStream.writeObject(fam);
            objOutStream.writeObject(inv);
            objOutStream.writeObject(travelSpeed);
            objOutStream.writeObject(numConsumed);
            objOutStream.writeObject(milesTravelled);
            objOutStream.writeObject(longitudeHeading);
            objOutStream.writeObject(latitudeHeading);
            objOutStream.writeObject(currLocation);
            objOutStream.writeObject(destination);
            objOutStream.writeObject(visitedLocations);
            objOutStream.writeObject(notVisitedLocations);
            objOutStream.writeObject(weather);
            objOutStream.writeObject(currCalendar);
            objOutStream.flush();
            objOutStream.close();
        } catch(FileNotFoundException fnf){
            System.err.println("File not found");
        } catch(IOException ioe){
            System.err.println(ioe);
            System.out.println("here");
            ioe.printStackTrace();
        }
    }

    public void loadSavedGame(File filename){
        //create new board object from saved game data
        //set current board to new board
        //Family tempFam = new Family();
        //Inventory tempInv = new Inventory();
        //ArrayList<Location> tempVisited = new ArrayList<Location>();
        //ArrayList<Location> tempNotVisited = new ArrayList<Location>();

        try{
            FileInputStream fileInStream = new FileInputStream(filename);
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            // savedBoard = (int[][])objInStream.readObject();
            // board = savedBoard;
            fam = (Family)objInStream.readObject();
            inv = (Inventory)objInStream.readObject();
            travelSpeed = (TravelSpeed)objInStream.readObject();
            numConsumed = (Rations)objInStream.readObject();
            milesTravelled = (Double)objInStream.readObject();
            longitudeHeading = (Double)objInStream.readObject();
            latitudeHeading = (Double)objInStream.readObject();
            currLocation = (Location)objInStream.readObject();
            destination = (Location)objInStream.readObject();
            visitedLocations = (ArrayList<Location>)objInStream.readObject();
            notVisitedLocations = (ArrayList<Location>)objInStream.readObject();
            weather = (Weather)objInStream.readObject();
            currCalendar = (Calendar)objInStream.readObject();
        }catch(FileNotFoundException fnf){
            System.err.println("File not found.");
        }catch(ClassNotFoundException cnf){
            System.err.println("Class not found");
        }catch(IOException ioe){
            System.err.println(ioe);
            System.out.println("here");
            Thread.currentThread().getStackTrace();
        }

        for(int i = 0; i < 7;i ++){
            for(int j = 0; j < 6; j++){
                // Connect4MoveMessage mess = new Connect4MoveMessage();
                // if(board[i][j] == 0){
                //     mess.setColor(Color.WHITE);
                // } else if(board[i][j] == 1){
                //     mess.setColor(Color.YELLOW);
                // } else{
                //     mess.setColor(Color.RED);
                // }
                // mess.setRow(j);
                // mess.setColumn(i);
                // setChanged();
        		// notifyObservers(mess);
            }
        }
    }

}
