package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class connector 
{
	String jdbcDriver;
	String protocolHeader;
	String user;
	String password;
	Connection conn;
	Statement s;

	public connector() throws Exception 
	{
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String protocolHeader = "jdbc:mysql://sql9.freemysqlhosting.net/sql9169410";
		String user = "sql9169410";
		String password = "nqgyYFiRfx";
				
		//connect to JDBC class database
		Class.forName(jdbcDriver);
	    conn = DriverManager.getConnection(protocolHeader, user, password);
	    s = conn.createStatement();
	   
	}
	
	
	
/////////////EMPLOYEE METHODS//////////////////////////////////////////////////////////////////////////////////////
	
	
	public void addNewEmployee(String fname, String lname, int branch, String street, String city, String state, String zip, String phone) throws Exception
	{
		String query = "insert into employees (first_name, last_name, branch_id) values ('"+fname+"', '"+lname+"', "+branch+")";
		//System.out.println(query);
		s.executeUpdate(query);
		
		query = "select id from employees where first_name = '"+fname+"' and last_name='"+lname+"'";
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
		
		int idnum = 0;
		
		while (rs.next())
		{
			idnum = rs.getInt("id");
		}
		
		query = "insert into employee_contact values ("+idnum+", '"+street+"', '"+city+"', '"+state+"', '"+zip+"', '"+phone+"')";
		//System.out.println(query);
		s.executeUpdate(query);
	}

	public void terminateEmployee(int id) throws Exception
	{
		String query = 	"select first_name, last_name, branch_id from employees where id = '"+id+"'";
		ResultSet rs = s.executeQuery(query);
		
		String fname = "";
		String lname = "";
		int bid = 0;
		while (rs.next())
		{
			fname = rs.getString("first_name");
			lname = rs.getString("last_name");
			bid = rs.getInt("branch_id");
		}	
		rs.close();
		
		query = "insert into terminated_employees values ("+id+", '"+fname+"', '"+lname+"', "+bid+")";
        //System.out.println(query);
		
		s.execute(query);
		
		query = "delete from employees where employees.id="+id;
		//System.out.println(query);
       
		s.execute(query);
		
	}

	public void updateEmployeeInfo(int id, String fname, String lname, int branch, String street, String city, String state, String zip, String phone) throws Exception
	{
		String query = "UPDATE employees set first_name = '"+fname+"', last_name='"+lname+"', branch_id = '"+branch+"' where id = "+id;
		//System.out.println(query);
		s.executeUpdate(query);
		
		query = "UPDATE employee_contact set street='"+street+"', city='"+city+"', state='"+state+"', zip='"+zip+"', phone='"+phone+"' where id ="+id;
		//System.out.println(query);
		s.executeUpdate(query);
	}
	
	public ResultSet searchEmpByID(int id) throws Exception
	{
		String query = 	"select employee_contact.id, employees.first_name, employees.last_name, employees.branch_id, street, city, state,"
				+ " zip, employee_contact.phone from employees right join employee_contact on employees.id = employee_contact.id where "
				+ "employee_contact.id ="+id;
		//System.out.println(query);
		
		ResultSet rs = s.executeQuery(query);
		ResultSet returnedRS = null;
		if (rs.first())
		{
		
			String fname = rs.getString("employees.first_name");
			String lname = rs.getString("employees.last_name");
		
			
			//if employee names show up in employees
			if(fname != null && lname != null) 
				//if not null, they're an active employee
			{
				
				returnedRS = rs;
				
			}
			else //if names didn't show up in employees table
			{
				System.out.println("Possible terminated employee");
				
			//they're in terminated_employees, which is queried by the method below
				returnedRS = retrieveTermEmpByID(id);
			}
		}
		return returnedRS;		
	}

	public ResultSet retrieveTermEmpByID(int id) throws Exception
	{
		String query = 	"select employee_contact.id, terminated_employees.first_name, terminated_employees.last_name, "
				+ "terminated_employees.branch_id, street, city, state, zip, employee_contact.phone from terminated_employees "
				+ "right join employee_contact on terminated_employees.id = employee_contact.id where employee_contact.id ="+id;
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
	
		return rs;
		
	}
	
	public String checkTermEmpByID(int id) throws Exception
	{
		
		ResultSet rs = s.executeQuery("select first_name from terminated_employees where id ="+id);
		String result = "active";
		
		if(rs.next())
		{
			result = "terminated";
		}
		return result;
		
	}
	
	public ResultSet searchEmpByName(String fname, String lname) throws Exception
	{
		String query = 	"select employee_contact.id, employees.first_name, employees.last_name, employees.branch_id, street, "
				+ "city, state, zip, employee_contact.phone from employees right join employee_contact on employees.id = "
				+ "employee_contact.id where employees.first_name='"+fname+"' and employees.last_name='"+lname+"'";
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
		ResultSet returnedRS;
		if (rs.first())
		{
			
			String fn = rs.getString("employees.first_name");
			String ln = rs.getString("employees.last_name");
		
			
			//if employee names show up in employees
			if(fn != null && ln != null) //if not null, they're an active employee
			{
				returnedRS = rs;
				
			}
			else //if names didn't show up in employees table
			{
				System.out.println("Possible terminated employee");
			//they're in terminated_employees, which is queried by the method below
				returnedRS = retrieveTermEmpByName(fname, lname);
			}
		}
			
		else //if the query returned no rows
		{
			System.out.println("Possible terminated employee");
			//search terminated employees
			returnedRS = retrieveTermEmpByName(fname, lname);
		}
		return returnedRS;
	}
	
	public ResultSet retrieveTermEmpByName(String fname, String lname) throws Exception
	{
		String query = 	"select employee_contact.id, terminated_employees.first_name, terminated_employees.last_name, "
				+ "terminated_employees.branch_id, street, city, state, zip, employee_contact.phone from terminated_employees "
				+ "right join employee_contact on terminated_employees.id = employee_contact.id where "
				+ "terminated_employees.first_name='"+fname+"' and terminated_employees.last_name='"+lname+"'";
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
		return rs;
		
	}
	
	public String checkTermEmpByName(String fname, String lname) throws Exception
	{
		ResultSet rs = s.executeQuery("select first_name from terminated_employees where first_name='"+fname+"' and last_name='"+lname+"'");
		String result = "active";
		
		if(rs.next())
		{
			result = "terminated";
		}
		return result;
		
	}
	
	
///////////SALES METHODS///////////////////////////////////////////////////////////////////////////////////////////
	
	
	public int getTotalEmployeeSales(int id) throws Exception
	{
		int sales = 0;
		
		String query = "select count(sale_no) as cumulative_sales from employee_sales right join products on "
				+ "employee_sales.prod_id = products.prod_id and employee_sales.employee_id = "+id;
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
		
		while (rs.next())
		{
			sales = rs.getInt("cumulative_sales");
		}
		
		return sales;
	}

	public int getTotalBranchSales(int id) throws Exception
	{
		int sales = 0;
		String query = 	"select count(sale_no) as sales from employee_sales left join employees on "
				+ "employee_sales.employee_id=employees.id where employee_sales.branch_id ="+id;
		//System.out.println(query);
		
		ResultSet rs = s.executeQuery(query);
		
		if (rs.first())
		{
			sales = rs.getInt("sales");
		}
		
		return sales;
	}

	public ResultSet getBranchSalesRSByEmployee(int id) throws Exception
	{		
		String query = 	"select IFNULL(employees.last_name, 'inactive') as last_name, count(sale_no) "
				+ "as sales from employees right join employee_sales on employees.id = employee_sales.employee_id "
				+ "where employee_sales.branch_id ="+id+" or employees.branch_id="+id+" group by last_name union  "
						+ "select IFNULL(employees.last_name, 'inactive'), count(sale_no) as sales from employees "
						+ "left join employee_sales on employees.id = employee_sales.employee_id where "
						+ "employee_sales.branch_id ="+id+" or employees.branch_id ="+id+" group by last_name";
		//System.out.println(query);
		
		ResultSet rs = s.executeQuery(query);
		
		return rs;		
	}
	
	public ArrayList<String[]> listForBranchSalesByEmp(ResultSet rs) throws Exception
	{
		ArrayList<String[]> l = new ArrayList<String[]>();
	
		while (rs.next())
		{
			String[] arr = new String[2]; //2 = number of columns (prod_name, count)
			String lname = rs.getString("last_name");
			arr[0] = lname;
			int sales = rs.getInt("sales");
			String saleString = Integer.toString(sales);
			arr[1] = saleString;
			
			l.add(arr);
		}
		
		return l;
	}

	public ResultSet getBranchSalesRSByProd(int id) throws Exception
	{
		String query = "select prod_name, count(sale_no) as sales from products left join employee_sales on "
				+ "products.prod_id = employee_sales.prod_id and employee_sales.branch_id ="+id+" group by prod_name";
		//System.out.println(query);
		ResultSet rs = s.executeQuery(query);
		
		return rs;
	}
	
	public ArrayList<String[]> listForBranchSalesByProd(ResultSet rs) throws Exception
	{
		ArrayList<String[]> l = new ArrayList<String[]>();
		
		while (rs.next())
		{
			String[] arr = new String[2]; //2 = number of columns (prod_name, count)
			String prod = rs.getString("prod_name");
			arr[0] = prod;
			int sales = rs.getInt("sales");
			String saleString = Integer.toString(sales);
			arr[1] = saleString;
			
			l.add(arr);
		}
		return l;
	}
	
	public ResultSet getEmployeeSalesRSByProd(int id) throws Exception
	{		
		String query = "select prod_name, count(sale_no) as sales from employee_sales right join products on "
				+ "employee_sales.prod_id = products.prod_id and employee_sales.employee_id = "+id+" group by prod_name";
		//System.out.println(query);
		
		ResultSet rs = s.executeQuery(query);
		
		return rs;		
	}
	
	public ArrayList<String[]> listForEmpSalesByProd(ResultSet rs) throws Exception
	{
		ArrayList<String[]> l = new ArrayList<String[]>();
	
		while (rs.next())
		{
			String[] arr = new String[2]; 
			String prod = rs.getString("prod_name");
			arr[0] = prod;
			int sales = rs.getInt("sales");
			String saleString = Integer.toString(sales);
			arr[1] = saleString;
			
			l.add(arr);
		}
		return l;
	}
	
	
}