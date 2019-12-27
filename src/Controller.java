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
		
		currGame = new GameState(Calendar.JUNE);

		// Let controller know about items
		currGame.addItem(oxen);
		currGame.addItem(ammo);
		currGame.addItem(food);
		currGame.addItem(blanket);
		currGame.addItem(water);

		this.chooseNearestDestination();
	}

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

	// Sets the current month to the month chosen
	public void setMonth(int month) {
		currGame.setCurrMonth(month);
	}

	// Gets the day the game is currently in
	public String getFullDate() {
		return currGame.getDateMMMMDDYYYY();
	}
 
	// Adds food to the player inventory
	public void addHuntingFood(int amount) {
		currGame.incrItemBy("food", amount);
	}

	// Uses a given amount of the specified item
	public void useItemAmount(String item, int amount) {
		currGame.useItemMultipleTimes(item, amount);
	}

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

	/**
	 *
	 * @return distance with 3 decimals
	 */
	public String nextLandmarkXMilesAwayString() {
		return String.format("%.3f", currGame.getDistanceToDestination());
	}

	/**
	 *
	 * rounds actual distance down
	 *
	 * @return
	 */
	@Deprecated
	public int totalMilesTraveled() {
		// returns an int saying how far we have traveled in total.
		return (int) Math.floor(currGame.getDistanceTravelled());
	}

	/**
	 *
	 * @return
	 */
	@Deprecated
	public double totalMilesTraveledDouble() {
		// returns an int saying how far we have traveled in total.
		return currGame.getDistanceTravelled();
	}

	/**
	 *
	 * @return distance with 3 decimals
	 */
	public String totalMilesTraveledString() {
		return String.format("%.3f", currGame.getDistanceTravelled());
	}

	/**
	 * returns true if distance to next landmark == 0
	 *
	 * @return
	 */
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

    public String checkDead(){
        // String dead = currGame.checkDead();
        // if(dead != ""){
        //
        // }
        return currGame.checkDead();
    }
    
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


	// Family getters and setters
	public void addFamilyMember(String name) {
		currGame.addMember(name);
	}

	// Set money
	public void setMoney(int money) {
		currGame.setMoney(money);
	}

	public double getMoney() {
		return currGame.getMoney();
	}

	public void changeMoney(int moneyLostorGained) {
		currGame.changeMoney(moneyLostorGained);
	}

	public double getCost(String anItem) {
		return currGame.getCost(anItem);
	}

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
		score += (int) Math.round(this.totalMilesTraveledDouble());
		score += (int) Math.round(this.getMoney());
		
		return score;
	}

    public void setPace(int pace){
        currGame.setTravelSpeed(pace);
    }

    public double getPace() {
    	return currGame.getSpeed();
    }
    public String getPaceName() {
    	return currGame.getSpeedName();
    }

    public void changeRations(int type){
        currGame.setRations(type);
    }

    public void saveGame(File filename){
        currGame.writeSavedGame(filename);
    }

    public void loadGame(File filename){
        currGame.loadSavedGame(filename);
    }
    
    public boolean getFamilyDead() {
    	if(currGame.getNumDead() >=5) {
    		return true;
    	}
    	return false;
    }
}
