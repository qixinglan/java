package entity;

public class produce {
	private int id;
	private String model;
	private String imagedes;
	private String producedes;
	private double price;
	
	public produce() {
		super();
	}
	public produce(int id, String model, String imagedes, String producedes,
			double price) {
		super();
		this.id = id;
		this.model = model;
		this.imagedes = imagedes;
		this.producedes = producedes;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getImagedes() {
		return imagedes;
	}
	public void setImagedes(String imagedes) {
		this.imagedes = imagedes;
	}
	public String getProducedes() {
		return producedes;
	}
	public void setProducedes(String producedes) {
		this.producedes = producedes;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
