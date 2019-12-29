/**
 * GameState
 * Model
 * @author John Yang. Edited by Evan Kuo
 */

// Imports
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
	
	// Distance variables
	private final double baseCoordinateDistanceToTravel = 0.03;
	private final double milesInOneDegree = 69;								// 1 degree distance ~ 69 miles
	
	// Location variables
	private double milesTravelled;
	private double longitudeHeading;
	private double latitudeHeading;
	private Location currLocation;
	private Location destination;
	private final Location[] allLocations;
	private ArrayList<Location> visitedLocations;
	private ArrayList<Location> notVisitedLocations;

	// Family variables
	private Family fam;
	private Inventory inv;
	private TravelSpeed travelSpeed;
    private Rations numConsumed;
    
    // Weather and calendar
	private Weather weather;
	private Calendar currCalendar;

    //set serialVersionUID to 1 to make compiler happy
    private static final long serialVersionUID = 1;

    
    // Constructor
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

	// Creates a gamestate with a specified month
	public GameState(int month) {
		this();
		this.setCurrMonth(month);
	}

	/* -------- Family Functions -------- */
	
	// Adds a member to the family
	public void addMember(String name) {
		Person member = new Person(name);
		fam.addMember(member);
	}
	
	// Sets the health of a family member with the given name
	public void setHealth(String name, int health) {
		fam.getMember(name).setHealth(health);
	}
	
	// Increments the given family member's health
	public void incHealth(String name){
        fam.getMember(name).incHealth();
    }
	
	// Decrements the given family member's health
	public void decHealth(String name){
		fam.getMember(name).decHealth();
	}

	// Gets the health of the family member with the given name
	public int getHealth(String name) {
		return fam.getMember(name).getHealth(); 
	}
	
	// Gets the family members as a list of Person objects
	public Person[] getFamilyList() {
		return fam.getFamilyList();
	}

	// Sets the given family member as dead
	public void setDead(String name) {
		fam.getMember(name).setDead();
	}
	
	// Increments the number of dead members of the family object
	public void incDead(){
		fam.incDead();
	}

	// Returns the number of dead family members
	public int getNumDead(){
		return fam.getNumDead();
	}
	
	// Returns a string of the names of the family members
	public String getNames(){
		return fam.getNames();
	}

	// Gets the status of the family 
	public String getFamilyStatus(){
        //fam.calcFamHealth();
		return fam.getFamilyStatus();
	}

	// Sets the health of the family
    public void calcFamHealth(){
        fam.calcFamHealth();
    }

    // Sets the status of the family
    public void setFamilyStatus(String status){
        fam.setFamHealth(status);
    }

    // Returns a string of the names of the dead family members
    public String checkDead(){
        return fam.checkDead();
    }
    
    // Increments the family health if conditions are good.
    public void incAllHealth(){
        fam.incAllHealth(numConsumed.getAmountConsumed());
    }

    // Decrements the family health if conditions are bad.
    public void decAllHealth(int amount){
        fam.decAllHealth(amount);
    }

    // Decrement the family's health
    public void dailyHealthDecrement(){
        fam.dailyHealthDecrement();
    }
    
    /* -------- Inventory Functions -------- */

    // Adds the given item to the inventory
	public void addItem(Item item) {
		inv.addItem(item);
	}
	
	// Removes the given item from the player inventory
	public void removeItem(Item item) {
		inv.removeItem(item);
	}

	// Sets the money
	public void setMoney(int amount) {
		inv.setMoney(amount);
	}

	// Gets the amount of the money
	public double getMoney() {
		return inv.getMoney();
	}

	// Adds or removes the given amount of money from the user
	public void changeMoney(double change) {
		inv.changeMoney(change);
	}

	// Get the cost of the given item
	public double getCost(String anItem) {
		return inv.getItem(anItem).getCost();
	}

	// Removes the given item from the inventory
	public void useItem(String name) {
		inv.getItem(name).decQuantity();
	}
	
	// Uses a given object the given number of times
	public void useItemMultipleTimes(String name, int amt) {
		Item item = inv.getItem(name);
		for (int i = 0; i < amt; i++) {

			// only use an item if you have some of it
			if (item.getQuantity() > 0) {
			item.decQuantity();
			}
		}
	}
	
	// Adds one to the number of the given object we have
	public void incrItem(String name) {
		inv.getItem(name).incQuantity();
	}
	
	// Adds the given amount of the number of the given object
	public void incrItemBy(String name, int amt) {
		Item item = inv.getItem(name);
		for (int i = 0; i < amt; i++) {
			item.incQuantity();
		}
	}
	
	// Returns the object with the given string name
	public Item getItem(String name) {
		return inv.getItem(name);
	}
	
	// Returns a string of all of the items we have
	public String getItems(){
		return inv.getItems();
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

	// Sets the daily ration amount as an int
    public void setRations(int type){
        numConsumed = Rations.makeRation(type);
    }

    // Sets the daily ration amount by a string name (Plentiful, barebones, etc.
    public void setRations(String name){
        numConsumed = Rations.makeRation(name);
    }

    // Returns the amount of food the family has eaten
    public int getRationsConsumed() {
    	return numConsumed.getAmountConsumed();
    }
	
	/* -------- Traveling and Location Functions -------- */

	// Sets the family's travel speed with the given int
	public void setTravelSpeed(int speed) {
		travelSpeed = TravelSpeed.makeTravelSpeed(speed);
	}
	
	// Sets the family's travel speed with the given string (grueling, relaxed, etc)s
	public void setTravelSpeed(String speedName) {
		travelSpeed = TravelSpeed.makeTravelSpeed(speedName);
	}

	// Gets the current travel speed as an int
	public double getSpeed() {
		return travelSpeed.getSpeed();
	}
	
	// Gets the current travel speed as the string name
	public String getSpeedName() {
		return travelSpeed.getSpeedName();
	}

	// Get an arraylist of the visited locations
	public ArrayList<Location> getVisitedLocations(){
		return notVisitedLocations;
	}
	
	// Get an arraylist of the locations we haven't visited yet
	public ArrayList<Location> getNotVisitedLocations(){
		return notVisitedLocations;
	}
	
	// Gets all of the locations as a list
	public Location[] getAllLocations(){
		Location[] temp = new Location[allLocations.length + 1];
		int i = 0;
		for (i = 0; i < allLocations.length; i++) {
			temp[i] = allLocations[i];
		}
		temp[i] = currLocation;
		return temp;
	}

	// Sets the given name as the destination
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

	// Returns the current location
	public Location getCurrLocation() {
		return currLocation;
	}

	// Returns the destination
	public Location getDestination() {
		return destination;
	}

	// Returns the double distance to the next destination
	public double getDistanceToDestination() {
		return calcMiles(currLocation, destination);
	}

	// Returns the double distance we've traveled
	public double getDistanceTravelled() {
		return milesTravelled;
	}

	// Calculates the number of miles from current location to other destination
	public double calcMiles(Location from, Location to) {
		double tempLonHeading = to.getLongitude() - from.getLongitude();
		double tempLatHeading = to.getLatitude() - from.getLatitude();
		double totalDistance = Math.sqrt((tempLonHeading * tempLonHeading) + (tempLatHeading * tempLatHeading));
		return totalDistance * milesInOneDegree;
	}

	// Returns whether or not we've visited the given location
	public boolean visitedHere(String name) {
		for (Location l : visitedLocations) {
			if (l.getName().equals(name)) {
				return true;
			}
		}

		return false;
	}
	
	/* -------- Weather and Date functions -------- */

	// Sets the temperature based on the season
	public void setTemperature() {
		weather.setTemperature(currLocation.getBaseTemperature(), this.getCurrSeason());
	}

	// Gets the temperature
	public double getTemperature() {
		return weather.getTemperature();
	}

	// Gets the weather conditions (raining, snowing, etc)
	public String getWeatherConditions() {
		return weather.getWeatherConditionString();
	}

	// Gets the color of the ground (green if grassy, brown if bare, etc)
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

	// Sets the current month
	public void setCurrMonth(int month) {
		currCalendar.set(1880,  month, 1);
	}

	// Gets the current year
	public int getCurrYear() {
		return currCalendar.get(Calendar.YEAR);
	}

	// Get month as int
	public int getCurrMonthInt() {
		return currCalendar.get(Calendar.MONTH);
	}

	// Get month as string
	public String getCurrMonthStr() {
		return currCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}

	// Get date in month
	public int getcurrDate() {
		return currCalendar.get(Calendar.DATE);
	}
	
	

	// get date in format MMMM dd, yyyy
	// where MMMM means the month as a full name
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

	// Time passes by by one day
	public void incrDay() {
		currCalendar.add(Calendar.DATE, 1);
	}

    /* -------- Saving and Loading -------- */

    // Saves the game
    public void writeSavedGame(File filename){
    	
    	// If the file exists already, delete it.
        if(filename.exists()){
            filename.delete();
        }
        
        try {
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
        }
        
        catch (FileNotFoundException fnf) {
            System.err.println("File not found");
        }
        
        catch (IOException ioe) {
            System.err.println(ioe);
            System.out.println("here");
            ioe.printStackTrace();
        }
        
        catch (Exception e) {
        	// do nothing
        }
    }

    // Loads the game
    public void loadSavedGame(File filename){
    	
        try{
            FileInputStream fileInStream = new FileInputStream(filename);
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
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
        }
        
        catch(FileNotFoundException fnf){
            System.err.println("File not found.");
        }
        
        catch(ClassNotFoundException cnf){
            System.err.println("Class not found");
        }
        
        catch(IOException ioe){
            System.err.println(ioe);
            System.out.println("here");
            Thread.currentThread().getStackTrace();
        }
        
        catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
