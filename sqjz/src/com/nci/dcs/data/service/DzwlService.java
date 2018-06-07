package com.nci.dcs.data.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Properties;

import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evia.transerver.Beijing54ToWgs84;

@Service
@Transactional
public class DzwlService {
	private String connetionString;
	private String userName;
	private String password;

	public void setConnectionString(String conn) {
		this.connetionString = conn;
	}

	public String getconnetionsString() {
		return connetionString;
	}

	public void setuserName(String username) {
		this.userName = username;
	}

	public String getuserName() {

		return userName;
	}

	public void setpassword(String passwd) {
		this.password = passwd;
	}

	public String getpassword() {

		return password;
	}

	public DzwlService() {
		Properties properties = new Properties();
		InputStream in = null;
		try {
			// System.out.println(getClass().getResource("/").getPath());
			in = new BufferedInputStream(new FileInputStream(getClass()
					.getResource("/").getPath() + "postgresConfig.properties"));
		} catch (FileNotFoundException e) {

		}
		try {
			properties.load(in);
			connetionString = properties
					.getProperty("postgresConfig.connectionString");
			userName = properties.getProperty("postgresConfig.userName");
			password = properties.getProperty("postgresConfig.password");
		} catch (IOException e) {

		}

	}

	public Boolean IsEntryDisallowArea(String fxryid, String x, String y,double buffer)
			throws SQLException, ClassNotFoundException {
		Connection conn=GetPostgresConnetion();
		double b=buffer/1852/60;
	    if(conn!=null){
		Statement s = conn.createStatement();
		String point = "'SRID=4326;POINT(" + x + " " + y + ")'";
		String sql = "";
		String[] personids = fxryid.split(",");
		if (personids.length > 1) {
			fxryid = "";
			for (String id : personids) {
				fxryid += "'" + id + "',";
			}
			fxryid += "''";
			sql = "select st_contains(st_buffer(geom,"+b+")," + point
					+ ") from fxrywlsz where geotype='02' and fxryid in ("
					+ fxryid + ")";
		} else {
			sql = "select st_contains(geom," + point
					+ ") from fxrywlsz where geotype='02' and fxryid='"
					+ fxryid + "'";
		}
		ResultSet result = s.executeQuery(sql);
		boolean isOut = false;
		int i=0;
		while (result.next()) {
			if(i==0){
			isOut =result.getBoolean(1);
			}
			else {
				isOut = isOut | result.getBoolean(1);
			}
			i++;
		}
		s.close();
		conn.close();
		return isOut;
	    }
	    else {
			throw new SQLException("连接数据库失败，请检测数据库库配置");
		}
		
	}
	public Boolean IsOutOfRange(String fxryid, String x, String y,double buffer)
			throws SQLException, ClassNotFoundException {
		double b=buffer/1852/60;
		Connection conn=GetPostgresConnetion();
	    if(conn!=null){
		Statement s = conn.createStatement();
		String point = "'SRID=4326;POINT(" + x + " " + y + ")'";
		String sql = "";
		String[] personids = fxryid.split(",");
		if (personids.length > 1) {
			fxryid = "";
			for (String id : personids) {
				fxryid += "'" + id + "',";
			}
			fxryid += "''";
			sql = "select st_contains(st_buffer(geom,"+b+")," + point
					+ ") from fxrywlsz where geotype='01'  and gid>17 and fxryid in ("
					+ fxryid + ")";
		} else {
			sql = "select st_contains(st_buffer(geom,"+b+")," + point
					+ ") from fxrywlsz where geotype='01' and gid>17 and fxryid='"
					+ fxryid + "'";
		}
		ResultSet result = s.executeQuery(sql);
		int i=0;
		boolean isOut=false;
		while (result.next()) {
			if(i==0)
			{
			  isOut=!result.getBoolean(1);
			}
			else {			    
				isOut = isOut&(!result.getBoolean(1));
			}
			i++;
		}
		s.close();
		conn.close();
		return isOut;
	    }
	    else {
			throw new SQLException("连接数据库失败，请检测数据库库配置");
		}
		
	}
	//转换坐标
	public String ConvertPoints(String strpoints)
	{
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		String[] points=strpoints.split(",");
		double[] coordinates=new double[2*points.length];
		for(int i=0;i<points.length;i++)
		{
			coordinates[2*i]=Double.parseDouble(points[i].split(" ")[0]);
			coordinates[2*i+1]=Double.parseDouble(points[i].split(" ")[1]);
		}
		converter.Wgs84ToBj54(2, coordinates, 0, points.length);
		converter=null;	
		String result="";
		for (int i = 0; i < coordinates.length; i++) {
			result+=coordinates[i]+",";
		}
		return result.substring(0,result.length()-1);
	}
	//转换坐标
	public String ConvertPoint(String x,String y)
	{
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		double[] coordinates=new double[2];
		coordinates[0]=Double.parseDouble(x);
		coordinates[1]=Double.parseDouble(y);
		converter.Wgs84ToBj54(2, coordinates, 0, 1);
		converter=null;
		return coordinates[0]+","+coordinates[1];
	}
	//转换坐标
	public String Convert54ToWgs84(String x,String y)
	{
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		double[] coordinates=new double[2];
		coordinates[0]=Double.parseDouble(x);
		coordinates[1]=Double.parseDouble(y);
		converter.Bj54ToWgs84(2, coordinates, 0, 1);
		converter=null;
		return coordinates[0]+","+coordinates[1];
	}
	//保存服刑人员电子围栏
	public boolean SaveFxry(String fxryid,String type,double[] coordinates) throws ClassNotFoundException, SQLException {
		Connection conn=GetPostgresConnetion();
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		converter.Bj54ToWgs84(2, coordinates, 0, coordinates.length/2);
		String geom="";
		for (int i = 0; i < coordinates.length; i+=2) {
			geom+=coordinates[i]+" "+coordinates[i+1]+",";
		}
		geom=geom.substring(0,geom.length()-1);
		String g="ST_GeomFromText('MULTIPOLYGON((("+geom+")))',4326)";
	    String sqlString="INSERT INTO public.fxrywlsz VALUES (nextval('fxrysz_seq'::regclass), '"+fxryid+"', "+g+", '"+type+"')";
	    Statement s = conn.createStatement();
	    try {
	    	s.execute(sqlString);
	    	return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	    finally
	    {
	    
	      s.close();
	      conn.close();
	    }
		
	}
	  public String DeleteFxry(String gid) throws SQLException, ClassNotFoundException {
		  Connection conn=GetPostgresConnetion();
		  Statement s = conn.createStatement();
		  String sql = "delete from fxrywlsz where gid="+gid;
		  try {
			  boolean result = s.execute(sql);
			  return "true";
		} catch (Exception e) {
			return "false";
			// TODO: handle exception
		}
	}
	 //获取服刑人员电子围栏
	   public String GetFxrydzwl(String fxryid) throws ClassNotFoundException, SQLException {
		Connection conn=GetPostgresConnetion();
		((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
		//((org.postgresql.PGConnection)conn).addDataType("box3d",Class.forName("org.postgis.PGbox3d"));
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		String fxrylist="[";
	    if(conn!=null){
		Statement s = conn.createStatement();
		String sql = "select geom,gid,geotype,fxryid from fxrywlsz where fxryid='"+fxryid+"' and gid>17";
		ResultSet result = s.executeQuery(sql);
		while (result.next()) {
			String fxry="{";
			fxry+="'gid':'"+result.getString(2)+"',";
			fxry+="'geotype':'"+result.getString(3)+"',";
			fxry+="'fxryid':'"+result.getString(4)+"',";
			String strgeom="";
			PGgeometry geom = (PGgeometry)result.getObject(1);
			int count=geom.getGeometry().numPoints();
			double [] coordinates = new double[count * 2];
			for(int i=0;i<count;i++)
			{
				Point p=geom.getGeometry().getPoint(i);
				coordinates[i*2]=p.x;
				coordinates[i*2+1]=p.y;
			}
			converter.Wgs84ToBj54(2, coordinates, 0, count);
			for(double item :coordinates)
			{
				strgeom+=item+",";
			}
			strgeom=strgeom.substring(0,strgeom.length()-1);
			fxry+="'geom':'"+strgeom+"'}";
			fxrylist+=fxry+",";
		}
		fxrylist=fxrylist.substring(0,fxrylist.length()-1)+"]";
		s.close();
		conn.close();
	    }
		return fxrylist;
	}
	
	   //获取区县电子围栏
	   public String GetFXRYXX(String fxryid) throws ClassNotFoundException, SQLException {
		Connection conn=GetPostgresConnetion();
		//((org.postgresql.PGConnection)conn).addDataType("geometry",Class.forName("org.postgis.PGgeometry"));
		//((org.postgresql.PGConnection)conn).addDataType("box3d",Class.forName("org.postgis.PGbox3d"));
		Beijing54ToWgs84 converter = new Beijing54ToWgs84();
		String strFxry="";
	    if(conn!=null){
		Statement s = conn.createStatement();
		String sql = "select geom from fxrywlsz where fxryid='"+fxryid+"' and gid<18 ";
		ResultSet result = s.executeQuery(sql);
		while (result.next()) {
			PGgeometry geom = (PGgeometry)result.getObject(1);
			int count=geom.getGeometry().numPoints();
			double [] coordinates = new double[count * 2];
			for(int i=0;i<count;i++)
			{
				Point p=geom.getGeometry().getPoint(i);
				coordinates[i*2]=p.x;
				coordinates[i*2+1]=p.y;
			}
			converter.Wgs84ToBj54(2, coordinates, 0, count);
			for(double item :coordinates)
			{
				strFxry+=item+",";
			}
		}
		
		s.close();
		conn.close();
	    }
		return strFxry.substring(0,strFxry.length()-1);
	}
	public Boolean IsOutOfRangeDefault(String fxryid, String x, String y,double buffer)
			throws SQLException, ClassNotFoundException {
		double b=buffer/1852/60;
		Connection conn=GetPostgresConnetion();
	    if(conn!=null){
		Statement s = conn.createStatement();
		String point = "'SRID=4326;POINT(" + x + " " + y + ")'";
		String sql = "";
		String[] personids = fxryid.split(",");
		if (personids.length > 1) {
			fxryid = "";
			for (String id : personids) {
				fxryid += "'" + id + "',";
			}
			fxryid += "''";
			sql = "select st_contains(st_buffer(geom,"+b+")," + point
					+ ") from fxrywlsz where geotype='01' and gid<18 and fxryid in ("
					+ fxryid + ")";
		} else {
			sql = "select st_contains(st_buffer(geom,"+b+")," + point
					+ ") from fxrywlsz where geotype='01' and gid<18 and fxryid='"
					+ fxryid + "'";
		}
		ResultSet result = s.executeQuery(sql);
		int i=0;
		boolean isOut=false;
		while (result.next()) {
			if(i==0)
			{
			  isOut=!result.getBoolean(1);
			}
			else {			    
				isOut = isOut&(!result.getBoolean(1));
			}
			i++;
		}
		s.close();
		conn.close();
		return isOut;
	    }
	    else {
			throw new SQLException("连接数据库失败，请检测数据库库配置");
		}
		
	}
	private Connection GetPostgresConnetion() throws ClassNotFoundException {
		try {
			// java.sql.DriverManager.registerDriver (new
			// org.postgresql.Driver());
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException cnfe) {
			throw new ClassNotFoundException("不能找到数据驱动");
		}
		Connection conn;
		try {
			conn = DriverManager.getConnection(connetionString,userName, password);
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}

    public String GetDZWL(String fxryid) throws SQLException
    {
    	String out="0";//禁止离开
    	String In="0";//禁止进入
    	try {
			Connection conn=GetPostgresConnetion();
			Statement s = conn.createStatement();
			String sqlString="select geotype from fxrywlsz where gid>17 and fxryid='"+fxryid+"'";
			ResultSet result=s.executeQuery(sqlString);
			while (result.next()) {
				String r=result.getString(1);
				if(r.equals("01"))
				{
					out="1";
				}
				else {
					In="1";
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return out+In;
    }
	public static void main(String[] args) {
		DzwlService service = new DzwlService();
		try {
            //String string=service.GetDZWL("平谷区");
			//System.out.println(string);
			System.out.println(service.GetFXRYXX("北京市"));
            boolean result = service.IsOutOfRangeDefault("北京市", "116.75307547511","40.474234268488",2000);
			System.out.println(result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
