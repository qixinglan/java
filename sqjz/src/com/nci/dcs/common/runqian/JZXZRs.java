package com.nci.dcs.common.runqian;

import java.util.List;

import com.runqian.report4.dataset.DataSet;
import com.runqian.report4.dataset.Group;
import com.runqian.report4.dataset.Row;
import com.runqian.report4.model.expression.DSFunction;
import com.runqian.report4.usermodel.Context;

public class JZXZRs extends DSFunction{

	/**
	 * 计算出数据集中某个字段或表达式值不一样的个数
	 */
	@SuppressWarnings("rawtypes")
	public Object calculate(Context ctx, boolean arg1) {
		
		DataSet ds = ctx.getDataSet(this.paramList.get(0).toString());;
		//取得数据集,并根据过滤表达式进行数据集过滤
		Group group = ds.getRootGroup();
		List list = group.getRows();
		if(list.size()<1)
			return "--";
		StringBuffer bf = new StringBuffer();
		for(int i=0;i<list.size();i++){
			Row r = (Row) list.get(i);
			Object o = r.getData("name");
			bf.append(o.toString()).append(",");
		}
		return bf.toString().substring(0, bf.toString().lastIndexOf(","));
		//返回list的大小,如果直接返回list,在报表中,就会把这些值不一样的结果分别扩展展现
		//return ObjectCache.getInteger( list.size() );
	}

}
