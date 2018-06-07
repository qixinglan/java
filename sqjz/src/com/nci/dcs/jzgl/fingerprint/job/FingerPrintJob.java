package com.nci.dcs.jzgl.fingerprint.job;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nci.dcs.common.utils.FTPUtil;
import com.nci.dcs.em.dwjk.model.CcFxryBaseinfo;
import com.nci.dcs.jzgl.fingerprint.model.FingerPrintCommpost;
import com.nci.dcs.jzgl.fingerprint.service.FingerPrintManagerService;
import com.nci.dcs.jzgl.rcbdgl.model.ViewFxryAddreportinfo;
import com.nci.dcs.jzgl.rcbdgl.service.AddReportInfoService;

@Component
public class FingerPrintJob{
	
	@Autowired
	private FingerPrintManagerService fingerPrintManagerService;
	
	@Autowired
	private AddReportInfoService addReportsService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 定时获取指纹报道信息
	 * @throws Exception 
	 */
	public void getTaskJob() throws Exception{
		List <FingerPrintCommpost> list = fingerPrintManagerService.getTask();
		FingerPrintCommpost post = new FingerPrintCommpost();
		try {
			
			for( int i=0;i<list.size();i++){
				post = list.get(i);
				switch(post.getPosttype()){
				case 1://打卡报道记录
					List<CcFxryBaseinfo>  basicinfo = fingerPrintManagerService.getFxryIdByPrintNo(post.getParams2());
					Calendar c = Calendar.getInstance();
					if(basicinfo.size()>2){
						throw new Exception("报道编号错误");
					}
					if(basicinfo.size()<1){
						throw new Exception("指纹编号为"+post.getParams2()+"的用户没有登记");
					}else{
						if(post.getParams3()!=null && !"".equals(post.getParams3())){
							c.setTime(sdf.parse(post.getParams3()));
						}else{
							c.setTime(new Date());
						}
						c.add(Calendar.MONTH, 1);
						addReportsService.addReportInfo(basicinfo.get(0).getId(), post.getParams3(), sdf.format(c.getTime()));//添加报道信息，获取报道图片，更新报道图片
					}
					fingerPrintManagerService.getReportPicture(post.getParams2(),getHeadPicName(post.getParams3()),3);
					break;
				case 2://首次登记指纹图像
					fingerPrintManagerService.getReportPicture(post.getParams1(),null,2);//获取登记指纹，拷贝登记指纹
					break;							
				case 3://首次登记照片
					fingerPrintManagerService.getReportPicture(post.getParams1(),null,1);//获取登记图片，拷贝登记图片
					break;
				}
				fingerPrintManagerService.updatePost(post.getPostid(),1);
			}
			  
			
		} catch (Exception e) {
			fingerPrintManagerService.updatePost(post.getPostid(),9);
			e.printStackTrace();
		}
	}
	
	private String getHeadPicName(String date) throws ParseException{
		Date time = sdf.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		String picName = c.get(Calendar.YEAR)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.DATE)
				+"-"+c.get(Calendar.HOUR_OF_DAY)+"-"+c.get(Calendar.MINUTE)+"-"+c.get(Calendar.SECOND);
		return picName;
	}

}
