package bean;

import entity.produce;

public class cartItem {
	private produce p;
	private int qty;
	
	public cartItem() {
		super();
	}
	public cartItem(produce p, int qty) {
		super();
		this.p = p;
		this.qty = qty;
	}
	public produce getP() {
		return p;
	}
	public void setP(produce p) {
		this.p = p;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
