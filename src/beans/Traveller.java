package beans;
import java.io.Serializable;
//import java.sql.Date;

@SuppressWarnings("serial")
public class Traveller implements Serializable
{
	public String ssn;
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getPassportnumber() {
		return passportnumber;
	}
	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String passportnumber;
	public String nationality;
	public String emailid;
public String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}