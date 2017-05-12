package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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


public class employeeResults 
{
	Stage stage;
	TableView<empInfo> table = new TableView<empInfo>();
	DropShadow shadow = new DropShadow();
 
	public employeeResults(Stage ps, ResultSet rs,String termCheck)
	{
		stage = ps;
		
		try {
			
			if (rs.first())
			{
				int eid;
				
					eid = rs.getInt("employee_contact.id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					int bid = rs.getInt("branch_id");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");
					String phone = rs.getString("employee_contact.phone");
					rs.close();
					
					////////////////
					
					tableDisplay(eid,fname,lname,bid,street,city,state,zip,phone,termCheck);
					
					////////////////
					
			}
			else{
				System.out.println("No employee match!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public employeeResults(Stage ps)
	{
		stage = ps;
		shadow.setColor(Color.TEAL);
	}
	
	public void confirm(String Confirm, String ending)
	{
		shadow.setColor(Color.CADETBLUE);
		String phrase = Confirm + ending;
		Text t1 = new Text();
		t1.setFont(Font.font("Calibri Light",23));
		t1.setText(phrase);
		
		Image image = new Image(getClass().getResource("blue-tick.png").toExternalForm(), 100, 100, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(200);
		i1.setY(150);
		
		GridPane g = new GridPane();
		g.setMinSize(500, 100); 
	    g.setPadding(new Insets(10, 10, 10, 10));  
	    g.setVgap(15); 
	    g.setHgap(30);     
	    g.setLayoutY(20);
	    g.setAlignment(Pos.CENTER); 
		g.add(t1, 0, 0);       
		
		
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
			        new employeeMain(stage);
			    }
			 } );
		
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
		list.addAll(re,g,i1);       

		Scene s1 = new Scene(root,500,400);
	        
		stage.setScene(s1);
		stage.show();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void employeeDelResults(ResultSet rs)
	{
		
		
		try {
			
			if (rs.first())
			{
				int eid;
				
					eid = rs.getInt("employee_contact.id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					int bid = rs.getInt("branch_id");
					String street = rs.getString("street");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String zip = rs.getString("zip");
					String phone = rs.getString("employee_contact.phone");
					rs.close();
					
					table.setEditable(true);
				        
					shadow.setColor(Color.CADETBLUE);
					
					Text t1 = new Text();
					t1.setX(90); 
					t1.setY(25); 
					t1.setText("Are you sure you want to terminate this Employee?");
					t1.setFont(Font.font("Yu Gothic",18));
					
					Label label = new Label();
					label.setLayoutX(152);
					label.setLayoutY(243);
					label.setTextFill(Color.RED);
					
					Line line = new Line(); 
					line.setStartX(40); 
					line.setStartY(40); 
					line.setEndX(560); 
					line.setEndY(40);
						
						
						String name = fname + " "+ lname;
						String address = street + " " + city + " " + state + " " + zip;
						String empID = Integer.toString(eid);
						String branchID = Integer.toString(bid);
						
						//System.out.println("checksum: " + empID + " " + name + branchID + address + phone);
						ObservableList<empInfo> data =
						        FXCollections.observableArrayList(new empInfo(empID, name, branchID, address, phone));
						
						//set length 2 px larger than column total for no scroll bar
						table.setLayoutX(24);
						table.setLayoutY(60);
					    table.setPrefSize(552, 100);
						table.setItems(data);
						
						TableColumn idcol = new TableColumn("ID");
						idcol.setCellValueFactory(
							    new PropertyValueFactory<empInfo,String>("empID"));
						idcol.setMinWidth(20);
						
						TableColumn namecol = new TableColumn("Name");
						namecol.setCellValueFactory(
							    new PropertyValueFactory<empInfo,String>("name"));
						namecol.setMinWidth(100);
						
						TableColumn bidcol = new TableColumn("Branch");
						bidcol.setCellValueFactory(
							    new PropertyValueFactory<empInfo,String>("branchID"));
						bidcol.setMinWidth(50);
						
						TableColumn addresscol = new TableColumn("Address");
						addresscol.setCellValueFactory(
							    new PropertyValueFactory<empInfo,String>("address"));
						addresscol.setMinWidth(280);
						
						TableColumn phonecol = new TableColumn("Contact");
						phonecol.setCellValueFactory(
							    new PropertyValueFactory<empInfo,String>("phone"));
						phonecol.setMinWidth(50);
						
						table.getColumns().addAll(idcol, namecol, bidcol, addresscol, phonecol);
						
						
						Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
						double r=1.5;
						Button re = new Button("Back");
						re.setShape(new Circle(r));
						re.setMinSize(2*r, 2*r);
						re.setMaxSize(2*r, 2*r);

						re.setLayoutX(275);
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
						        new employeeMain(stage);
						    }
						 } );
					
						
						Button sub = new Button("Submit");
						sub.setPrefSize(120, 40);
						sub.setLayoutX(240);
						sub.setLayoutY(265);
						sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
						sub.addEventHandler(MouseEvent.MOUSE_ENTERED, 
								    new EventHandler<MouseEvent>() {
								        @Override public void handle(MouseEvent e) {
								            sub.setEffect(shadow);
								            sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: aliceblue  ; "); 
								        }
								});
						sub.addEventHandler(MouseEvent.MOUSE_EXITED, 
								    new EventHandler<MouseEvent>() {
								        @Override public void handle(MouseEvent e) {
								            sub.setEffect(null);
								            sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
								        }
								});
						sub.setOnAction(new EventHandler<ActionEvent>() 
						{
						    public void handle(ActionEvent e) 
						    {
						        
						    	try {
									new connector().terminateEmployee(eid);
									String confirm = "'" + name + "' was successfully ";
									String ending = "deleted!";
									confirm(confirm,ending);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									label.setText("  ERROR! This employee has already been terminated.\n\t\t Please select another employee.");
									sub.setVisible(false);
								}
						    	
						    }
						 } );
					       
						
						Image image = new Image(getClass().getResource("term2.png").toExternalForm(), 120, 120, true, true);
						ImageView i1 = new ImageView(image);
						i1.setX(440);
						i1.setY(250);
					    Group root = new Group();
						ObservableList<Node> list = root.getChildren(); 
						list.addAll(table,re,line,i1,label,t1,sub);       

						Scene s1 = new Scene(root,600,400);
					        
						
					//////////////////////////////////////////////////////////
						Group v = new Group();    
						
						ImageView selectedImage = new ImageView();   
				        Image animate = new Image(Main.class.getResourceAsStream("r4.gif"));
				        selectedImage.setImage(animate);
				        selectedImage.setLayoutX(50);
				        selectedImage.setLayoutY(50);
						v.getChildren().addAll(selectedImage);
						Scene s2 = new Scene(v,500,400);
						Timer t = new Timer();
						t.schedule(new TimerTask() {
								@Override
								public void run() {
									Platform.runLater(() -> {
										//ints.close();
										stage.setScene(s1);
						            });		
								}
					        }, 1000);
					/////////////////////////////////////////////////////////
						
			
						stage.setScene(s2);
						stage.show();
					        
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void tableDisplay(int id, String fname, String lname, int bid, String street, String city, String state, String zip, String phone, String termCheck)
	{
		
		shadow.setColor(Color.CADETBLUE);
		
		Text t1 = new Text();
		t1.setX(240); 
		t1.setY(25); 
		t1.setText("Search Results");
		t1.setFont(Font.font("Yu Gothic",18));
		
		Line line = new Line(); 
		line.setStartX(40); 
		line.setStartY(40); 
		line.setEndX(560); 
		line.setEndY(40);
		
		
		
        table.setEditable(true);
        Label tcheck = new Label();
        if(termCheck.equals("terminated"))
        {
        	tcheck.setText("**NOTICE: EMPLOYEE #"+ id +" IS TERMINATED**");
        }
        tcheck.setFont(Font.font("Yu Gothic",18));
        tcheck.setTextFill(Color.RED);
        tcheck.setLayoutX(110);
        tcheck.setLayoutY(190);
        
        
		String name = fname + " "+ lname;
		String address = street + " " + city + " " + state + " " + zip;
		String empID = Integer.toString(id);
		String branchID = Integer.toString(bid);
		
		ObservableList<empInfo> data =
		        FXCollections.observableArrayList(new empInfo(empID, name, branchID, address, phone));
		
		table.setLayoutX(24);
		table.setLayoutY(60);
	    table.setPrefSize(552, 100); 
		table.setItems(data);
		
		TableColumn idcol = new TableColumn("ID");
		idcol.setCellValueFactory(
			    new PropertyValueFactory<empInfo,String>("empID"));
		idcol.setMinWidth(20);
		
		TableColumn namecol = new TableColumn("Name");
		namecol.setCellValueFactory(
			    new PropertyValueFactory<empInfo,String>("name"));
		namecol.setMinWidth(100);
		
		TableColumn bidcol = new TableColumn("Branch");
		bidcol.setCellValueFactory(
			    new PropertyValueFactory<empInfo,String>("branchID"));
		bidcol.setMinWidth(50);
		
		TableColumn addresscol = new TableColumn("Address");
		addresscol.setCellValueFactory(
			    new PropertyValueFactory<empInfo,String>("address"));
		addresscol.setMinWidth(280);
		
		TableColumn phonecol = new TableColumn("Contact");
		phonecol.setCellValueFactory(
			    new PropertyValueFactory<empInfo,String>("phone"));
		phonecol.setMinWidth(50);
		
		table.getColumns().addAll(idcol, namecol, bidcol, addresscol, phonecol);
		
		
		Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
		double r=1.5;
		Button re = new Button("Back");
		re.setShape(new Circle(r));
		re.setMinSize(2*r, 2*r);
		re.setMaxSize(2*r, 2*r);

		re.setLayoutX(275);
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
		        new employeeMain(stage);
		    }
		 } );
		
	       
		
		Image image = new Image(getClass().getResource("emp2.png").toExternalForm(), 140, 140, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(440);
		i1.setY(250);
		
	    Group root = new Group();
	    ObservableList<Node> list = root.getChildren(); 
		list.addAll(table,re,tcheck,i1,t1,line);       
		Scene s1 = new Scene(root,600,400);
		/////////////////////////////////////
		Group v = new Group();    
		
		ImageView selectedImage = new ImageView();   
        Image animate = new Image(Main.class.getResourceAsStream("r4.gif"));
        selectedImage.setImage(animate);
        selectedImage.setLayoutX(50);
        selectedImage.setLayoutY(50);
		v.getChildren().addAll(selectedImage);
		Scene s2 = new Scene(v,500,400);
		Timer t = new Timer();
		t.schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						//ints.close();
						stage.setScene(s1);
		            });		
				}
	        }, 1000); 
		////////////////////////////////////
		stage.setScene(s2);//////////////////////////////
		stage.show();
	        
	        
	}
	
	
	public void employeeUpdate(Stage ps, ResultSet rs)
	{
		
		stage = ps;
		try {
		if (rs.first())
		{
			
				Text t1 = new Text();
				t1.setFont(Font.font("Calibri Light",20));
				t1.setX(45); 
				t1.setY(23); 
				t1.setText("EMPLOYEE EDIT");
				
				Label label = new Label();
				label.setLayoutX(125);
				label.setLayoutY(400);
				label.setTextFill(Color.RED);
				
				Line line = new Line(); 
				line.setStartX(40); 
				line.setStartY(35); 
				line.setEndX(460); 
				line.setEndY(35);
			
				int eid;
	
				eid = rs.getInt("employee_contact.id");
				
				String fname = rs.getString("employees.first_name");
				String lname = rs.getString("employees.last_name");
				int bid = rs.getInt("employees.branch_id");
				String street = rs.getString("street");
				String city = rs.getString("city");
				String state = rs.getString("state");
				String zip = rs.getString("zip");
				String phone = rs.getString("employee_contact.phone");
				rs.close();
				
				
				 Text employeeID = new Text("Employee ID:");
				 Text eidtext = new Text(Integer.toString(eid)); 
				 
				 
				 Text firstname = new Text("First Name:");
				 TextField fnametext = new TextField(); 
				 fnametext.setText(fname);
				 
				 Text lastname = new Text("Last Name:");
				 TextField lnametext = new TextField(); 
				 lnametext.setText(lname);
				 
				 Text branchID = new Text("Branch ID:");
				 TextField bidtext = new TextField(); 
				 bidtext.setText(Integer.toString(bid));
				 
				 Text eStreet = new Text("Street:");
				 TextField streettext = new TextField(); 
				 streettext.setText(street);
				 
				 Text eCity = new Text("City:");
				 TextField citytext = new TextField(); 
				 citytext.setText(city);
				 
				 Text eState = new Text("State:");
				 TextField statetext = new TextField(); 
				 statetext.setText(state);
				 
				 Text eZip = new Text("Zip:");
				 TextField ziptext = new TextField(); 
				 ziptext.setText(zip);
				 
				 Text ePhone = new Text("Phone:");
				 TextField phonetext = new TextField(); 
				 phonetext.setText(phone);
				
				 GridPane gridPane = new GridPane();    
			      
			    // gridPane.setMinSize(500, 500);   
			     gridPane.setPadding(new Insets(10, 10, 10, 10));  
			     gridPane.setVgap(17); 
			     gridPane.setHgap(30);       
			     gridPane.setAlignment(Pos.CENTER); 
			     gridPane.setLayoutX(40);
			     gridPane.setLayoutY(40);
			       
			     Label space = new Label();
			     space.setText("                                           ");
			     
			     gridPane.add(employeeID, 0, 0); 
			     gridPane.add(space, 1, 0);
			     gridPane.add(eidtext, 2, 0); 
			       
			     gridPane.add(firstname, 0, 1);       
			     gridPane.add(fnametext, 2, 1); 
			     
			     gridPane.add(lastname, 0, 2);       
			     gridPane.add(lnametext, 2, 2); 
			      
			     gridPane.add(branchID, 0, 3);       
			     gridPane.add(bidtext, 2, 3); 
				
			     gridPane.add(eStreet, 0, 4);       
			     gridPane.add(streettext, 2, 4); 
			      
			     gridPane.add(eCity, 0, 5);       
			     gridPane.add(citytext, 2, 5); 
			      
			     gridPane.add(eState, 0, 6);       
			     gridPane.add(statetext, 2, 6); 
			      
			     gridPane.add(eZip, 0, 7);       
			     gridPane.add(ziptext, 2, 7); 
			      
			     gridPane.add(ePhone, 0, 8);       
			     gridPane.add(phonetext, 2, 8); 
			     
			        Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
					double r=1.5;
					Button re = new Button("Back");
					re.setShape(new Circle(r));
					re.setMinSize(2*r, 2*r);
					re.setMaxSize(2*r, 2*r);

					re.setLayoutX(225);
					re.setLayoutY(500); 
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
					        new employeeMain(ps);
					    }
					 } );
		
				Button sub = new Button("Submit");
				sub.setPrefSize(120, 40);
				sub.setLayoutX(190);
				sub.setLayoutY(425);
				sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
				sub.addEventHandler(MouseEvent.MOUSE_ENTERED, 
						    new EventHandler<MouseEvent>() {
						        @Override public void handle(MouseEvent e) {
						            sub.setEffect(shadow);
						            sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: aliceblue  ; "); 
						        }
						});
				sub.addEventHandler(MouseEvent.MOUSE_EXITED, 
						    new EventHandler<MouseEvent>() {
						        @Override public void handle(MouseEvent e) {
						            sub.setEffect(null);
						            sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; "); 
						        }
						});
				sub.setOnAction(new EventHandler<ActionEvent>() 
				{
				    public void handle( ActionEvent e) 
				    {
					     String uNameF = null;
					     String uNameL = null;
				         String uStreet = null;
				        
				         String uCity = null;
				         String uState  = null;
				         String uZip = null;
				         String uPhone = null;
				       	 int uBid= 0;
				    	 if ((fnametext.getText() != null && !fnametext.getText().isEmpty()))
						 {
							  uNameF = fnametext.getText();
						 }
						 if ((lnametext.getText() != null && !lnametext.getText().isEmpty()))
						 {
							  uNameL = lnametext.getText();
						 }
						 if ((bidtext.getText() != null && !bidtext.getText().isEmpty()))
						 {
							//bidCheck = bidtext.getText();
							  uBid = Integer.parseInt(bidtext.getText());
						 }
						 if ((streettext.getText() != null && !streettext.getText().isEmpty()))
						 {
							  uStreet = streettext.getText();
						 }
						 if ((citytext.getText() != null && !citytext.getText().isEmpty()))
						 {
							  uCity = citytext.getText();
						 }
						 if ((statetext.getText() != null && !statetext.getText().isEmpty()))
						 {
							  uState = statetext.getText();
						 }
						 if ((ziptext.getText() != null && !ziptext.getText().isEmpty()))
						 {
							  uZip = ziptext.getText();
						 }
						 if ((phonetext.getText() != null && !phonetext.getText().isEmpty()))
						 {
							  uPhone = phonetext.getText();
						 }
							
				    	  try {
						  		new connector().updateEmployeeInfo(eid,uNameF,uNameL,uBid,uStreet,uCity,uState,uZip,uPhone);
								String Confirm = uNameF + " " + uNameL + " was successfully ";
								String ending = "updated!";
								new employeeResults(ps).confirm(Confirm,ending);
							  } catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								label.setText("Error! Please complete all entries and try again!");
							  }
								
				    }
				 } );
				
				Image image = new Image(getClass().getResource("edit.png").toExternalForm(), 90, 90, true, true);
				ImageView i1 = new ImageView(image);
				i1.setX(30);
				i1.setY(430);
				
				Group root = new Group();
				ObservableList<Node> list = root.getChildren(); 
			    list.addAll(gridPane,t1,label,i1,line,re,sub);       

					Scene s2 = new Scene(root,500,550);
		     
		    stage.setScene(s2);
			stage.show();
					
	
				
				}
		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
					 
		}
	}
	
}
