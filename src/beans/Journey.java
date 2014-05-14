package beans;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Journey implements Serializable
{
	public String flightnumber;
	public String destination;
	public String boardingpoint;
	public String emailid;
	public String airlinename;
	public String traveldate;
	public String duration;
public String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getFlightnumber() {
		return flightnumber;
	}
	public void setFlightnumber(String flightnumber) {
		this.flightnumber = flightnumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getBoardingpoint() {
		return boardingpoint;
	}
	public void setBoardingpoint(String boardingpoint) {
		this.boardingpoint = boardingpoint;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAirlinename() {
		return airlinename;
	}
	public void setAirlinename(String airlinename) {
		this.airlinename = airlinename;
	}
	public String getTraveldate() {
		return traveldate;
	}
	public void setTraveldate(String traveldate) {
		this.traveldate = traveldate;
	}
	
	
}