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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class salesByEmp 
{
	Stage ps;
	ResultSet rs;
	public salesByEmp(Stage st)
	{
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.CADETBLUE);
		
		ps = st;
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",23));
		t1.setX(50); 
		t1.setY(50); 
		t1.setText("EMPLOYEE SALES");
		
		Text t2 = new Text();
		t2.setFont(Font.font("Calibri Light",20));
		t2.setText("Enter the Employee ID:");
		
		TextField id = new TextField();
		id.setPrefWidth(40);
		
		Label label = new Label();
		label.setLayoutX(300);
		label.setLayoutY(105);
		label.setTextFill(Color.RED);
		
		Label l2 = new Label();
		l2.setFont(Font.font("Calibri Light",18));
		
		
		Label result = new Label();
		result.setFont(Font.font("Calibri Light",21));
		
		
		GridPane g = new GridPane();
		g.setMinSize(500, 100); 
	    g.setPadding(new Insets(10, 10, 10, 10));  
	    g.setVgap(15); 
	    g.setHgap(30);     
	    g.setLayoutY(65);
	    g.setAlignment(Pos.CENTER); 
		g.add(t2, 0, 0);       
	    g.add(id, 1, 0); 
	    g.add(l2, 0, 2);       
	    g.add(result, 1, 2); 
	   
	    
	    Image image = new Image(getClass().getResource("sales.png").toExternalForm(), 100, 100, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(40);
		i1.setY(260);
		
		
		Button b1 = new Button("Search Total Sales");
		b1.setPrefSize(150, 50);
		b1.setLayoutX(175);
		b1.setLayoutY(200);
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
		b1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
			    public void handle(ActionEvent e) {
			        if ((id.getText() != null && !id.getText().isEmpty())) 
			        {
			           
			        	String entry = id.getText();
			        	System.out.println(entry + " was entered..");//////////////////////////////////////////////
			        	
			        	
			        	boolean check = isInteger(entry);
			        	
			        	
			            if(check == true){
			            	System.out.println("--Number confirmed--");//////////////////////////////////////
			            	int empNum = Integer.parseInt(entry);
			            	 
									try {
										label.setText(" ");
										int sumSales = new connector().getTotalEmployeeSales(empNum);
										
										System.out.println(sumSales + " is the cumulative sales.");////////////////////////
										l2.setText("Employee " + empNum + " total sales:");
										result.setText(Integer.toString(sumSales));
										
										
										
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										label.setText("Please enter a valid ID.");
									}
									
								
			            }
			            else
			            { 
			            	label.setText("Please enter a valid ID.");
			            }
			              
			         
			        } else {
			            label.setText("Please enter a valid ID.");
			        }
			     }
			 });
	
		Button b2 = new Button("Search Product Sales");
		b2.setPrefSize(150, 50);
		b2.setLayoutX(175);
		b2.setLayoutY(260);
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
			    public void handle(ActionEvent e) {
			        if ((id.getText() != null && !id.getText().isEmpty())) 
			        {
			           
			        	String entry = id.getText();
			        	System.out.println(entry + " was entered..");//////////////////////////////////////////////
			        	
			        	
			        	boolean check = isInteger(entry);
			        	
			        	
			            if(check == true){
			            	System.out.println("--Number confirmed--");//////////////////////////////////////
			            	int empNum = Integer.parseInt(entry);
			            	 
									try {
										rs = new connector().getEmployeeSalesRSByProd(empNum);
										new salesResults(ps,rs, Integer.toString(empNum));
										
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										label.setText("Please enter a valid ID.");
										
									}
									
								
			            }
			            else
			            { 
			            	label.setText("Please enter a valid ID.");
			            }
			              
			         
			        } else {
			            label.setText("Please enter a valid ID.");
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
	    list.addAll(t1,g,label,i1,b1,b2,re);       

			Scene s2 = new Scene(root,500,400);
			
		
		ps.setScene(s2);
		ps.show();
		
	}
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	   
	    return true;
	}
}
