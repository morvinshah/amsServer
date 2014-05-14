package services;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import beans.Employee;
import beans.Flightdetails;
import beans.Journey;
import beans.Person;
import beans.SearchEmployee;
import beans.SearchTraveller;
import beans.Traveller;

public class DatabaseConnection {

	private static final String Null = null;
	// Connection con= null;
	static ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10,
			rs11, rs12, rs13;
	// private ConnectionPooling pooling ;
	Connection con = null;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/svlm1";
	// static ResultSet rs2;
	Statement st = null, st1 = null, st2 = null;
	// Statement stmt;
	PreparedStatement stmt1 = null, stmt2 = null, stmt3 = null, stmt4 = null,
			stmt5 = null, stmt6 = null, stmt7 = null, stmt8 = null,
			stmt9 = null, stmt10 = null;

	PreparedStatement pstmt, pstmt1, pstmt2, pstmt3, pstmt4, pstmt5, pstmt6,
			pstmt7, pstmt8, pstmt9, pstmt10, pstmt11, pstmt12, pstmt13;

	DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/ams",
					"root", "root");

			if (!con.isClosed()) {
				System.out.println("Successfully Connected");
			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Person signIn(String username, String password) {
		Person result = null;

		System.out.println("sign in");
		try {
			if (con.isClosed()) {
				System.out.println("connection closed");
			} else {
				System.out.println("open");
			}
			// String
			// query="Select emailid,type from person  where emailid='"+username+"' AND password='"+password+"';";

			// String
			// query="Select emailid,type from person  where emailid=? and password=?";

			String query = "Select * from person  where emailid=? AND password=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			// st = con.createStatement();
			// pstmt.executeUpdate();
			// rs=st.executeQuery(query);

			Person p = new Person();

			rs.last();
			System.out.println("last:" + rs.getRow());
			int count = rs.getRow();
			rs.beforeFirst();
			System.out.println("count=" + count);
			if (count != 0) {
				while (rs.next()) {
					p.setEmailid(rs.getString(1));
					p.setFirstname(rs.getString(2));
					p.setLastname(rs.getString(3));
					p.setAddress(rs.getString(4));
					p.setCity(rs.getString(5));
					p.setState(rs.getString(6));
					p.setZipcode(rs.getString(7));
					p.setDob(rs.getString(8));
					p.setType(rs.getString(9));
					p.setLogintimeString(rs.getString(11));

					p.setMsg("true:Success");
					result = p;

				}
			} else {
				p.setMsg("false:Validation failed");
				result = p;
			}
			System.out.println("Message: " + p.getMsg());

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public String retrieveDate(String u) {
		String time = "first time";
		try {

			st1 = con.createStatement();
			String q = "select logintime from person where emailid='" + u
					+ "';";

			rs = st1.executeQuery(q);
			if (rs.next())
				time = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		}
		return time;
	}

	public void updateTime(String u, String s) {
		try {

			st2 = con.createStatement();
			String q = "update person set logintime='" + s
					+ "' where emailid='" + u + "';";
			st2.executeUpdate(q);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	// update person

	public String updatePinfo(String emailid,String address, String city, String state, String zipcode)
	{
		String result = "";
		int rowcount_update = 0;
		try {

			String update_query = "UPDATE person SET address = IFNULL(?,address),city = IFNULL(?,city),state = IFNULL(?,state),zipcode = IFNULL(?,zipcode)WHERE emailid=?";

			pstmt = con.prepareStatement(update_query);

			pstmt.setString(1, address);
			pstmt.setString(2, city);
			pstmt.setString(3, state);
			pstmt.setString(4, zipcode);
			pstmt.setString(5, emailid);

			rowcount_update = pstmt.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		if (rowcount_update > 0) 
		{
			result = "true";
			System.out.println("Updation successful");
		} 
		else
			result = "Updation failed! Try again";

		return result;
	}

	public String addFlight(String fno, String fname, String src, String dest, String crew, int seats, String duration)
	{
		// Connection conn = getConnection();
		String result = "";
		System.out.println("crew:"+ crew);
		
		System.out.println("in dbconnection page");
		int rowcount2;
		try {
			System.out.println("try block");
			pstmt1 = con.prepareStatement("SELECT * from flightdetails where flightnumber=?");
			pstmt1.setString(1, fno);
			rs = pstmt1.executeQuery();
			if (rs.next()) 
			{
				System.out.println("in if");
				return "false: Duplicate Flight Entry";
			}
			else 
			{
				System.out.println("in else");
				pstmt = con.prepareStatement("INSERT INTO flightdetails (flightnumber, airlinename, source, destination, crewdetails, numberofseats, duration) VALUES(?,?,?,?,?,?,?)");
				pstmt.setString(1, fno);
				pstmt.setString(2, fname);
				pstmt.setString(3, src);
				pstmt.setString(4, dest);
				pstmt.setString(5, crew);
				pstmt.setInt(6, seats);
				pstmt.setString(7, duration);
				rowcount2 = pstmt.executeUpdate();
				System.out.println("row count"+rowcount2);
				if (rowcount2 != 0) 
				{
					System.out.println("in if 2");
					result = "true";
					System.out.println("Data inserted successfully");

				} else 
				{
					System.out.println("in else 2");
					result = "false:Data can't be inserted into database";
					System.out.println("result in else:"+result);
				}
			}
		}
		catch(SQLException s)
		{
			s.printStackTrace();
		}
		catch (Exception e) {
			e.getStackTrace();
		}
		
		System.out.println("result in db: "+ result);
		return result;

	}

		public String updateFlight(String duration, int numberofseats,
			String crewdetails, String flightnumber) {
		String result = "";
		int rowcount_update = 0;
		try {

			String update_query = "UPDATE flightdetails SET crewdetails = IFNULL(?,crewdetails),numberofseats = IFNULL(?,numberofseats),duration = IFNULL(?,duration) WHERE flightnumber=?";

			pstmt = con.prepareStatement(update_query);

			pstmt.setString(1, crewdetails);
			pstmt.setInt(2, numberofseats);
			pstmt.setString(3, duration);
			pstmt.setString(4, flightnumber);
			rowcount_update = pstmt.executeUpdate();

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		if (rowcount_update > 0) {
			result = "Updation successful";
			System.out.println("Updation successful");
		} else
			result = "Updation failed! Try again";

		return result;
	}

	public static void main(String[] s) {
		//
		DatabaseConnection d = new DatabaseConnection();
		d.listFlightsToUpdate("", "", "oak");
	}

	public Flightdetails[] listFlightsToUpdate(String flightnumber,
			String source, String destination) {
		Flightdetails[] f = null;
		int count = 0;

		try {

			StringBuilder queryBuilder = new StringBuilder(
					"select * from flightdetails where ");

			if (flightnumber != null && !"".equals(flightnumber)) {
				queryBuilder.append("flightnumber = ");
				queryBuilder.append('"' + flightnumber + '"');
			}

			if (source != null && !"".equals(source)) {
				queryBuilder.append("source = ");
				queryBuilder.append('"' + source + '"');
			}

			if (destination != null && !"".equals(destination)) {
				queryBuilder.append("destination = ");
				queryBuilder.append('"' + destination + '"');
			}

			String qs = queryBuilder.toString();

			pstmt = con.prepareStatement(qs);
			// pstmt = con.qs;

			rs = pstmt.executeQuery();

			rs.last();
			count = rs.getRow();
			rs.beforeFirst();

			if (count > 0) {
				f = new Flightdetails[count];
				int i = 0;

				while (rs.next()) {
					Flightdetails flight = new Flightdetails();
					flight.setFlightnumber(rs.getString(1));
					flight.setAirlinename(rs.getString(2));
					flight.setSource(rs.getString(3));
					flight.setDestination(rs.getString(4));
					flight.setCrewdetails(rs.getString(5));
					flight.setNumberofseats(rs.getInt(6));
					flight.setDuration(rs.getString(7));
					flight.setMsg("Success");
					f[i] = flight;
					i++;
				}

			} else {
				f = new Flightdetails[1];								
				Flightdetails flight1 = new Flightdetails();
				flight1.setMsg ("failed");				
				f[0] = flight1;
				

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		// try
		// {
		// }
		// catch (SQLException e) {
		//
		// e.printStackTrace();
		// }
		// }
		return f;
	}

	// to retrieve single flight details to be updated
	public Flightdetails listOneFlight(String flightnumber) {
		Flightdetails f = new Flightdetails();
		try {
			String query = "select * from flightdetails where flightnumber=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, flightnumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				f.flightnumber = rs.getString(1);
				f.airlinename = rs.getString(2);
				f.source = rs.getString(3);
				f.destination = rs.getString(4);
				f.crewdetails = rs.getString(5);
				f.numberofseats = rs.getInt(6);
				f.duration = rs.getString(7);
				f.msg = "Success";
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return f;
	}

	
	public String deleteOneFlight(String flightnumber)
	{
		String res="";
		try
		{
			String query="delete from flightdetails where flightnumber=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, flightnumber);
			int rowcount = pstmt.executeUpdate();
			if(rowcount>0)
				res="success";
			else
				res="fail";
			
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
		return res;
		
	}
	
	public SearchTraveller[] searchtraveller(String fname,String lname,String fno,String dest,String traveldate)
	{
		SearchTraveller [] t=null;

		int count=0;

		try {

			StringBuilder queryBuilder  =  new StringBuilder(" select person.firstname, person.lastname,journey.flightnumber,journey.destination,journey.traveldate from person join journey on person.emailid=journey.emailid where ");

			if (fno != null && !"".equals(fno)) {
				queryBuilder.append("journey.flightnumber = ");
				queryBuilder.append('"' + fno + '"');
			}

			if (fname != null && !"".equals(fname)) {
				queryBuilder.append("person.firstname = ");
				queryBuilder.append('"' + fname + '"');
			}

			if (lname != null && !"".equals(lname)) {
				queryBuilder.append("person.lastname = ");
				queryBuilder.append('"' + lname + '"');
			}
			if (dest != null && !"".equals(dest)) {
				queryBuilder.append("journey.destination = ");
				queryBuilder.append('"' + dest + '"');
			}
			if (traveldate != null && !"".equals(traveldate)) {
				queryBuilder.append("journey.traveldate = ");
				queryBuilder.append('"' + traveldate + '"');
			}

			String qs = queryBuilder.toString();

			pstmt = con.prepareStatement(qs);
			//pstmt = con.qs;

			rs = pstmt.executeQuery();

			rs.last();
			count=rs.getRow();
			rs.beforeFirst();


			System.out.println("number of records returned:"+ count);
			if(count>0)
			{
				t= new SearchTraveller[count];
				int i=0;

				while(rs.next())
				{
					SearchTraveller traveller = new SearchTraveller();
					traveller.setFlightnumber(rs.getString(1));
					traveller.setFirstname(rs.getString(2));
					traveller.setLastname(rs.getString(3));
					traveller.setDestination(rs.getString(4));
					traveller.setTraveldate(rs.getString(5));
					traveller.setMsg("Success");	
					t[i]=traveller;
					i++;
				}

			}
			else
			{
				t = new SearchTraveller[1];								
				SearchTraveller trav = new SearchTraveller();
				trav.setMsg ("failed");				
				t[0] = trav;
				
				
//				SearchTraveller[] travellererr = new SearchTraveller[1];
//				travellererr[0].setMsg("failed");

			}
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return t;
	}
	public String signup(String fname, String lname, String usn, String pwd,
			String address, String city, String state, String zipcode,
			String dob, String ssn, String ppn, String nationality) 
{
		String logintime="New Login";
	String result="";
	int r1, r2;
	try
	{
		String query3 = "insert into person values(?,?,?,?,?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(query3);
		pstmt.setString(1, usn);
		pstmt.setString(2, fname);
		pstmt.setString(3, lname);
		pstmt.setString(4, address);
		pstmt.setString(5, city);
		pstmt.setString(6, state);
		pstmt.setString(7, zipcode);
		pstmt.setString(8, dob);
		pstmt.setString(9, "cust");
		pstmt.setString(10, pwd);
		pstmt.setString(11, logintime);
		r1=pstmt.executeUpdate();
		
		
		
		
		String query7 = "insert into traveller values(?,?,?,?)";
		pstmt2 = con.prepareStatement(query7);
		pstmt2.setString(1, ssn);
		pstmt2.setString(2, ppn);
		pstmt2.setString(3, nationality);
		pstmt2.setString(4, usn);
		r2=pstmt2.executeUpdate();
		
		if (r1 >0 && r2>0) 
		{
			result = "true";
			System.out.println("Insertion successful");
		} 
		else
			result = "Insertion failed! Try again";


	}
	catch(SQLException e)
	{
		
			e.printStackTrace();
		
	}
	
	
	return result;
}
	
	public String addemployees(String firstname,String lastname,String usn,String ssn,String type,
			String addr,String city,String zip,String state,String dob,String password,String workd,String pos,String hire)
	{ 
		String logintime ="New login";
		String result="";
		int r1, r2;
		System.out.println("before try");
		try
		{	System.out.println("in try");
			String query3 = "insert into person values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query3);
			pstmt.setString(1, usn);
			pstmt.setString(2, firstname);
			pstmt.setString(3, lastname);
			pstmt.setString(4, addr);
			pstmt.setString(5, city);
			pstmt.setString(6, state);
			pstmt.setString(7, zip);
			pstmt.setString(8, dob);
			pstmt.setString(9, type);
			pstmt.setString(10, password);
			pstmt.setString(11, logintime);
			System.out.println("before execute");
			r1=pstmt.executeUpdate();
			System.out.println("r1="+ r1);
			System.out.println("after exec");
			
			
			
			
			String query7 = "insert into employee values(?,?,?,?,?)";
			pstmt2 = con.prepareStatement(query7);
			pstmt2.setString(1, usn);
			pstmt2.setString(2, ssn);
			pstmt2.setString(3, workd);
			pstmt2.setString(4, pos);
			pstmt2.setString(5, hire);
			r2=pstmt2.executeUpdate();
			
			if (r1 >0 && r2>0) 
			{
				result = "true";
				System.out.println("Insertion successful");
			} 
			else
				result = "Insertion failed! Try again";


		}
		catch(SQLException e)
		{
			
				e.printStackTrace();
			
		}
		
		
		return result;
		
	}
	
	
	//list all travellers
	
	public Traveller[] listtravellers() 
	{

			int i = 0;
			Traveller[] t = null;
			Connection conn = null;
			try {
				
					String query15 = "select * from traveller";
					pstmt = con.prepareStatement(query15);
					 rs = pstmt.executeQuery();
					rs.last();
					int count = rs.getRow();
					rs.beforeFirst();
					t = new Traveller[count];
					while (rs.next()) {
						Traveller t1 = new Traveller();

						t1.setSsn(rs.getString(1));
						t1.setPassportnumber(rs.getString(2));
						t1.setNationality(rs.getString(3));
						t1.setEmailid(rs.getString(4));

						t[i] = t1;
						i++;
					

				}
				
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}

			return t;

		}
	
	public Employee[] listemployees() 
	{

			int i = 0;
			Employee[] emp = null;
			
			try 
			{		
					
					String query13 = "select * from employee";
					pstmt = con.prepareStatement(query13);
					 rs = pstmt.executeQuery();

					
					rs.last();
					int count = rs.getRow();
					rs.beforeFirst();
					emp = new Employee[count];
					while (rs.next()) 
					{
						Employee em = new Employee();

						em.setEmailid(rs.getString(1));
						em.setSsn(rs.getString(2));
						em.setWorkdesc(rs.getString(3));
						em.setPosition(rs.getString(4));
						em.setHiredate(rs.getString(5));

						emp[i] = em;
						i++;
					}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			return emp;

		}
	
	public Journey[] listreservations() 
	{
			int i = 0;

			Journey[] j1 = null;
			
			try
			{
					String query14 = "select * from journey";
					pstmt = con.prepareStatement(query14);
					 rs = pstmt.executeQuery();
					rs.last();
					int count = rs.getRow();
					rs.beforeFirst();
					j1 = new Journey[count];

					while (rs.next()) 
					{
						Journey me = new Journey();

						me.setFlightnumber(rs.getString(1));
						me.setDestination(rs.getString(2));
						me.setBoardingpoint(rs.getString(3));
						me.setEmailid(rs.getString(4));
						me.setAirlinename(rs.getString(5));
						me.setTraveldate(rs.getString(6));

						j1[i] = me;
						i++;
					}

			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return j1;

		}
	
	public Flightdetails[] listflights() {

		int i = 0;

		Flightdetails[] flight = null;
		
		try {
			
				String query12 = "select * from flightdetails";
				pstmt = con.prepareStatement(query12);
				ResultSet rs = pstmt.executeQuery();

				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();
				flight = new Flightdetails[count];
				while (rs.next()) 
				{
					Flightdetails fd = new Flightdetails();

					fd.setFlightnumber(rs.getString(1));
					fd.setAirlinename(rs.getString(2));
					fd.setSource(rs.getString(3));
					fd.setDestination(rs.getString(4));
					fd.setCrewdetails(rs.getString(5));
					fd.setNumberofseats(rs.getInt(6));

					flight[i] = fd;
					i++;
				}

			}
			 catch (Exception e) 
			 {
				 e.printStackTrace();
			 }

		return flight;

	}
	
	
	public SearchEmployee[] searchEmployee(String firstname,String lastname, String city,String state,String position,String hiredate)
	{
		SearchEmployee [] e=null;

		int count=0;
				
				try {

					StringBuilder queryBuilder  =  new StringBuilder(" select person.firstname, person.lastname,person.city,person.state,employee.position,employee.hiredate,person.emailid from person join employee on person.emailid=employee.emailid where ");

					if (firstname != null && !"".equals(firstname)) {
						queryBuilder.append("person.firstname = ");
						queryBuilder.append('"' + firstname + '"');
					}

					if (lastname != null && !"".equals(lastname)) {
						queryBuilder.append("person.lastname = ");
						queryBuilder.append('"' + lastname + '"');
					}

					if (city != null && !"".equals(city)) {
						queryBuilder.append("person.city = ");
						queryBuilder.append('"' + city + '"');
					}
					if (state != null && !"".equals(state)) {
						queryBuilder.append("person.state = ");
						queryBuilder.append('"' + state + '"');
					}
					if (position != null && !"".equals(position)) {
						queryBuilder.append("traveller.position = ");
						queryBuilder.append('"' + position + '"');
					}
					if (hiredate != null && !"".equals(hiredate)) {
						queryBuilder.append("traveller.hiredate = ");
						queryBuilder.append('"' + hiredate + '"');
					}		
					String qs = queryBuilder.toString();
					
					pstmt = con.prepareStatement(qs);
					//pstmt = con.qs;
					
					rs = pstmt.executeQuery();
					
						rs.last();
						 count=rs.getRow();
						rs.beforeFirst();
						
					
					
					if(count>0)
					{
						e= new SearchEmployee[count];
						int i=0;

						while(rs.next())
						{
							SearchEmployee employee = new SearchEmployee();
							employee.setFirstname(rs.getString(1));
							employee.setLastname(rs.getString(2));
							employee.setCity(rs.getString(3));
							employee.setState(rs.getString(4));
							employee.setPosition(rs.getString(5));
							employee.setHiredate(rs.getString(6));
							employee.setEmailid(rs.getString(7));							
							employee.setMsg("Success");	
							e[i]=employee;
							i++;
						}
					
				}
					else
					{
//						SearchEmployee[] employeeerr = new SearchEmployee[1];
//						employeeerr[0].setMsg("failed");
						
						e = new SearchEmployee[1];								
						SearchEmployee trav = new SearchEmployee();
						trav.setMsg ("failed");				
						e[0] = trav;

					}
				}

				catch (SQLException ex) {

					ex.printStackTrace();
				}
			
				return e;
	}
	
	public String deleteEmployee(String email)
	{
		String res="";
		try
		{
			String query="delete from employee where emailid=?";
			String query1="delete from person where emailid=?";
			
			pstmt = con.prepareStatement(query);
			pstmt1 = con.prepareStatement(query1);
			pstmt.setString(1, email);
			pstmt1.setString(1, email);
			
			int rowcount = pstmt.executeUpdate();
			
			if(rowcount>0)
			{
				int rowcount1 = pstmt1.executeUpdate();
				if(rowcount1>0)
				res="success";
			}
			else
				res="fail";
			
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
		return res;
		
	}
	
	public Flightdetails[] listflightsCustomer(String sr, String d) {

		int i = 0;

		
		Flightdetails[] de = null;
		
		
		try {
				String query12 = "select * from flightdetails where source = ? and destination = ?";
				
				pstmt = con.prepareStatement(query12);
				pstmt.setString(1, sr);
				pstmt.setString(2, d);
				rs = pstmt.executeQuery();

				
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();
				de = new Flightdetails[count];
				while (rs.next()) 
				{
					Flightdetails fd = new Flightdetails();

					fd.setFlightnumber(rs.getString(1));
					fd.setAirlinename(rs.getString(2));
					fd.setSource(rs.getString(3));
					fd.setDestination(rs.getString(4));
					fd.setCrewdetails(rs.getString(5));
					fd.setNumberofseats(rs.getInt(6));
					fd.setDuration(rs.getString(7));

					de[i] = fd;
					i++;
				}

			}

		 catch (SQLException e)
		 {
			e.printStackTrace();
		}

		return de;

	}
	
	public Journey[] payfortickets(String f,String l,String eml,String fldur,String fln,String flno,String ts, String src, String dest,String tmps) 
	{
		
		Journey[] journ = null;
		int i=0;
		try {
				String query24 = "insert into payments values(?,?,?,?,?)";
				pstmt = (PreparedStatement) con.prepareStatement(query24);
				pstmt.setString(1, f);
				pstmt.setString(2, l);
				pstmt.setString(3, eml);
				pstmt.setString(4, flno);
				pstmt.setString(5, tmps);
				pstmt.executeUpdate();
				
				
				
				
				String query25 = "insert into journey values(?,?,?,?,?,?,?)";
				pstmt2 = (PreparedStatement) con.prepareStatement(query25);
				pstmt2.setString(1, flno);
				pstmt2.setString(2, dest);
				pstmt2.setString(3, src);
				pstmt2.setString(4, eml);
				pstmt2.setString(5, ts);
				pstmt2.setString(6, fln);
				pstmt2.setString(7, fldur);
				pstmt2.executeUpdate();
				
				
				
				
				String query27 = "select * from journey where flightnumber = ? and traveldate = ? and duration = ?";
				pstmt3 = (PreparedStatement) con.prepareStatement(query27);
				pstmt3.setString(1, flno);
				pstmt3.setString(2, ts);
				pstmt3.setString(3, fldur);
				rs = pstmt3.executeQuery();
				rs.last();
				int count = rs.getRow();
				rs.beforeFirst();
				journ = new Journey[count];
				while (rs.next()) {
					Journey jrny = new Journey();

					jrny.setFlightnumber(rs.getString(1));
					jrny.setDestination(rs.getString(2));
					jrny.setBoardingpoint(rs.getString(3));
					jrny.setEmailid(rs.getString(4));
					jrny.setTraveldate(rs.getString(5));
					jrny.setAirlinename(rs.getString(6));
					jrny.setDuration(rs.getString(7));
					

					journ[i] = jrny;
					i++;
					
				}
				
				
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		
		return journ;
		
	}
}
