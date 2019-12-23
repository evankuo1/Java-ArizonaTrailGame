// Imports
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.ColorAdjust;


import java.util.Optional;



// The view for the application. This contains main.
public class View extends Application{

	// Various miscellaneous variables such as the controller, media player, etc.
	Controller controller = new Controller(); 						// Set up controller
	MediaPlayer playSong; 											// used to help the song play
	static List<MenuClass> menuList = new ArrayList<MenuClass>(); 	// Add all menu booleans to this list
    File save_game_file = new File("saved_game.dat");		    	// For saving
	Person mainCharacter;											// Put all of the various people/inventory objects here
	
	// This is what the default text color is. This is here so if you want to change the text color
	// you can just change this one variable instead of changing every line of code that uses text
	static Color defaultColor = Color.WHITE;
	
	// The screens
	MenuClass onRoot = new MenuClass(); 							// title screen
    MenuClass onMainMenu = new MenuClass(); 						// main menu
	MenuClass onMenu2 = new MenuClass(); 							// choose name screen
	MenuClass onMenu3 = new MenuClass(); 							// choose job screen
	MenuClass onMenu4 = new MenuClass(); 							// choose pet screen
	MenuClass onMenu5 = new MenuClass(); 							// choose adult companion screen
	MenuClass onMenu6 = new MenuClass(); 							// choose child 1 screen
	MenuClass onMenu7 = new MenuClass(); 							// choose child 2 screen
    MenuClass onMenu8 = new MenuClass(); 							// choose child 3 screen
    MenuClass onMenu9 = new MenuClass(); 							// choose time of year screen
    MenuClass onMenu10 = new MenuClass(); 							// Money & pre-shop screen
    MenuClass onMenu11 = new MenuClass(); 							// Buy Oxen screen
    MenuClass onMenu12 = new MenuClass(); 							// Buy Food screen
    MenuClass onMenu13 = new MenuClass(); 							// Buy Clothing screen
    MenuClass onMenu14 = new MenuClass(); 							// Buy Wagon Wheels screen
    MenuClass onMenu15 = new MenuClass(); 							// Buy Wagon Axles screen
    MenuClass onTravelingMenu = new MenuClass(); 					// Traveling menu
    MenuClass onSuppliesMenu = new MenuClass(); 					// check supplies
    MenuClass onRationsMenu = new MenuClass(); 						// set rations
    MenuClass onMap = new MenuClass();								// map menu class
    MenuClass onChangePaceMenu = new MenuClass(); 					// set pace
    MenuClass onTubacTransitionMenu = new MenuClass(); 				// tubac screen
    MenuClass onTucsonTransitionMenu = new MenuClass();
    MenuClass onPicachoTransitionMenu = new MenuClass();
    MenuClass onPhoenixTransitionMenu = new MenuClass();
    MenuClass onPrescottTransitionMenu = new MenuClass();
    MenuClass onFlagstaffTransitionMenu = new MenuClass();
    MenuClass onGrandCanyonTransitionMenu = new MenuClass();
    MenuClass onKanabTransitionMenu = new MenuClass();
    MenuClass onTubacShopMenu = new MenuClass(); 					// tubac shop
    MenuClass onTucsonShopMenu = new MenuClass();
    MenuClass onPicachoShopMenu = new MenuClass();
    MenuClass onPhoenixShopMenu = new MenuClass();
    MenuClass onPrescottShopMenu = new MenuClass();
    MenuClass onFlagstaffShopMenu = new MenuClass();
    MenuClass onGrandCanyonShopMenu = new MenuClass();
    MenuClass onSavedGameMenu = new MenuClass(); 					// save game
    MenuClass onCurrStore = new MenuClass();
    MenuClass onAllShoppingMenu = new MenuClass(); 					// the menu used for all shopping in towns
    MenuClass onHuntingMenu = new MenuClass(); 						// hunting game
    MenuClass onHuntingExplanationMenu = new MenuClass(); 			// hunting game explanation
    MenuClass onWhileTravelingMenu = new MenuClass(); 				// Menu to size up situation while traveling. Different from at landmark
	MenuClass onBuyingExplanation = new MenuClass(); 				// buying explanation
	MenuClass onStartShop = new MenuClass(); 						// first shop
	MenuClass onJobExplanationScreen = new MenuClass(); 			// Job explanation screen
	MenuClass onDeathScreenMenu = new MenuClass();
	MenuClass onStarvationMenu = new MenuClass();
	MenuClass onThirstMenu = new MenuClass();
	MenuClass onGameOverMenu = new MenuClass();

	// Holds various strings used for storage and menu printing.
	String name = "";
	String jobPicked = "";
	String dogName = "";
	String travelingCompanionName = "";
	String childCompanionName = "";
	String startingMonth = "";
    String companionName = "";
    String child1 = "";
    String child2 = "";
    String child3 = "";
    String numOxen = "";
    String poundsFood = "";
    String numClothing = "";
    String numAmmo = "";
    String gallonsWater = "";
    String whileTravelingChoice = "";
    String changePace = "";
    String changeRations = "";
    String allShoppingChoice = "";
    String allShoppingChoiceName = "";
    String lastVisitedTown = "";
    String gameMode = "";
    String displayedDead = "";
    String tubacShopChoice = "";
    String tucsonShopChoice = "";
    String picachoShopChoice = "";
    String phoenixShopChoice = "";
    String prescottShopChoice = "";
    String flagstaffShopChoice = "";
    String grandCanyonShopChoice = "";

	// Various variables for menu printing to work
	TextClass nameText = new TextClass();
	TextClass jobPickedText = new TextClass();
	TextClass dogNameText = new TextClass();
    TextClass companionNameText = new TextClass();
    TextClass child1Text = new TextClass();
    TextClass child2Text = new TextClass();
    TextClass child3Text = new TextClass();
	TextClass travelingCompanionNameText = new TextClass();
	TextClass childCompanionNameText = new TextClass();
	TextClass startingMonthText = new TextClass();
    TextClass numOxenText = new TextClass();
    TextClass poundsFoodText = new TextClass();
    TextClass numClothingText = new TextClass();
    TextClass numAmmoText = new TextClass();
    TextClass gallonsWaterText = new TextClass();
    TextClass whileTravelingChoiceText = new TextClass();
    TextClass changeRationsText = new TextClass();
    TextClass changePaceText = new TextClass();
    TextClass tubacShopChoiceText = new TextClass();
    TextClass allShoppingChoiceText = new TextClass();
    TextClass allShoppingChoiceNameText = new TextClass();
    TextClass gameModeText = new TextClass();
    TextClass tucsonShopChoiceText = new TextClass();
    TextClass picachoShopChoiceText = new TextClass();
    TextClass phoenixShopChoiceText = new TextClass();
    TextClass prescottShopChoiceText = new TextClass();
    TextClass flagstaffShopChoiceText = new TextClass();
    TextClass grandCanyonShopChoiceText = new TextClass();

	// make stores for each town
	Store currStore = new Store();
	Store tubacStore = new Store();
	Store tucsonStore = new Store();
	Store picachoStore = new Store();
	Store phoenixStore = new Store();
	Store prescottStore = new Store();
	Store flagstaffStore = new Store();
	Store grandCanyonStore = new Store();


	// Set up the StackPanes that will hold everything
	StackPane root = new StackPane();
    StackPane mainMenu = new StackPane();
	StackPane menu2 = new StackPane();
	StackPane menu3 = new StackPane();
	StackPane menu4 = new StackPane();
	StackPane menu5 = new StackPane();
	StackPane menu6 = new StackPane();
	StackPane menu7 = new StackPane();
    StackPane menu8 = new StackPane();
    StackPane menu9 = new StackPane();
    StackPane menu10 = new StackPane();
    StackPane menu11 = new StackPane();
    StackPane menu12 = new StackPane();
    StackPane menu13 = new StackPane();
    StackPane menu14 = new StackPane();
    StackPane menu15 = new StackPane();
    StackPane suppliesMenu = new StackPane();
    StackPane mapScreen = new StackPane();								// map StackPane
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
    
    
    
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Setup canvas and gc to draw images.
		Canvas canvas = new Canvas(1000,500);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Tells the switchMenu() method what menu we're on
        //switchMenus(root);
		switchMenus(onMainMenu);

        //add main menu
        buildMenu(root, mainMenu, Color.BLACK);

		// Set up the 2nd menu and add it to the stackpane that'll hold all other stackpanes.
		buildMenu(root, menu2, Color.BLACK);

		// Set up 3rd menu
		buildMenu(root, menu3, Color.BLACK);

		// 4th menu
		buildMenu(root, menu4, Color.BLACK);

		// 5th, 6th, 7th, job explanation, and many more
		buildMenu(root, menu5, Color.BLACK);
		buildMenu(root, menu6, Color.BLACK);
		buildMenu(root, menu7, Color.BLACK);
        buildMenu(root, menu8, Color.BLACK);
        buildMenu(root, menu9, Color.BLACK);
        buildMenu(root, menu10, Color.BLACK);
        buildMenu(root, menu11, Color.BLACK);
        buildMenu(root, menu12, Color.BLACK);
        buildMenu(root, menu13, Color.BLACK);
        buildMenu(root, menu14, Color.BLACK);
        buildMenu(root, menu15, Color.BLACK);
		buildMenu(root, jobExplanationScreen, Color.BLACK);
		buildMenu(root, buyingExplanation, Color.BLACK);
		buildMenu(root, startShop, Color.BLACK);
		buildMenu(root, travelingMenu, Color.BLACK);
		buildMenu(root, whileTravelingMenu, Color.BLACK);
		buildMenu(root, suppliesMenu, Color.BLACK);
		//buildMapScreen(root);							// build map screen
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

		// Add canvas to the stackpane.
		mainMenu.getChildren().add(canvas);
		Text introText = makeText("Press Space To Continue!", Color.BLACK, mainMenu, Pos.CENTER, 0, 0); // make text

		// Add title text
		Text titleText = new Text("The  Arizona  Trail");
		titleText.setFont(new Font("ArcadeClassic", 100));
		StackPane.setAlignment(titleText, Pos.TOP_CENTER);
		mainMenu.getChildren().add(titleText);
		mainMenu.toFront();
		
		// Load game
        makeText("Press Enter to Load Previous Save",  Color.BLACK, mainMenu, Pos.CENTER, 0, 35);

		// Make the image
		// change preserverRatio to true?
		// Image trail = new Image("https://www.history.com/.image/ar_16:9%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cg_faces:center%2Cq_auto:good%2Cw_768/MTU3OTIzNTc4NTMyOTMxMjE4/9-things-you-may-not-know-about-the-oregon-trails-featured-photo.jpg", 1000, 500, false, false);
        Image trail = new Image(new File("images/oregonTrailBackground.jpg").toURI().toString(), 1000, 500, false, false);

		// Draw the image and the text.
		gc.drawImage(trail, 0, 0);

		// Setup scene and show it.
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/fontstyle.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Arizona Trail");
		//primaryStage.setFullScreen(true); // If we want the stage to be fullscreen
		primaryStage.show();

		// Set up song and play it.
		try {
			// Default uses mp3
			Media titleSong = new Media(new File("titletheme.mp3").toURI().toString());
			playSong = new MediaPlayer(titleSong);
			playSong.setCycleCount(MediaPlayer.INDEFINITE);
			playSong.play();
		}

		// For Micheal's PC
		catch (Exception e) {
			// Use wav if mp3 doesn't work
			Media titleSong = new Media(new File("titletheme.wav").toURI().toString());
			playSong = new MediaPlayer(titleSong);
			playSong.setCycleCount(MediaPlayer.INDEFINITE);
			playSong.play();
		}

		// space
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			
		    public void handle(KeyEvent keyEvent) {
		    	// When the user presses space, go FROM MAIN MENU -> MENU 2
		        if (keyEvent.getCode() == KeyCode.SPACE)  {
                    if(onMainMenu.getTheBoolean()){
                        //if user picks 1, start game normally, switch to root menu
                        //else if user picks 2, load game from saved file, switch to travel menu
                        //switchMenus(onRoot);
                    }

		        	if (onMainMenu.getTheBoolean()) {
			        	// Make some intro text
			        	controller = new Controller();
			        	Text startText = makeText("On the Arizona Trail you'll travel from the Mexican border to Utah!", defaultColor, menu2, Pos.TOP_CENTER, 0, 0);
	
	
			        	// Add picture
			        	putScaledImage("images/thMRQA2QMB.jpg", menu2, Pos.CENTER, 800, 300, 0, 0);
	
	
			        	// Ask the user for a name. 
			        	Text askForNameText = makeText("What's your name?", defaultColor, menu2, Pos.CENTER, 0, 200);
	
			        	//Text askForNameText = makeText("What's your name? Type and then press enter", defaultColor, menu2, Pos.CENTER, 0, 200);
	
			        	// Have this menu be displayed at the top.
				    	switchMenus(onMenu2);
				    	name = "";
			        	root.getChildren().remove(menu2);
			        	root.getChildren().add(menu2);
		        	}

		        	if (onTravelingMenu.getTheBoolean()) {
		        		// see if someone should die
		        		Person person = controller.healthNotGood();

		        		if (person != null) {
		        			if (person.getCauseOfDeath().equals("Dysentery")) {
		        				switchMenus(onDeathScreenMenu);
		        				deathScreenMenu.getChildren().clear();
		        				makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 0);
		        				makeText("They died of dysentry.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
		        				putScaledImage("images/ghost.jpg", deathScreenMenu, Pos.CENTER, 300, 300, 0, 0);
		        				root.getChildren().remove(deathScreenMenu);
		        				root.getChildren().add(deathScreenMenu);
		        			}
		        			else if (person.getCauseOfDeath().equals("Thirst")) {
			        			switchMenus(onThirstMenu);
			        			thirstMenu.getChildren().clear();
			        			makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, thirstMenu, Pos.TOP_CENTER, 0, 0);
			        			makeText("They died of thirst.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
			        			putScaledImage("images/skeleton.gif", thirstMenu, Pos.CENTER, 300, 300, 0, 0);
			        			root.getChildren().remove(thirstMenu);
			        			root.getChildren().add(thirstMenu);
		        				
		        			}
		        			
		        			else if (person.getCauseOfDeath().equals("Starvation")) {
			        			switchMenus(onStarvationMenu);
			        			starvationMenu.getChildren().clear();
			        			makeText("Tragedy has struck! The element have claimed the life of " + person.getName(), defaultColor, starvationMenu, Pos.TOP_CENTER, 0, 0);
			        			makeText("They died of starvation.", defaultColor, deathScreenMenu, Pos.TOP_CENTER, 0, 25);
			        			putScaledImage("images/dry.jpg", starvationMenu, Pos.CENTER, 300, 300, 0, 0);
			        			root.getChildren().remove(starvationMenu);
			        			root.getChildren().add(starvationMenu);
		        			}
		        		}
		        		else if (controller.doDayCycle()) {
		        			
		        			if (lastVisitedTown.equals("")) {
			        			switchMenus(onTubacTransitionMenu);
			        			makeText("You have reached Tubac!", defaultColor, tubacTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/Tubac_Church.jpg", tubacTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(tubacTransitionMenu);
			        			root.getChildren().add(tubacTransitionMenu);
			        			lastVisitedTown = "Tubac";
		        			}

		        			else if (lastVisitedTown.equals("Tubac")) {
			        			switchMenus(onTucsonTransitionMenu);
			        			makeText("You have reached Tucson!", defaultColor, tucsonTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/tucson.jpg", tucsonTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(tucsonTransitionMenu);
			        			root.getChildren().add(tucsonTransitionMenu);
			        			lastVisitedTown = "Tucson";
		        			}

		        			else if (lastVisitedTown.equals("Tucson")) {
			        			switchMenus(onPicachoTransitionMenu);
			        			makeText("You have reached Picacho!", defaultColor, picachoTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/picacho.jpg", picachoTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(picachoTransitionMenu);
			        			root.getChildren().add(picachoTransitionMenu);
			        			lastVisitedTown = "Picacho";
		        			}

		        			else if (lastVisitedTown.equals("Picacho")) {
		        				switchMenus(onPhoenixTransitionMenu);
			        			makeText("You have reached Phoenix!", defaultColor, phoenixTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/phoenix.jpg", phoenixTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(phoenixTransitionMenu);
			        			root.getChildren().add(phoenixTransitionMenu);
			        			lastVisitedTown = "Phoenix";
		        			}

		        			else if (lastVisitedTown.equals("Phoenix")) {
		        				switchMenus(onPrescottTransitionMenu);
			        			makeText("You have reached Prescott!", defaultColor, prescottTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/prescott.jpg", prescottTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(prescottTransitionMenu);
			        			root.getChildren().add(prescottTransitionMenu);
			        			lastVisitedTown = "Prescott";
		        			}

		        			else if (lastVisitedTown.equals("Prescott")) {
		        				switchMenus(onFlagstaffTransitionMenu);
			        			makeText("You have reached Flagstaff!", defaultColor, flagstaffTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/flagstaff.jpg", flagstaffTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(flagstaffTransitionMenu);
			        			root.getChildren().add(flagstaffTransitionMenu);
			        			lastVisitedTown = "Flagstaff";
		        			}

		        			else if (lastVisitedTown.equals("Flagstaff")) {
		        				switchMenus(onGrandCanyonTransitionMenu);
			        			makeText("You have reached the Grand Canyon!", defaultColor, grandCanyonTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/grandcanyon.jpg", grandCanyonTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			root.getChildren().remove(grandCanyonTransitionMenu);
			        			root.getChildren().add(grandCanyonTransitionMenu);
			        			lastVisitedTown = "Grand Canyon";
		        			}

		        			else if (lastVisitedTown.equals("Grand Canyon")) {
		        				switchMenus(onKanabTransitionMenu);
			        			makeText("You have reached Kanab. You got to Utah! Game Over! You Won!", defaultColor, kanabTransitionMenu, Pos.TOP_CENTER, 0,0);
			        			putScaledImage("images/kanab.jpg", kanabTransitionMenu, Pos.CENTER, 400, 400, 0, -25);
			        			makeText("You got " + controller.getScore() + " points!", defaultColor, kanabTransitionMenu, Pos.BOTTOM_CENTER, 0, 0);
			        			root.getChildren().remove(kanabTransitionMenu);
			        			root.getChildren().add(kanabTransitionMenu);
			        			lastVisitedTown = "Kanab";
		        			}
		        		}

		        		else {
			        		redrawTravelingMenu(root, travelingMenu);
		        		}
		        	}
		        }
		    }
		});

		// backspace
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			
		    public void handle(KeyEvent keyEvent) {
		    	// When the user presses space, go FROM MAIN MENU -> MENU 2
		        if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
		        	if (onSuppliesMenu.getTheBoolean()) {
		        		redrawTravelingMenu(root, travelingMenu);
		        	}
		        }
		    }
		});


		// If we're on menu2 and press enter. GO FROM MENU2 -> MENU3
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					
					if (onGameOverMenu.getTheBoolean()) {
						switchMenus(onMainMenu);
                    	mainMenu.toFront();
					}
					// If user presses enter on MENU 2. MENU 2 -> MENU 3 TRANSITION
					else if (onMenu2.getTheBoolean()) {

						// Add main character to family
						controller.addFamilyMember(name);

						// Switch to menu 3 and set it up
						jobPicked = "";
						switchMenus(onMenu3);
						menu3.getChildren().clear();
						Text whatJobText = makeText(name + ", what job do you have? Pick a Number.", defaultColor, menu3, Pos.TOP_CENTER, 0, 0);

						// cowboy
						putScaledImage("images/cowboy_concept_procedural_animation_by_grimmdev-d8l0klm_noBackground.png", menu3, Pos.CENTER, 150, 150, 0, -100);

						// Job selection
						Text bankerText = makeText("1. You are a rich banker", defaultColor, menu3, Pos.CENTER, 0, 0);
						Text carpenterText = makeText("2. You are a carpenter", defaultColor, menu3, Pos.CENTER, 0, 50);
						Text programmerText = makeText("3. You are a debt ridden  U  of A programming student", defaultColor, menu3, Pos.CENTER, 0, 100);
						Text explanationText = makeText("4. Get an explanation about how your choices matter.", defaultColor, menu3, Pos.CENTER, 0, 150);


						// switch to menu 3
						root.getChildren().remove(menu3);
						root.getChildren().add(menu3);

					}

					// If user presses enter on MENU 3. MENU 3 -> MENU 4 TRANSITION
					else if (onMenu3.getTheBoolean()) {

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

							dogName = "";
							switchMenus(onMenu4);

							makeText("What is the name of your dog?", defaultColor, menu4, Pos.TOP_CENTER, 0, 0);

							putScaledImage("images/annoying_dog_animation_pixel_art_by_tha_jackable-dadc6y2.png", menu4, Pos.CENTER, 150, 150, 0, 0);
							root.getChildren().remove(menu4);
							root.getChildren().add(menu4);

							// If the user actually wanted to go to the explanation screen.
							if (jobPicked.equals("4")) {
								switchMenus(onJobExplanationScreen);

								makeText("Traveling in the hot Arizona desert requires lots of cash.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 0);
								makeText("If you're the banker, you'll have a lot of money to buy supplies.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 50);
								makeText("If you're the carpenter you'll have a decent amount of money.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 100);
								makeText("And if you're a U of A student, then you're riddled with debt.", defaultColor, jobExplanationScreen, Pos.TOP_CENTER, 0, 150);
								makeText("However, the harder you go the more points you'll earn!", defaultColor, jobExplanationScreen, Pos.CENTER, 0, 50);
								makeText("The banker earns the least points  while  the U of A student earns the most.", defaultColor, jobExplanationScreen, Pos.CENTER, 0, 100);
								makeText("Press the backspace key to go back.", defaultColor, jobExplanationScreen, Pos.BOTTOM_CENTER, 0, 0);
								root.getChildren().remove(jobExplanationScreen);
								root.getChildren().add(jobExplanationScreen);
							}
						}
					}

					// MENU 4 -> MENU 5 TRANSITION
					else if (onMenu4.getTheBoolean()) {

						// Add dog
						controller.addFamilyMember(dogName);

						companionName = "";
						travelingCompanionName = "";
						switchMenus(onMenu5);

						makeText("What is the name of your adult traveling companion?", defaultColor, menu5, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/aol_s_running_man___pixel_edition_by_breadfries-d8aytm5.png", menu5, Pos.CENTER, 150, 150, 0,0);
						root.getChildren().remove(menu5);
						root.getChildren().add(menu5);
					}

					// MENU 5 -> MENU 6 TRANSITION
					else if (onMenu5.getTheBoolean()) {

						// Add adult
						controller.addFamilyMember(travelingCompanionName);

						child1 = "";
						switchMenus(onMenu6);

						makeText("What is the name of your first child?", defaultColor, menu6, Pos.TOP_CENTER, 0, 0);

						//makeText("What is the name of your child traveling companion?", defaultColor, menu6, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/child.png", menu6, Pos.CENTER, 150, 150, 0, 0);
						root.getChildren().remove(menu6);
						root.getChildren().add(menu6);
					}

                    //MENU 6 -> MENU 7
                    else if (onMenu6.getTheBoolean()) {

                    	// Add child 1
                    	controller.addFamilyMember(child1);

                    	child2 = "";
						switchMenus(onMenu7);

						putScaledImage("images/photo-961.png", menu7, Pos.CENTER, 150, 150, 0, 0);
						makeText("What is the name of your second child?", defaultColor, menu7, Pos.TOP_CENTER, 0, 0);
						root.getChildren().remove(menu7);
						root.getChildren().add(menu7);
					}

                    //MENU 7 -> 8
                    else if (onMenu7.getTheBoolean()) {

                    	// Add child 2
                    	controller.addFamilyMember(child2);

                    	child3 = "";
						switchMenus(onMenu8);

						putScaledImage("images/girl.png", menu8, Pos.CENTER, 150, 150, 0, 0);
						makeText("What is the name of your third child?", defaultColor, menu8, Pos.TOP_CENTER, 0, 0);
						root.getChildren().remove(menu8);
						root.getChildren().add(menu8);
					}

					// MENU 8 -> MENU 9 TRANSITION
					else if (onMenu8.getTheBoolean()) {

                    	// Add child 3
                    	controller.addFamilyMember(child3);

                    	startingMonth = "";
						switchMenus(onMenu9);

						makeText("It is the year 1840.", defaultColor, menu9, Pos.TOP_CENTER, 0, 0);
						makeText("Your jumping  off point is the  town  of  Nogales  near  the  Mexican  border.", defaultColor, menu9, Pos.TOP_CENTER, 0, 50);
						makeText("What month  will you  be  leaving?", defaultColor, menu9, Pos.TOP_CENTER, 0, 100);
						
						putScaledImage("images/season.jpg", menu9, Pos.CENTER, 150, 150, 0, -50);
						makeText("1. January", defaultColor, menu9, Pos.CENTER, 0, 50);
						makeText("2. March", defaultColor, menu9, Pos.CENTER, 0,  75);
						makeText("3. May", defaultColor, menu9, Pos.CENTER, 0, 100);
						makeText("4. October", defaultColor, menu9, Pos.CENTER, 0, 125);

						root.getChildren().remove(menu9);
						root.getChildren().add(menu9);
					}

					// MENU 7 -> BUYINGEXPLANATION TRANSITION
					else if (onMenu9.getTheBoolean()) {
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

						switchMenus(onBuyingExplanation);
						makeText("Before embarking on the Arizona Trail you should buy some equipment.", defaultColor, buyingExplanation, Pos.CENTER, 0, 0);
						makeText("You have  " + controller.getMoney() + " dollars  but you don't have to spend it all here.", defaultColor, buyingExplanation, Pos.CENTER, 0, 50);
						makeText("Press Enter to Continue", defaultColor, buyingExplanation, Pos.BOTTOM_CENTER, 0, 0);
						putScaledImage("images/saloon.png", buyingExplanation, Pos.TOP_CENTER, 700, 200, 0, 0);
						root.getChildren().remove(buyingExplanation);
						root.getChildren().add(buyingExplanation);

					}

					// BUYINGEXPLANATION -> STARTSHOP TRANSITION
					else if (onBuyingExplanation.getTheBoolean()) {
						switchMenus(onStartShop);
						makeText("So, you're going on the Arizona Trail. You've come to the right place!", defaultColor, startShop, Pos.TOP_CENTER, 0, 0);
						putScaledImage("images/merchant2.png", startShop, Pos.CENTER_LEFT, 300, 600, 0, 0);
						makeText("You'll want bison, clothes, food, ammo, and spare parts.", defaultColor, startShop, Pos.CENTER_RIGHT, -50, -100);
						makeText("I've got great prices, pardner, so make sure you spend a ton.", defaultColor, startShop, Pos.CENTER_RIGHT, -50, 100);
						root.getChildren().remove(startShop);
						root.getChildren().add(startShop);
					}

                    //MENU 9 -> MENU 10
                    else if (onStartShop.getTheBoolean()) {

                    	numOxen = "";
                        switchMenus(onMenu10);

                        makeText("Before  heading out for the trail you should buy equipment and supplies.", defaultColor, menu10, Pos.TOP_CENTER, 0, 0);
                        makeText("You have " + controller.getMoney() + " dollars.", defaultColor, menu10, Pos.TOP_CENTER, 0, 50);
                        makeText("How many Oxen  would you like to buy? I recommend at least 2 Oxen.", defaultColor, menu10, Pos.TOP_CENTER, 0, 100);
                        makeText("Oxen are " + controller.getCost("oxen") + " dollars.", defaultColor, menu10, Pos.TOP_CENTER, 0, 150);
                        putScaledImage("images/ox.png", menu10, Pos.CENTER, 150, 150, 0, 50);
                        root.getChildren().remove(menu10);
						root.getChildren().add(menu10);
                    }




                    //MENU 10 -> MENU 11
                    else if (onMenu10.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("oxen", Integer.valueOf(numOxen));

                    	// only continue is buy is valid
                    	if (validBuy) {
	                    	poundsFood = "";
	                        switchMenus(onMenu11);
	                        
	                        menu11.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, menu11, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, menu11, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many Pounds of Food would you like to buy? I recommend at least 200 lbs.", defaultColor, menu11, Pos.TOP_CENTER, 0, 100);
	                        makeText("A pound of food is $" + controller.getCost("food"), defaultColor, menu11, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/lamb.png", menu11, Pos.CENTER, 150, 150, 0, 50);
	                        root.getChildren().remove(menu11);
							root.getChildren().add(menu11);
                    	}

                    }

                    //MENU 11 -> MENU 12
                    else if (onMenu11.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("food", Integer.valueOf(poundsFood));

                    	// only continue if buy is valid
                    	if (validBuy) {
	                    	numClothing = "";
	                        switchMenus(onMenu12);
	
	                        menu12.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, menu12, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, menu12, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many blankets would you like to buy? I recommend at least 10 sets.", defaultColor, menu12, Pos.TOP_CENTER, 0, 100);
	                        makeText("Blankets are $" + controller.getCost("blanket") + "   per set.", defaultColor, menu12, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/blanket.jpg", menu12, Pos.CENTER, 150, 150, 0, 50);
	                        root.getChildren().remove(menu12);
							root.getChildren().add(menu12);
                    	}

                    }

                    //MENU 12 -> MENU 13
                    else if (onMenu12.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("blanket", Integer.valueOf(numClothing));

                    	// only continue is buy is valid
                    	if (validBuy) {
	                    	numAmmo = "";
	                        switchMenus(onMenu13);
	                        //money -= Integer.valueOf(numClothing) * 10; //$10 per set
	                        menu13.getChildren().clear();
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, menu13, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, menu13, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many bullets would you like to buy? Bullets are good for hunting.", defaultColor, menu13, Pos.TOP_CENTER, 0, 100);
	                        makeText("Bullets are $" + controller.getCost("ammo"), defaultColor, menu13, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/bullet.png", menu13, Pos.CENTER, 150, 150, 0, 50);
	                        root.getChildren().remove(menu13);
							root.getChildren().add(menu13);
                    	}
                    }

                    //MENU 13 -> MENU 14
                    else if (onMenu13.getTheBoolean()) {

                    	boolean validBuy = controller.buyItem("ammo", Integer.valueOf(numAmmo));

                    	// only continue if buy is valid
                    	if (validBuy) {
	                    		
	                    	gallonsWater = "";
	                        switchMenus(onMenu14);
	                        //money -= Integer.valueOf(numAmmo) * 2; //$2 per box
	                        
	                        menu14.getChildren().clear();
	
	                        makeText("Before heading out for the trail you should buy equipment and supplies.", defaultColor, menu14, Pos.TOP_CENTER, 0, 0);
	                        makeText("You have $" + controller.getMoney(), defaultColor, menu14, Pos.TOP_CENTER, 0, 50);
	                        makeText("How many Gallons of water would you like to buy? I recommend at least 600 gallons. Water is very important for your health.", defaultColor, menu14, Pos.TOP_CENTER, 0, 100);
	                        makeText("One gallon is $" + controller.getCost("water"), defaultColor, menu14, Pos.TOP_CENTER, 0, 150);
	                        putScaledImage("images/water.png", menu14, Pos.CENTER, 150, 150, 0, 50);
	                        root.getChildren().remove(menu14);
							root.getChildren().add(menu14);
                    	}
                    }

                    //MENU 14 -> MENU 15
                     else if (onMenu14.getTheBoolean()) {
                    	 if (controller.buyItem("water", Integer.valueOf(gallonsWater))) {
                 			buildMapScreen(root);
                 			switchMenus(onMap);
                 			mapScreen.toFront();
                    	 }
                    	}


                    //MENU 15 -> MENU 16
                    else if (onMap.getTheBoolean()) {
                    		
                            boolean validBuy = controller.buyItem("water", Integer.valueOf(gallonsWater));
                    		switchMenus(onTravelingMenu);

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
                    	     final Animation animation = new WagonAnimation(
                    	                imageView,
                    	                Duration.millis(1000),
                    	                COUNT, COLUMNS,
                    	                OFFSET_X, OFFSET_Y,
                    	                WIDTH, HEIGHT
                    	        );
                    	        animation.setCycleCount(Animation.INDEFINITE);
                    	        animation.play();
                    	        ImageView layer3 = new ImageView(new Image("Layer3.png",1000,100,false,false));
                    	        Rectangle sky = new Rectangle(1000,300,Color.SKYBLUE);
                    			Rectangle ground = new Rectangle(1000,500,Color.DARKGOLDENROD);
                    			sky.setY(-100);
                    			ground.setY(200);
                    			ImageView layer3a = new ImageView(new Image("Layer3.png",1000,100,false,false));
                    			Pane background = new Pane();

                    			background.getChildren().addAll(sky,ground,layer3,layer3a);

                    			layer3a.setX(-1000);
                    			layer3a.setY(100);
                    			layer3.setY(100);

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

                    	     root.getChildren().remove(travelingMenu);
     						 root.getChildren().add(travelingMenu);



                    }

                    else if (onMenu15.getTheBoolean()){
                        //switchMenus(onTravelingMenu);
                        //makeText("Your current Inventory: ", defaultColor, menu);
                    }

					// If on the traveling menu
                    else if (onTravelingMenu.getTheBoolean()) {
                    	whileTravelingChoice = "";
                    	switchMenus(onWhileTravelingMenu);
                    	whileTravelingMenu.getChildren().clear();
                    	makeText("You can do the following", defaultColor, whileTravelingMenu, Pos.TOP_CENTER, 0, 0);
                    	makeText("1. Continue on the trail.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -150);
                    	makeText("2. Check your supplies.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -100);
                    	makeText("3. Look at the map.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, -50);
                    	makeText("4. Change pace.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 0);
                    	makeText("5. Change food rations.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 50);
                    	makeText("6. Hunt for food.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 100);
                    	makeText("7. Save Game.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 150);
                    	makeText("8. Main Menu - !!!WILL NOT SAVE GAME!!!.", defaultColor, whileTravelingMenu, Pos.CENTER, 0, 200);


                    	 root.getChildren().remove(whileTravelingMenu);
 						 root.getChildren().add(whileTravelingMenu);
                    }

					// This is for the various choices the user can pick on the whileTraveling menu
                    else if (onWhileTravelingMenu.getTheBoolean()) {

                    	// Return to traveling menu
                    	if (whileTravelingChoice.equals("1")) {
                    		redrawTravelingMenu(root, travelingMenu);


                    	}

                    	else if (whileTravelingChoice.equals("2")) {
                    		switchMenus(onSuppliesMenu);
                    		suppliesMenu.getChildren().clear();
                    		makeText("You have the following supplies", defaultColor, suppliesMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText("Oxen - " + controller.getItemAmount("oxen"), defaultColor, suppliesMenu, Pos.CENTER, 0, -150);
                    		makeText("Ammo - " + controller.getItemAmount("ammo"), defaultColor, suppliesMenu, Pos.CENTER, 0, -125);
                    		makeText("Food - " + controller.getItemAmount("food"), defaultColor, suppliesMenu, Pos.CENTER, 0, -100);
                    		makeText("Blankets - " + controller.getItemAmount("blanket"), defaultColor, suppliesMenu, Pos.CENTER, 0, -75);
                    		makeText("Gallons of Water - " + controller.getItemAmount("water"), defaultColor, suppliesMenu, Pos.CENTER, 0, -50);
                    		makeText("Press Backspace to Go Back", defaultColor, suppliesMenu, Pos.BOTTOM_CENTER, 0, 0);

                    		root.getChildren().remove(suppliesMenu);
                    		root.getChildren().add(suppliesMenu);

                    	}

                        else if (whileTravelingChoice.equals("3")) {
                    		//view map
                        	buildMapScreen(root);
                        	switchMenus(onMap);
                        	mapScreen.toFront();
                    	}

                        else if (whileTravelingChoice.equals("4")) {
                    		//change travel pace
                        	changePace = "";
                            switchMenus(onChangePaceMenu);

                            putScaledImage("images/running.gif", changePaceMenu, Pos.CENTER, 150, 150, 0, -100);

                            makeText("Select a Traveling speed", defaultColor, changePaceMenu, Pos.TOP_CENTER, 0, 0);
                            makeText("1 - Resting", defaultColor, changePaceMenu, Pos.CENTER, 0, 0);
                            makeText("2 - Slow", defaultColor, changePaceMenu, Pos.CENTER, 0, 50);
                            makeText("3 - Steady", defaultColor, changePaceMenu, Pos.CENTER, 0, 100);
                            makeText("4 - Fast", defaultColor, changePaceMenu, Pos.CENTER, 0, 150);
                            makeText("5 - Grueling", defaultColor, changePaceMenu, Pos.CENTER, 0, 200);

                            //controller.setPace(Integer.valueOf(travelingPace) - 1);
                            root.getChildren().remove(changePaceMenu);
                            root.getChildren().add(changePaceMenu);
                    	}

                        else if (whileTravelingChoice.equals("5")) {
                        	changeRations = "";
                            switchMenus(onRationsMenu);
                            rationsMenu.getChildren().clear();
                            makeText("Change food rations", defaultColor, rationsMenu, Pos.TOP_CENTER, 0,0);
                            makeText("Change the amount of food your party can eat each day.", defaultColor, rationsMenu, Pos.TOP_CENTER, 0,50);
                            putScaledImage("images/change_rations.jpg", rationsMenu, Pos.TOP_CENTER, 150, 300, 0, 100);
                            makeText("1 - Filling: meals are large", defaultColor, rationsMenu, Pos.CENTER, 0,0);
                            makeText("2 - Meager: meals are small", defaultColor, rationsMenu, Pos.CENTER, 0,50);
                            makeText("3 - Bare Bones: meals are very small", defaultColor, rationsMenu, Pos.CENTER, 0,100);

                            // scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                            //
                            //     @Override
                            //     public void handle(KeyEvent keyEvent) {
                            //         if(keyEvent.getCode() == KeyCode.ENTER) {
                            //             switchMenus(onTravelingMenu);
                            //             root.getChildren().remove(travelingMenu);
                            //             root.getChildren().add(travelingMenu);
                            //             //primaryStage.setScene(scene);
                            //         }
                            //
                            //     }
                            // });

                            root.getChildren().remove(rationsMenu);
                            root.getChildren().add(rationsMenu);
                    	}

                    	// chose hunting game
                    	else if (whileTravelingChoice.equals("6")) {
                    		switchMenus(onHuntingExplanationMenu);
                    		makeText("You will now hunt the Coues. These are Arizona's very own deer!", defaultColor, huntingExplanationMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText("Press space to shoot. When you shoot you will lose 1 bullet.", defaultColor, huntingExplanationMenu, Pos.TOP_CENTER, 0, 25);
                    		putScaledImage("images/coues.jpg", huntingExplanationMenu, Pos.CENTER, 300, 300, 0, 0);
                    		makeText("When you're done hunting press the Escape key.", defaultColor, huntingExplanationMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		root.getChildren().remove(huntingExplanationMenu);
                    		root.getChildren().add(huntingExplanationMenu);
                    	}

                        else if (whileTravelingChoice.equals("7")) {
                    		//save game
                            switchMenus(onSavedGameMenu);

                            makeText("Your Game Was Saved!", defaultColor, savedGameMenu, Pos.TOP_CENTER, 0,50);
                            putScaledImage("images/save_game.jpg", savedGameMenu, Pos.CENTER, 300, 600, 0, 50);
                            makeText("Press Enter to continue.", defaultColor, savedGameMenu, Pos.BOTTOM_CENTER, 0,0);

                            root.getChildren().remove(savedGameMenu);
                            root.getChildren().add(savedGameMenu);

                    	}
                        else if (whileTravelingChoice.equals("8")) {
                        	switchMenus(onMainMenu);
                        	mainMenu.toFront();
                        }
                    }
					
                    else if (onHuntingExplanationMenu.getTheBoolean()) {
                		try {
                			switchMenus(onHuntingMenu);
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
						} catch (Exception e) {
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
                    	allShoppingChoice = "";
                    	tubacShopChoice = "";
                    	switchMenus(onTubacShopMenu);

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


                    	root.getChildren().remove(tubacShopMenu);
                    	root.getChildren().add(tubacShopMenu);

                    }

                    else if (onTucsonTransitionMenu.getTheBoolean()) {
                    	allShoppingChoice = "";
                    	tucsonShopChoice = "";
                    	switchMenus(onTucsonShopMenu);

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


                    	root.getChildren().remove(tucsonShopMenu);
                    	root.getChildren().add(tucsonShopMenu);

                    }

                    else if (onPicachoTransitionMenu.getTheBoolean()) {
                    	allShoppingChoice = "";
                    	picachoShopChoice = "";
                    	switchMenus(onPicachoShopMenu);

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


                    	root.getChildren().remove(picachoShopMenu);
                    	root.getChildren().add(picachoShopMenu);

                    }

                    else if (onPhoenixTransitionMenu.getTheBoolean()) {
                    	allShoppingChoice = "";
                    	phoenixShopChoice = "";
                    	switchMenus(onPhoenixShopMenu);

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
                    	allShoppingChoice = "";
                    	prescottShopChoice = "";
                    	switchMenus(onPrescottShopMenu);

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


                    	root.getChildren().remove(prescottShopMenu);
                    	root.getChildren().add(prescottShopMenu);
                    }

                    else if (onFlagstaffTransitionMenu.getTheBoolean()) {
                    	allShoppingChoice = "";
                    	flagstaffShopChoice = "";
                    	switchMenus(onFlagstaffShopMenu);

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


                    	root.getChildren().remove(flagstaffShopMenu);
                    	root.getChildren().add(flagstaffShopMenu);
                    }

                    else if (onGrandCanyonTransitionMenu.getTheBoolean()) {
                    	allShoppingChoice = "";
                    	grandCanyonShopChoice = "";
                    	switchMenus(onGrandCanyonShopMenu);

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


                    	root.getChildren().remove(grandCanyonShopMenu);
                    	root.getChildren().add(grandCanyonShopMenu);

                    }
					
					// enter to go back from map to traveling menu
                    else if(onMap.getTheBoolean()) {
                    	switchMenus(onWhileTravelingMenu);
                    	whileTravelingMenu.toFront();
			        }

					// hit enter on shop menu
                    else if (onTubacShopMenu.getTheBoolean()) {
                    	if (tubacShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = tubacStore;
                    		onCurrStore = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceName = "food";
                    		currStore = tubacStore;
                    		onCurrStore = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = tubacStore;
                    		onCurrStore = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tubacStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = tubacStore;
                    		onCurrStore = onTubacShopMenu;
                    		currStoreMenu = tubacShopMenu;
                    	}

                    	else if (tubacShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	tubacShopChoice = "";
                    }

					// hit enter on shop menu
                    else if (onTucsonShopMenu.getTheBoolean()) {
                    	if (tucsonShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = tucsonStore;
                    		onCurrStore = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceName = "blanket";
                    		currStore = tucsonStore;
                    		onCurrStore = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = tucsonStore;
                    		onCurrStore = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, tucsonStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = tucsonStore;
                    		onCurrStore = onTucsonShopMenu;
                    		currStoreMenu = tucsonShopMenu;
                    	}

                    	else if (tucsonShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	tucsonShopChoice = "";
                    }

					// hit enter on shop menu
                    else if (onPicachoShopMenu.getTheBoolean()) {
                    	
                    	if (picachoShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = picachoStore;
                    		onCurrStore = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceName = "blanket";
                    		currStore = picachoStore;
                    		onCurrStore = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = picachoStore;
                    		onCurrStore = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, picachoStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = picachoStore;
                    		onCurrStore = onPicachoShopMenu;
                    		currStoreMenu = picachoShopMenu;
                    	}

                    	else if (picachoShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	picachoShopChoice = "";
                    }

                    else if (onPhoenixShopMenu.getTheBoolean()) {
                    	if (phoenixShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = phoenixStore;
                    		onCurrStore = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceName = "blanket";
                    		currStore = phoenixStore;
                    		onCurrStore = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = phoenixStore;
                    		onCurrStore = onPhoenixShopMenu; 
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, phoenixStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = phoenixStore;
                    		onCurrStore = onPhoenixShopMenu;
                    		currStoreMenu = phoenixShopMenu;
                    	}

                    	else if (phoenixShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	phoenixShopChoice = "";
                    }


                    else if (onPrescottShopMenu.getTheBoolean()) {
                    	if (prescottShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = prescottStore;
                    		onCurrStore = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceName = "food";
                    		currStore = prescottStore;
                    		onCurrStore = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = prescottStore;
                    		onCurrStore = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, prescottStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = prescottStore;
                    		onCurrStore = onPrescottShopMenu;
                    		currStoreMenu = prescottShopMenu;
                    	}

                    	else if (prescottShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	prescottShopChoice = "";
                    }


                    else if (onFlagstaffShopMenu.getTheBoolean()) {
                    	if (flagstaffShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "blanket", root, controller, "images/blanket.jpg");
                    		allShoppingChoiceName = "blanket";
                    		currStore = flagstaffStore;
                    		onCurrStore = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceName = "food";
                    		currStore = flagstaffStore;
                    		onCurrStore = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = flagstaffStore;
                    		onCurrStore = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, flagstaffStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = flagstaffStore;
                    		onCurrStore = onFlagstaffShopMenu;
                    		currStoreMenu = flagstaffShopMenu;
                    	}

                    	else if (flagstaffShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	flagstaffShopChoice = "";
                    }


                    else if (onGrandCanyonShopMenu.getTheBoolean()) {
                    	if (grandCanyonShopChoice.equals("1")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "oxen", root, controller, "images/ox.png");
                    		allShoppingChoiceName = "oxen";
                    		currStore = grandCanyonStore;
                    		onCurrStore = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoice.equals("2")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "food", root, controller, "images/lamb.png");
                    		allShoppingChoiceName = "food";
                    		currStore = grandCanyonStore;
                    		onCurrStore = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoice.equals("3")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "water", root, controller, "images/water.png");
                    		allShoppingChoiceName = "water";
                    		currStore = grandCanyonStore;
                    		onCurrStore = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoice.equals("4")) {
                    		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, grandCanyonStore, "ammo", root, controller, "images/bullet.png");
                    		allShoppingChoiceName = "ammo";
                    		currStore = grandCanyonStore;
                    		onCurrStore = onGrandCanyonShopMenu;
                    		currStoreMenu = grandCanyonShopMenu;
                    	}

                    	else if (grandCanyonShopChoice.equals("5")) {
                    		redrawTravelingMenu(root, travelingMenu);
                    	}
                    	grandCanyonShopChoice = "";
                    }

					// If player made a purchase
                    else if (onAllShoppingMenu.getTheBoolean()) {

                    	buyAtShop(controller, allShoppingChoiceName, Integer.valueOf(allShoppingChoice), currStore, currStoreMenu, onCurrStore, root );
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
                    		switchMenus(onGameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    		root.getChildren().remove(gameOverMenu);
                    		root.getChildren().add(gameOverMenu);
                    	}
                    }
					
                    else if (onStarvationMenu.getTheBoolean()) {
                    	if (!controller.getFamilyDead()) {
                    	redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	else {
                    		switchMenus(onGameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    		root.getChildren().remove(gameOverMenu);
                    		root.getChildren().add(gameOverMenu);
                    	}
                    }
					
                    else if (onThirstMenu.getTheBoolean()) {
                    	if (!controller.getFamilyDead()) {
                    	redrawTravelingMenu(root, travelingMenu);
                    	}
                    	
                    	else {
                    		switchMenus(onGameOverMenu);
                    		makeText("Game over. Everyone died.", defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 0);
                    		makeText(String.format("You earned %d points.", controller.getScore()), defaultColor, gameOverMenu, Pos.TOP_CENTER, 0, 50);
                    		makeText("Press ENTER to return to Main Menu", defaultColor, gameOverMenu, Pos.BOTTOM_CENTER, 0, 0);
                    		putScaledImage("images/death.jpg", gameOverMenu, Pos.CENTER, 300, 300, 0, 0);
                    		root.getChildren().remove(gameOverMenu);
                    		root.getChildren().add(gameOverMenu);
                    	}
                    }


				}

			}
		});


		// If we're on a menu and try typing something
		scene.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent keyEvent) {




				// If user types on MENU 2. NO TRANSITION
				if (onMenu2.getTheBoolean()) {
					name = printUserInput(keyEvent, name, nameText, menu2, Pos.BOTTOM_CENTER);
				}

				// If user types on MENU 3. NO TRANSITION
				else if (onMenu3.getTheBoolean()) {
					jobPicked = printUserInputNumber(keyEvent, jobPicked, jobPickedText, menu3, Pos.BOTTOM_CENTER);
				}

				else if (onJobExplanationScreen.getTheBoolean()) {
					if (keyEvent.getCharacter().equals("\b")) {
						jobPicked = "";
						switchMenus(onMenu3);
						root.getChildren().remove(menu3);
						root.getChildren().add(menu3);
					}
				}

				// MENU 4
				else if (onMenu4.getTheBoolean()) {
					dogName = printUserInput(keyEvent, dogName, dogNameText, menu4, Pos.BOTTOM_CENTER);
				}

				// MENU 5
				else if (onMenu5.getTheBoolean()) {
					//travelingCompanionName = printUserInput(keyEvent, travelingCompanionName, travelingCompanionNameText, menu5, Pos.BOTTOM_CENTER);
                    companionName = printUserInput(keyEvent, companionName, companionNameText, menu5, Pos.BOTTOM_CENTER);
                    //System.out.println();
				}

				// MENU 6
				else if (onMenu6.getTheBoolean()) {
					child1 = printUserInput(keyEvent, child1, child1Text, menu6, Pos.BOTTOM_CENTER);
				}

                //MENU 7
                else if (onMenu7.getTheBoolean()) {
					child2 = printUserInput(keyEvent, child2, child2Text, menu7, Pos.BOTTOM_CENTER);
				}

                //MENU 8
                else if (onMenu8.getTheBoolean()) {
					child3 = printUserInput(keyEvent, child3, child3Text, menu8, Pos.BOTTOM_CENTER);
				}

				// MENU 9
				else if (onMenu9.getTheBoolean()) {
					startingMonth = printUserInputNumber(keyEvent, startingMonth, startingMonthText, menu9, Pos.BOTTOM_CENTER);
				}

                //MENU 10
                else if (onMenu10.getTheBoolean()) {
                    numOxen = printUserInputBigNumber(keyEvent, numOxen, numOxenText, menu10, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numOxen) * 20; //$20 per oxen
                }

                //MENU 11
                else if (onMenu11.getTheBoolean()) {
                    poundsFood = printUserInputBigNumber(keyEvent, poundsFood, poundsFoodText, menu11, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(poundsFood); //$1 per pound
                }

                //MENU 12
                else if (onMenu12.getTheBoolean()) {
                    numClothing = printUserInputBigNumber(keyEvent, numClothing, numClothingText, menu12, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numClothing) * 10; //$10 per set
                }

                //MENU 13
                else if (onMenu13.getTheBoolean()) {
                    numAmmo = printUserInputBigNumber(keyEvent, numAmmo, numAmmoText, menu13, Pos.BOTTOM_CENTER);
                    //money -= Integer.parseInt(numAmmo) * 2; //$2 per box
                }

                //MENU 14
                else if (onMenu14.getTheBoolean()) {
                    gallonsWater = printUserInputBigNumber(keyEvent, gallonsWater, gallonsWaterText, menu14, Pos.BOTTOM_CENTER);
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
                	tubacShopChoice = printUserInputStoreNumber(keyEvent, tubacShopChoice, tubacShopChoiceText, tubacShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onTucsonShopMenu.getTheBoolean()) {
                	tucsonShopChoice = printUserInputStoreNumber(keyEvent, tucsonShopChoice, tucsonShopChoiceText, tucsonShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPicachoShopMenu.getTheBoolean()) {
                	picachoShopChoice = printUserInputStoreNumber(keyEvent, picachoShopChoice, picachoShopChoiceText, picachoShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPhoenixShopMenu.getTheBoolean()) {
                	phoenixShopChoice = printUserInputStoreNumber(keyEvent, phoenixShopChoice, phoenixShopChoiceText, phoenixShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onPrescottShopMenu.getTheBoolean()) {
                	prescottShopChoice = printUserInputStoreNumber(keyEvent, prescottShopChoice, prescottShopChoiceText, prescottShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onFlagstaffShopMenu.getTheBoolean()) {
                	flagstaffShopChoice = printUserInputStoreNumber(keyEvent, flagstaffShopChoice, flagstaffShopChoiceText, flagstaffShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onGrandCanyonShopMenu.getTheBoolean()) {
                	grandCanyonShopChoice = printUserInputStoreNumber(keyEvent, grandCanyonShopChoice, grandCanyonShopChoiceText, grandCanyonShopMenu, Pos.BOTTOM_CENTER);
                }

                else if (onAllShoppingMenu.getTheBoolean()) {
                	allShoppingChoice = printUserInputBigNumber(keyEvent, allShoppingChoice, allShoppingChoiceText, allShoppingMenu, Pos.BOTTOM_CENTER);
                }
			}
		});

        primaryStage.setOnCloseRequest((WindowClosing) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Game");
            alert.setHeaderText("Save game before exiting?");
            //alert.setContentText("Are you ok with this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //user clicks OK
                controller.saveGame(save_game_file);
            }
        });
	}

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
		Text theText = new Text(aString);
		theText.setFont(new Font("ArcadeClassic", 24));
		theText.setFill(aColor);
		menu.getChildren().add(theText);
		theText.setTranslateX(translateX);
		theText.setTranslateY(translateY);
		StackPane.setAlignment(theText, position);
		return theText;
	}

	/**
	 * This method allows for the quick switching of menus
	 * @param currMenu The menu that we want to switch to
	 */
	public static void switchMenus(MenuClass currMenu) {
		// Keeps track of whether or not this menu was already in the list.
		// If it wasn't, then it is added.
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

		theString.trim();
		return theString;
	}

	public String printUserInputNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") || keyEvent.getCharacter().equals("4"))
		   && theString.length() == 0) {
			theString = keyEvent.getCharacter();
		}
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
		return theString;
	}

	public String printUserInputStoreNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") || keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5"))
		   && theString.length() == 0) {
			theString = keyEvent.getCharacter();
		}
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
		return theString;
	}


	public String printUserInputBigNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If user inputs a number
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") ||
			 keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5") || keyEvent.getCharacter().equals("6") ||
			 keyEvent.getCharacter().equals("7") || keyEvent.getCharacter().equals("8") || keyEvent.getCharacter().equals("9") ||
			 keyEvent.getCharacter().equals("0"))) {

			// add num
			theString = theString + keyEvent.getCharacter();
		}
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
		return theString;
	}

	public String printUserInputMediumNumber(KeyEvent keyEvent, String theString, TextClass theText, StackPane menu, Pos position) {

		// If user inputs a number
		if ((keyEvent.getCharacter().equals("1") || keyEvent.getCharacter().equals("2") || keyEvent.getCharacter().equals("3") ||
			 keyEvent.getCharacter().equals("4") || keyEvent.getCharacter().equals("5") || keyEvent.getCharacter().equals("6") ||
			 keyEvent.getCharacter().equals("7") || keyEvent.getCharacter().equals("8")) && theString.length() == 0) {

			// add num
			theString = keyEvent.getCharacter();
		}
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
		return theString;
	}



	public void buildMenu(StackPane root, StackPane menu, Color backgroundColor) {
		menu.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
		root.getChildren().add(menu);
	}
	
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
		
		//mapDisplay.getChildren().add(locationInfo);
		
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
							
							makeText(String.format("Distance\nas the crow flies:\n\t%.3f miles", 
											controller.calcMiles(controller.getCurrLocationObj(), l)), 
									defaultColor,
									locationInfo,
									Pos.TOP_CENTER, 
									0, 
									100).setWrappingWidth((root.getWidth() / 4));
							makeText(
									String.format("Region:\n\t%s", 
											l.getRegion().getName()), 
									defaultColor,
									locationInfo,
									Pos.TOP_CENTER, 
									0, 
									175).setWrappingWidth((root.getWidth() / 4));
							makeText(
									String.format("Fall Temperature:\n\t%.0f F", 
											l.getRegion().getBaseTemperature()), 
									defaultColor,
									locationInfo,
									Pos.TOP_CENTER, 
									0, 
									225).setWrappingWidth((root.getWidth() / 4));
							
						}
					});
			location.setOnMouseExited(
					new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							initMapLocationInfo(locationInfo);
						}
					});
			
			/*
			location.setStyle("-fx-border-width: 5px; "
					+ "-fx-border-style: solid; "
					+ "-fx-border-color: purple;");
			*/
			
			mapDisplay.getChildren().add(location);
			initMapLocationInfo(locationInfo);
		}
		
		mapSplitter.getChildren().addAll(mapDisplay, locationInfo);
		mapScreen.getChildren().add(mapSplitter);
		
		makeText("Map of Arizona", defaultColor, mapScreen, Pos.TOP_CENTER, 0, 0);
    	makeText("Press ENTER to continue", defaultColor, mapScreen, Pos.BOTTOM_CENTER, 0, 0);
	}
	
	private void initMapLocationInfo(StackPane sp) {
		sp.getChildren().clear();
		makeText("Mouse Over", defaultColor, sp, Pos.CENTER, 0, -50);
		makeText("a Location", defaultColor, sp, Pos.CENTER, 0, -25);
		makeText("for", defaultColor, sp, Pos.CENTER, 0, 0);
		makeText("More", defaultColor, sp, Pos.CENTER, 0, 25);
		makeText("Information", defaultColor, sp, Pos.CENTER, 0, 50);
	}

	public static void putScaledImage(String image, StackPane menu, Pos position, int width, int height, int translateX, int translateY) {
		Image theImage = new Image(new File(image).toURI().toString(), width, height, true, false);
		ImageView theImageView = new ImageView(theImage);
		StackPane.setAlignment(theImageView, position);
		theImageView.setTranslateX(translateX);
		theImageView.setTranslateY(translateY);
		menu.getChildren().add(theImageView);
	}

	public void redrawTravelingMenu(StackPane root, StackPane travelingMenu) {

        //boolean validBuy = controller.buyItem("water", Integer.valueOf(gallonsWater));
		whileTravelingChoice = "";
		switchMenus(onTravelingMenu);

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
	     final Animation animation = new WagonAnimation(
	                imageView,
	                Duration.millis(1000),
	                COUNT, COLUMNS,
	                OFFSET_X, OFFSET_Y,
	                WIDTH, HEIGHT
	        );
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

	     root.getChildren().remove(travelingMenu);
	     root.getChildren().add(travelingMenu);
	}

	public void buyAtShop(Controller controller, String item, int amount, Store store, StackPane menu, MenuClass menuClass, StackPane root) {


    	boolean validBuy = controller.buyItemTownStore(item, amount, store);

    	// only continue if buy is valid
    	if (validBuy) {
    	allShoppingChoice = "";
        switchMenus(menuClass);
        root.getChildren().remove(menu);
		root.getChildren().add(menu);
    	}
	}

	public static void shoppingAtShop(MenuClass menuClass, StackPane menu, Store store, String item, StackPane root, Controller controller, String image) {
		switchMenus(menuClass);
		menu.getChildren().clear();
		makeText("I have " + store.getItem(item).getQuantity() + " " + item + "in stock.", defaultColor, menu, Pos.TOP_CENTER, 0, 0);
		makeText("Each " +  item + " costs " + store.getItem(item).getCost() + " dollars.", defaultColor, menu, Pos.TOP_CENTER, 0, 25);
		makeText("You have " + controller.getItemAmount(item) + " " + item + " in your inventory.", defaultColor, menu, Pos.TOP_CENTER, 0, 75);
		makeText("You have " + String.format("%.2f", controller.getMoney()) + " dollars to spend.", defaultColor, menu, Pos.TOP_CENTER, 0, 100);
		makeText("Choose how many to buy.", defaultColor, menu, Pos.TOP_CENTER, 0, 150);
		putScaledImage(image, menu, Pos.CENTER, 150, 150, 0, 0);
		root.getChildren().remove(menu);
		root.getChildren().add(menu);
	}

	public static void reduceShoppingCode(MenuClass onAllShoppingMenu, StackPane allShoppingMenu, Store store, String item, StackPane root, Controller controller, String image, String allShoppingChoiceName, Store currStore, MenuClass onCurrStore, MenuClass onShopMenu, StackPane currStoreMenu, StackPane storeMenu) {
		shoppingAtShop(onAllShoppingMenu, allShoppingMenu, store, item, root, controller, image);
		allShoppingChoiceName = item;
		currStore = store;
		onCurrStore = onShopMenu;
		currStoreMenu = storeMenu;
	}

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

	public static void main(String []args) {
		launch(args);
	}
}
