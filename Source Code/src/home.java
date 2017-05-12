package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class home 
{
	public home (Stage ps)
	{
		final Glow glow = new Glow();
	    glow.setLevel(0.0);
	  
	    final Timeline timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.setAutoReverse(true);
	    final KeyValue kv = new KeyValue(glow.levelProperty(), 0.3);
	    final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
	    timeline.getKeyFrames().add(kf);
	    timeline.play();    
	    
		
		DropShadow shadow = new DropShadow();
		
		
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",30));
		t1.setX(195); 
		t1.setY(80); 
		t1.setText("Welcome");
		
		Text t2 = new Text();
		t2.setFont(Font.font("Yu Gothic",20));
		t2.setX(158);
		t2.setY(120);
		t2.setText("Select A Department");
		
		Label l1 = new Label();
		l1.setText("Employees");
		l1.setFont(Font.font("Yu Gothic",20));
		l1.setLayoutX(105);
		l1.setLayoutY(320);
		
		Label l2 = new Label();
		l2.setText("Sales");
		l2.setFont(Font.font("Yu Gothic",20));
		l2.setLayoutX(325);
		l2.setLayoutY(320);
		
		Button b1 = new Button("Manage Employees");
		b1.setLayoutX(95);
		b1.setLayoutY(250);
		b1.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		double r=1.5;
		b1.setShape(new Circle(r));
		b1.setMinSize(2*r, 2*r);
		b1.setMaxSize(2*r, 2*r);
		
		Image image = new Image(getClass().getResource("emp.png").toExternalForm(), 110, 110, true, true);
		b1.setGraphic(new ImageView(image));
	
		b1.setEffect(glow);
		 
		b1.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		    
		        new employeeMain(ps);
		    }
		 } );
		
		b1.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            b1.setEffect(shadow);
		        }
		});
		b1.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            b1.setEffect(glow);
		        }
		});
		
		
		Button b2 = new Button("Search Sales");
		
		b2.setLayoutX(295);
		b2.setLayoutY(250);
		b2.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		r=1.5;
		b2.setShape(new Circle(r));
		b2.setMinSize(2*r, 2*r);
		b2.setMaxSize(2*r, 2*r);
		
		Image image2 = new Image(getClass().getResource("report-icon.png").toExternalForm(), 110, 110, true, true);
		b2.setGraphic(new ImageView(image2));
		b2.setEffect(glow);
		b2.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		        new salesMain(ps);
		    }
		 } );
		
		b2.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            b2.setEffect(shadow);
		        }
		});
		b2.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent e) {
		            b2.setEffect(glow);
		        }
		});
		
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
	    list.addAll(t1,t2,l1,l2,b1,b2);       
	 
		Scene s1 = new Scene(root,500,400);
		ps.setScene(s1);
		ps.show();
		
	}
}
