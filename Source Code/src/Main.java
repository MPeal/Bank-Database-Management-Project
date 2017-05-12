package application;
	
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Bank Database Management Program
 * 
 * This program provided a user interface for interacting with a database.
 * It is intended to be for an end user handling employee information as well
 * as building sales reports based on data gathered from the database.
 * 
 * @Version 8.3.1
 * 
 */

public class Main extends Application {
	

	public void start(Stage ps) {
		try {
		
			ps.setTitle("Version 8.3.1");
			VBox v = new VBox();    
			ImageView selectedImage = new ImageView();   
	        Image animate = new Image(Main.class.getResourceAsStream("r2.gif"));
	        selectedImage.setImage(animate);
			v.getChildren().addAll(selectedImage);
			Scene s2 = new Scene(v,500,400);
			Timer t = new Timer();
			t.schedule(new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(() -> {
							
							new home(ps);
			            });		
					}
		        }, 1300); 
			ps.setScene(s2);
			ps.show();
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	
		launch(args);
		
		System.out.print("Kaboom! ");
		System.exit(0);
	}
	
}
