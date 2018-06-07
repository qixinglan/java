package com.nci.dcs.jzgl.education.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Order;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.common.web.jquery.jqgrid.search.JQGridSearchRuleParser;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.jzgl.education.model.FxryEducation;
import com.nci.dcs.jzgl.education.service.FxryEducationService;

public class FxryEducationAction extends BaseAction<FxryEducation>{

	/**
	 * @name 
	 * @author caolj
	 * @date 2015年4月1日 下午3:52:10
	 * @message:
	 */
	private static final long serialVersionUID = -1914677597483484792L;
	
	protected AjaxFormResult ajaxFormResult;
	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}
	
	@Autowired
	private FxryEducationService service;
	
	@Override
	public String list() throws Throwable {
		Log.info("");
		return SUCCESS;
	}
	
	/**
	 * @name 获取列表信息
	 * @return
	 * @author caolj
	 * @date 2015年4月1日 下午4:23:20
	 * @message:
	 */
	public String getData() {
		this.getRequestPage();
		service.findPaged(page);
		return SUCCESS;
	}
	
	/**
	 * 查看
	 */
	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		this.entity = service.get(id);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 删除
	 */
	@Override
	public String delete() throws Throwable {
		String id = request.getParameter("id");
		service.delete(id);
		return SUCCESS;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 保存
	 */
	@Override
	public String add() throws Throwable {
		String id = entity.getId();
		if(id == null || "".equals(id)){
			entity.setId(null);
		}
		String date = request.getParameter("date");
		Date month = DateTimeFmtSpec.getDateFormat().parse(date+"-01");
		entity.setMonth(month);
		entity.setCreater(getUserId());
		entity.setCreatedate(new Date());
		entity.setOrgId(getCurOrgId());
		service.create(entity);
		ajaxFormResult = AjaxFormResult.success(entity.getId());
		return SUCCESS;
	}
	
	private Map<String,Object> zlmap;
	public Map<String,Object> getZlmap() {
		return zlmap;
	}

	public void setZlmap(Map<String,Object> zlmap) {
		this.zlmap = zlmap;
	}
	
	/**
	 * @name 获取集中教育统计信息
	 * @return
	 * @throws Throwable
	 * @author caolj
	 * @date 2015年4月2日 上午11:37:38
	 * @message:
	 */
	public String educationReport() throws Throwable {
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("crimeType"));
		List<Category> searchTimes = new ArrayList<Category>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		//获取查询起止时间
		String filters = request.getParameter("filters");
		List<SearchRule> rules = JQGridSearchRuleParser.getSearchRules(filters);
		Date startTime = null, endTime = null;
		for (int i = 0; i < rules.size(); i++) {
			String field = rules.get(i).getField();
			if ("time".equals(field)) {
				String op = rules.get(i).getOp();
				if ("ge".equals(op)) {
					startTime = DateTimeFmtSpec.getDateFormat().parse(
							rules.get(i).getData()+"-01");
				} else if ("le".equals(op)) {
					endTime = DateTimeFmtSpec.getDateFormat().parse(
							rules.get(i).getData()+"-01");
				}
			}
		}
		if (startTime != null && endTime != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			cal.add(Calendar.MONTH, 1);
			Date tempDate = cal.getTime();
			List<FxryEducation> result = service.findByCriteria(startTime,tempDate);
			cal.clear();
			cal.setTime(startTime);
			DataSet personDs = new DataSet("集中教育人数");
			DataSet stageDs = new DataSet("集中教育期数");
			List<Data> personDatas = new ArrayList<Data>();
			List<Data> stageDatas = new ArrayList<Data>();
			long rs = 0;
			long qs = 0;
			Date temp;
			while (endTime.compareTo(cal.getTime()) >= 0) {
				temp = cal.getTime();
				for(FxryEducation education : result){
					if(temp.compareTo(education.getMonth())==0){
						rs += education.getPersons();
						qs++;
					}
				}
				searchTimes.add(new Category(DateTimeFmtSpec
						.getMonthFormat().format(cal.getTime())));
				personDatas.add(new Data(String.valueOf(rs)));
				stageDatas.add(new Data(String.valueOf(qs)));
				rs = 0;
				qs = 0;
				cal.add(Calendar.MONTH, 1);
			}
			personDs.setData(personDatas);
			stageDs.setData(stageDatas);
			dateSets.add(stageDs);
			dateSets.add(personDs);
		}
		zlmap = new HashMap<String,Object>();
		zlmap.put("category", searchTimes);
		zlmap.put("dataset", dateSets);
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
