package dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import entity.Book;

//���ﳵ��
/*
 * �����Ʒ
 * ɾ����Ʒ
 * �޸���Ʒ����
 * �����Ʒ
 * �Ƽ�
 * ��Ʒ�б�
 */
public class Cart {
	//���ϱ��湺�ﳵ��Ʒ
	private List<Book> items = new ArrayList<Book>();
	private double total=0;
	//����Ʒ��ӵ����ﳵ
	public boolean add(Book book){
		for(Book e :items){
			if(e.getBid()==book.getBid()){
				return false;
			}
		}
		items.add(book);
		return true;
	}
	//ɾ����Ʒ
	public void delete(int id){
		Iterator<Book> it=items.iterator();
		while(it.hasNext()){
			if(it.next().getBid()==id){
				it.remove();
			}
		}
	}
	
	//�����Ʒ
	public void clear(){
		items.clear();
	}
	//�Ƽ�
	public double jijia(){
		total=0;
		for(Book e :items){
			total+=e.getPrice();
		}
		return total;
	}
	//������Ʒ�б�
	public List<Book> getItems() {
		return items;
	}
	
}
