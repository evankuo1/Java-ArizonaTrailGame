import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HuntingGame extends BorderPane {

    private static final double W = 600, H = 400;

    private static final String IMAGE_URL = "http://pixelartmaker.com/art/5ff29281238ef04.png";

	protected static final int SPEED = 2;
	ImageView deerView;
	ImageView deerView1;
	ImageView deerView2;
	Text t;
    private Image userImage;
    private Node  user;
    private int deer=3;
    boolean sprint, goNorth, goSouth, goEast, goWest;
    

	protected boolean shoot;

	protected boolean shootAniDone=true;
	protected int bullets;
	protected int meat=0;
	Timeline timeline = new Timeline();
	private Rectangle deerBox = new Rectangle(75,75);
	private Rectangle deerBox1 = new Rectangle(75,75);
	private Rectangle deerBox2 = new Rectangle(75,75);
    public HuntingGame() throws Exception {
    	super();
    	deerBox.setFill(Color.TRANSPARENT);
    	this.getChildren().add(deerBox);
    	deerBox.relocate(300, 0);
    	deerBox1.setFill(Color.TRANSPARENT);
    	this.getChildren().add(deerBox1);
    	deerBox1.relocate(100, 200);
    	deerBox2.setFill(Color.TRANSPARENT);
    	this.getChildren().add(deerBox2);
    	deerBox2.relocate(0, 100);
    	bullets=20;
    	userImage = new Image(IMAGE_URL,75,75, false, true);
    	user = new ImageView(userImage);

        Pane field = new Pane(user);
        this.getChildren().add(field);
        //center the user
        moveUserTo(W / 2, H-75);
        
        Image deerTarget = new Image("https://i.pinimg.com/originals/1f/b1/be/1fb1be2b7907d69a8979a9acced17673.png",75,75,false,false);
         deerView = new ImageView(deerTarget);
        this.getChildren().add(deerView);
        deerView.relocate(300, 0);
        
        Image deerTarget1 = new Image("https://i.pinimg.com/originals/1f/b1/be/1fb1be2b7907d69a8979a9acced17673.png",75,75,false,false);
        deerView1 = new ImageView(deerTarget1);
       this.getChildren().add(deerView1);
       deerView1.relocate(100, 200);
       
       Image deerTarget2 = new Image("https://i.pinimg.com/originals/1f/b1/be/1fb1be2b7907d69a8979a9acced17673.png",75,75,false,false);
       deerView2 = new ImageView(deerTarget2);
      this.getChildren().add(deerView2);
      deerView2.relocate(0, 100);
        
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        Scene scene = new Scene(this, W, H, Color.FORESTGREEN);
        //set flags for what user pressed
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = true; break;
                    case DOWN:  goSouth = true; break;
                    case LEFT:  goWest  = true; break;
                    case RIGHT: goEast  = true; break;
                    case SHIFT: sprint = true; break;
                    case SPACE: shoot = true; break;
                    
				default:
					break;
                }
            }
        });
        //remove flags for when user releases arrowkeys or sprint
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goNorth = false; break;
                    case DOWN:  goSouth = false; break;
                    case LEFT:  goWest  = false; break;
                    case RIGHT: goEast  = false; break;
                    case SHIFT: sprint = false; break;
                    case SPACE: shoot = false; break;
				default:
					break;
                }
            }
        });

        
        
        //Run through user input constantly based on the flags set by the event handler.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= SPEED;
                if (goSouth) dy += SPEED;
                if (goEast)  dx += SPEED;
                if (goWest)  dx -= SPEED;
                if (sprint) { dx *= 3; dy *= 3; }
                if (shoot && shootAniDone){
                	 shoot(user.getLayoutX(), user.getLayoutY());
                	 }

                moveUserBy(dx, dy);
            }
        };
        timer.start();
        TranslateTransition tt = new TranslateTransition(Duration.millis(1000),deerBox);
        tt.setByX(225);
        tt.setAutoReverse(true);
        tt.setCycleCount(Animation.INDEFINITE);
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(1000),deerBox1);
        tt1.setByX(150);
      
        tt1.setAutoReverse(true);
        tt1.setCycleCount(Animation.INDEFINITE);
        TranslateTransition tt2 = new TranslateTransition(Duration.millis(2000),deerBox2);
        tt2.setByX(300);
        tt2.setAutoReverse(true);
        tt2.setCycleCount(Animation.INDEFINITE);
        TranslateTransition tt3 = new TranslateTransition(Duration.millis(1000),deerView);
        tt3.setByX(225);
        tt3.setAutoReverse(true);
        tt3.setCycleCount(Animation.INDEFINITE);
        TranslateTransition tt4 = new TranslateTransition(Duration.millis(2000),deerView2);
        tt4.setByX(300);
        tt4.setAutoReverse(true);
        tt4.setCycleCount(Animation.INDEFINITE);
        TranslateTransition tt5 = new TranslateTransition(Duration.millis(1000),deerView1);
        tt5.setByX(150);
        
        tt5.setAutoReverse(true);
        tt5.setCycleCount(Animation.INDEFINITE);
        tt.play();
        tt1.play();
        tt2.play();
        tt3.play();
        tt4.play();
        tt5.play();
        
    }

    private void moveUserBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = user.getBoundsInLocal().getWidth()  / 2;
        final double cy = user.getBoundsInLocal().getHeight() / 2;

        double x = cx + user.getLayoutX() + dx;
        double y = cy + user.getLayoutY() + dy;

        moveUserTo(x, y);
    }

    private void moveUserTo(double x, double y) {
        final double cx = user.getBoundsInLocal().getWidth()  / 2;
        final double cy = user.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= W &&
            y - cy >= 0 &&
            y + cy <= H) {
        	user.relocate(x - cx, y - cy);
        }
    }
    public void shoot( double x, double y){
    			BorderPane ref = this;
    			if (bullets >=1) {
	    	        Rectangle rectBasicTimeline = new Rectangle(user.getLayoutX()+50, user.getLayoutY(), 10, 10);
	    	        rectBasicTimeline.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
	    	            public void changed(ObservableValue<? extends Bounds> observable,
	    	                    Bounds oldValue, Bounds newValue) {
	    	                
	    	                    if (((Path)Shape.intersect(rectBasicTimeline, deerBox)).getElements().size() > 0) {
	    	                        addMeat();
	    	                    	
	    	                    	ref.getChildren().remove(deerBox);
	    	                    	ref.getChildren().remove(deerView);
	    	                        
	    	                    	ref.getChildren().remove(rectBasicTimeline);
	    	                    }
	    	                    else if (((Path)Shape.intersect(rectBasicTimeline, deerBox1)).getElements().size() > 0) {
	    	                        addMeat();
	    	                    	
	    	                    	ref.getChildren().remove(deerBox1);
	    	                    	ref.getChildren().remove(deerView1);
	    	                        
	    	                    	ref.getChildren().remove(rectBasicTimeline);
	    	                    }
	    	                    else if (((Path)Shape.intersect(rectBasicTimeline, deerBox2)).getElements().size() > 0) {
	    	                        
	    	                        addMeat();
	    	                    	ref.getChildren().remove(deerBox2);
	    	                    	ref.getChildren().remove(deerView2);
	    	                        
	    	                    	ref.getChildren().remove(rectBasicTimeline);
	    	                    }
	    	                
	    	            }
	    	        });
	    	        KeyValue kv = new KeyValue(rectBasicTimeline.yProperty(), -100);
	    	        KeyFrame kf = new KeyFrame(Duration.millis(2600), kv);
	    	        this.getChildren().add(rectBasicTimeline);
	    	        
	    	        timeline.getKeyFrames().add(kf);
	    	   
	    	        shootAniDone=false;
	    	        timeline.playFromStart();
	    	        shootAniDone=true;
	    	        bullets--;
    			}
    	    }
    protected void addMeat() {
		if (deer!=0) {
			deer--;
			meat+=100;
			
		}
		
		
	}
    /**
     * Gets the number of pounds left over
     * @return number of pounds of meat user earned
     */
	public int getMeat() {
    	return meat;
    }
	/**
	 * Set the number of bullets the 
	 * @param x = number of bullets in inventory
	 */
    public void setBullets(int x) {
    	bullets=x;
    }
    /**
     * Gets the number of bullets left over
     * @return number of bullets left over
     */
    public int getBullets() {
    	return bullets;
    }
    /**
     * Draws a Text object and adds it to the Hunting Game
     * @param s - Desired text to be written on minigame screen
     * @param x - Desired x coordinate of the Text object
     * @param y - Desired y coordinate of the Text object
     */
    public void drawText(String s, int x, int y) {
    	this.getChildren().remove(t);
    	t= new Text(s);
    	
    	t.setY(y);
    	t.setX(x);
    	this.getChildren().add(t);
    }
}