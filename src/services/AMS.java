package services;

import java.util.Date;

import beans.Employee;
import beans.Flightdetails;
import beans.Journey;
import beans.Person;
import beans.SearchEmployee;
import beans.SearchTraveller;
import beans.Traveller;




public class AMS {

DatabaseConnection db=new DatabaseConnection();
Date date= new Date();
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

public Person signIn(String username,String password)
{
System.out.println("enter func");
Person result= null;

	if((username.length()==0) || (password.length()==0) || (username == null) || (password == null))

	{
		Person result1= new Person();
		result1.setMsg("error.. null length for something");
		return result1;
	}
	else
	{
	
		result=db.signIn(username,password);
		
	}	
return result;	
}

public String retrieveDate(String u)
{
	String result=db.retrieveDate(u) ;
	return result;
}

public void updateTime(String u, String s)
{
	db.updateTime(u,s) ;
}


// update person

public String updatePinfo(String emailid, String address,String city,String state,String zipcode)
{
String result;
result=db.updatePinfo(emailid,address,city,state,zipcode);
return result;
}

public Flightdetails[] listFlightsToUpdate(String flightnumber, String source,String destination)
{
	Flightdetails[] f=db.listFlightsToUpdate(flightnumber, source, destination);
	return f;
}

public Flightdetails listOneFlight(String flightnumber)
{
	Flightdetails f=db.listOneFlight(flightnumber);
	return f;
}

public String updateFlight(String duration,int numberofseats,String crewdetails,String flightnumber)
{
String f=db.updateFlight(duration,numberofseats, crewdetails,flightnumber);
return f;
}

public String addFlight(String fno, String fname, String src, String dest, String crew,int seats, String duration)
{
	
	System.out.println("in ams");
	System.out.println("crew in ams:"+ crew);
	String result=db.addFlight(fno, fname, src, dest, crew, seats, duration);
	
	System.out.println("result in ams:"+ result);
	return result;
}

public String deleteFlight (String fno, String fname, String src,String dest, String duration)
{
	String result="";
	//uncomment here
	//result=db.deleteFlight (fno,fname,src,dest);
	return result;
}

public String deleteOneFlight(String flightnumber)
{
	String result= db.deleteOneFlight(flightnumber);
	return result; 
}


public SearchTraveller[] searchtraveller(String fname,String lname,String fno,String dest,String traveldate)
{
	SearchTraveller [] t=db.searchtraveller(fname, lname, fno, dest, traveldate);
	return t;
}

public String signup(String fname, String lname, String usn, String pwd,
		String address, String city, String state, String zipcode,
		String dob, String ssn, String ppn, String nationality)
{
	String res=db.signup(fname,lname,usn,pwd,address,city,state,zipcode,dob,ssn,ppn,nationality);
	return res;
	
}

public String addemployees(String firstname,String lastname,String usn,String ssn,String type,
		String addr,String city,String zip,String state, String dob,String password,String workd,String pos,String hire)
{
	String res=db.addemployees(firstname,lastname,usn,ssn,type,addr,city,zip,state,dob,password,workd,pos,hire);
	return res;
}


//list all travellers
public Traveller[] listtravellers() 
{	
	Traveller[] t=db.listtravellers();
	return t;
}

public Employee[] listemployees() 
{	
	Employee [] emp=db.listemployees();
	return emp;
}

public Journey[] listreservations() 
{	
	Journey [] j= db.listreservations();
	return j;
}

public Flightdetails[] listflights() 
{	
	Flightdetails[] f= db.listflights();
	return f;
}

public SearchEmployee[] searchEmployee(String firstname,String lastname, String city,String state,String position,String hiredate)
{
	SearchEmployee [] t=db.searchEmployee(firstname, lastname, city, state, position,hiredate);
	return t;
}

public String deleteEmployee(String email)
{
	String result="";

	result=db.deleteEmployee (email);
	return result;
}

public Flightdetails[] listflightsCustomer(String sr, String d) 
{
Flightdetails[] f= db.listflightsCustomer(sr,d);
return f;
}

public Journey[] payfortickets(String f,String l,String eml,String fldur,String fln,String flno,String ts, String src, String dest,String tmps) 
{

	Journey[] j=db.payfortickets(f,l,eml,fldur,fln,flno,ts,src,dest,tmps);
	return j;
}

}
