package dao;

import java.sql.ResultSet;
import java.util.List;

import entity.Administrator;
import entity.Book;

public class AdminiDao extends BaseDao{
//	private static final int rowspage=10;//ÿҳ��Ŀ
	private static final String findAll="select * from administrator";//��ѯ����
//	private static final String findByPage="select * from (select aname,apwd,atype,rownum n from administrator" +
//			"where rownum <="+rowspage+"*?) where n>="+rowspage+"*(?-1)";
	//��ҳ����,ÿҳʮ��
	private static final String findByPage="select * from (select aname,apwd,atype,rownum n from administrator where rownum <=10*? order by aname asc) where n>10*(?-1)";
	private static final String updateByAname="update administrator set aname=? ,apwd=? ,atype=? where aname=?";
	private static final String insert="insert into administrator values(?,?,?)";
	
	//��ӹ���Ա
	public void insert(String aname,String apwd,int atype) throws Exception{
		update(insert, new Object[]{aname,apwd,atype});
	}
	
	
	
	//ɾ�����ݹ���Ա�û���
	private static final String delete="delete from administrator where aname=?";
	public void deleteByAname(String aname) throws Exception{
		update(delete, new Object[]{aname});
	}
	
	//���¹���Ա��Ϣ
	public void updateByAname(String aname,String apwd,String atype,String yaname) throws Exception{
		update(updateByAname, new Object[]{aname,apwd,atype,yaname});
	}
	
	
	
	//��ѯ����
	public List<Administrator> findAll() throws Exception{
		List<Administrator> list=query(findAll, null);
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	//��ҳ����
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
