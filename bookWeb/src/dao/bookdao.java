package dao;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import entity.Book;

public class bookdao extends BaseDao{
	private static final String findByType="select * from book where type=?";//根据类别
	private static final String findById="select * from book where bid=?";// 根据bid
	private static final String findByKeyword="select * from book where " +
			"bname like ? or type like ? or author like ?";//模糊查询，      可能不用
	private static final String findAll="select * from book";//找到所有电子书
	private static final String findByPage="select * from (select " +
			"bid,bname,introduce,author,imgadd,bookadd,totalchapter,type,price" +
			",rownum n from book where rownum <=10*? order by bname asc) where n>10*(?-1)";//分页查找
	private static final String deleteByBid="delete from book where bid=?";// 根据bid删除一本书
	private static final String updateByBid="update book set bname=?,author=?,type=?,introduce=?,imgadd=?,bookadd=?,price=?,totalchapter=? where bid=?";// 根据bid更新一本书
	private static final String insert="insert into book values(bookuser_seq.nextval,?,?,?,?,?,?,?,?)";
	private static final String findByBname="select * from book where bname=?";
	private static final String updateimgaddByBid="update book set imgadd=? where bid=?" ;
	
	/**
	 * 根据bid修改图书图片地址
	 * @throws Exception 
	 * 
	 */		
	public void updateimgaddByBid(String filename,int bid) throws Exception{
		update(updateimgaddByBid, new Object[]{filename,bid});
	}
	/**
	 * 根据出名查找一本书
	 */
	public List<Book> findByBname(String bname) throws Exception{
		List<Book> list=query(findByBname, new Object[]{bname});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 *  插入一本书
	 * @throws Exception 
	 * 
	 */
	public void insert(String bname,String author,String type,String introduce,String imgadd,String bookadd,double price,int totalchapter) throws Exception{
		update(insert, new Object[]{bname,author,type,introduce,imgadd,bookadd,price,totalchapter});
		
	}
	/**
	 *  根据bid修改一本书
	 * @throws Exception 
	 * 
	 */
	public void updateByBid(String bname,String author,String type,String introduce,String imgadd,String bookadd,double price,int totalchapter,int bid) throws Exception{
		update(updateByBid, new Object[]{bname,author,type,introduce,imgadd,bookadd,price,totalchapter,bid});
		
	}
	/**
	 *  根据bid删除一本书
	 * @throws Exception 
	 * 
	 */
	public void deleteByBid(int bid) throws Exception{
		update(deleteByBid, new Object[]{bid});
		
	}
	/**
	 * 分页查找
	 * 
	 */
	public List<Book> findByPage(int page) throws Exception{
		List<Book> list=query(findByPage, new Object[]{page,page});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 模糊查询的排序解决方案
	 * 
	 */
	private static final String findByBnameKey="select * from book where " +
			"bname = ?";//书名精确查询
	private static final String findByAuthorKey="select * from book where " +
			"author = ?";//作者精确查询
	private static final String findByTypeKey="select * from book where " +
			"type = ?";//类别精确查询
	private static final String findByBnameKeyword="select * from book where " +
			"bname like ?";//书名模糊查询
	private static final String findByAuthorKeyword="select * from book where " +
			"author like ?";//作者模糊查询
	private static final String findByTypeKeyword="select * from book where " +
			"type like ?";//类别模糊查询
	/**
	 * 根据模糊查询找到一群书,以及排序解决方案
	 * @throws Exception 
	 */
	public List<Book> findByKeyword(String keyword) throws Exception{
		keyword=keyword.trim();//去除两边空白
		List<Book> list1=query(findByBnameKey, new Object[]{keyword});
		List<Book> list2=query(findByAuthorKey, new Object[]{keyword});
		List<Book> list3=query(findByTypeKey, new Object[]{keyword});
		char []a=keyword.toCharArray();//模糊查询加  %
		keyword="";
		for(int i=0;i<a.length;i++){
			keyword+="%"+a[i]+"%";
		}
		System.out.println(keyword);
		List<Book> list4=query(findByBnameKeyword, new Object[]{keyword});
		List<Book> list5=query(findByAuthorKeyword, new Object[]{keyword});
		List<Book> list6=query(findByTypeKeyword, new Object[]{keyword});
		//去重排序
		list2.removeAll(list1);
		list1.addAll(list2);
		list3.removeAll(list1);
		list1.addAll(list3);
		list4.removeAll(list1);
		list1.addAll(list4);
		list5.removeAll(list1);
		list1.addAll(list5);
		list6.removeAll(list1);
		list1.addAll(list6);
		List<Book> list=list1;
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 找到所有书
	 * @throws Exception 
	 */
	public List<Book> findAll() throws Exception{
		List<Book> list=query(findAll, null);
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	/**
	 * 根据主键找到一本书
	 * @throws Exception 
	 */
	public Book findById(int bid) throws Exception{
		List<Book> list=query(findById, new Object[]{bid});
		if(list.size()!=0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 根据类别找到一群书
	 * @throws Exception 
	 */
	public List<Book> findByType(String type) throws Exception{
		List<Book> list=query(findByType, new Object[]{type});
		if(list.size()!=0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	public Object toEntity(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		Book book=new Book();
		book.setBid(rs.getInt("bid"));
		book.setAuthor(rs.getString("author"));
		book.setBname(rs.getString("bname"));
		book.setImgadd(rs.getString("imgadd"));
		book.setIntroduce(rs.getString("introduce"));
		book.setType(rs.getString("type"));
		book.setBookadd(rs.getString("bookadd"));
		book.setPrice(rs.getDouble("price"));
		book.setTotalchapter(rs.getInt("totalchapter"));
		return book;
	}
//	@Test
//	public void ff() throws Exception{
//		findById(3);
//	}
	
	
}
