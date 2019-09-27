import javafx.application.Application;
import javafx.stage.Stage;

public class DrawGame extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		HuntingGame game = new HuntingGame();
		game.drawText("What", 200, 100);
		game.drawText("Okay", 200, 100);
		arg0.setScene(game.getScene());
		arg0.show();
	}
	public static void main(String [] args) {
		launch(args);
	}
}
