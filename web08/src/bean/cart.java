package bean;

import java.util.ArrayList;
import java.util.List;

//购物车类
/*
 * 添加商品
 * 删除商品
 * 修改商品数量
 * 清空商品
 * 计价
 * 商品列表
 */
public class cart {
	//集合保存购物车商品
	private List<cartItem> items = new ArrayList<cartItem>();
	//将商品添加到购物车
	public boolean add(cartItem item){
		for(cartItem e :items){
			if(e.getP().getId()==item.getP().getId()){
				return false;
			}
		}
		items.add(item);
		return true;
	}
	//删除商品
	public void delete(int id){
		for(cartItem e :items){
			if(e.getP().getId()==id){
				items.remove(e);
			}
		}
	}
	//修改商品数量
	public void modify(int id,int qty){
		for(cartItem e :items){
			if(e.getP().getId()==id){
				e.setQty(qty);
			}
		}
		
	}
	//清空商品
	public void clear(){
		items.clear();
	}
	//计价
	public double jijia(){
		double total=0;
		for(cartItem e :items){
			total+=e.getQty()*e.getP().getPrice();
		}
		return total;
	}
	//返回商品列表
	public List<cartItem> relist(){
		return items;
	}
	
	
}
