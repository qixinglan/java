package dao;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BaseDao {
	public SqlMapClient client;
	public BaseDao() throws IOException {
		super();
		Reader reader=Resources.getResourceAsReader("config/SqlMapConfig.xml");
		client=SqlMapClientBuilder.buildSqlMapClient(reader);
	}
	
}
