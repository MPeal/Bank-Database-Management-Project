package application;

import javafx.beans.property.SimpleStringProperty;

public class empInfo {

	  public final SimpleStringProperty empID;
      public final SimpleStringProperty name;
      public final SimpleStringProperty branchID;
      public final SimpleStringProperty address;
      public final SimpleStringProperty phone;
      

      public empInfo(String id, String fullname, String bid, String fulladdress, String phonenum ) 
      {
          this.empID = new SimpleStringProperty(id);
          this.name = new SimpleStringProperty(fullname);
          this.branchID = new SimpleStringProperty(bid);
          this.address = new SimpleStringProperty(fulladdress);
          this.phone = new SimpleStringProperty(phonenum);
          
      }
      public String getEmpID() {
          return empID.get();
      }

      public void setEmpID(String id) {
          empID.set(id);
      }
      
      public String getName() {
          return name.get();
      }

      public void setName(String fullname) {
          name.set(fullname);
      }
      public String getBranchID() {
          return branchID.get();
      }

      public void setBranchID(String bid) {
          branchID.set(bid);
      }
      public String getAddress() {
          return address.get();
      }

      public void setAddress(String fulladdress) {
          address.set(fulladdress);
      }
      public String getPhone() {
          return phone.get();
      }

      public void setPhone(String phonenum) {
          phone.set(phonenum);
      }
      
     
      
  }