package com.nci.dcs.homepage.report.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.DateTimeFmtSpec;
import com.nci.dcs.homepage.report.model.ReportData;
import com.nci.dcs.jzgl.cxtj.dto.Category;
import com.nci.dcs.jzgl.cxtj.dto.Data;
import com.nci.dcs.jzgl.cxtj.dto.DataSet;
import com.nci.dcs.jzgl.education.model.FxryEducation;
import com.nci.dcs.jzgl.education.service.FxryEducationService;
import com.nci.dcs.system.model.User;

@Service
public class FxryEducationReportService extends IReportService {
	
	@Autowired
	private FxryEducationService service;

	@Override
	public ReportData getReportData(User user) {
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.asc("crimeType"));
		List<Category> searchTimes = new ArrayList<Category>();
		List<DataSet> dateSets = new ArrayList<DataSet>();
		// 获取查询起止时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startTime = null, endTime = null;
		Calendar cal = Calendar.getInstance();
		Timestamp tt = new Timestamp(cal.getTime().getTime());
		String detail="北京市最近一年共举办集中教育 ";
		try {
			endTime = sdf.parse(tt.toString().substring(0,7)+"-01");
			cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -1);
			cal.add(Calendar.MONTH, 1);
			tt = new Timestamp(cal.getTime().getTime());
			startTime = sdf.parse(tt.toString().substring(0,7)+"-01");;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (startTime != null && endTime != null) {
			cal.setTime(endTime);
			cal.add(Calendar.MONTH, 1);
			Date tempDate = cal.getTime();
			List<FxryEducation> result = service.findByCriteria(startTime,
					tempDate);
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
				for (FxryEducation education : result) {
					if (temp.compareTo(education.getMonth()) == 0) {
						rs += education.getPersons();
						qs++;
					}
				}
				searchTimes.add(new Category(DateTimeFmtSpec.getMonthFormat()
						.format(cal.getTime())));
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
			long count =0;
			for(Data stage:stageDatas){
				count=count+Integer.parseInt(stage.getValue());
			}
			detail=detail+count+"期";
			count =0;
			for(Data person:personDatas){
				count=count+Integer.parseInt(person.getValue());
			}
			detail=detail+count+"人";
			
		}
		
		ReportData data = new ReportData();
		data.setCategory(searchTimes);
		data.setDataset(dateSets);
		data.setDetail(detail);
		return data;
	}
}
