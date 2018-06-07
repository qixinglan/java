package dao;

import java.sql.ResultSet;
import java.util.List;

import entity.Book;
import entity.ShopRecord;
import entity.User;

public class shoprecorddao extends BaseDao{
	private final String findByU_id="select * from shoprecord where u_id=?";
	private final String insert="insert into shoprecord values(?,?,1)";
	private final String findChapterByU_idBid="select * from shoprecord where u_id=? and bid=?";
	private final String updateChapter="update shoprecord set chapter=? where u_id=? and bid=?";
	private final String deleteByUid="delete from shoprecord where u_id=?";
	private static final String findByUidPage="select * from (select " +
			"u_id,bid,chapter" +
			",rownum n from shoprecord where rownum <=10*? and u_id=? order by bid asc) where n>10*(?-1)";//分页查找
	private static final String deleteByUidBid="delete from shoprecord where u_id=? and bid=?";
	/**
	 * 根据UIDBID删除已购买书籍
	 * @throws Exception 
	 */
	public void deleteByUidBid(int uid,int bid) throws Exception{
		update(deleteByUidBid, new Object[]{uid,bid});
	}
	
	
	/**
	 * 根据用户ID找到已购买书籍
	 * @throws Exception 
	 */
	public List<ShopRecord> findByUidPage(int uid,int page) throws Exception{
		List<ShopRecord> list=query(findByUidPage, new Object[]{page,uid,page});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 根据用户ID删除该用户已购买书籍
	 * @throws Exception 
	 */
	public void deleteByUid(int uid) throws Exception{
		update(deleteByUid, new Object[]{uid});
	}
	/**
	 * 根据用户ID找到已购买书籍
	 * @throws Exception 
	 */
	public List<ShopRecord> findByU_id(int u_id) throws Exception{
		List<ShopRecord> list=query(findByU_id, new Object[]{u_id});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 根据用户ID和电子书id找到已购买书籍(目的阅读记录chapter）
	 * @throws Exception 
	 */
	public void updateChapter(int chapter,int u_id,int bid) throws Exception{
		update(updateChapter, new Object[]{chapter,u_id,bid});
	}
	/**
	 * 插入一个购买记录
	 */
	public void insert(int u_id,int bid) throws Exception{
		update(insert,new Object[]{u_id,bid});
	}
	/**
	 * 根据用户ID和电子书id修改已购买书籍的阅读记录
	 * @throws Exception 
	 */
	public ShopRecord findChapter(int u_id,int bid) throws Exception{
		List<ShopRecord> list=query(findChapterByU_idBid, new Object[]{u_id,bid});
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public Object toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		int u_id=rs.getInt("u_id");
		int bid=rs.getInt("bid");
		int chapter=rs.getInt("chapter");
		ShopRecord shoprecord=new ShopRecord();
		shoprecord.setChapter(chapter);
		shoprecord.setBid(bid);
		shoprecord.setU_id(u_id);
		return shoprecord;
	}

}
