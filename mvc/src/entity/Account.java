package entity;

public class Account {
	private int id;
	private String username;
	private double balance;
	private String pwd;
	
	
	
	public Account() {
		super();
	}
	public Account(int id, String username, double balance, String pwd) {
		super();
		this.id = id;
		this.username = username;
		this.balance = balance;
		this.pwd = pwd;
	}
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", balance="
				+ balance + ", pwd=" + pwd + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
