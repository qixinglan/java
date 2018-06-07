package daoimpl;

import java.io.IOException;
import java.sql.SQLException;

import oracle.jdbc.internal.ClientDataSupport;

import vo.Province;
import dao.BaseDao;

public class ProvinceDaoImpl extends BaseDao{

	public ProvinceDaoImpl() throws IOException {
		super();
		
	}
	public Province selectByPid(int pid) throws SQLException{
		return (Province)client.queryForList("selectCity", pid).get(pid-2);

}
}
