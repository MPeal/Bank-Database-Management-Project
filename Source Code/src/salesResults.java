package application;

import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class salesResults
{
	Stage stage;
	TableView<empSalesInfo> table = new TableView<empSalesInfo>();
	DropShadow shadow = new DropShadow();
	
	
	public salesResults(Stage ps,ResultSet rs, String empNum)
	{
		shadow.setColor(Color.CADETBLUE);
		stage = ps;
		try {
			ArrayList<String[]> list = new connector().listForEmpSalesByProd(rs);
			tableDisplay(empNum, list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public salesResults(Stage ps)
	{
		stage = ps;
		shadow.setColor(Color.CADETBLUE);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void tableDisplay(String empNum, ArrayList<String[]> list)
	{
		Text t1 = new Text();
		t1.setFont(Font.font("Yu Gothic",18));
		t1.setX(100); 
		t1.setY(25); 
		t1.setText("Sales by Product for EMPLOYEE #" + empNum );
		
		Line line = new Line(); 
		line.setStartX(40); 
		line.setStartY(40); 
		line.setEndX(460); 
		line.setEndY(40);
		
        table.setEditable(true);
        
        ObservableList<empSalesInfo> data =
		        FXCollections.observableArrayList();
        for(int i = 0; i<list.size();i++)
        {
     	   String[] a = list.get(i);
     	   String first = a[0];
     	   String second = a[1];
     	   
     	   data.add(new empSalesInfo(first,second));
        }
		table.setLayoutX(139);
		table.setLayoutY(50);
	    table.setPrefSize(222, 266); //set length 2 px larger than column total for no scroll bar
		table.setItems(data);
		
		TableColumn prodCol = new TableColumn("Product");
		prodCol.setCellValueFactory(
			    new PropertyValueFactory<empSalesInfo,String>("prodName"));
		prodCol.setMinWidth(130);
		
		
		TableColumn salesCol = new TableColumn("Sales No.");
		salesCol.setCellValueFactory(
			    new PropertyValueFactory<empSalesInfo,String>("sales"));
		salesCol.setMinWidth(90);
        
		table.getColumns().addAll(prodCol, salesCol);
		
		
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
		        new salesByEmp(stage);
		    }
		 } );
	
	        Group root = new Group();
			ObservableList<Node> olist = root.getChildren(); 
		    olist.addAll(table,re,t1,line);       

				Scene s1 = new Scene(root,500,400);
	      
			
			stage.setScene(s1);
			stage.show();
        
        
	}
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void salesProdResults(ResultSet rs, String branchChoice)
	{
		
		try {
			ArrayList<String[]> list = new connector().listForBranchSalesByProd(rs);
			

			Text t1 = new Text();
			t1.setText("Total Sales By Product"); 
			t1.setFont(Font.font("Yu Gothic",18));
			
			Text t2 = new Text();
			t2.setText("ID: " + branchChoice);
			t2.setFont(Font.font("Yu Gothic",18));
			
			Line line = new Line(); 
			line.setStartX(40); 
			line.setStartY(40); 
			line.setEndX(460); 
			line.setEndY(40);
			
			GridPane g = new GridPane();
			g.setMinSize(500, 50); 
		    g.setPadding(new Insets(10, 10, 10, 10));  
		    g.setVgap(15);
		    g.setHgap(30);
		    g.setLayoutY(0);
		    g.setAlignment(Pos.CENTER); 
			g.add(t1, 0, 0);       
		    g.add(t2, 1, 0); 
			
			
			table.setEditable(true);
	        
	        ObservableList<empSalesInfo> data =
			        FXCollections.observableArrayList();
	       
	        for(int i = 0; i<list.size();i++)
	        {
	     	   String[] a = list.get(i);
	     	   String first = a[0];
	     	   String second = a[1];
	     	   
	     	   data.add(new empSalesInfo(first,second));
	        }
			
	        table.setLayoutX(139);
			table.setLayoutY(50);
		    table.setPrefSize(222, 266);
			table.setItems(data);
			
			TableColumn prodCol = new TableColumn("Product");
			prodCol.setCellValueFactory(
				    new PropertyValueFactory<empSalesInfo,String>("prodName"));
			prodCol.setMinWidth(130);
			
			
			TableColumn salesCol = new TableColumn("Sales No.");
			salesCol.setCellValueFactory(
				    new PropertyValueFactory<empSalesInfo,String>("sales"));
			salesCol.setMinWidth(90);
	        
			table.getColumns().addAll(prodCol, salesCol);
			
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
			        new salesByBranch(stage);
			    }
			 } );
		
		        Group root = new Group();
				ObservableList<Node> olist = root.getChildren(); 
			    olist.addAll(table,re,g,line);       

					Scene s1 = new Scene(root,500,400);
		      
				
				stage.setScene(s1);
				stage.show();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void branchSalesByEmployee(ResultSet rs, String branchChoice)
	{
		try {
			
			ArrayList<String[]> list = new connector().listForBranchSalesByEmp(rs);
			
			Text t1 = new Text();
			t1.setText("Total Sales By Employee"); 
			t1.setFont(Font.font("Yu Gothic",18));
			
			Text t2 = new Text();
			t2.setText("ID: " + branchChoice);
			t2.setFont(Font.font("Yu Gothic",18));
			
			Line line = new Line(); 
			line.setStartX(40); 
			line.setStartY(40); 
			line.setEndX(460); 
			line.setEndY(40);
			
			GridPane g = new GridPane();
			g.setMinSize(500, 50); 
		    g.setPadding(new Insets(10, 10, 10, 10));  
		    g.setVgap(15); 
		    g.setHgap(30);     
		    g.setLayoutY(0);
		    g.setAlignment(Pos.CENTER); 
			g.add(t1, 0, 0);       
		    g.add(t2, 1, 0); 
			
			
			table.setEditable(true);
	        
	        ObservableList<empSalesInfo> data =
			        FXCollections.observableArrayList();
	       
	        for(int i = 0; i<list.size();i++)
	        {
	           String first, second;
	     	   String[] a = list.get(i);
	     	   if (a[0].equals("inactive"))
	     	   {
	     		   first = "**Terminated**";
	     	   }
	     	   else{
	     	       first = a[0];
	     	   }
	     	       second = a[1];
	     	   
	     	   data.add(new empSalesInfo(first,second));
	        }
			
	        table.setLayoutX(139);
			table.setLayoutY(50);
		    table.setPrefSize(222, 266);
			table.setItems(data);
			
			TableColumn prodCol = new TableColumn("Last Name");
			prodCol.setCellValueFactory(
				    new PropertyValueFactory<empSalesInfo,String>("prodName"));
			prodCol.setMinWidth(130);
			
			
			TableColumn salesCol = new TableColumn("Sales");
			salesCol.setCellValueFactory(
				    new PropertyValueFactory<empSalesInfo,String>("sales"));
			salesCol.setMinWidth(90);
	        
			table.getColumns().addAll(prodCol, salesCol);
			
			
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
			        new salesByBranch(stage);
			    }
			 } );
		
		        Group root = new Group();
				ObservableList<Node> olist = root.getChildren(); 
			    olist.addAll(table,re,g,line);       

				Scene s1 = new Scene(root,500,400);
				stage.setScene(s1);
				stage.show();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
