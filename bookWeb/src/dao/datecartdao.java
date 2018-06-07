package dao;



import java.sql.ResultSet;
import java.util.List;

import entity.Book;
import entity.DateCart;

public class datecartdao extends BaseDao{
	private String findByU_id="select * from cart where u_id =?";
	private String insert="insert into cart values (?,?)" ;
	private String deleteByUid="delete from cart where u_id=?";
	private static final String findByUidPage="select * from (select " +
			"u_id,bid" +
			",rownum n from cart where rownum <=10*? and u_id=? order by bid asc) where n>10*(?-1)";//��ҳ���� 
	private static final String deleteByUidBid="delete from cart where u_id=? and bid=?";
	/**
	 * ɾ��һ���� ����UIdbid
	 */
	public void deleteByUidBid(int uid,int bid) throws Exception{
		
		update(deleteByUidBid,new Object[]{uid,bid});
	}
	/**
	 * ����u_id�ҵ����ﳵ���û����鼮����ҳ��ʾ
	 * @throws Exception 
	 */
	public List<DateCart> findByUidPage(int uid,int page) throws Exception{
		List<DateCart> list=query(findByUidPage, new Object[]{page,uid,page});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * ����u_id�ҵ����ﳵ���û����鼮
	 * @throws Exception 
	 */
	public List<DateCart> findByU_id(int u_id) throws Exception{
		List<DateCart> list=query(findByU_id, new Object[]{u_id});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * ����һ���� ���빺�ﳵ
	 */
	public void add(int u_id,int bid) throws Exception{
		
		update(insert,new Object[]{u_id,bid});
	}
	/**
	 * ����U_idɾ��һ����
	 * 
	 * @throws Exception 
	 */
	public void delete(int u_id) throws Exception{
		update(deleteByUid,new Object[]{u_id});
	}
	
	public Object toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		DateCart dateCart=new DateCart();
		int u_id=rs.getInt("u_id");
		int bid=rs.getInt("bid");
		dateCart.setBid(bid);
		dateCart.setU_id(u_id);
		return dateCart;
	}
	 
}
