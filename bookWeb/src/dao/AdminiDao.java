package dao;

import java.sql.ResultSet;
import java.util.List;

import entity.Administrator;
import entity.Book;

public class AdminiDao extends BaseDao{
//	private static final int rowspage=10;//每页数目
	private static final String findAll="select * from administrator";//查询所有
//	private static final String findByPage="select * from (select aname,apwd,atype,rownum n from administrator" +
//			"where rownum <="+rowspage+"*?) where n>="+rowspage+"*(?-1)";
	//分页查找,每页十条
	private static final String findByPage="select * from (select aname,apwd,atype,rownum n from administrator where rownum <=10*? order by aname asc) where n>10*(?-1)";
	private static final String updateByAname="update administrator set aname=? ,apwd=? ,atype=? where aname=?";
	private static final String insert="insert into administrator values(?,?,?)";
	
	//添加管理员
	public void insert(String aname,String apwd,int atype) throws Exception{
		update(insert, new Object[]{aname,apwd,atype});
	}
	
	
	
	//删除依据管理员用户名
	private static final String delete="delete from administrator where aname=?";
	public void deleteByAname(String aname) throws Exception{
		update(delete, new Object[]{aname});
	}
	
	//更新管理员信息
	public void updateByAname(String aname,String apwd,String atype,String yaname) throws Exception{
		update(updateByAname, new Object[]{aname,apwd,atype,yaname});
	}
	
	
	
	//查询所有
	public List<Administrator> findAll() throws Exception{
		List<Administrator> list=query(findAll, null);
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	//分页查找
		public List<Administrator> findByPage(int page) throws Exception{
			System.out.println("aa");
			List<Administrator> list=query(findByPage, new Object[]{page,page});
			if(list.size()!=0){
				return list;
			}else{
				return null;
			}
		}

	@Override
	public Object toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		Administrator admini=new Administrator();
		String aname=rs.getString("aname");
		String apwd=rs.getString("apwd");
		int atype=rs.getInt("atype");
		admini.setAname(aname);
		admini.setApwd(apwd);
		admini.setAtype(atype);
		return admini;
	}
}
