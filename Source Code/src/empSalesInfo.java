package application;
import javafx.beans.property.SimpleStringProperty;

public class empSalesInfo {

	  public final SimpleStringProperty prodName;
      public final SimpleStringProperty sales;
     
      public empSalesInfo(String prod, String empSale) 
      {
          this.prodName = new SimpleStringProperty(prod);
          this.sales = new SimpleStringProperty(empSale);
         
      }
      public String getProdName() {
          return prodName.get();
      }

      public void setProdName(String prod) {
          prodName.set(prod);
      }
      
      public String getSales() {
          return sales.get();
      }

      public void setSales(String empSale) {
          sales.set(empSale);
      }
  }