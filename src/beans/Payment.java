package beans;
import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Payment implements Serializable
{
	
	public int emaidid;
	public double amount;
	public Date paydate;
public String msg;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getEmaidid() {
		return emaidid;
	}
	public void setEmaidid(int emaidid) {
		this.emaidid = emaidid;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	
	
}