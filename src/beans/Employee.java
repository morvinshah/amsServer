package beans;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Employee implements Serializable {
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getWorkdesc() {
		return workdesc;
	}
	public void setWorkdesc(String workdesc) {
		this.workdesc = workdesc;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public String emailid;
	public String ssn;
	public String workdesc;
	public String position;
	public String hiredate;
public String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
