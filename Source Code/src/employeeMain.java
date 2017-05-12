package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class employeeMain 
{
	Stage ps;
	public employeeMain (Stage st)
	{
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.CADETBLUE);
		ps = st;
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",22));
		t1.setX(160); 
		t1.setY(44); 
		t1.setText("EMPLOYEE MENU");
		
		
		Label l1 = new Label();
		l1.setText("Search For Employee");
		l1.setFont(Font.font("Yu Gothic",16));
		l1.setLayoutX(118);
		l1.setLayoutY(110);
		
		Button b1 = new Button("Search Employee");
		b1.setLayoutX(40);
		b1.setLayoutY(120);
		b1.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		double r=1.5;
		b1.setShape(new Circle(r));
		b1.setMinSize(2*r, 2*r);
		b1.setMaxSize(2*r, 2*r);
		
		Image image = new Image(getClass().getResource("search.png").toExternalForm(), 70, 70, true, true);
		b1.setGraphic(new ImageView(image));
		b1.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b1.setEffect(shadow);
			        }
			});
			b1.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b1.setEffect(null);
			        }
			});
		
		b1.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		       new employeeFunctions(ps).searchEmp();
		    }
		 } );
		
		Label l2 = new Label();
		l2.setText("Add New Employee");
		l2.setFont(Font.font("Yu Gothic",16));
		l2.setLayoutX(235);
		l2.setLayoutY(167);
		
		Button b2 = new Button("Add New Employee");
		b2.setLayoutX(390);
		b2.setLayoutY(172);
		b2.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		b2.setShape(new Circle(r));
		b2.setMinSize(2*r, 2*r);
		b2.setMaxSize(2*r, 2*r);
		
		Image image2 = new Image(getClass().getResource("add.png").toExternalForm(), 70, 70, true, true);
		b2.setGraphic(new ImageView(image2));
		b2.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b2.setEffect(shadow);
			        }
			});
			b2.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b2.setEffect(null);
			        }
			});
		b2.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		       new employeeFunctions(ps).addEmp();
		    }
		 } );
		
		
		Label l3 = new Label();
		l3.setText("Terminate Employee");
		l3.setFont(Font.font("Yu Gothic",16));
		l3.setLayoutX(118);
		l3.setLayoutY(225);
		
		Button b3 = new Button("Terminate Employee");
		b3.setLayoutX(40);
		b3.setLayoutY(238);
		b3.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		
		b3.setShape(new Circle(r));
		b3.setMinSize(2*r, 2*r);
		b3.setMaxSize(2*r, 2*r);
		
		Image image3 = new Image(getClass().getResource("delete1.png").toExternalForm(), 70, 70, true, true);
		b3.setGraphic(new ImageView(image3));
		b3.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b3.setEffect(shadow);
			        }
			});
			b3.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b3.setEffect(null);
			        }
			});
		b3.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		       new employeeFunctions(ps).deleteEmp();
		    }
		 } );
		
		Label l4 = new Label();
		l4.setText("Update Employee Data");
		l4.setFont(Font.font("Yu Gothic",16));
		l4.setLayoutX(210);
		l4.setLayoutY(285);
		
		Button b4 = new Button("Update Info");
		b4.setPrefSize(150, 50);
		b4.setLayoutX(390);
		b4.setLayoutY(295);
		//b4.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: aliceblue  ; "); 
		b4.setStyle(" -fx-border-color: transparent; -fx-body-color: white  ;  -fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
				+ " -fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; -fx-background-insets: 0, 1, 2; "
				+ "-fx-background-radius: 5, 4, 3;");
		
		
		b4.setShape(new Circle(r));
		b4.setMinSize(2*r, 2*r);
		b4.setMaxSize(2*r, 2*r);
		
		Image image4 = new Image(getClass().getResource("update.png").toExternalForm(), 70, 70, true, true);
		b4.setGraphic(new ImageView(image4));
		b4.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b4.setEffect(shadow);
			        }
			});
			b4.addEventHandler(MouseEvent.MOUSE_EXITED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			            b4.setEffect(null);
			        }
			});
		
		b4.setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent e) 
		    {
		        new employeeFunctions(ps).updateEmp();
		    }
		 } );
	
		Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
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
			
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
	    list.addAll(l1,l2,l3,l4,t1,b1,b2,b3,b4,re);       
	    
	    Scene s2 = new Scene(root,500,400);
		ps.setScene(s2);
		ps.show();
	}
}
