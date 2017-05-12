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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class employeeFunctions 
{
	Stage ps;
	ResultSet rs;
	Button re = new Button("Back");
	DropShadow shadow = new DropShadow();
	
	public employeeFunctions (Stage st)
	{
		ps = st;
		
		Image returni = new Image(getClass().getResource("back.png").toExternalForm(), 50, 50, true, true);
		double r=1.5;
		
		shadow.setColor(Color.TEAL);
		
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
		        new employeeMain(ps);
		    }
		 } );
	}
	public void searchEmp()
	{
		Text t1 = new Text();
		t1.setFont(Font.font("Calibri Light",18));
		t1.setX(70); 
		t1.setY(200); 
		t1.setText("Enter the Employee ID or the First AND Last Name");
		
		Image image = new Image(getClass().getResource("lead.png").toExternalForm(), 120, 120, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(190);
		i1.setY(40);
		
		TextField id = new TextField();
		id.setLayoutX(150);
		id.setLayoutY(218);
		id.setMaxWidth(200);
		id.setMinWidth(200);
		
		Label label = new Label();
		label.setLayoutX(157);
		label.setLayoutY(243);
		label.setTextFill(Color.RED);
		
		Button b1 = new Button("Search");
		b1.setPrefSize(120, 40);
		b1.setLayoutX(190);
		b1.setLayoutY(275);
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
		b1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			    public void handle(ActionEvent e) {
				String termCheck;
			        if ((id.getText() != null && !id.getText().isEmpty())) 
			        {
			           
			        	String entry = id.getText();
			        	System.out.println(entry + " was entered..");//////////////////////////////////////////////
			        	
			        	boolean check = isInteger(entry);
			        	
			            if(check == true)
			            {
			            	System.out.println("--Number confirmed--");//////////////////////////////////////
			            	int empNum = Integer.parseInt(entry);
			            	 
			            	try {
								termCheck = new connector().checkTermEmpByID(empNum);
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
								termCheck = "active";
							}
									try {
										rs = new connector().searchEmpByID(empNum);
										new employeeResults(ps,rs,termCheck);
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
										label.setText("Please enter a valid ID or Full Name.");
									}
									
								
			            }
			            else
			            { 
			            	System.out.println("--Text confirmed--");//////////////////////////////////////
			            	if(entry.contains(" "))
			            	{
			            	
			            	String[] nameSplit = entry.split("\\s");
			            	String fname = nameSplit[0];
			            	String lname = nameSplit[1];
			            	
			            	try {
								termCheck = new connector().checkTermEmpByName(fname,lname);
							} catch (Exception e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
								termCheck = "active";
							}
			            	try {
			            		
			            		rs = new connector().searchEmpByName(fname,lname);
			            		
			            		if(rs.first())
			        			{
			            		
			            		new employeeResults(ps,rs,termCheck);
			        			}
			            		else{
			            			label.setText("Please enter a valid ID or Full Name.");
			            		}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								label.setText("Please enter a valid ID or Full Name.");
							}
							
							}else {
								label.setText("Please enter a valid ID or Full Name.");
							}
			            }
			              
			         
			        } else {
			            label.setText("Please enter a valid ID or Full Name.");
			        }
			     }
			 });
			
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
	    list.addAll(t1,id,label,b1,i1,re);       

			Scene s1 = new Scene(root,500,400);
			
		
		ps.setScene(s1);
		ps.show();
	}
	
	public void addEmp()
	{
		
		Text t1 = new Text();
		t1.setFont(Font.font("Calibri Light",20));
		t1.setX(45); 
		t1.setY(30); 
		t1.setText("Enter New Employee Information");
		
		Label label = new Label();
		label.setLayoutX(125);
		label.setLayoutY(400);
		label.setTextFill(Color.RED);
		
		Line line = new Line(); 
		line.setStartX(40); 
		line.setStartY(40); 
		line.setEndX(460); 
		line.setEndY(40);
		
		 Text firstname = new Text("First Name:");
		 TextField fnametext = new TextField(); 
		
		 Text lastname = new Text("Last Name:");
		 TextField lnametext = new TextField(); 
		 
		 Text branchID = new Text("Branch ID:");
		 TextField bidtext = new TextField(); 
		 
		 Text eStreet = new Text("Street:");
		 TextField streettext = new TextField(); 
		 
		 Text eCity = new Text("City:");
		 TextField citytext = new TextField(); 
		
		 Text eState = new Text("State:");
		 TextField statetext = new TextField(); 
		
		 Text eZip = new Text("Zip:");
		 TextField ziptext = new TextField(); 
		 
		 Text ePhone = new Text("Phone:");
		 TextField phonetext = new TextField(); 
		 
		 GridPane gridPane = new GridPane();    
	    // gridPane.setMinSize(500, 500); 
	     gridPane.setPadding(new Insets(10, 10, 10, 10));  
	     gridPane.setVgap(19); 
	     gridPane.setHgap(30);   
	     
	     gridPane.setLayoutX(40);
	     gridPane.setLayoutY(55);
	     
	     gridPane.setAlignment(Pos.CENTER); 
	     
	     Label space = new Label();
	     space.setText("                                           ");
	     
	     gridPane.add(firstname, 0, 0); 
	     gridPane.add(space, 1, 0);      //this probably seems weird but its adding space between the left [0] and right [2] columns  
	     gridPane.add(fnametext, 2, 0); 
	     
	     gridPane.add(lastname, 0, 1);       
	     gridPane.add(lnametext, 2, 1); 
	      
	     gridPane.add(branchID, 0, 2);       
	     gridPane.add(bidtext, 2, 2); 
		
	     gridPane.add(eStreet, 0, 3);       
	     gridPane.add(streettext, 2, 3); 
	      
	     gridPane.add(eCity, 0, 4);       
	     gridPane.add(citytext, 2, 4); 
	      
	     gridPane.add(eState, 0, 5);       
	     gridPane.add(statetext, 2, 5); 
	      
	     gridPane.add(eZip, 0, 6);       
	     gridPane.add(ziptext, 2, 6); 
	      
	     gridPane.add(ePhone, 0, 7);       
	     gridPane.add(phonetext, 2, 7); 
	     
	     ///////////////////////////////////////////////
	     
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
			

		///////////////////////////////////////////////////////
			
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
		         String NameF = null;
		         String NameL = null;
		         String Street = null;
		        
		         String City = null;
		         String State  = null;
		         String Zip = null;
		         String Phone = null;
		       	 int Bid= 0;
		    	 if ((fnametext.getText() != null && !fnametext.getText().isEmpty()))
				 {
					  NameF = fnametext.getText();
				 }
				 if ((lnametext.getText() != null && !lnametext.getText().isEmpty()))
				 {
					  NameL = lnametext.getText();
				 }
				 if ((bidtext.getText() != null && !bidtext.getText().isEmpty()))
				 {
					  Bid = Integer.parseInt(bidtext.getText());
				 }
				 if ((streettext.getText() != null && !streettext.getText().isEmpty()))
				 {
					  Street = streettext.getText();
				 }
				 if ((citytext.getText() != null && !citytext.getText().isEmpty()))
				 {
					  City = citytext.getText();
				 }
				 if ((statetext.getText() != null && !statetext.getText().isEmpty()))
				 {
					  State = statetext.getText();
				 }
				 if ((ziptext.getText() != null && !ziptext.getText().isEmpty()))
				 {
					  Zip = ziptext.getText();
				 }
				 if ((phonetext.getText() != null && !phonetext.getText().isEmpty()))
				 {
					  Phone = phonetext.getText();
				 }
					
		    	try {
		    		
		    		   
						new connector().addNewEmployee(NameF,NameL,Bid,Street,City,State,Zip,Phone);
						String Confirm = NameF + " " + NameL + " was successfully ";
						String ending = "registered!";
						new employeeResults(ps).confirm(Confirm,ending);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						label.setText("Error! Please complete all entries and try again!");
					}
						
		    }
		 } );
		
		Image image = new Image(getClass().getResource("newemp.png").toExternalForm(), 100, 100, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(30);
		i1.setY(440);
		
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
		this.re.setLayoutY(450);
		
	    list.addAll(gridPane,t1,i1,line,label,re,sub);       

			Scene s1 = new Scene(root,500,550);
			
		
		ps.setScene(s1);
		ps.show();
	}
	
	
	public void deleteEmp()
	{
		Text t1 = new Text();
		t1.setFont(Font.font("Calibri Light",18));
		t1.setX(90); 
		t1.setY(218); 
		t1.setText("Enter the Employee ID:");
		
		TextField id = new TextField();
		id.setLayoutX(300);
		id.setLayoutY(200);
		id.setMaxWidth(60);
		id.setMinWidth(60);
		
		Label label = new Label();
		label.setLayoutX(152);
		label.setLayoutY(243);
		label.setTextFill(Color.RED);
		
		Image image = new Image(getClass().getResource("term.png").toExternalForm(), 120, 120, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(190);
		i1.setY(40);
		
		Button sub = new Button("Submit");
		sub.setPrefSize(120, 40);
		sub.setLayoutX(190);
		sub.setLayoutY(275);
		sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
		sub.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	sub.setEffect(shadow);
			            //b1.setStyle(" -fx-border-color: white; -fx-body-color: white  ; ");
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
		       
				        if ((id.getText() != null && !id.getText().isEmpty())) 
				        {
				           
				        	String entry = id.getText();
				        	System.out.println(entry + " was entered..");//////////////////////////////////////////////
				        	
				        	
				        	boolean check = isInteger(entry);
				        	
				        	
				            if(check == true)
				            {
				            	System.out.println("--Number confirmed--");//////////////////////////////////////
				            	int empNum = Integer.parseInt(entry);
				            	 
										try {
											rs = new connector().searchEmpByID(empNum);
											new employeeResults(ps).employeeDelResults(rs);
											
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
											label.setText("Please enter a valid ID.");
										}
										
				            }
				            else{ 
				            	System.out.println("--Text confirmed--");//////////////////////////////////////
				            	}
				            label.setText("Please enter the employee ID number only.");   
				         
				        } else {
				            label.setText("Please enter the employee ID number.");
				        }
				     }
				 });
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
		this.re.setLayoutY(350);
		
	    list.addAll(t1,re,id,label,i1,sub);       

			Scene s1 = new Scene(root,500,400);
			
		
		ps.setScene(s1);
		ps.show();
	}
	
	public void updateEmp()
	{
		Text t1 = new Text();
		t1.setFont(Font.font("Calibri Light",18));
		t1.setX(90); 
		t1.setY(218); 
		t1.setText("Enter the Employee ID:");
		
		TextField id = new TextField();
		id.setLayoutX(300);
		id.setLayoutY(200);
		id.setMaxWidth(60);
		id.setMinWidth(60);
		
		Label label = new Label();
		label.setLayoutX(152);
		label.setLayoutY(235);
		label.setTextFill(Color.RED);
		
		Image image = new Image(getClass().getResource("editing.png").toExternalForm(), 120, 120, true, true);
		ImageView i1 = new ImageView(image);
		i1.setX(190);
		i1.setY(40);
		
		Button sub = new Button("Submit");
		sub.setPrefSize(120, 40);
		sub.setLayoutX(190);
		sub.setLayoutY(275);
		sub.setStyle(" -fx-border-color: darkslateblue; -fx-body-color: white  ; ");
		sub.addEventHandler(MouseEvent.MOUSE_ENTERED, 
			    new EventHandler<MouseEvent>() {
			        @Override public void handle(MouseEvent e) {
			        	sub.setEffect(shadow);
			            //b1.setStyle(" -fx-border-color: white; -fx-body-color: white  ; ");
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
		       
				        if ((id.getText() != null && !id.getText().isEmpty())) 
				        {
				           
				        	String entry = id.getText();
				        	System.out.println(entry + " was entered..");//////////////////////////////////////////////
				        	
				        	
				        	boolean check = isInteger(entry);
				        	
				        	
				            if(check == true){
				            	System.out.println("--Number confirmed--");//////////////////////////////////////
				            	int empNum = Integer.parseInt(entry);
				            	label.setText(" ");
										try {
											rs = new connector().searchEmpByID(empNum);
											new employeeResults(ps).employeeUpdate(ps,rs);
											
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
											label.setText("Please enter a VALID employee number."); 
										}
							
				            }
				            else{ 
				            	System.out.println("--Text confirmed--");//////////////////////////////////////
				            	}
				            label.setText("Please enter a VALID employee number.");   
				         
				        } else {
				            label.setText("Please enter a VALID employee number.");
				        }
				     }
				 });
		
		Group root = new Group();
		ObservableList<Node> list = root.getChildren(); 
		this.re.setLayoutY(350);
		
	    list.addAll(t1,re,id,label,i1,sub);       

			Scene s1 = new Scene(root,500,400);
			
		
		ps.setScene(s1);
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
