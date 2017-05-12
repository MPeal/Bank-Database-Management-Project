/*ADD AN EMPLOYEE*/    /*DONE*/
	insert into employees (first_name, last_name, branch_id) values ('"+fname+"', '"+lname+"', "+branch+");
 
	insert into employee_contact values ("+idnum+", '"+street+"', '"+city+"', '"+state+"', '"+zip+"', '"+phone+"');
 
 
 
 
 /*UPDATE EMPLOYEE INFO*/    /*DONE*/
	UPDATE employees
	set first_name = '"+fname+"', last_name='"+lname+"', branch_id = '"+branch+"' where id = "+id";
	
	UPDATE employee_contact 
	set street='"+street+"', city='"+city+"', state='"+state+"', zip='"+zip+"', phone='"+phone+"' where id ="+id";
 
 
 
 
 
 
/*DELETE AN EMPLOYEE*/    /*DONE*/
	/*fire employee (by ID)*/
	select first_name, last_name, branch_id 
	from employees 
	where id = '"+id+"';

	/*from resultset, assign first_name, last_name, branch_id as VARS*/

	insert into terminated_employees values ("+id+", '"+fname+"', '"+lname+"', "+bid+");
 
	delete from employees 
	where employees.id=+id;
 
 

 
 
 /*LOOK UP EMPLOYEE INFORMATION*/
 
	/*a) look up EVERYTHING*/
	select employee_contact.id, employees.first_name, employees.last_name, employees.branch_id, street, city, state, zip, employee_contact.phone
	from employees right join employee_contact on employees.id = employee_contact.id
	where employee_contact.id = 'value';
	

	
	
	
	
	/*LOOK UP SPECIFIC EMPLOYEE SALES INFORMATION*/      /*DONE*/

	/*a) employee total sales*/
	select count(sale_no) as cumulative_sales 
	from employee_sales right join products on employee_sales.prod_id = products.prod_id and employee_sales.employee_id = +id;
	
	/*b) employee sales by product*/
	select prod_name, count(sale_no) as sales 
	from employee_sales right join products on employee_sales.prod_id = products.prod_id and employee_sales.employee_id = "+id+" 
	group by prod_name;



	
	
/*LOOK UP BRANCH SALES INFORMATION*/   /*DONE*/

	/*a) branch total sales*/
	select count(sale_no) as sales 
	from employee_sales left join employees on employee_sales.employee_id=employees.id 
	where employee_sales.branch_id =+id;

	/*b) branch sales by employee*/
	/*TOP HALF INCLUDES INACTIVE EMPLOYEES*/
	select IFNULL(employees.last_name, 'inactive'), count(sale_no) as sales
	from employees right join employee_sales on employees.id = employee_sales.employee_id
	where employee_sales.branch_id = "+id+" or employees.branch_id = "+id+"
	group by last_name
	union     /*BOTTOM HALF INCLUDES PPL WHO DON'T HAVE ANY SALES*/
	select IFNULL(employees.last_name, 'inactive'), count(sale_no) as sales
	from employees left join employee_sales on employees.id = employee_sales.employee_id
	where employee_sales.branch_id = "+id+" or employees.branch_id = "+id+"
	group by last_name
	
	
	/*c) branch sales by product*/
	select prod_name, count(sale_no) as sales 
	from products left join employee_sales on products.prod_id = employee_sales.prod_id and employee_sales.branch_id ="+id+" 
	group by prod_name;
	

/*COMPARE CUMULATIVE BRANCH SALES INFORMATION*/
select branches.branch_name, count(employee_sales.sale_no) as sales
from branches left join employee_sales on branches.branch_id = employee_sales.branch_id
group by branch_name
order by count(sale_no) desc

/*COMPARE CUMULATIVE BRANCHES SALES BY PRODUCT*/
select branches.branch_name, products.prod_name, count(employee_sales.sale_no) as sales
from employee_sales right join branches on employee_sales.branch_id = branhes.branch_id
     employee_sales right join products on employee_sales.prod_id = products.prod_id
group by branch_name, prod_name
order by branch_name desc