package bean;

public class Stock {
	private int code;
	private double price;
	private String name;
	
	public Stock() {
		super();
	}
	public Stock(int code, double price, String name) {
		super();
		this.code = code;
		this.price = price;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
