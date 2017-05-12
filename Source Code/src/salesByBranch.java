package application;

import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class salesByBranch 
{
	Stage ps;
	ResultSet rs;
	public salesByBranch(Stage st)
	{
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.CADETBLUE);
		
		ps = st;
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",23));
		t1.setX(50); 
		t1.setY(50); 
		t1.setText("BRANCH SALES");
		
		Text t2 = new Text();
		t2.setFont(Font.font("Calibri Light",20));
		t2.setText("Select A Branch:");
		
		TextField id = new TextField();
		id.setPrefWidth(40);
		
		ChoiceBox<String> branchcb = new ChoiceBox<String>(); 
		branchcb.setStyle("-fx-body-color: white  ; ");
	    branchcb.getItems().addAll("1 - SCSU", "2 - Hamden", "3 - Branford", "4 - North Branford", 
	    							 "5 - North Haven", "6 - East Haven", "7 - West Haven", "8 - Yale", "9 - Guilford"); 
		
	    Image image = new Image(getClass().getResource("pie.png").toExternalForm(), 100, 100, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(40);
		i1.setY(260);
		
		Line line = new Line(); 
		
		Label l2 = new Label();
		l2.setFont(Font.font("Calibri Light",18));
		l2.setLayoutX(120);
		l2.setLayoutY(144);
		
	    Label l3 = new Label();
	    l3.setFont(Font.font("Calibri Light",18));
		l3.setLayoutX(75);
		l3.setLayoutY(175);
	    
		Label result = new Label();
		result.setFont(Font.font("Calibri Light",21));
		result.setLayoutX(215);
		result.setLayoutY(173);
		
		GridPane g = new GridPane();
		g.setMinSize(500, 100); 
	    g.setPadding(new Insets(10, 10, 10, 10));  
	    g.setVgap(15); 
	    g.setHgap(30);     
	    g.setLayoutY(55);
	    g.setAlignment(Pos.CENTER); 
		g.add(t2, 0, 0);       
	    g.add(branchcb, 1, 0); 
	   
	
		Label label = new Label();
		label.setLayoutX(272);
		label.setLayoutY(125);
		label.setTextFill(Color.RED);
		
		Button b1 = new Button("Sum Total Sales");
		b1.setPrefSize(150, 50);
		b1.setLayoutX(320);
		b1.setLayoutY(155);
		b1.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
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
		    	
		    	String choice = branchcb.getValue();
		    	if (choice != null)
		        {
		        
		        	System.out.println(choice);
		        	int branchNum = branchInt(choice);
		            	 
								try {
									label.setText(" ");
									int sumSales = new connector().getTotalBranchSales(branchNum);	
									System.out.println(sumSales + " is the cumulative sales.");
									l2.setText("Total sales");
									l3.setText(" Branch " + branchNum + ":");
									line.setStartX(50); 
									line.setStartY(170); 
									line.setEndX(270); 
									line.setEndY(170);
									result.setText(Integer.toString(sumSales));
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									label.setText("Error in database response.");
								}
							
		        }
		        else {
		            label.setText("Please select a branch.");
		        }
		    }
		 } );
		
		Button b2 = new Button("Employee Total Sales");
		b2.setPrefSize(150, 50);
		b2.setLayoutX(320);
		b2.setLayoutY(210);
		b2.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
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
		b2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) 
			{
				String choice = branchcb.getValue();
		    	if (choice != null)
		        {
		    		System.out.println(choice);
		        	int branchNum = branchInt(choice);
		            	 
								try {
										label.setText(" ");
										rs = new connector().getBranchSalesRSByEmployee(branchNum);
										new salesResults(ps).branchSalesByEmployee(rs,choice);
									
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										label.setText("Error in database response.");
									}
									
								
			     }else
			     { 
			    	 label.setText("Please select a branch.");
			     }
			              
			}
			     
		 });
		
		Button b3 = new Button("Sales By Product");
		b3.setPrefSize(150, 50);
		b3.setLayoutX(320);
		b3.setLayoutY(265);
		b3.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
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
		b3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) 
			{
				String choice = branchcb.getValue();
		    	if (choice != null)
		        {
		    		System.out.println(choice);
		        	int branchNum = branchInt(choice);
		            	 
								try {
										label.setText(" ");
										rs = new connector().getBranchSalesRSByProd(branchNum);
										new salesResults(ps).salesProdResults(rs,choice);
									
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										label.setText("Error in database response.");
									}
									
								
			     }else
			     { 
			    	 label.setText("Please select a branch.");
			     }
			              
			}
			     
		 });
		
		
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
		        new salesMain(ps);
		    }
		 } );
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
	    list.addAll(t1,g,l2,l3,result,i1,line,label,b1,b2,b3,re);       

			Scene s2 = new Scene(root,500,400);
			
		
		ps.setScene(s2);
		ps.show();
		
		
	}
	public static int branchInt(String choice) {
	    int result = 0;
	   
	    
	    switch (choice)
	    {
	    case "1 - SCSU":
	    	result = 1;
	    	break;
	    		 
	    case "2 - Hamden":
	    	result = 2;
	    	break;
   		 
	    case "3 - Branford":
	    	result = 3;
	    	break;
   		 
	    case "4 - North Branford":
	    	result = 4;
	    	break;
   		 
	    case "5 - North Haven":
	    	result = 5;
	    	break;
   		 
	    case "6 - East Haven":
	    	result = 6;
	    	break;
   		 
	    case "7 - West Haven":
	    	result = 7;
	    	break;
   		 
	    case "8 - Yale":
	    	result = 8;
	    	break;
   		 
	    case "9 - Guilford":
	    	result = 9;
	    	break;
	    	
   		 
	    }
	    
	    return result;
	}
}
