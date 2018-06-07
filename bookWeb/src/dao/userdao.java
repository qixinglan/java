package dao;



import java.sql.ResultSet;
import java.util.List;

import entity.Book;
import entity.User;

public class userdao extends BaseDao{
	private static final String findAll="select * from user_b";
	private static final String add="insert into user_b(u_id,uname,pwd,email) values(bookuser_seq.nextval,?,?,?)";
	private static final String updateByU_id="update user_b set uname=?,pwd=?,email=? where u_id=?";
	private static final String findByPage="select * from (select " +
			"u_id,uname,pwd,email,status" +
			",rownum n from user_b where rownum <=10*? order by uname asc) where n>10*(?-1)";//分页查找
	private static final String deleteByUid="delete from user_b where u_id=?";//根据uid删除
	private static final String findByUid="select * from user_b where u_id=?";//根据uid查找
	private static final String upStatusByUid="update user_b set status=? where u_id=?";//更新状态根据uid
	/**
	 * 更新status根据uid
	 */
	public void upStatueByUid(int status, int uid) throws Exception{
		
		update(upStatusByUid,new Object[]{status,uid});
	}
	/**
	 * 根据uid查找一个用户
	 * @throws Exception 
	 */
	public List<User> findByUid(int uid) throws Exception{
		List<User> list=query(findByUid, new Object[]{uid});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 删除一个用户根据uid
	 */
	public void deleteByUid(int uid) throws Exception{
		
		update(deleteByUid,new Object[]{uid});
	}
	/**
	 * 分页查找
	 * @throws Exception 
	 */
	public List<User> findByPage(int page) throws Exception{
		List<User> list=query(findByPage, new Object[]{page,page});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 找到所有用户
	 * @throws Exception 
	 */
	public List findAll() throws Exception{
		List<User> list=query(findAll, null);
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 插入一个用户
	 */
	public void add(User user) throws Exception{
		
		update(add,new Object[]{user.getUname(),user.getPassword(),user.getEmail()});
	}
	/**
	 * 更新一个用户
	 */
	public void updateByU_id(User user) throws Exception{
		
		update(updateByU_id,new Object[]{user.getUname(),user.getPassword(),user.getEmail(),user.getUid()});
	}
	public Object toEntity(ResultSet rs) throws Exception {
		User user=new User();
		user.setUid(rs.getInt("u_id"));
		user.setPassword(rs.getString("pwd"));
		user.setUname(rs.getString("uname"));
		user.setEmail(rs.getString("email"));
		user.setStatus(rs.getInt("status"));
		return user;
	}

}
