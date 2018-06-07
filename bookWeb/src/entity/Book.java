package entity;

public class Book {
	private int bid;
	private String bname;
	private String author;
	private String type;
	private String introduce;
	private String imgadd;
	private String bookadd;
	private double price;
	private int totalchapter;
	public int getTotalchapter() {
		return totalchapter;
	}
	public void setTotalchapter(int totalchapter) {
		this.totalchapter = totalchapter;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBookadd() {
		return bookadd;
	}
	public void setBookadd(String bookadd) {
		this.bookadd = bookadd;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getImgadd() {
		return imgadd;
	}
	public void setImgadd(String imgadd) {
		this.imgadd = imgadd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + bid;
		result = prime * result + ((bname == null) ? 0 : bname.hashCode());
		result = prime * result + ((bookadd == null) ? 0 : bookadd.hashCode());
		result = prime * result + ((imgadd == null) ? 0 : imgadd.hashCode());
		result = prime * result
				+ ((introduce == null) ? 0 : introduce.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + totalchapter;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bid != other.bid)
			return false;
		if (bname == null) {
			if (other.bname != null)
				return false;
		} else if (!bname.equals(other.bname))
			return false;
		if (bookadd == null) {
			if (other.bookadd != null)
				return false;
		} else if (!bookadd.equals(other.bookadd))
			return false;
		if (imgadd == null) {
			if (other.imgadd != null)
				return false;
		} else if (!imgadd.equals(other.imgadd))
			return false;
		if (introduce == null) {
			if (other.introduce != null)
				return false;
		} else if (!introduce.equals(other.introduce))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (totalchapter != other.totalchapter)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
