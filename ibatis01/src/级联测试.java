import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import vo.City;
import vo.Province;
import daoimpl.ProvinceDaoImpl;


public class ¼¶Áª²âÊÔ {
	public static void main(String[] args) throws IOException, SQLException {
		ProvinceDaoImpl pro=new ProvinceDaoImpl();
		Province p=pro.selectByPid(2);
		System.out.println(p.getPname());
		List<City> list=p.getCityList();
		for(City c:list){
			System.out.println(c.getCname());
		}
	}
}
