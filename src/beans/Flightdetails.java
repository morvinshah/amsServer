package beans;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Flightdetails implements Serializable {
	public String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String flightnumber;	
	public String getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}
	public String getAirlinename() {
		return airlinename;
	}
	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getCrewdetails() {
		return crewdetails;
	}
	public void setCrewdetails(String crewdetails) {
		this.crewdetails = crewdetails;
	}
	public int getNumberofseats() {
		return numberofseats;
	}
	public void setNumberofseats(int numberofseats) {
		this.numberofseats = numberofseats;
	}
	public String airlinename;	
	public String source;	
	public String destination;	
	public String crewdetails;	
	public int numberofseats;
	public String duration;
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	}
