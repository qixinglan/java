package bean;

import java.util.ArrayList;
import java.util.List;

//���ﳵ��
/*
 * �����Ʒ
 * ɾ����Ʒ
 * �޸���Ʒ����
 * �����Ʒ
 * �Ƽ�
 * ��Ʒ�б�
 */
public class cart {
	//���ϱ��湺�ﳵ��Ʒ
	private List<cartItem> items = new ArrayList<cartItem>();
	//����Ʒ��ӵ����ﳵ
	public boolean add(cartItem item){
		for(cartItem e :items){
			if(e.getP().getId()==item.getP().getId()){
				return false;
			}
		}
		items.add(item);
		return true;
	}
	//ɾ����Ʒ
	public void delete(int id){
		for(cartItem e :items){
			if(e.getP().getId()==id){
				items.remove(e);
			}
		}
	}
	//�޸���Ʒ����
	public void modify(int id,int qty){
		for(cartItem e :items){
			if(e.getP().getId()==id){
				e.setQty(qty);
			}
		}
		
	}
	//�����Ʒ
	public void clear(){
		items.clear();
	}
	//�Ƽ�
	public double jijia(){
		double total=0;
		for(cartItem e :items){
			total+=e.getQty()*e.getP().getPrice();
		}
		return total;
	}
	//������Ʒ�б�
	public List<cartItem> relist(){
		return items;
	}
	
	
}
