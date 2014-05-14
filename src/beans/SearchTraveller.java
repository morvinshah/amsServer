package beans;
import java.io.Serializable;

@SuppressWarnings("serial")
public class SearchTraveller implements Serializable{
	
	public String flightnumber;
	public String getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String firstname;
	public String lastname;
	public String destination;
	public String traveldate;
	public String msg;

}
