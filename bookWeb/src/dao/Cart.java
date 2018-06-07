package dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Book;

//购物车类
/*
 * 添加商品
 * 删除商品
 * 修改商品数量
 * 清空商品
 * 计价
 * 商品列表
 */
public class Cart {
	//集合保存购物车商品
	private List<Book> items = new ArrayList<Book>();
	private double total=0;
	//将商品添加到购物车
	public boolean add(Book book){
		for(Book e :items){
			if(e.getBid()==book.getBid()){
				return false;
			}
		}
		items.add(book);
		return true;
	}
	//删除商品
	public void delete(int id){
		Iterator<Book> it=items.iterator();
		while(it.hasNext()){
			if(it.next().getBid()==id){
				it.remove();
			}
		}
	}
	
	//清空商品
	public void clear(){
		items.clear();
	}
	//计价
	public double jijia(){
		total=0;
		for(Book e :items){
			total+=e.getPrice();
		}
		return total;
	}
	//返回商品列表
	public List<Book> getItems() {
		return items;
	}
	
}
