// Imports

// IO
import java.io.File;

// Data structures
import java.util.ArrayList;
import java.util.List;

// Animation
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;

// Application
import javafx.application.Application;

// Events and geometry
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

// Scene
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;

// Additional utils
import javafx.util.Duration;
import java.util.Optional;


// The view for the application. This contains main.
public class View extends Application{

	/* Various miscellaneous variables */
	Controller controller = new Controller(); 						// Set up controller
	MediaPlayer playSong; 											// used to help the song play
	static List<MenuClass> menuList = new ArrayList<MenuClass>(); 	// Add all menu booleans to this list
    File save_game_file = new File("saved_game.dat");		    	// For saving
	Person mainCharacter;											// Put all of the various people/inventory objects here
	static Color defaultColor = Color.WHITE;						// the color for the text
	
	
	/* The Screens */
	
	// These are the beginning screens
	MenuClass onRootMenu = new MenuClass(); 						// title screen
    MenuClass onMainMenu = new MenuClass(); 						// main menu
	MenuClass onNameMenu = new MenuClass(); 						// choose name screen
	MenuClass onJobMenu = new MenuClass(); 							// choose job screen
	MenuClass onPetMenu = new MenuClass(); 							// choose pet screen
	MenuClass onCompanionMenu = new MenuClass(); 					// choose adult companion screen
	MenuClass onChild1Menu = new MenuClass(); 						// choose child 1 screen
	MenuClass onChild2Menu = new MenuClass(); 						// choose child 2 screen
    MenuClass onChild3Menu = new MenuClass(); 						// choose child 3 screen
    MenuClass onSeasonMenu = new MenuClass(); 						// choose time of year screen
    MenuClass onShopIntroductionMenu = new MenuClass(); 			// Money & pre-shop screen
    MenuClass onBuyOxenMenu = new MenuClass(); 						// Buy Oxen screen
    MenuClass onBuyFoodMenu = new MenuClass(); 						// Buy Food screen
    MenuClass onBuyClothesMenu = new MenuClass(); 					// Buy Clothing screen
    MenuClass onBuyWheelsMenu = new MenuClass(); 					// Buy Wagon Wheels screen
    MenuClass onBuyAxlesMenu = new MenuClass(); 					// Buy Wagon Axles screen
	MenuClass onJobExplanationMenu = new MenuClass(); 				// Job explanation screen
    
    // These are the traveling screens
    MenuClass onTravelingMenu = new MenuClass(); 					// Traveling menu
    MenuClass onSuppliesMenu = new MenuClass(); 					// check supplies
    MenuClass onRationsMenu = new MenuClass(); 						// set rations
    MenuClass onMapMenu = new MenuClass();							// map menu class
    MenuClass onChangePaceMenu = new MenuClass(); 					// set pace
    MenuClass onWhileTravelingMenu = new MenuClass(); 				// Menu to size up situation while traveling. Different from at landmark
    MenuClass onSavedGameMenu = new MenuClass(); 					// save game
    
    // These are the city transition screens
    MenuClass onTubacTransitionMenu = new MenuClass(); 				// tubac screen
    MenuClass onTucsonTransitionMenu = new MenuClass();				// tucson screen
    MenuClass onPicachoTransitionMenu = new MenuClass();			// pichacho screen
    MenuClass onPhoenixTransitionMenu = new MenuClass();			// phoenix screen
    MenuClass onPrescottTransitionMenu = new MenuClass();			// prescott screen
    MenuClass onFlagstaffTransitionMenu = new MenuClass();			// flagstaff screen
    MenuClass onGrandCanyonTransitionMenu = new MenuClass();		// grand canyon screen
    MenuClass onKanabTransitionMenu = new MenuClass();				// kanab screen
    
    // These are the shopping screens
    MenuClass onCurrStoreMenu = new MenuClass();
    MenuClass onAllShoppingMenu = new MenuClass(); 					// the menu used for all shopping in towns
	MenuClass onBuyExplanationMenu = new MenuClass(); 				// buying explanation
	MenuClass onStartShopMenu = new MenuClass(); 					// first shop
    MenuClass onTubacShopMenu = new MenuClass(); 					// tubac shop
    MenuClass onTucsonShopMenu = new MenuClass();					// tucson shop
    MenuClass onPicachoShopMenu = new MenuClass();					// pichacho shop
    MenuClass onPhoenixShopMenu = new MenuClass();					// phoenix shop
    MenuClass onPrescottShopMenu = new MenuClass();					// prescott shop
    MenuClass onFlagstaffShopMenu = new MenuClass();				// flagstaff shop		
    MenuClass onGrandCanyonShopMenu = new MenuClass();				// grand canyon shop
	
	// These are the hunting screens
    MenuClass onHuntingMenu = new MenuClass(); 						// hunting game
    MenuClass onHuntingExplanationMenu = new MenuClass(); 			// hunting game explanation
    
    // These are the various game over screens
	MenuClass onDeathScreenMenu = new MenuClass();					// death
	MenuClass onStarvationMenu = new MenuClass();					// starvation
	MenuClass onThirstMenu = new MenuClass();						// thirst
	MenuClass onGameOverMenu = new MenuClass();						// game over

	
	/* Strings for storage and menu printing */
	
	// People and companions
	String nameString = "";
	String dogNameString = "";
	String travelingCompanionNameString = "";
    String companionNameString = "";
    String child1String = "";
    String child2String = "";
    String child3String = "";
    
    // Supplies
    String numOxenString = "";
    String poundsFoodString = "";
    String numClothingString = "";
    String numAmmoString = "";
    String gallonsWaterString = "";
    
    // Shop choices
    String tubacShopChoiceString = "";
    String tucsonShopChoiceString = "";
    String picachoShopChoiceString = "";
    String phoenixShopChoiceString = "";
    String prescottShopChoiceString = "";
    String flagstaffShopChoiceString = "";
    String grandCanyonShopChoiceString = "";
    String allShoppingChoiceString = "";
    String allShoppingChoiceNameString = "";
    
    // Menu strings
    String whileTravelingChoice = "";
    String changePace = "";
    String changeRations = "";
    
    // Miscellaneous
	String jobPicked = "";
	String startingMonth = "";
    String lastVisitedTown = "";
    String gameMode = "";
    String displayedDead = "";
    
    
	/* Textclasses for menu printing */
    
    // Person and companions
	TextClass nameText = new TextClass();
    TextClass companionNameText = new TextClass();
    TextClass child1Text = new TextClass();
    TextClass child2Text = new TextClass();
    TextClass child3Text = new TextClass();
	TextClass jobPickedText = new TextClass();
	TextClass dogNameText = new TextClass();
	TextClass travelingCompanionNameText = new TextClass();
	TextClass childCompanionNameText = new TextClass();
	
	// Supplies
    TextClass numOxenText = new TextClass();
    TextClass poundsFoodText = new TextClass();
    TextClass numClothingText = new TextClass();
    TextClass numAmmoText = new TextClass();
    TextClass gallonsWaterText = new TextClass();
    
    // Shops
    TextClass tucsonShopChoiceText = new TextClass();
    TextClass picachoShopChoiceText = new TextClass();
    TextClass phoenixShopChoiceText = new TextClass();
    TextClass prescottShopChoiceText = new TextClass();
    TextClass flagstaffShopChoiceText = new TextClass();
    TextClass grandCanyonShopChoiceText = new TextClass();
    TextClass tubacShopChoiceText = new TextClass();
    TextClass allShoppingChoiceText = new TextClass();
    TextClass allShoppingChoiceNameText = new TextClass();
    
    // Menus and Choices
    TextClass whileTravelingChoiceText = new TextClass();
    TextClass changeRationsText = new TextClass();
    TextClass changePaceText = new TextClass();
	TextClass startingMonthText = new TextClass();
    TextClass gameModeText = new TextClass();

    
    /* Make the stores */
	Store currStore = new Store();
	Store tubacStore = new Store();
	Store tucsonStore = new Store();
	Store picachoStore = new Store();
	Store phoenixStore = new Store();
	Store prescottStore = new Store();
	Store flagstaffStore = new Store();
	Store grandCanyonStore = new Store();


	/* Set up the StackPanes that will hold everything */
	
	// Beginning
	static StackPane root = new StackPane();
    StackPane mainMenu = new StackPane();
	StackPane onNameStack = new StackPane();
	StackPane onJobStack = new StackPane();
	StackPane onPetStack = new StackPane();
	StackPane onCompanionStack = new StackPane();
	StackPane onChild1Stack = new StackPane();
	StackPane onChild2Stack = new StackPane();
    StackPane onChild3Stack = new StackPane();
    StackPane onSeasonStack = new StackPane();
    StackPane onShopIntroductionStack = new StackPane();
    StackPane onBuyOxenStack = new StackPane();
    StackPane onBuyFoodStack = new StackPane();
    StackPane onBuyClothesStack = new StackPane();
    StackPane onBuyWheelsStack = new StackPane();
    StackPane onBuyAxlesStack = new StackPane();
    
    StackPane suppliesMenu = new StackPane();
    StackPane mapScreen = new StackPane();								
    StackPane tubacTransitionMenu = new StackPane();
    StackPane tubacShopMenu = new StackPane();
    StackPane tucsonTransitionMenu = new StackPane();
    StackPane picachoTransitionMenu = new StackPane();
    StackPane phoenixTransitionMenu = new StackPane();
    StackPane prescottTransitionMenu = new StackPane();
    StackPane flagstaffTransitionMenu = new StackPane();
    StackPane grandCanyonTransitionMenu = new StackPane();
    StackPane kanabTransitionMenu = new StackPane();
    StackPane tucsonShopMenu = new StackPane();
    StackPane picachoShopMenu = new StackPane();
    StackPane phoenixShopMenu = new StackPane();
    StackPane prescottShopMenu = new StackPane();
    StackPane flagstaffShopMenu = new StackPane();
    StackPane grandCanyonShopMenu = new StackPane();
    StackPane travelingMenu = new StackPane();
	StackPane jobExplanationScreen = new StackPane();
	StackPane rationsMenu = new StackPane();
	StackPane buyingExplanation = new StackPane();
	StackPane startShop = new StackPane();
	StackPane whileTravelingMenu = new StackPane();
	StackPane changePaceMenu = new StackPane();
    StackPane savedGameMenu = new StackPane();
    StackPane allShoppingMenu = new StackPane();
    StackPane currStoreMenu = new StackPane();
    StackPane huntingExplanationMenu = new StackPane();
    StackPane deathScreenMenu = new StackPane();
    StackPane gameOverMenu = new StackPane();
    StackPane starvationMenu = new StackPane();
    StackPane thirstMenu = new StackPane();
    
    
	/**
	 * This will make a Text object that is formatted as specified. Doesn't work for special text that needs
	 * special formatting, such as a title
	 * @param aString The String that we want to display on the menu
	 * @param aColor The color we want the text to be displayed in. Is defaultColor most of the time.
	 * @param menu The menu we want this text to be displayed on.
	 * @param position The position the text should be on the menu
	 * @return TheText We return theText when we need user input. Most of the time this can be ignored.
	 */
	public static Text makeText(String aString, Color aColor, StackPane menu, Pos position, int translateX, int translateY) {
		
		// Make the new text
		Text theText = new Text(aString);
		
		// Set its font and color
		theText.setFont(new Font("ArcadeClassic", 24));
		theText.setFill(aColor);
		
		// Add it to the menu and put it in the right spot
		menu.getChildren().add(theText);
		theText.setTranslateX(translateX);
		theText.setTranslateY(translateY);
		
		// Put it on the stackpane
		StackPane.setAlignment(theText, position);
		
		// Return the text we've created
		return theText;
	}

	/**
	 * This method allows for the quick switching of menus
	 * @param currMenu The menu that we want to switch to
	 */
	public static void switchMenus(MenuClass currMenu, StackPane currStack) {
		
		// Keeps track of whether or not this menu was already in the list. If it wasn't, then it is added.
		boolean wasntInIt = true;

		// Loop through the list and see if this menu is in the list.
		for (int i = 0; i < menuList.size(); i++) {
			
			// If it is in the list, set it to true
			if (currMenu.equals(menuList.get(i))) {
				currMenu.setTheBoolean(true);
				wasntInIt = false; // It was in it.
			}
			
			// If this isn't the menu we want to be displayed, then it must be false.
			else {
				menuList.get(i).setTheBoolean(false);
			}
		}

		// If the menu wasn't in the list already, then add it to the list and
		// mark it as true.
		if (wasntInIt) {
			currMenu.setTheBoolean(true);
			menuList.add(currMenu);
		}
		
		// Put the stack up front
		currStack.toFront();
	}

	/**
	 * This method will print out user input onto the screen and modifies the "theString" String as to save
	 * the user answer. This method should not be used for the user inputs where the user just types 1 number
	 * @param keyEvent The keyEvent that is recording the user input
	 * @param theString The String that holds the user input
	 * @param theText A TextClass object that is used solely to make this method work
	 * @param menu The menu the outputed text is to be written on
	 * @param position Where on the menu the outputed text is to be written
	 * @return theString theString is returned so that it can be saved.
	 */
	public String printUserInput(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {
		
		// If the user hits any character other than backspace
		if (!keyEvent.getCharacter().equals("\b")) {
			theString = theString + keyEvent.getCharacter();
		}

		// If the user does hit backspace.
		else {
			if (theString.length() > 0) {
				theString = theString.substring(0, theString.length() - 1);
			}
		}

		// If this is the 1st time the user has input something.
		if (theText.getText() == null) {
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}
		
		// If not the 1st time.
		else {
			menu.getChildren().remove(theText.getText());
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}

		// Remove whitespace
		theString.trim();
		
		// Return it
		return theString;
	}

	public String printUserInputNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If the user pressed 1, 2, 3, or 4
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") || keyEvent.getCharacter().equals("4"))
		   && theString.length() == 0) {
			theString = keyEvent.getCharacter();
		}
		
		// If the user pressed backspace
		else if (keyEvent.getCharacter().equals("\b") && theString.length() > 0) {
			theString = "";
		}

		// If this is the 1st time the user has input something.
		if (theText.getText() == null) {
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}
		
		// If not the 1st time.
		else {
			menu.getChildren().remove(theText.getText());
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}

		// remove possible whitespace
		theString.trim();
		
		// Return
		return theString;
	}

	public String printUserInputStoreNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If the user put 1, 2, 3, or 4
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") || keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5"))
		   && theString.length() == 0) {
			theString = keyEvent.getCharacter();
		}
		
		// If the user pressed backspace
		else if (keyEvent.getCharacter().equals("\b") && theString.length() > 0) {
			theString = "";
		}

		// If this is the 1st time the user has input something.
		if (theText.getText() == null) {
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}
		// If not the 1st time.
		else {
			menu.getChildren().remove(theText.getText());
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}

		// remove possible whitespace
		theString.trim();
		
		// Return it
		return theString;
	}


	public String printUserInputBigNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If user inputs any number
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") ||
			 keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5") || keyEvent.getCharacter().equals("6") ||
			 keyEvent.getCharacter().equals("7") || keyEvent.getCharacter().equals("8") || keyEvent.getCharacter().equals("9") ||
			 keyEvent.getCharacter().equals("0"))) {

			// add num
			theString = theString + keyEvent.getCharacter();
		}
		
		// If the user pressed backspace
		else if (keyEvent.getCharacter().equals("\b") && theString.length() > 0) {
			theString = "";
		}

		// If this is the 1st time the user has input something.
		if (theText.getText() == null) {
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}
		
		// If not the 1st time.
		else {
			menu.getChildren().remove(theText.getText());
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}

		// remove possible whitespace
		theString.trim();
		
		// Return it
		return theString;
	}

	public String printUserInputMediumNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If user inputs a number other than 9
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") ||
			 keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5") || keyEvent.getCharacter().equals("6") ||
			 keyEvent.getCharacter().equals("7") || keyEvent.getCharacter().equals("8")) && theString.length() == 0) {

			// add num
			theString = keyEvent.getCharacter();
		}
		
		// If the user presses backspace
		else if (keyEvent.getCharacter().equals("\b") && theString.length() > 0) {
			theString = "";
		}

		// If this is the 1st time the user has input something.
		if (theText.getText() == null) {
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}
		
		// If not the 1st time.
		else {
			menu.getChildren().remove(theText.getText());
			theText.setText(makeText(theString, defaultColor, menu, position, 0, 0));
		}

		// remove possible whitespace
		theString.trim();
		
		// Return it
		return theString;
	}

	
	// Create a screen with the specified background and then puts it on the stackpane
	public void buildMenu(StackPane root, StackPane menu, Color backgroundColor) {
		// Make the background with the given colors
		menu.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
		// Add it to the root
		root.getChildren().add(menu);
	}
	
	
	// Builds the map
	public void buildMapScreen(StackPane root) {
		
		mapScreen.getChildren().clear();
		root.getChildren().remove(mapScreen);
		buildMenu(root, mapScreen, Color.BLACK);
		
		StackPane mapSplitter = new StackPane();
		
		StackPane mapDisplay =  new StackPane();
		mapDisplay.setPrefHeight(root.getHeight());
		mapDisplay.setPrefWidth(root.getWidth());
		StackPane.setAlignment(mapDisplay, Pos.TOP_LEFT);
		mapDisplay.setTranslateX(0);
		mapDisplay.setTranslateY(0);
		putScaledImage("images/arizona-outline-rubber-stamp_grande.png", mapDisplay, Pos.CENTER_LEFT, 1500, (int) root.getHeight() - 140, 80, 10);
		
		StackPane locationInfo = new StackPane();
		locationInfo.setId("locationInfo");
		locationInfo.setPrefHeight(root.getHeight());
		locationInfo.setMaxWidth(root.getWidth() / 4);
		StackPane.setAlignment(locationInfo, Pos.TOP_RIGHT);
		locationInfo.setTranslateX(0);
		locationInfo.setTranslateY(0);
		locationInfo.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		
		Location[] allLocations = controller.getAllLocations();
		int mapScaleX = 100;
		int mapScaleY = 60;
		int mapShiftX = 114;
		int mapShiftY = 38;
		int mapLocationSize = 30;
		for (Location l : allLocations) {
			Rectangle location;
			if (l.equals(controller.getCurrLocationObj())) {
				location = new Rectangle(mapLocationSize, mapLocationSize, Color.GREEN);
			}
			else if (l.equals(controller.getCurrDestination())){
				location = new Rectangle(mapLocationSize, mapLocationSize, Color.YELLOW);
			}
			else if (l.getName().equals("Kanab")){
				location = new Rectangle(mapLocationSize, mapLocationSize, Color.RED);
			}
			else {
				location = new Rectangle(mapLocationSize, mapLocationSize, Color.WHITE);
			}

			location.setX((l.getLongitude() + mapShiftX) * mapScaleX);
			location.setY((-l.getLatitude() + mapShiftY) * mapScaleY);
			location.setTranslateX(location.getX());
			location.setTranslateY(location.getY());
			StackPane.setAlignment(location, Pos.TOP_LEFT);
			
			location.setOnMouseEntered(
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							locationInfo.getChildren().clear();
							
							if (l.equals(controller.getCurrLocationObj())) {
								makeText("Current Location:", defaultColor, locationInfo, Pos.TOP_CENTER, 0, 0);
							}
							else if (l.equals(controller.getCurrDestination())){
								makeText("Current Destination:", defaultColor, locationInfo, Pos.TOP_CENTER, 0, 0);
							}
							else if (l.getName().equals("Kanab")){
								makeText("Final Destination:", defaultColor, locationInfo, Pos.TOP_CENTER, 0, 0);
							}
							
							makeText(l.getName(), defaultColor, locationInfo, Pos.TOP_CENTER, 0, 50);
							
							makeText(String.format("Distance\nas the crow flies:\n\t%.3f miles", controller.calcMiles(controller.getCurrLocationObj(), l)), defaultColor,locationInfo,Pos.TOP_CENTER, 0, 100).setWrappingWidth((root.getWidth() / 4));
							makeText(String.format("Region:\n\t%s", l.getRegion().getName()), defaultColor, locationInfo,Pos.TOP_CENTER, 0, 175).setWrappingWidth((root.getWidth() / 4));
							makeText(String.format("Fall Temperature:\n\t%.0f F", l.getRegion().getBaseTemperature()), defaultColor, locationInfo, Pos.TOP_CENTER, 0, 225).setWrappingWidth((root.getWidth() / 4));
							
						}
					}
			);
			
			location.setOnMouseExited(
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							initMapLocationInfo(locationInfo);
						}
					}
			);
			
			mapDisplay.getChildren().add(location);
			initMapLocationInfo(locationInfo);
		}
		
		mapSplitter.getChildren().addAll(mapDisplay, locationInfo);
		mapScreen.getChildren().add(mapSplitter);
		
		makeText("Map of Arizona", defaultColor, mapScreen, Pos.TOP_CENTER, 0, 0);
    	makeText("Press ENTER to continue", defaultColor, mapScreen, Pos.BOTTOM_CENTER, 0, 0);
	}
	
	// Used for map text display
	private void initMapLocationInfo(StackPane sp) {
		sp.getChildren().clear();
		makeText("Mouse Over", defaultColor, sp, Pos.CENTER, 0, -50);
		makeText("a Location", defaultColor, sp, Pos.CENTER, 0, -25);
		makeText("for", defaultColor, sp, Pos.CENTER, 0, 0);
		makeText("More", defaultColor, sp, Pos.CENTER, 0, 25);
		makeText("Information", defaultColor, sp, Pos.CENTER, 0, 50);
	}

	// Puts images
	public static void putScaledImage(String image, StackPane menu, Pos position, int width, int height, int translateX, int translateY) {
		Image theImage = new Image(new File(image).toURI().toString(), width, height, true, false);
		ImageView theImageView = new ImageView(theImage);
		StackPane.setAlignment(theImageView, position);
		theImageView.setTranslateX(translateX);
		theImageView.setTranslateY(translateY);
		menu.getChildren().add(theImageView);
	}

	// Redraws the traveling menu every time the user presses space or goes back to the traveling menu
	public void redrawTravelingMenu(StackPane root, StackPane travelingMenu) {

        //boolean validBuy = controller.buyItem("water", Integer.valueOf(gallonsWater));
		whileTravelingChoice = "";
		switchMenus(onTravelingMenu, travelingMenu);

		travelingMenu.getChildren().clear();


		//Courtesy of ApologeticbyNature on Spriter's Resource from the Original Apple 2 Oregon Trail copyright Don Rawitsch, Bill Heinemann, and Paul Dillenberger
		//final Image IMAGE = new Image("https://www.spriters-resource.com/resources/sheets/24/26478.gif");
		final Image IMAGE = new Image(new File("images/OregonTrailImages26478.gif").toURI().toString());
		final int COLUMNS  =   1;
		final int COUNT    =  2;
		final int OFFSET_X =  0;
		final int OFFSET_Y =  0;
		final int WIDTH    = 96;
		final int HEIGHT   = 29;

	    final ImageView imageView = new ImageView(IMAGE);
	    imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
	    final Animation animation = new WagonAnimation(imageView, Duration.millis(1000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);
	    animation.setCycleCount(Animation.INDEFINITE);
	    animation.play();
	    Rectangle sky = new Rectangle(1000,300,Color.SKYBLUE);
	    Rectangle ground = new Rectangle(1000,500,Color.DARKGOLDENROD);
		sky.setY(-100);
		ground.setY(200);
	    ImageView layer3 = new ImageView(new Image("Layer3.png",1000,100,false,false));

	    ImageView layer3a = new ImageView(new Image("Layer3.png",1000,100,false,false));
		Pane background = new Pane();

		background.getChildren().addAll(ground,sky,layer3,layer3a);

		layer3a.setX(-1000);
		layer3a.setY(100);
		layer3.setY(100);
		ground.setFill(controller.getGround());
		// change mountain color
		ColorAdjust groundColorEffect = changeMountainColor(controller.getGround());
		layer3a.setEffect(groundColorEffect);
		layer3.setEffect(groundColorEffect);

		TranslateTransition tt3 = new TranslateTransition(Duration.millis(5000),layer3);
		tt3.setByX(1000f);
		tt3.setCycleCount(Animation.INDEFINITE);
		tt3.setInterpolator(Interpolator.LINEAR);
		tt3.play();

		TranslateTransition tr3 = new TranslateTransition(Duration.millis(5000),layer3a);
		tr3.setByX(1000f);

		tr3.setCycleCount(Animation.INDEFINITE);
		tr3.setInterpolator(Interpolator.LINEAR);
		tr3.play();
		travelingMenu.getChildren().add(background);
	    travelingMenu.getChildren().add(imageView);

	    makeText("Travel Speed - " + controller.getPaceName(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -200);
	    makeText("Date - " + controller.getFullDate(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -175);
	    makeText("Weather - " + controller.getWeather(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -150);
	    makeText("Health - " + controller.getFamilyStatus(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -125);
	    makeText("Food - " + controller.getItemAmount("food"), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -100);
        makeText("Water - " + controller.getItemAmount("water"), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -75);
	    makeText("Next Landmark - " + controller.nextLandmarkXMilesAwayString(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -50);
	    makeText("Miles Traveled - " + controller.totalMilesTraveledString(), defaultColor, travelingMenu, Pos.BOTTOM_CENTER, 0, -25);
	    if (controller.getPace() == 0) {
	    	makeText("Press Space  to  Rest", defaultColor, travelingMenu, Pos.TOP_CENTER, 0, 0);
	    }
	    else {
	    	makeText("Press Space  to  Travel", defaultColor, travelingMenu, Pos.TOP_CENTER, 0, 0);
	    }
	    makeText("Press Enter to size up the situation", defaultColor, travelingMenu, Pos.TOP_CENTER, 0, 50);
	}

	// Buys items at shops
	public void buyAtShop(Controller controller, String item, int amount, Store store, StackPane menu, MenuClass menuClass, StackPane root) {

    	boolean validBuy = controller.buyItemTownStore(item, amount, store);

    	// only continue if buy is valid
    	if (validBuy) {
	    	allShoppingChoiceString = "";
	        switchMenus(menuClass, menu);
    	}
	}

	// Display shop
	public static void shoppingAtShop(MenuClass menuClass, StackPane menu, Store store, String item, StackPane root, Controller controller, String image) {
		switchMenus(menuClass, menu);
		menu.getChildren().clear();
		makeText("I have " + store.getItem(item).getQuantity() + " " + item + "in stock.", defaultColor, menu, Pos.TOP_CENTER, 0, 0);
		makeText("Each " +  item + " costs " + store.getItem(item).getCost() + " dollars.", defaultColor, menu, Pos.TOP_CENTER, 0, 25);
		makeText("You have " + controller.getItemAmount(item) + " " + item + " in your inventory.", defaultColor, menu, Pos.TOP_CENTER, 0, 75);
		makeText("You have " + String.format("%.2f", controller.getMoney()) + " dollars to spend.", defaultColor, menu, Pos.TOP_CENTER, 0, 100);
		makeText("Choose how many to buy.", defaultColor, menu, Pos.TOP_CENTER, 0, 150);
		putScaledImage(image, menu, Pos.CENTER, 150, 150, 0, 0);
	}

	// Helper function for shop
	public static void reduceShoppingCode(MenuClass onAllShoppingMenu, StackPane allShoppingMenu, Store store, String item, StackPane root, Controller controller, String image, String allShoppingChoiceNameString, Store currStore, MenuClass onCurrStoreMenu, MenuClass onShopMenu, StackPane currStoreMenu, StackPane storeMenu) {
		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, store, item, root, controller, image);
		allShoppingChoiceNameString = item;
		currStore = store;
		onCurrStoreMenu = onShopMenu;
		currStoreMenu = storeMenu;
	}

	// Used to make the mountains change color with the season
	public ColorAdjust changeMountainColor(Color c) {
		Image mountain =  new Image("Layer3.png",1000,100,false,false);
		PixelReader pr = mountain.getPixelReader();
		Color actualMountainColor = pr.getColor((int) mountain.getWidth() - 1, (int) mountain.getHeight() - 1);
		Color groundColor = controller.getGround();
		ColorAdjust groundColorEffect = new ColorAdjust(
				(groundColor.getHue() - actualMountainColor.getHue()) / 360,
				groundColor.getSaturation() - actualMountainColor.getSaturation(),
				groundColor.getBrightness() - actualMountainColor.getBrightness(),
				0.0);

		return groundColorEffect;
	}
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Setup canvas and gc to draw the main menu background image
		Canvas canvas = new Canvas(1000,500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		mainMenu.getChildren().add(canvas);
        Image trail = new Image(new File("images/oregonTrailBackground.jpg").toURI().toString(), 1000, 500, false, false);
		gc.drawImage(trail, 0, 0);
		
		// Setup scene and show it.
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/fontstyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Arizona Trail");
		primaryStage.show();
		
		// Set up song and play it.
		try {
			// Default uses mp3
			Media titleSong = new Media(new File("music/titletheme.mp3").toURI().toString());
			playSong = new MediaPlayer(titleSong);
			playSong.setCycleCount(MediaPlayer.INDEFINITE);
			playSong.setVolume(0.35);
			playSong.play();
		}
		catch (Exception e) {
			// Use wav if mp3 doesn't work
			Media titleSong = new Media(new File("music/titletheme.wav").toURI().toString());
			playSong = new MediaPlayer(titleSong);
			playSong.setCycleCount(MediaPlayer.INDEFINITE);
			playSong.setVolume(0.35);
			playSong.play();
		}
		
        // Create title text
		Text titleText = new Text("The  Arizona  Trail");
		titleText.setFont(new Font("ArcadeClassic", 100));
		StackPane.setAlignment(titleText, Pos.TOP_CENTER);
		mainMenu.getChildren().add(titleText);
		
        // Create text
		makeText("Press Space To Continue!", Color.BLACK, mainMenu, Pos.CENTER, 0, 0);
        makeText("Press Enter to Load Previous Save",  Color.BLACK, mainMenu, Pos.CENTER, 0, 35);
        
        // Build the menus
        buildMenu(root, mainMenu, Color.BLACK);
		buildMenu(root, onNameStack, Color.BLACK);
		buildMenu(root, onJobStack, Color.BLACK);
		buildMenu(root, onPetStack, Color.BLACK);
		buildMenu(root, onCompanionStack, Color.BLACK);
		buildMenu(root, onChild1Stack, Color.BLACK);
		buildMenu(root, onChild2Stack, Color.BLACK);
        buildMenu(root, onChild3Stack, Color.BLACK);
        buildMenu(root, onSeasonStack, Color.BLACK);
        buildMenu(root, onShopIntroductionStack, Color.BLACK);
        buildMenu(root, onBuyOxenStack, Color.BLACK);
        buildMenu(root, onBuyFoodStack, Color.BLACK);
        buildMenu(root, onBuyClothesStack, Color.BLACK);
        buildMenu(root, onBuyWheelsStack, Color.BLACK);
        buildMenu(root, onBuyAxlesStack, Color.BLACK);
		buildMenu(root, jobExplanationScreen, Color.BLACK);
		buildMenu(root, buyingExplanation, Color.BLACK);
		buildMenu(root, startShop, Color.BLACK);
		buildMenu(root, travelingMenu, Color.BLACK);
		buildMenu(root, whileTravelingMenu, Color.BLACK);
		buildMenu(root, suppliesMenu, Color.BLACK);
		buildMenu(root, rationsMenu, Color.BLACK);
		buildMenu(root, changePaceMenu, Color.BLACK);
		buildMenu(root, tubacTransitionMenu, Color.BLACK);
		buildMenu(root, tubacShopMenu, Color.BLACK);
        buildMenu(root, savedGameMenu, Color.BLACK);
        buildMenu(root, allShoppingMenu, Color.BLACK);
        buildMenu(root, tucsonTransitionMenu, Color.BLACK);
        buildMenu(root, picachoTransitionMenu, Color.BLACK);
        buildMenu(root, phoenixTransitionMenu, Color.BLACK);
        buildMenu(root, prescottTransitionMenu, Color.BLACK);
        buildMenu(root, flagstaffTransitionMenu, Color.BLACK);
        buildMenu(root, grandCanyonTransitionMenu, Color.BLACK);
        buildMenu(root, kanabTransitionMenu, Color.BLACK);
        buildMenu(root, tucsonShopMenu, Color.BLACK);
        buildMenu(root, picachoShopMenu, Color.BLACK);
        buildMenu(root, phoenixShopMenu, Color.BLACK);
        buildMenu(root, prescottShopMenu, Color.BLACK);
        buildMenu(root, flagstaffShopMenu, Color.BLACK);
        buildMenu(root, grandCanyonShopMenu, Color.BLACK);
        buildMenu(root, huntingExplanationMenu, Color.BLACK);
        buildMenu(root, deathScreenMenu, Color.BLACK);
        buildMenu(root, gameOverMenu, Color.BLACK);
        buildMenu(root, starvationMenu, Color.BLACK);
        buildMenu(root, thirstMenu, Color.BLACK);
        
		// Go to main menu
		switchMenus(onMainMenu, mainMenu);
		
		// User presses Space
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			
		    public void handle(KeyEvent keyEvent) {
		    	
		    	// When the user presses space
		        if (keyEvent.getCode() == KeyCode.SPACE)  {

		        	// Main menu -> name screen
		        	if (onMainMenu.getTheBoolean()) {
		        		
			        	// Make some intro text
			        	Text startText = makeText("On the Arizona Trail you'll travel from the Mexican border to Utah!", defaultColor, onNameStack, Pos.TOP_CENTER, 0, 0);
	
			        	// Add picture
			        	putScaledImage("images/thMRQA2QMB.jpg", onNameStack, Pos.CENTER, 800, 300, 0, 0);
	
			        	// Ask the user for a name. 
			        	Text askForNameText = makeText("What's your name?", defaultColor, onNameStack, Pos.CENTER, 0, 200);
	
			        	// Have this menu be displayed at the top.
				    	switchMenus(onNameMenu, onNameStack);
				    	nameString = "";
		        	}

		        	// When the user presses space, if they're traveling then check if someone should die
		        	else if (onTravelingMenu.getTheBoolean()) {
		        		
		        		// see if someone should die
		        		Person person = controller.healthNotGood();

		        		// If we have people
		        		if (person != null) {
		        			
		        			// Sick
		        			if (person.getCauseOfDeath().equals("Dysentery")) {
		        				switchMenus(onDeathScreenMenu, deathScreenMenu);
		        				deathScreenMenu.getChildren().clear();
		        				makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 0);
		        				makeText("They died of dysentry.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
		        				putScaledImage("images/ghost.jpg", deathScreenMenu, Pos.CENTER, 300, 300, 0, 0);
		        			}
		        			
		        			// Thirst
		        			else if (person.getCauseOfDeath().equals("Thirst")) {
			        			switchMenus(onThirstMenu, thirstMenu);
			        			thirstMenu.getChildren().clear();
			        			makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, thirstMenu, Pos.TOP_CENTER, 0, 0);
			        			makeText("They died of thirst.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
			        			putScaledImage("images/skeleton.gif", thirstMenu, Pos.CENTER, 300, 300, 0, 0);
		        			}
		        			
		        			// Starvation
		        			else if (person.getCauseOfDeath().equals("Starvation")) {
			        			switchMenus(onStarvationMenu, starvationMenu);
			        			starvationMenu.getChildren().clear();
			        			makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, starvationMenu, Pos.TOP_CENTER, 0, 0);
			        			makeText("They died of starvation.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
			        			putScaledImage("images/dry.jpg", starvationMenu, Pos.CENTER, 300, 300, 0, 0);
		        			}
		        		}
		        		
		        		// If nobody is dying, then do the day cycle
		        		else if (controller.doDayCycle()) {
		        			
		        			// Check if we haven't gone to any towns yet
		        			if (lastVisitedTown.equals("")) {
			        			switchMenus(onTubacTransitionMenu, tubacTransitionMenu);
			        			makeText("You have reached Tubac!", defaultColor, tubacTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/Tubac_Church.jpg", tubacTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Tubac";
		        			}

		        			// Last town was Tubac
		        			else if (lastVisitedTown.equals("Tubac")) {
			        			switchMenus(onTucsonTransitionMenu, tucsonTransitionMenu);
			        			makeText("You have reached Tucson!", defaultColor, tucsonTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/tucson.jpg", tucsonTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Tucson";
		        			}

		        			// Last town was Tucson
		        			else if (lastVisitedTown.equals("Tucson")) {
			        			switchMenus(onPicachoTransitionMenu, picachoTransitionMenu);
			        			makeText("You have reached Picacho!", defaultColor, picachoTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/picacho.jpg", picachoTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Picacho";
		        			}

		        			// Last town was Pichacho
		        			else if (lastVisitedTown.equals("Picacho")) {
		        				switchMenus(onPhoenixTransitionMenu, phoenixTransitionMenu);
			        			makeText("You have reached Phoenix!", defaultColor, phoenixTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/phoenix.jpg", phoenixTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Phoenix";
		        			}

		        			// Last town was Phoenix
		        			else if (lastVisitedTown.equals("Phoenix")) {
		        				switchMenus(onPrescottTransitionMenu, prescottTransitionMenu);
			        			makeText("You have reached Prescott!", defaultColor, prescottTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/prescott.jpg", prescottTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Prescott";
		        			}

		        			// Last town was Prescott
		        			else if (lastVisitedTown.equals("Prescott")) {
		        				switchMenus(onFlagstaffTransitionMenu, flagstaffTransitionMenu);
			        			makeText("You have reached Flagstaff!", defaultColor, flagstaffTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/flagstaff.jpg", flagstaffTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Flagstaff";
		        			}

		        			// Last town was Flagstaff
		        			else if (lastVisitedTown.equals("Flagstaff")) {
		        				switchMenus(onGrandCanyonTransitionMenu, grandCanyonTransitionMenu);
			        			makeText("You have reached the Grand Canyon!", defaultColor, grandCanyonTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/grandcanyon.jpg", grandCanyonTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			lastVisitedTown = "Grand Canyon";
		        			}

		        			// Last town was Grand Canyon
		        			else if (lastVisitedTown.equals("Grand Canyon")) {
		        				switchMenus(onKanabTransitionMenu, kanabTransitionMenu);
			        			makeText("You have reached Kanab. You got to Utah! Game Over! You Won!", defaultColor, kanabTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/kanab.jpg", kanabTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			makeText("You got " + controller.getScore() + " points!", defaultColor, kanabTransitionMenu, Pos.BOTTOM_CENTER, 0, 0);
			        			lastVisitedTown = "Kanab";
		        			}
		        		}

		        		// Else, redraw the traveling menu
		        		else {
			        		redrawTravelingMenu(root, travelingMenu);
		        		}
		        		
		        	}
		        }
		    }
		});

		// If the user presses Backspace
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			
		    public void handle(KeyEvent keyEvent) {
		    	
		    	// If the user was on the supplies menu
		        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
		        	if (onSuppliesMenu.getTheBoolean()) {
		        		redrawTravelingMenu(root, travelingMenu);
		        	}
		        }
		    }
		});


		// User presses enter
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				
				if (keyEvent.getCode() == KeyCode.ENTER) {
					
					// Gameover -> mainMenu
					if (onGameOverMenu.getTheBoolean()) {
						switchMenus(onMainMenu, mainMenu);
					}
					
					// name menu -> job menu
					else if (onNameMenu.getTheBoolean()) {

						// Add main character to family
						controller.addFamilyMember(nameString);

						// Switch to menu 3 and set it up
						jobPicked = "";
						switchMenus(onJobMenu, onJobStack);
						onJobStack.getChildren().clear();
						Text whatJobText = makeText(nameString + ", what job do you have? Pick a Number.", defaultColor, onJobStack, Pos.TOP_CENTER, 0, 0);

						// cowboy
						putScaledImage("images/cowboy_concept_procedural_animation_by_grimmdev-d8l0klm_noBackground.png", onJobStack, Pos.CENTER, 150, 150, 0, -100);

						// Job selection
						Text bankerText = makeText("1. You are a rich banker", defaultColor, onJobStack, Pos.CENTER, 0, 0);
						Text carpenterText = makeText("2. You are a carpenter", defaultColor, onJobStack, Pos.CENTER, 0, 50);
						Text programmerText = makeText("3. You are a debt ridden  U  of A programming student", defaultColor, onJobStack, Pos.CENTER, 0, 100);
						Text explanationText = makeText("4. Get an explanation about how your choices matter.", defaultColor, onJobStack, Pos.CENTER, 0, 150);

					}

					// job menu -> pet menu
					else if (onJobMenu.getTheBoolean()) {

						// If the user has made a pick
						if (jobPicked.length() > 0) {

							// Set money based on job picked
							if (jobPicked.equals("1")) {
								controller.setMoney(1600);
							}

							else if (jobPicked.equals("2")) {
								controller.setMoney(800);
							}

							else if (jobPicked.equals("3")) {
								controller.setMoney(400);
							}

							dogNameString = "";
							switchMenus(onPetMenu, onPetStack);

							makeText("What is the name of your dog?", defaultColor, onPetStack, Pos.TOP_CENTER, 0, 0);

							putScaledImage("images/annoying_dog_animation_pixel_art_by_tha_jackable-dadc6y2.png", onPetStack, Pos.CENTER, 150, 150, 0, 0);
							
							// If the user actually wanted to go to the explanation screen.
							if (jobPicked.equals("4")) {
								switchMenus(onJobExplanationMenu, jobExplanationScreen);

								makeText("Traveling in the hot Arizona desert requires lots of cash.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 0);
								makeText("If you're the banker, you'll have a lot of money to buy supplies.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 50);
								makeText("If you're the carpenter you'll have a decent amount of money.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 100);
								makeText("And if you're a U of A student, then you're riddled with debt.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 150);
								makeText("However, the harder you go the more points you'll earn!", defaultColor, jobExplanationScreen, Pos.CENTER, 0, 50);
								makeText("The banker earns the least points  while  the U of A student earns the most.", defaultColor, jobExplanationScreen, Pos.CENTER, 0, 100);
								makeText("Press the backspace key to go back.", defaultColor, jobExplanationScreen, Pos.BOTTOM_CENTER, 0, 0);
							}
						}
					}

					// pet menu -> companion
					else if (onPetMenu.getTheBoolean()) {

						// Add dog
						controller.addFamilyMember(dogNameString);

						companionNameString = "";
						travelingCompanionNameString = "";
						switchMenus(onCompanionMenu, onCompanionStack);

						makeText("What is the name of your adult traveling companion?", defaultColor, onCompanionStack, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/aol_s_running_man___pixel_edition_by_breadfries-d8aytm5.png", onCompanionStack, Pos.CENTER, 150, 150, 0,0);
					}

					// companion -> child1
					else if (onCompanionMenu.getTheBoolean()) {

						// Add adult
						controller.addFamilyMember(travelingCompanionNameString);

						child1String = "";
						switchMenus(onChild1Menu, onChild1Stack);

						makeText("What is the name of your first child?", defaultColor, onChild1Stack, Pos.TOP_CENTER, 0, 0);

						//makeText("What is the name of your child traveling companion?", defaultColor, onChild1Stack, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/child.png", onChild1Stack, Pos.CENTER, 150, 150, 0, 0);
					}

                    // child1 -> child2
                    else if (onChild1Menu.getTheBoolean()) {

                    	// Add child 1
                    	controller.addFamilyMember(child1String);

                    	child2String = "";
						switchMenus(onChild2Menu, onChild2Stack);

						putScaledImage("images/photo-961.png", onChild2Stack, Pos.CENTER, 150, 150, 0, 0);
						makeText("What is the name of your second child?", defaultColor, onChild2Stack, Pos.TOP_CENTER, 0, 0);
					}

                    // child2 -> child3
                    else if (onChild2Menu.getTheBoolean()) {

                    	// Add child 2
                    	controller.addFamilyMember(child2String);

                    	child3String = "";
						switchMenus(onChild3Menu, onChild3Stack);

						putScaledImage("images/girl.png", onChild3Stack, Pos.CENTER, 150, 150, 0, 0);
						makeText("What is the name of your third child?", defaultColor, onChild3Stack, Pos.TOP_CENTER, 0, 0);
					}

					// child3 -> season
					else if (onChild3Menu.getTheBoolean()) {

                    	// Add child 3
                    	controller.addFamilyMember(child3String);

                    	startingMonth = "";
						switchMenus(onSeasonMenu, onSeasonStack);

						makeText("It is the year 1840.", defaultColor, onSeasonStack, Pos.TOP_CENTER, 0, 0);
						makeText("Your jumping  off point is the  town  of  Nogales  near  the  Mexican  border.", defaultColor, onSeasonStack, Pos.TOP_CENTER, 0, 50);
						makeText("What month  will you  be  leaving?", defaultColor, onSeasonStack, Pos.TOP_CENTER, 0, 100);
						
						putScaledImage("images/season.jpg", onSeasonStack, Pos.CENTER, 150, 150, 0, -50);
						makeText("1. January", defaultColor, onSeasonStack, Pos.CENTER, 0, 50);
						makeText("2. March", defaultColor, onSeasonStack, Pos.CENTER, 0,  75);
						makeText("3. May", defaultColor, onSeasonStack, Pos.CENTER, 0, 100);
						makeText("4. October", defaultColor, onSeasonStack, Pos.CENTER, 0, 125);

					}

					// season -> buyingExplanation
					else if (onSeasonMenu.getTheBoolean()) {
						if (startingMonth.equals("1")) {
							controller.setMonth(0);
						}

						else if (startingMonth.equals("2")) {
							controller.setMonth(2);
						}

						else if (startingMonth.equals("3")) {
							controller.setMonth(4);
						}

						else {
							controller.setMonth(9);
						}

						switchMenus(onBuyExplanationMenu, buyingExplanation);
						makeText("Before embarking on the Arizona Trail you should buy some equipment.", defaultColor, buyingExplanation, Pos.CENTER, 0, 0);
						makeText("You have  " + controller.getMoney() + " dollars  but you don't have to spend it all here.", defaultColor, buyingExplanation, Pos.CENTER, 0, 50);
						makeText("Press Enter to Continue", defaultColor, buyingExplanation, Pos.BOTTOM_CENTER, 0, 0);
						putScaledImage("images/saloon.png", buyingExplanation, Pos.TOP_CENTER, 700, 200, 0, 0);

					}

					// BUYINGEXPLANATION -> STARTSHOP
					else if (onBuyExplanationMenu.getTheBoolean()) {
						switchMenus(onStartShopMenu, startShop);
						makeText("So, you're going on the Arizona Trail. You've come to the right place!", defaultColor, startShop, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/merchant2.png", startShop, Pos.CENTER_LEFT, 300, 600, 0, 0);
						makeText("You'll want bison, clothes, food, ammo, and spare parts.", defaultColor, startShop, Pos.CENTER_RIGHT, -50, -100);
						makeText("I've got great prices, pardner, so make sure you spend a ton.", defaultColor, startShop, Pos.CENTER_RIGHT, -50, 100);

					}

                    //Startshop -> shop introduction
                    else if (onStartShopMenu.getTheBoolean()) {

                    	numOxenString = "";
                        switchMenus(onShopIntroductionMenu, onShopIntroductionStack);

                        makeText("Before  heading out for the trail you should buy equipment and supplies.", defaultColor, onShopIntroductionStack, Pos.TOP_CENTER, 0, 0);
                        makeText("You have " + controller.getMoney() + " dollars.", defaultColor, onShopIntroductionStack, Pos.TOP_CENTER, 0, 50);
                        makeText("How many Oxen  would you like to buy? I recommend at least 2 Oxen.", defaultColor, onShopIntroductionStack, Pos.TOP_CENTER, 0, 100);
                        makeText("Oxen are " + controller.getCost("oxen") + " dollars.", defaultColor, onShopIntroductionStack, Pos.TOP_CENTER, 0, 150);
                        putScaledImage("images/ox.png", onShopIntroductionStack, Pos.CENTER, 150, 150, 0, 50);

                    }




                    // shop introduction -> buy oxen
                    else if (onShopIntroductionMenu.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("oxen", Integer.valueOf(numOxenString));

                    	// only continue is buy is valid
                    	if (validBuy) {
	                    	poundsFoodString = "";
	                        switchMenus(onBuyOxenMenu, onBuyOxenStack);
	                        
	                        onBuyOxenStack.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, onBuyOxenStack, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, onBuyOxenStack, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many Pounds of Food would you like to buy? I recommend at least 200 lbs.", defaultColor, onBuyOxenStack, Pos.TOP_CENTER, 0, 100);
	                        makeText("A pound of food is $" + controller.getCost("food"), defaultColor, onBuyOxenStack, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/lamb.png", onBuyOxenStack, Pos.CENTER, 150, 150, 0, 50);

                    	}
                    }

                    // buy oxen -> buy food
                    else if (onBuyOxenMenu.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("food", Integer.valueOf(poundsFoodString));

                    	// only continue if buy is valid
                    	if (validBuy) {
	                    	numClothingString = "";
	                        switchMenus(onBuyFoodMenu, onBuyFoodStack);
	
	                        onBuyFoodStack.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, onBuyFoodStack, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, onBuyFoodStack, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many blankets would you like to buy? I recommend at least 10 sets.", defaultColor, onBuyFoodStack, Pos.TOP_CENTER, 0, 100);
	                        makeText("Blankets are $" + controller.getCost("blanket") + "   per set.", defaultColor, onBuyFoodStack, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/blanket.jpg", onBuyFoodStack, Pos.CENTER, 150, 150, 0, 50);

                    	}
                    }

                    // buy food -> buy clothes
                    else if (onBuyFoodMenu.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("blanket", Integer.valueOf(numClothingString));

                    	// only continue is buy is valid
                    	if (validBuy) {
	                    	numAmmoString = "";
	                        switchMenus(onBuyClothesMenu, onBuyClothesStack);
	                        //money -= Integer.valueOf(numClothing) * 10; //$10 per set
	                        onBuyClothesStack.getChildren().clear();
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, onBuyClothesStack, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, onBuyClothesStack, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many bullets would you like to buy? Bullets are good for hunting.", defaultColor, onBuyClothesStack, Pos.TOP_CENTER, 0, 100);
	                        makeText("Bullets are $" + controller.getCost("ammo"), defaultColor, onBuyClothesStack, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/bullet.png", onBuyClothesStack, Pos.CENTER, 150, 150, 0, 50);

                    	}
                    }

                    // buy clothes -> buy wheels
                    else if (onBuyClothesMenu.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("ammo", Integer.valueOf(numAmmoString));

                    	// only continue if buy is valid
                    	if (validBuy) {
	               		
	                    	gallonsWaterString = "";
	                        switchMenus(onBuyWheelsMenu, onBuyWheelsStack);
	                        //money -= Integer.valueOf(numAmmo) * 2; //$2 per box
	                        
	                        onBuyWheelsStack.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, onBuyWheelsStack, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, onBuyWheelsStack, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many Gallons of water would you like to buy? I recommend at least 600 gallons. Water is very important for your health.", defaultColor, onBuyWheelsStack, Pos.TOP_CENTER, 0, 100);
	                        makeText("One gallon is $" + controller.getCost("water"), defaultColor, onBuyWheelsStack, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/water.png", onBuyWheelsStack, Pos.CENTER, 150, 150, 0, 50);

                    	}
                    }

                    // buy wheels -> map
                     else if (onBuyWheelsMenu.getTheBoolean()) {
                    	 if (controller.buyItem("water", Integer.valueOf(gallonsWaterString))) {
                 			buildMapScreen(root);
                 			switchMenus(onMapMenu, mapScreen);
                    	 }
                    }

                    // map -> traveling
                    else if (onMapMenu.getTheBoolean()) {
                            boolean validBuy = controller.buyItem("water", Integer.valueOf(gallonsWaterString));
                    		redrawTravelingMenu(root, travelingMenu);
                    }

					// If on the traveling menu
                    else if (onTravelingMenu.getTheBoolean()) {
                    	whileTravelingChoice = "";
                    	switchMenus(onWhileTravelingMenu, whileTravelingMenu);
                    	whileTravelingMenu.getChildren().clear();
                    	makeText("You can do the following", defaultColor, whileTravelingMenu, Pos.TOP_CENTER, 0, 0);
                    	makeText("1. Continue on the trail.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -150);
                    	makeText("2. Check your supplies.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -100);
                    	makeText("3. Look at the map.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -50);
                    	makeText("4. Change pace.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 0);
                    	makeText("5. Change food rations.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 50);
                    	makeText("6. Hunt for food.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 100);
                    	makeText("7. Save Game.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 150);
                    	makeText("8. Main Menu - WILL NOT SAVE GAME!.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 200);
                    }

					// This is for the various choices the user can pick on the whileTraveling menu
                    else if (onWhileTravelingMenu.getTheBoolean()) {

                    	// Return to traveling menu
                    	if (whileTravelingChoice.equals("1")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}

                    	else if (whileTravelingChoice.equals("2")) {
                    		switchMenus(onSuppliesMenu, suppliesMenu);
                    		suppliesMenu.getChildren().clear();
                    		makeText("You have the following supplies", defaultColor, suppliesMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText("Oxen - " + controller.getItemAmount("oxen"), defaultColor, suppliesMenu, Pos.CENTER, 0, -150);
                    		makeText("Ammo - " + controller.getItemAmount("ammo"), defaultColor, suppliesMenu, Pos.CENTER, 0, -125);
                    		makeText("Food - " + controller.getItemAmount("food"), defaultColor, suppliesMenu, Pos.CENTER, 0, -100);
                    		makeText("Blankets - " + controller.getItemAmount("blanket"), defaultColor, suppliesMenu, Pos.CENTER, 0, -75);
                    		makeText("Gallons of Water - " + controller.getItemAmount("water"), defaultColor, suppliesMenu, Pos.CENTER, 0, -50);
                    		makeText("Press Backspace to Go Back", defaultColor, suppliesMenu, Pos.BOTTOM_CENTER, 0, 0);

                    	}

                        else if (whileTravelingChoice.equals("3")) {
                    		//view map
                        	buildMapScreen(root);
                        	switchMenus(onMapMenu, mapScreen);

                    	}

                        else if (whileTravelingChoice.equals("4")) {
                        	changePace = "";
                            switchMenus(onChangePaceMenu, changePaceMenu);

                            putScaledImage("images/running.gif", changePaceMenu, Pos.CENTER, 150, 150, 0, -100);

                            makeText("Select a Traveling speed", defaultColor, changePaceMenu, Pos.TOP_CENTER, 0, 0);
                            makeText("1 - Resting", defaultColor, changePaceMenu, Pos.CENTER, 0, 0);
                            makeText("2 - Slow", defaultColor, changePaceMenu, Pos.CENTER, 0, 50);
                            makeText("3 - Steady", defaultColor, changePaceMenu, Pos.CENTER, 0, 100);
                            makeText("4 - Fast", defaultColor, changePaceMenu, Pos.CENTER, 0, 150);
                            makeText("5 - Grueling", defaultColor, changePaceMenu, Pos.CENTER, 0, 200);

                            //controller.setPace(Integer.valueOf(travelingPace) - 1);

                    	}

                        else if (whileTravelingChoice.equals("5")) {
                        	changeRations = "";
                            switchMenus(onRationsMenu, rationsMenu);
                            rationsMenu.getChildren().clear();
                            makeText("Change food rations", defaultColor, rationsMenu, Pos.TOP_CENTER, 0,0);
                            makeText("Change the amount of food your party can eat each day.", defaultColor, rationsMenu, Pos.TOP_CENTER, 0,50);
                            putScaledImage("images/change_rations.jpg", rationsMenu, Pos.TOP_CENTER, 150, 300, 0, 100);
                            makeText("1 - Filling: meals are large", defaultColor, rationsMenu, Pos.CENTER, 0,0);
                            makeText("2 - Meager: meals are small", defaultColor, rationsMenu, Pos.CENTER, 0,50);
                            makeText("3 - Bare Bones: meals are very small", defaultColor, rationsMenu, Pos.CENTER, 0,100);

                    	}

                    	// chose hunting game
                    	else if (whileTravelingChoice.equals("6")) {
                    		switchMenus(onHuntingExplanationMenu, huntingExplanationMenu);
                    		makeText("You will now hunt the Coues. These are Arizona's very own deer!", defaultColor, huntingExplanationMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText("Press space to shoot. When you shoot you will lose 1 bullet.", defaultColor, huntingExplanationMenu, Pos.TOP_CENTER, 0, 25);
                    		putScaledImage("images/coues.jpg", huntingExplanationMenu, Pos.CENTER, 300, 300, 0, 0);
                    		makeText("When you're done hunting press the Escape key.", defaultColor, huntingExplanationMenu, Pos.BOTTOM_CENTER, 0, 0);
                    	}

                        else if (whileTravelingChoice.equals("7")) {
                            switchMenus(onSavedGameMenu, savedGameMenu);
                            makeText("Your Game Was Saved!", defaultColor, savedGameMenu, Pos.TOP_CENTER, 0,50);
                            putScaledImage("images/save_game.jpg", savedGameMenu, Pos.CENTER, 300, 600, 0, 50);
                            makeText("Press Enter to continue.", defaultColor, savedGameMenu, Pos.BOTTOM_CENTER, 0,0);

                    	}
                    	
                        else if (whileTravelingChoice.equals("8")) {
                        	switchMenus(onMainMenu, mainMenu);
                        }
                    }
					
                    else if (onHuntingExplanationMenu.getTheBoolean()) {
                		try {
							HuntingGame huntingGame = new HuntingGame();
							huntingGame.setBullets(controller.getItemAmount("ammo"));
							int originalNumBullets = huntingGame.getBullets();
							Scene huntingGameScene = huntingGame.getScene();
							huntingGame.drawText("Bullets - " + controller.getItemAmount("ammo"), 0, 0);

							primaryStage.setScene(huntingGameScene);
							huntingGameScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

							@Override
							public void handle(KeyEvent keyEvent) {
								if(keyEvent.getCode() == KeyCode.ESCAPE) {
									primaryStage.setScene(scene);
									primaryStage.setWidth(1000);
									primaryStage.setHeight(700);
									controller.useItemAmount("ammo", originalNumBullets - huntingGame.getBullets());
									controller.addHuntingFood(huntingGame.getMeat());
									redrawTravelingMenu(root, travelingMenu);
								}
							}
							});
                		} 
                		catch (Exception e) {
							e.printStackTrace();
						}
                    }

                    else if (onHuntingMenu.getTheBoolean()) {
                		redrawTravelingMenu(root, travelingMenu);
                		primaryStage.setScene(scene);
                    }

                    else if (onRationsMenu.getTheBoolean()) {
                    	controller.changeRations(Integer.valueOf(changeRations));
                    	redrawTravelingMenu(root, travelingMenu);
                    }

                    else if (onChangePaceMenu.getTheBoolean()) {
                    	if (1 <= Integer.valueOf(changePace) && Integer.valueOf(changePace) <= 5) {
                    		controller.setPace(Integer.valueOf(changePace));
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    }

					// hit enter on transition menu
                    else if (onTubacTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	tubacShopChoiceString = "";
                    	switchMenus(onTubacShopMenu, tubacShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	tubacStore.addItem(new Item("oxen", 125, 3));
                    	tubacStore.addItem(new Item("food", .8, 100));
                    	tubacStore.addItem(new Item("water", 3, 100));
                    	tubacStore.addItem(new Item("ammo", .5, 100));


                    	makeText("Howdy. This is the trusty Tubac General Store. Home to all of your needs.", defaultColor, tubacShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", tubacShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, tubacShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Food", defaultColor, tubacShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, tubacShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, tubacShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, tubacShopMenu, Pos.CENTER, 0, 125);

                    }

                    else if (onTucsonTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	tucsonShopChoiceString = "";
                    	switchMenus(onTucsonShopMenu, tucsonShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	tucsonStore.addItem(new Item("oxen", 300, 2));
                    	tucsonStore.addItem(new Item("blanket", 5, 20));
                    	tucsonStore.addItem(new Item("water", 3, 90));
                    	tucsonStore.addItem(new Item("ammo", .5, 110));


                    	makeText("This here is Tucson. We've got it all.", defaultColor, tucsonShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", tucsonShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, tucsonShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Blanket", defaultColor, tucsonShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, tucsonShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, tucsonShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, tucsonShopMenu, Pos.CENTER, 0, 125);

                    }

                    else if (onPicachoTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	picachoShopChoiceString = "";
                    	switchMenus(onPicachoShopMenu, picachoShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	picachoStore.addItem(new Item("oxen", 1000, 2));
                    	picachoStore.addItem(new Item("blanket", 30, 20));
                    	picachoStore.addItem(new Item("water", 100, 90));
                    	picachoStore.addItem(new Item("ammo", .1, 110));


                    	makeText("Welcome to Picacho!", defaultColor, picachoShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", picachoShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, picachoShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Blanket", defaultColor, picachoShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, picachoShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, picachoShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, picachoShopMenu, Pos.CENTER, 0, 125);

                    }

                    else if (onPhoenixTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	phoenixShopChoiceString = "";
                    	switchMenus(onPhoenixShopMenu, phoenixShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	phoenixStore.addItem(new Item("oxen", 50, 1));
                    	phoenixStore.addItem(new Item("food", 5, 20));
                    	phoenixStore.addItem(new Item("water", 3, 90));
                    	phoenixStore.addItem(new Item("ammo", .5, 110));


                    	makeText("You're in the capital of the greatest land on Earth. You're in Phoenix", defaultColor, phoenixShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", phoenixShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, phoenixShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Food", defaultColor, phoenixShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, phoenixShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, phoenixShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, phoenixShopMenu, Pos.CENTER, 0, 125);


                    	root.getChildren().remove(phoenixShopMenu);
                    	root.getChildren().add(phoenixShopMenu);
                    }


                    else if (onPrescottTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	prescottShopChoiceString = "";
                    	switchMenus(onPrescottShopMenu, prescottShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	prescottStore.addItem(new Item("oxen", 100, 1));
                    	prescottStore.addItem(new Item("food", 10, 20));
                    	prescottStore.addItem(new Item("water", .1, 90));
                    	prescottStore.addItem(new Item("ammo", 100, 1));


                    	makeText("Welcome to Prescott. We like newcomers.", defaultColor, prescottShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", prescottShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, prescottShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Food", defaultColor, prescottShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, prescottShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, prescottShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, prescottShopMenu, Pos.CENTER, 0, 125);

                    }

                    else if (onFlagstaffTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	flagstaffShopChoiceString = "";
                    	switchMenus(onFlagstaffShopMenu, flagstaffShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	flagstaffStore.addItem(new Item("blanket", 50, 1));
                    	flagstaffStore.addItem(new Item("food", 5, 20));
                    	flagstaffStore.addItem(new Item("water", 3, 90));
                    	flagstaffStore.addItem(new Item("ammo", .5, 110));


                    	makeText("We're up pretty high here in Flagstaff", defaultColor, flagstaffShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", flagstaffShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Blanket", defaultColor, flagstaffShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Food", defaultColor, flagstaffShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, flagstaffShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, flagstaffShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, flagstaffShopMenu, Pos.CENTER, 0, 125);

                    }

                    else if (onGrandCanyonTransitionMenu.getTheBoolean()) {
                    	
                    	allShoppingChoiceString = "";
                    	grandCanyonShopChoiceString = "";
                    	switchMenus(onGrandCanyonShopMenu, grandCanyonShopMenu);

                    	// Fill each town's store with items
                    	// make a store and then fill it up with its times
                    	grandCanyonStore.addItem(new Item("oxen", 2000, 1));
                    	grandCanyonStore.addItem(new Item("food", 1000, 20));
                    	grandCanyonStore.addItem(new Item("water", 1000, 90));
                    	grandCanyonStore.addItem(new Item("ammo", 1000, 110));


                    	makeText("The Grand Canyon is so awesome that it is one of the 7 Wonders of the Earth!", defaultColor, grandCanyonShopMenu, Pos.TOP_CENTER, 0,0);
                    	putScaledImage("images/shopkeeper1.jpg", grandCanyonShopMenu, Pos.CENTER, 150, 150, 0, -75);
                    	makeText("1. Buy Oxen", defaultColor, grandCanyonShopMenu, Pos.CENTER, 0, 25);
                    	makeText("2. Buy Food", defaultColor, grandCanyonShopMenu, Pos.CENTER, 0, 50);
                    	makeText("3. Buy Water", defaultColor, grandCanyonShopMenu, Pos.CENTER, 0, 75);
                    	makeText("4. Buy Ammo", defaultColor, grandCanyonShopMenu, Pos.CENTER, 0, 100);
                    	makeText("5. Leave store and continue on Arizona Trail", defaultColor, grandCanyonShopMenu, Pos.CENTER, 0, 125);

                    }
					
					// enter to go back from map to traveling menu
                    else if(onMapMenu.getTheBoolean()) {
                    	switchMenus(onWhileTravelingMenu, whileTravelingMenu);
			        }

					// hit enter on shop menu
                    else if (onTubacShopMenu.getTheBoolean()) {
                    	if (tubacShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = tubacStore;
                    		onCurrStoreMenu = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceNameString = "food";
                    		currStore = tubacStore;
                    		onCurrStoreMenu = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = tubacStore;
                    		onCurrStoreMenu = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = tubacStore;
                    		onCurrStoreMenu = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	tubacShopChoiceString = "";
                    }

					// hit enter on shop menu
                    else if (onTucsonShopMenu.getTheBoolean()) {
                    	if (tucsonShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = tucsonStore;
                    		onCurrStoreMenu = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceNameString = "blanket";
                    		currStore = tucsonStore;
                    		onCurrStoreMenu = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = tucsonStore;
                    		onCurrStoreMenu = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = tucsonStore;
                    		onCurrStoreMenu = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	tucsonShopChoiceString = "";
                    }

					// hit enter on shop menu
                    else if (onPicachoShopMenu.getTheBoolean()) {
                    	
                    	if (picachoShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = picachoStore;
                    		onCurrStoreMenu = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceNameString = "blanket";
                    		currStore = picachoStore;
                    		onCurrStoreMenu = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = picachoStore;
                    		onCurrStoreMenu = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = picachoStore;
                    		onCurrStoreMenu = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	picachoShopChoiceString = "";
                    }

                    else if (onPhoenixShopMenu.getTheBoolean()) {
                    	if (phoenixShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = phoenixStore;
                    		onCurrStoreMenu = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceNameString = "blanket";
                    		currStore = phoenixStore;
                    		onCurrStoreMenu = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = phoenixStore;
                    		onCurrStoreMenu = onPhoenixShopMenu; 
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = phoenixStore;
                    		onCurrStoreMenu = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	phoenixShopChoiceString = "";
                    }


                    else if (onPrescottShopMenu.getTheBoolean()) {
                    	if (prescottShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = prescottStore;
                    		onCurrStoreMenu = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceNameString = "food";
                    		currStore = prescottStore;
                    		onCurrStoreMenu = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = prescottStore;
                    		onCurrStoreMenu = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = prescottStore;
                    		onCurrStoreMenu = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	prescottShopChoiceString = "";
                    }


                    else if (onFlagstaffShopMenu.getTheBoolean()) {
                    	if (flagstaffShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceNameString = "blanket";
                    		currStore = flagstaffStore;
                    		onCurrStoreMenu = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceNameString = "food";
                    		currStore = flagstaffStore;
                    		onCurrStoreMenu = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = flagstaffStore;
                    		onCurrStoreMenu = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = flagstaffStore;
                    		onCurrStoreMenu = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	flagstaffShopChoiceString = "";
                    }


                    else if (onGrandCanyonShopMenu.getTheBoolean()) {
                    	if (grandCanyonShopChoiceString.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceNameString = "oxen";
                    		currStore = grandCanyonStore;
                    		onCurrStoreMenu = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoiceString.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceNameString = "food";
                    		currStore = grandCanyonStore;
                    		onCurrStoreMenu = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoiceString.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceNameString = "water";
                    		currStore = grandCanyonStore;
                    		onCurrStoreMenu = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoiceString.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceNameString = "ammo";
                    		currStore = grandCanyonStore;
                    		onCurrStoreMenu = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoiceString.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	grandCanyonShopChoiceString = "";
                    }

					// If player made a purchase
                    else if (onAllShoppingMenu.getTheBoolean()) {
                    	buyAtShop(controller, allShoppingChoiceNameString, Integer.valueOf(allShoppingChoiceString), currStore, currStoreMenu, onCurrStoreMenu, root );
                    }

                    else if (onSavedGameMenu.getTheBoolean()){
                        controller.saveGame(save_game_file);
                        redrawTravelingMenu(root, travelingMenu);
                    }
					
					// load previous save
                    else if (onMainMenu.getTheBoolean()) {

                        controller.loadGame(save_game_file);
                        redrawTravelingMenu(root, travelingMenu);
                            
                    }
					
                    else if (onDeathScreenMenu.getTheBoolean()) {
                    	if (!controller.getFamilyDead()) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	else {
                    		switchMenus(onGameOverMenu, gameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    	}
                    }
					
                    else if (onStarvationMenu.getTheBoolean()) {
                    	if (!controller.getFamilyDead()) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	else {
                    		switchMenus(onGameOverMenu, gameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    	}
                    }
					
                    else if (onThirstMenu.getTheBoolean()) {
                    	if (!controller.getFamilyDead()) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	else {
                    		switchMenus(onGameOverMenu, gameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    	}
                    }
				}
			}
		});

		// If we're on a menu and try typing something
		scene.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {

				// If user types on MENU 2. NO TRANSITION
				if (onNameMenu.getTheBoolean()) {
					nameString = printUserInput(keyEvent, nameString, nameText, onNameStack, Pos.BOTTOM_CENTER);
				}

				// If user types on MENU 3. NO TRANSITION
				else if (onJobMenu.getTheBoolean()) {
					jobPicked = printUserInputNumber(keyEvent, jobPicked, jobPickedText, onJobStack, Pos.BOTTOM_CENTER);
				}

				else if (onJobExplanationMenu.getTheBoolean()) {
					if (keyEvent.getCharacter().equals("\b")) {
						jobPicked = "";
						switchMenus(onJobMenu, onJobStack);
					}
				}

				// MENU 4
				else if (onPetMenu.getTheBoolean()) {
					dogNameString = printUserInput(keyEvent, dogNameString, dogNameText, onPetStack, Pos.BOTTOM_CENTER);
				}

				// MENU 5
				else if (onCompanionMenu.getTheBoolean()) {
					//travelingCompanionName = printUserInput(keyEvent, travelingCompanionName, travelingCompanionNameText, onCompanionStack, Pos.BOTTOM_CENTER);
                    companionNameString = printUserInput(keyEvent, companionNameString, companionNameText, onCompanionStack, Pos.BOTTOM_CENTER);
                    //System.out.println();
				}

				// MENU 6
				else if (onChild1Menu.getTheBoolean()) {
					child1String = printUserInput(keyEvent, child1String, child1Text, onChild1Stack, Pos.BOTTOM_CENTER);
				}

                //MENU 7
                else if (onChild2Menu.getTheBoolean()) {
					child2String = printUserInput(keyEvent, child2String, child2Text, onChild2Stack, Pos.BOTTOM_CENTER);
				}

                //MENU 8
                else if (onChild3Menu.getTheBoolean()) {
					child3String = printUserInput(keyEvent, child3String, child3Text, onChild3Stack, Pos.BOTTOM_CENTER);
				}

				// MENU 9
				else if (onSeasonMenu.getTheBoolean()) {
					startingMonth = printUserInputNumber(keyEvent, startingMonth, startingMonthText, onSeasonStack, Pos.BOTTOM_CENTER);
				}

                //MENU 10
                else if (onShopIntroductionMenu.getTheBoolean()) {
                    numOxenString = printUserInputBigNumber(keyEvent, numOxenString, numOxenText, onShopIntroductionStack, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numOxen) * 20; //$20 per oxen
                }

                //MENU 11
                else if (onBuyOxenMenu.getTheBoolean()) {
                    poundsFoodString = printUserInputBigNumber(keyEvent, poundsFoodString, poundsFoodText, onBuyOxenStack, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(poundsFood); //$1 per pound
                }

                //MENU 12
                else if (onBuyFoodMenu.getTheBoolean()) {
                    numClothingString = printUserInputBigNumber(keyEvent, numClothingString, numClothingText, onBuyFoodStack, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numClothing) * 10; //$10 per set
                }

                //MENU 13
                else if (onBuyClothesMenu.getTheBoolean()) {
                    numAmmoString = printUserInputBigNumber(keyEvent, numAmmoString, numAmmoText, onBuyClothesStack, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numAmmo) * 2; //$2 per box
                }

                //MENU 14
                else if (onBuyWheelsMenu.getTheBoolean()) {
                    gallonsWaterString = printUserInputBigNumber(keyEvent, gallonsWaterString, gallonsWaterText, onBuyWheelsStack, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numWagonWheels) * 10; //$10 per wheel
                }


				// traveling menu
                else if (onWhileTravelingMenu.getTheBoolean()) {
                	whileTravelingChoice = printUserInputMediumNumber(keyEvent, whileTravelingChoice, whileTravelingChoiceText, whileTravelingMenu, Pos.BOTTOM_CENTER);
                }

				// rations menu
                else if (onRationsMenu.getTheBoolean()) {
                	changeRations = printUserInputMediumNumber(keyEvent, changeRations, changeRationsText, rationsMenu, Pos.BOTTOM_CENTER);
                }

				// pace menu
                else if (onChangePaceMenu.getTheBoolean()) {
                	changePace = printUserInputBigNumber(keyEvent, changePace, changePaceText, changePaceMenu, Pos.BOTTOM_CENTER);
                }

                else if (onTubacShopMenu.getTheBoolean()) {
                	tubacShopChoiceString = printUserInputStoreNumber(keyEvent, tubacShopChoiceString, tubacShopChoiceText, tubacShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onTucsonShopMenu.getTheBoolean()) {
                	tucsonShopChoiceString = printUserInputStoreNumber(keyEvent, tucsonShopChoiceString, tucsonShopChoiceText, tucsonShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPicachoShopMenu.getTheBoolean()) {
                	picachoShopChoiceString = printUserInputStoreNumber(keyEvent, picachoShopChoiceString, picachoShopChoiceText, picachoShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPhoenixShopMenu.getTheBoolean()) {
                	phoenixShopChoiceString = printUserInputStoreNumber(keyEvent, phoenixShopChoiceString, phoenixShopChoiceText, phoenixShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPrescottShopMenu.getTheBoolean()) {
                	prescottShopChoiceString = printUserInputStoreNumber(keyEvent, prescottShopChoiceString, prescottShopChoiceText, prescottShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onFlagstaffShopMenu.getTheBoolean()) {
                	flagstaffShopChoiceString = printUserInputStoreNumber(keyEvent, flagstaffShopChoiceString, flagstaffShopChoiceText, flagstaffShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onGrandCanyonShopMenu.getTheBoolean()) {
                	grandCanyonShopChoiceString = printUserInputStoreNumber(keyEvent, grandCanyonShopChoiceString, grandCanyonShopChoiceText, grandCanyonShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onAllShoppingMenu.getTheBoolean()) {
                	allShoppingChoiceString = printUserInputBigNumber(keyEvent, allShoppingChoiceString, allShoppingChoiceText, allShoppingMenu, Pos.BOTTOM_CENTER);
                }
			}
		});

        primaryStage.setOnCloseRequest((WindowClosing) -> {
        	if (!onMainMenu.getTheBoolean() && !onNameMenu.getTheBoolean()) {
	            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	            alert.setTitle("Exit Game");
	            alert.setHeaderText("Save game before exiting?");
	
                //user clicks OK
	            Optional<ButtonType> result = alert.showAndWait();
	            if (result.get() == ButtonType.OK){
	                controller.saveGame(save_game_file);
	            }
        	}
        	
        	else {
	            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	            alert.setTitle("Exit Game");
	            alert.setHeaderText("Are you sure you want to exit?");
	            Optional<ButtonType> result = alert.showAndWait();
	            if (result.get() == ButtonType.OK){
	            	// do nothing. just exit
	            }
        	}
        });
	}

	public static void main(String []args) {
		launch(args);
	}
}
