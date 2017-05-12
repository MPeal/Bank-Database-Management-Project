package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class salesMain 
{
	Stage ps;
	public salesMain(Stage st)
	{
		DropShadow shadow = new DropShadow();
		//shadow.setSpread(12);
		shadow.setColor(Color.TEAL);
		ps = st;
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",22));
		t1.setX(180); 
		t1.setY(44); 
		t1.setText("SALES MENU");
		
		Text t2 = new Text();
		t2.setFont(Font.font("Calibri Light",20));
		t2.setX(50); 
		t2.setY(100); 
		t2.setText("Select A Filter:");
			
		Button b1 = new Button("Filter By Employee");
		b1.setPrefSize(150, 50);
		b1.setLayoutX(80);
		b1.setLayoutY(140);
		b1.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
		b1.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b1.setEffect(shadow);
			            //b1.setStyle(" -fx-border-color: white; -fx-body-color: white  ; ");
			            b1.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: aliceblue  ; "); 
			        }
			});
			b1.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b1.setEffect(null);
			            b1.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
			        }
			});
		
		b1.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		       new salesByEmp(ps);
		    }
		 } );
		
		Button b2 = new Button("Filter By Branch");
		b2.setPrefSize(150, 50);
		b2.setLayoutX(260);
		b2.setLayoutY(140);
		b2.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
		b2.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b2.setEffect(shadow);
			            b2.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: aliceblue  ; "); 
			        }
			});
			b2.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b2.setEffect(null);
			            b2.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
			        }
			});
		
		b2.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		       new salesByBranch(ps);
		    }
		 } );
		
		
		Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
		double r=1.5;
		Button re = new Button("Back");
		re.setShape(new Circle(r));
		re.setMinSize(2*r, 2*r);
		re.setMaxSize(2*r, 2*r);

		re.setLayoutX(225);
		re.setLayoutY(350);
		re.setGraphic(new ImageView(returni));
		
		re.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	re.setEffect(shadow);
			        }
			});
			re.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            re.setEffect(null);
			        }
			});
		
		re.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		        new home(ps);
		    }
		 } );
		
		Image image = new Image(getClass().getResource("filter.png").toExternalForm(), 140, 140, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(204);
		i1.setY(220);
		
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
	    list.addAll(t1,t2,i1,b1,b2,re);       

			Scene s2 = new Scene(root,500,400);
			
		
		ps.setScene(s2);
		ps.show();
	}
}
