/**
 * Controller
 * @author John Yang. Edited by Evan Kuo
 */

// Imports
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.io.File;
import javafx.scene.paint.Color;

public class Controller {
	
	// Generate a Gamestate object, which is the model of our MVC model
	GameState currGame;

	// Set up inventory
	Item oxen = new Item("oxen", 100, 0);
    Item water = new Item("water", .35, 0);
	Item ammo = new Item("ammo", .2, 0);
	Item food = new Item("food", .5, 0);
	Item blanket = new Item("blanket", 10, 0);


	// The constructor
	public Controller() {
		
		// Create the model
		currGame = new GameState(Calendar.JUNE);

		// Let controller know about items
		currGame.addItem(oxen);
		currGame.addItem(ammo);
		currGame.addItem(food);
		currGame.addItem(blanket);
		currGame.addItem(water);

		this.chooseNearestDestination();
	}
	

	/* -------- These are the destination/location methods -------- */
	
	// Get the current location as a string
	public String getCurrLocation() {
		return currGame.getCurrLocation().getName();
	}
	
	// Get the current location as an object
	public Location getCurrLocationObj() {
		return currGame.getCurrLocation();
	}
	
	// Get the current destination
	public Location getCurrDestination() {
		return currGame.getDestination();
	}
	
	// Get all of the locations in a list
	public Location[] getAllLocations(){
		return currGame.getAllLocations();
	}

	// NOT IMPLEMENTED YET
	public String[] getXNearestLocations(int i) {
		// search through notVisitedLocations for nearest i locations
		return null;
	}
	
	// Returns the number of miles from one location to another
	public double calcMiles(Location from, Location to) {
		return currGame.calcMiles(from, to);
	}
	
	// Gets the destinations that haven't been visited yet
	public String[] getDestinations() {
		ArrayList<Location> locations = currGame.getNotVisitedLocations();
		String[] destinations = new String[locations.size()];
		for (int i = 0; i < locations.size(); i++) {
			destinations[i] = locations.get(i).getName();
		}
		return destinations;
	}
	
	// Gets the locations visited so far
	public ArrayList<Location> getVisitedLocations() {
		return currGame.getVisitedLocations();
	}
	
	// Gets the nearest unvisited location
	public void chooseNearestDestination() {
		ArrayList<Location> locations = currGame.getNotVisitedLocations();

		if (locations.size() == 0) {
			return;
		}

		double bestDist = Double.MAX_VALUE;
		Location bestDestination = locations.get(0);
		for (Location l : locations) {
			double currDist = currGame.calcMiles(currGame.getCurrLocation(), l);
			if (currDist < bestDist) {
				bestDist = currDist;
				bestDestination = l;
			}
		}
		this.chooseDestination(bestDestination.getName());
	}

	// Choose the destination as a string
	public void chooseDestination(String name) {
		currGame.setDestination(name);
	}

	// Return the distance of the last landmark as a 3 decimal number string
	public String nextLandmarkXMilesAwayString() {
		return String.format("%.3f", currGame.getDistanceToDestination());
	}
	
	// Return the total miles traveled as a 3 decimal number string
	public String totalMilesTraveledString() {
		return String.format("%.3f", currGame.getDistanceTravelled());
	}

	
	/* -------- These are the date methods -------- */
	
	// Sets the current month to the month chosen
	public void setMonth(int month) {
		currGame.setCurrMonth(month);
	}

	// Gets the day the game is currently in
	public String getFullDate() {
		return currGame.getDateMMMMDDYYYY();
	}
	
	
	/* -------- These are the item methods -------- */
 
	// Adds food to the player inventory
	public void addHuntingFood(int amount) {
		currGame.incrItemBy("food", amount);
	}

	// Uses a given amount of the specified item
	public void useItemAmount(String item, int amount) {
		currGame.useItemMultipleTimes(item, amount);
	}
	
	// Set money
	public void setMoney(int money) {
		currGame.setMoney(money);
	}

	// Get amount of player money
	public double getMoney() {
		return currGame.getMoney();
	}

	// Add or remove the given amount of money to/from the player
	public void changeMoney(int moneyLostorGained) {
		currGame.changeMoney(moneyLostorGained);
	}
	
	// Returns the cost of an item
	public double getCost(String anItem) {
		return currGame.getCost(anItem);
	}

	// Returns how many of an item the player has
	public int getItemAmount(String anItem) {
		return currGame.getItem(anItem).getQuantity();
	}

	// Returns true if have enough money for transaction, false if not
	public boolean buyItem(String itemName, int amount) {
		Item anItem = currGame.getItem(itemName);
		double cost = anItem.getCost();
		cost = cost * amount;
		if (cost > currGame.getMoney()) {
			return false;
		}

		else {
			currGame.changeMoney(-cost);
			currGame.incrItemBy(itemName, amount);
			return true;
		}
	}

	// Buys an item from the town store, which adds the item to the player inventory, removes 
	// money from the player, and remove the items from the shop's stock
	public boolean buyItemTownStore(String itemName, int amount, Store store) {
		Item anItem = store.getItem(itemName);
		double cost = anItem.getCost();
		cost = cost * amount;

		// only let player buy if player has enough money and if store has enough
		if (cost > currGame.getMoney() || (amount > anItem.getQuantity())) {
			return false;
		}

		else {
			currGame.changeMoney(-cost);
			currGame.incrItemBy(itemName, amount);
			store.getItem(itemName).decQuanityMultipleTimes(amount);
			return true;
		}
	}
	
	
	/* -------- These are the status methods, such as the family health, the weather, ground, etc -------- */

	// Returns the health of the family
	public String getFamilyStatus() {
		return currGame.getFamilyStatus();
	}

	// Returns the current weather (snow, rain, sunny, etc) as a String
	public String getWeather() {
		return currGame.getWeatherConditions();
	}

	// Returns the state of the ground (grassy, snowy, bare, etc)
	public Color getGround() {
		return currGame.getGround();
	}

	// When the user presses space while traveling, do a single day
	public boolean doDayCycle() {
		//	get random events?
		// use one day's rations
        int dying_health_loss = 30;
        int starving_health_loss = 20;
        int thirsy_health_loss = 15;

		boolean destinationReached = currGame.travelOneDay();

		if (destinationReached) {
			this.chooseNearestDestination();
		}
		currGame.incrDay();
		
        useItemAmount("food", currGame.getRationsConsumed());
        useItemAmount("water", currGame.getRationsConsumed() / 5); 
        
        if (currGame.getItem("food").getQuantity() > 0 && currGame.getItem("water").getQuantity() > 0) {
        	currGame.incAllHealth();
        }
        currGame.dailyHealthDecrement();
        currGame.calcFamHealth();

        if(currGame.getItem("food").getQuantity() == 0 && currGame.getItem("water").getQuantity() == 0){
            currGame.setFamilyStatus("DYING!");
            currGame.decAllHealth(dying_health_loss);
        } else if(currGame.getItem("food").getQuantity() == 0){
            currGame.setFamilyStatus("STARVING!");
            currGame.decAllHealth(starving_health_loss);
        } else if(currGame.getItem("water").getQuantity() == 0){
            currGame.setFamilyStatus("THIRSTY!");
            currGame.decAllHealth(thirsy_health_loss);
        }
		return destinationReached;
	}

	// Checks if anyone has died
    public String checkDead(){
        return currGame.checkDead();
    }
    
    // If the family's health is low, then roll a dice to see if anyone will die
    public Person healthNotGood() {

        if (currGame.getFamilyStatus().equals("Fair")) {
        	Random randomNum = new Random();
        	int randomNumber = randomNum.nextInt(100); 

        	
        	if (randomNumber < 11) {
        		Person[] familyList = currGame.getFamilyList();
        		for (int i = familyList.length - 1; i >= 0; i-- ) {
        			if (!familyList[i].isDead()) {
        				familyList[i].setDead();
        				return familyList[i];
        			}
        		}
        	}
        }
        
        if (currGame.getFamilyStatus().equals("DYING!")) {
        	Random randomNum = new Random();
        	int randomNumber = randomNum.nextInt(100);
        	
        	if (randomNumber < 20) {
        		Person[] familyList = currGame.getFamilyList();
        		for (int i = familyList.length - 1; i >= 0; i-- ) {
        			if (!familyList[i].isDead()) {
        				familyList[i].setDead();
        				familyList[i].diedOf("Dysentery");
        				return familyList[i];
        			}
        		}
        	}
        }
        
        if (currGame.getFamilyStatus().equals("STARVING!")) {
        	Random randomNum = new Random();
        	int randomNumber = randomNum.nextInt(100);
        	
        	if (randomNumber < 10) {
        		Person[] familyList = currGame.getFamilyList();
        		for (int i = familyList.length - 1; i >= 0; i-- ) {
        			if (!familyList[i].isDead()) {
        				familyList[i].setDead();
        				familyList[i].diedOf("Starvation");
        				return familyList[i];
        			}
        		}
        	}
        }
        
        if (currGame.getFamilyStatus().equals("THIRSTY!")) {
        	Random randomNum = new Random();
        	int randomNumber = randomNum.nextInt(100);
        	
        	if (randomNumber < 15) {
        		Person[] familyList = currGame.getFamilyList();
        		for (int i = familyList.length - 1; i >= 0; i-- ) {
        			if (!familyList[i].isDead()) {
        				familyList[i].setDead();
        				familyList[i].diedOf("Thirst");
        				return familyList[i];
        			}
        		}
        	}
        }
        
        return null;
    }
    
    // Check if the entire family has died
    public boolean getFamilyDead() {
    	
    	if(currGame.getNumDead() >=5) {
    		return true;
    	}
    	
    	return false;
    }

	// Add a family member with the given name
	public void addFamilyMember(String name) {
		currGame.addMember(name);
	}

	// Sets the pace that we're traveling at
    public void setPace(int pace){
        currGame.setTravelSpeed(pace);
    }

    // Gets the pace we're traveling at
    public double getPace() {
    	return currGame.getSpeed();
    }
    
    // Get the name of the pace we're going at (grueling, relaxed, etc)
    public String getPaceName() {
    	return currGame.getSpeedName();
    }

    // Change the num of food everyone eats in a given day
    public void changeRations(int type){
        currGame.setRations(type);
    }
    
    
    /* -------- Ancillary functions such as saving and scoring -------- */

    // Saves the game
    public void saveGame(File filename){
        currGame.writeSavedGame(filename);
    }

    // loads a save
    public void loadGame(File filename){
        currGame.loadSavedGame(filename);
    }
    
	// Final score based on ending family health
	public int getScore() {
		
		int score = 0;
		String endingHealth = currGame.getFamilyStatus();
		
		if (this.getFamilyDead()) {
			score = 500;
		}
		
		else if (endingHealth.equals("Poor")) {
			score = 1000;
		}
		
		else if (endingHealth.equals("Fair")) {
			score = 2000;
		}
		
		else {
			score = 3000;
		}
		
		score += this.getVisitedLocations().size() * 100;
		//score += (int) Math.round(this.totalMilesTraveledDouble());
		score += (int) Math.round(this.getMoney());
		
		return score;
	}
}
