package com.nci.dcs.jzgl.fingerprint.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.Constants;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.utils.ImageUtils;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.nci.dcs.jzgl.dagl.service.FXRYBaseInfoService;
import com.nci.dcs.jzgl.fingerprint.model.CcFingerprintMachine;
import com.nci.dcs.jzgl.fingerprint.model.CcFxryTupian;
import com.nci.dcs.jzgl.fingerprint.service.FingerPrintManagerService;
import com.nci.dcs.jzgl.fingerprint.service.FxryTuPianService;
import com.nci.dcs.jzgl.rcbdgl.service.AddReportInfoService;
import com.nci.dcs.official.service.OrganizationInfoService;
import com.nci.dcs.system.model.PhotoUpload;
import com.nci.dcs.system.service.PhotoUploadServcie;
import com.thoughtworks.xstream.core.util.Base64Encoder;

import edu.emory.mathcs.backport.java.util.Arrays;

@Component
public class FingerPrintManagerAction extends BaseAction<CcFingerprintMachine> {
	private AjaxFormResult ajaxFormResult;
	public List resList;
	public CcFingerprintMachine lawEn;
	private String fileName;
	private InputStream targetFile;
	private String personId; // 指纹仪端人员编号
	@Autowired
	private PhotoUploadServcie photoUploadServcie;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private FingerPrintManagerService fingerPrintManagerService;
	@Autowired
	private OrganizationInfoService orgService;
	@Autowired
	private AddReportInfoService addReportsService;
	@Autowired
	private FXRYBaseInfoService fXRYBaseInfoService;
	@Autowired
	private FxryTuPianService tuPianService;
	@Autowired
	private FXRYBaseInfoService baseInfoService;

	public CcFxryTupian fxryTuPian = null;

	public OrganizationInfoService getOrgService() {
		return orgService;
	}

	public void setOrgService(OrganizationInfoService orgService) {
		this.orgService = orgService;
	}

	@Override
	public String add() throws Throwable {
		int machineId = 0;
		if (!checkBeforeAdd(entity.getId(), entity.getMachineId())) {
			ajaxFormResult = new AjaxFormResult(false, "机器码已存在，请重新输入");
			return "success";
		}
		try {
			machineId = Integer.parseInt(entity.getMachineId());
			if (machineId < 1 || machineId > 65535) {
				ajaxFormResult = new AjaxFormResult(false, "机器码允许范围在1-65535之间");
				return "success";
			}
		} catch (Exception e) {
			ajaxFormResult = new AjaxFormResult(false, "机器码格式不正确");
			return "success";
		}

		fingerPrintManagerService.create(entity);
		ajaxFormResult = new AjaxFormResult(true, "");

		if ("edit".equals(request.getParameter("oper"))) {
			this.view();
		}
		return "success";
	}

	public String selectMachine() {
		resList = fingerPrintManagerService.queryMachine(getCurOrgId());
		return "success";
	}

	public String deletePrint() {
		String personId = request.getParameter("personId");
		if (personId != null && !"".equals(personId)
				&& !"null".equals(personId)) {
			resList = fingerPrintManagerService.queryMachine(getCurOrgId());
			CcFingerprintMachine mac = new CcFingerprintMachine();
			for (int i = 0; i < resList.size(); i++) {
				mac = (CcFingerprintMachine) resList.get(i);
				fingerPrintManagerService.addTask(4, personId,
						mac.getMachineId(), null);
			}
		}
		return "success";
	}

	/**
	 * 获取指纹图片
	 */
	public String getFingerPrintPicture() {
		// 获取图片路径
		String fingerNo = request.getParameter("fingerPrintNo");
		CcFxryTupian tuPian = tuPianService.get(fingerNo);
		createTuPian(tuPian);
		String str=getExitPic(tuPian);
		ajaxFormResult=new AjaxFormResult(true,str);
		return "success";
	}
    
	private String getExitPic(CcFxryTupian tuPian){
		StringBuilder strExitPic=new StringBuilder();
		if(tuPian!=null){
			if (tuPian.getLeftF1() != null)strExitPic.append("1");
			if (tuPian.getLeftF2() != null)strExitPic.append("2");
			if (tuPian.getLeftF3() != null)strExitPic.append("3");
			if (tuPian.getLeftF4() != null)strExitPic.append("4");
			if (tuPian.getLeftF5() != null)strExitPic.append("5");
			if (tuPian.getRightF1() != null)strExitPic.append("6");
			if (tuPian.getRightF2() != null)strExitPic.append("7");
			if (tuPian.getRightF3() != null)strExitPic.append("8");
			if (tuPian.getRightF4() != null)strExitPic.append("9");
			if (tuPian.getRightF5() != null)strExitPic.append("0");
		} else strExitPic.append("");
		return strExitPic.toString();
		
	}
	private void createTuPian(CcFxryTupian tuPian) {
		if (tuPian != null) {
			String path = PathUtils.getRealPath() + "upload" + File.separator
					+ "fingerprint" + File.separator 
					+ tuPian.getId().substring(0,6)+File.separator 
					+ tuPian.getId().substring(6,12)+File.separator
					+ tuPian.getId().substring(6,14)+File.separator
					+tuPian.getId()+File.separator;
			
			File file = new File(path);
			if (file.exists())
				return ;
			else
				file.mkdirs();
			if (tuPian.getLeftF1() != null
					&& !new File(path + "1.jpeg").exists()){
				ImageUtils.createImage(tuPian.getLeftF1(), path + "1.jpeg");
				
			}
			if (tuPian.getLeftF2() != null
					&& !new File(path + "2.jpeg").exists()){
				ImageUtils.createImage(tuPian.getLeftF2(), path + "2.jpeg");
			
			}
			if (tuPian.getLeftF3() != null
					&& !new File(path + "3.jpeg").exists()){
				ImageUtils.createImage(tuPian.getLeftF3(), path + "3.jpeg");
			
			}
				
			if (tuPian.getLeftF4() != null
					&& !new File(path + "4.jpeg").exists()){
				ImageUtils.createImage(tuPian.getLeftF4(), path + "4.jpeg");
				
			}
			if (tuPian.getLeftF5() != null
					&& !new File(path + "5.jpeg").exists()){
				ImageUtils.createImage(tuPian.getLeftF5(), path + "5.jpeg");
				
			}
			if (tuPian.getRightF1() != null
					&& !new File(path + "6.jpeg").exists()){
				ImageUtils.createImage(tuPian.getRightF1(), path + "6.jpeg");
				
			}
			if (tuPian.getRightF2() != null
					&& !new File(path + "7.jpeg").exists()){
				ImageUtils.createImage(tuPian.getRightF2(), path + "7.jpeg");
				
			}
			if (tuPian.getRightF3() != null
					&& !new File(path + "8.jpeg").exists()){
				ImageUtils.createImage(tuPian.getRightF3(), path + "8.jpeg");
				
			}
			if (tuPian.getRightF4() != null
					&& !new File(path + "9.jpeg").exists()){
				ImageUtils.createImage(tuPian.getRightF4(), path + "9.jpeg");
				
			}
			if (tuPian.getRightF5() != null
					&& !new File(path + "10.jpeg").exists()){
				ImageUtils.createImage(tuPian.getRightF5(), path + "10.jpeg");
				
			}
			if (tuPian.getFacePic() != null
					&& !new File(path + "11.jpeg").exists()){
				ImageUtils.createImage(tuPian.getFacePic(), path + "11.jpeg");			
			}
		}
	}

	/**
	 * 采集指纹
	 * 
	 * @return
	 */
	@Deprecated
	public String collectPrint() {
		String name = request.getParameter("name");
		String reCollect = request.getParameter("boolReCollect");
		String fingerNo = request.getParameter("fingerNo");
		fingerPrintManagerService.reCollectPrint(reCollect, getCurOrgId(),
				fingerNo);
		personId = generateCode();
		fingerPrintManagerService.collectPrint(personId, name, getCurOrgId());

		return "success";
	}

	/**
	 * 采集指纹2
	 * 
	 * @return
	 * @throws IOException
	 */
	public String collectFingers() throws IOException {
		BASE64Decoder decode = new BASE64Decoder();

		String fingerNo = request.getParameter("fingerNo");
		String fxryId = request.getParameter("fxryId");
		String fingerFlag = request.getParameter("fingerFlag");
		CcFxryTupian tuPian = new CcFxryTupian();
		if(fingerFlag!=null && "left".equals(fingerFlag)){
			String left1 = request.getParameter("left1");
			String left2 = request.getParameter("left2");
			String left3 = request.getParameter("left3");
			String left4 = request.getParameter("left4");
			String left5 = request.getParameter("left5");
			
			String mleft1 = request.getParameter("mleft1");
			String mleft2 = request.getParameter("mleft2");
			String mleft3 = request.getParameter("mleft3");
			String mleft4 = request.getParameter("mleft4");
			String mleft5 = request.getParameter("mleft5");
			
			tuPian.setLeftF1(decode.decodeBuffer(left1==null?"":left1));
			tuPian.setLeftF2(decode.decodeBuffer(left2==null?"":left2));
			tuPian.setLeftF3(decode.decodeBuffer(left3==null?"":left3));
			tuPian.setLeftF4(decode.decodeBuffer(left4==null?"":left4));
			tuPian.setLeftF5(decode.decodeBuffer(left5==null?"":left5));
			
			tuPian.setMLeft1(decode.decodeBuffer(mleft1==null?"":mleft1));
			tuPian.setMLeft2(decode.decodeBuffer(mleft2==null?"":mleft2));
			tuPian.setMLeft3(decode.decodeBuffer(mleft3==null?"":mleft3));
			tuPian.setMLeft4(decode.decodeBuffer(mleft4==null?"":mleft4));
			tuPian.setMLeft5(decode.decodeBuffer(mleft5==null?"":mleft5));
			
			if (fingerNo != "" && fingerNo != null) {
				CcFxryTupian t = tuPianService.get(fingerNo);
				tuPian.setFacePic(t.getFacePic());
			} 
			tuPian.setId(generateCode());
			
			tuPianService.create(tuPian);
			FXRYBaseinfo baseinfo = baseInfoService.getById(fxryId);
			if (baseinfo != null) {
				String oldNo = baseinfo.getFingerPrintNo();
				baseinfo.setFingerPrintNo(tuPian.getId());
				baseInfoService.updateFxryPic(baseinfo);
				if(oldNo!=null){
					tuPianService.delete(oldNo);
					//删除旧缓存图片
					String path = PathUtils.getRealPath() + "upload" + File.separator
							+ "fingerprint" + File.separator 
							+ oldNo.substring(0,6)+File.separator 
							+ oldNo.substring(6,12)+File.separator
							+ oldNo.substring(6,14)+File.separator
							+oldNo+File.separator;
					File file = new File(path);
					CommonUtils.deleteFile(file);
				}
			}else{
				if (fingerNo != "" && fingerNo != null) {
					tuPianService.delete(fingerNo);
				}
			}
		}else if(fingerFlag!=null && "right".equals(fingerFlag)){
			tuPian = tuPianService.get(fingerNo);
			String right1 = request.getParameter("right1");
			String right2 = request.getParameter("right2");
			String right3 = request.getParameter("right3");
			String right4 = request.getParameter("right4");
			String right5 = request.getParameter("right5");
			
			String mright1 = request.getParameter("mright1");
			String mright2 = request.getParameter("mright2");
			String mright3 = request.getParameter("mright3");
			String mright4 = request.getParameter("mright4");
			String mright5 = request.getParameter("mright5");
			
			tuPian.setRightF1(decode.decodeBuffer(right1==null?"":right1));
			tuPian.setRightF2(decode.decodeBuffer(right2==null?"":right2));
			tuPian.setRightF3(decode.decodeBuffer(right3==null?"":right3));
			tuPian.setRightF4(decode.decodeBuffer(right4==null?"":right4));
			tuPian.setRightF5(decode.decodeBuffer(right5==null?"":right5));
			
			tuPian.setMRight1(decode.decodeBuffer(mright1==null?"":mright1));
			tuPian.setMRight2(decode.decodeBuffer(mright2==null?"":mright2));
			tuPian.setMRight3(decode.decodeBuffer(mright3==null?"":mright3));
			tuPian.setMRight4(decode.decodeBuffer(mright4==null?"":mright4));
			tuPian.setMRight5(decode.decodeBuffer(mright5==null?"":mright5));
			tuPianService.update(tuPian);
		}
		personId = tuPian.getId();
		return "success";
	}

	/**
	 * 采集头像
	 * 
	 * @return
	 * @throws IOException
	 */
	public String collectFacePicture() throws IOException {
		BASE64Decoder decode = new BASE64Decoder();
		String fingerNo = request.getParameter("fingerNo");
		String facePic = request.getParameter("facePic");
		String faceId=request.getParameter("faceId");
		String fxryId=request.getParameter("fxryId");

		CcFxryTupian tuPian = null;
		if (CommonUtils.isNull(fingerNo)) {
			tuPian = new CcFxryTupian();
			tuPian.setId(UUID.randomUUID().toString().replace("-", ""));
		} else {
			tuPian = tuPianService.get(fingerNo);
		}
		tuPian.setFacePic(decode.decodeBuffer(facePic));
		tuPianService.create(tuPian);
		personId = tuPian.getId();
		/**
		 * 将图片存为文件
		 */
		if(!CommonUtils.isNull(faceId)){
			photoUploadServcie.deleteFile(faceId);
		}
		String photoFileName = personId + ".jpeg";
		File photo = photoUploadServcie.createTempFile(tuPian.getFacePic(),
				photoFileName);
		PhotoUpload photoUpload = new PhotoUpload(photo, "image/jpeg",
				photoFileName);
		faceId=photoUploadServcie.saveFile(photoUpload);
		photo.delete();
		if(CommonUtils.isNull(resList)){
			resList=new ArrayList();
		}
		if(fxryId!=null && !"".equals(fxryId)){
			FXRYBaseinfo baseinfo = baseInfoService.getById(fxryId);
			if (baseinfo != null) {
				baseinfo.setPicture(faceId);
				baseinfo.setFingerPrintNo(personId);
				baseInfoService.updateFxryPic(baseinfo);
			}
		}
		resList.add(personId);
		resList.add(faceId);
		return "success";
	}

	public String getMFingers() {
		// 获取图片
		String fxryId = request.getParameter("fxryId");
		FXRYBaseinfo baseinfo = baseInfoService.getById(fxryId);
		Base64Encoder encoder = new Base64Encoder();
		if (baseinfo != null) {
			if(baseinfo.getFingerPrintNo()==null)
				return "success";
			fxryTuPian = tuPianService.get(baseinfo.getFingerPrintNo());
			resList = new ArrayList();
			resList.add(encoder.encode(fxryTuPian.getMLeft1() == null ? ""
					.getBytes() : fxryTuPian.getMLeft1()));
			resList.add(encoder.encode(fxryTuPian.getMLeft2() == null ? ""
					.getBytes() : fxryTuPian.getMLeft2()));
			resList.add(encoder.encode(fxryTuPian.getMLeft3() == null ? ""
					.getBytes() : fxryTuPian.getMLeft3()));
			resList.add(encoder.encode(fxryTuPian.getMLeft4() == null ? ""
					.getBytes() : fxryTuPian.getMLeft4()));
			resList.add(encoder.encode(fxryTuPian.getMLeft5() == null ? ""
					.getBytes() : fxryTuPian.getMLeft5()));
			resList.add(encoder.encode(fxryTuPian.getMRight1() == null ? ""
					.getBytes() : fxryTuPian.getMRight1()));
			resList.add(encoder.encode(fxryTuPian.getMRight2() == null ? ""
					.getBytes() : fxryTuPian.getMRight2()));
			resList.add(encoder.encode(fxryTuPian.getMRight3() == null ? ""
					.getBytes() : fxryTuPian.getMRight3()));
			resList.add(encoder.encode(fxryTuPian.getMRight4() == null ? ""
					.getBytes() : fxryTuPian.getMRight4()));
			resList.add(encoder.encode(fxryTuPian.getMRight5() == null ? ""
					.getBytes() : fxryTuPian.getMRight5()));
		}
		return "success";
	}

	public String generateCode() {
		String postcode = getOrgPostCode();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String date = sdf.format(today);
		String prefix = postcode + date;
		return fingerPrintManagerService.getCode(prefix);
	}

	private String getOrgPostCode() {
		try {
			return getCurOrg().getPostalCode();
		} catch (Exception e) {
			return "0XXXXXX";
		}
	}

	public boolean checkBeforeAdd(String id, String equnumber) {
		List res;
		boolean result;
		if (id != null) {
			CcFingerprintMachine entity = fingerPrintManagerService.get(id);

			if (entity != null && equnumber.equals(entity.getMachineId())) {
				entity = null;
				return true;
			}
			entity = null;
		}

		res = fingerPrintManagerService.findBySql(
				"select 1 from CC_FINGERPRINT_MACHINE where machine_Id =?",
				equnumber);

		if (res.size() == 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public String audit() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String auditBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Throwable {
		String[] ids = request.getParameter("id").split(",");
		for (int i = 0; i < ids.length; i++)
			fingerPrintManagerService.delete(ids[i]);
		return SUCCESS;
	}

	@Override
	public String deleteBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disableBulk() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enable() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String enableBulk() throws Throwable {
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

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Throwable {
		resList = fingerPrintManagerService.find(getCurOrgId());
		return "success";

	}

	@Override
	public String update() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String view() throws Throwable {
		String id = request.getParameter("id");
		lawEn = fingerPrintManagerService.get(id);
		return "success";
	}

	public List getResList() {
		return resList;
	}

	public void setResList(List resList) {
		this.resList = resList;
	}

	public String search() {
		this.getRequestPage();
		page.getCriterions().add(
				Restrictions.in("orgId",
						orgService.getChildrenIds(getCurOrgId())));
		fingerPrintManagerService.findPaged(page);
		return "success";
	}

	private final String[] headers = new String[] { "序号", "设备编号", "购进日期",
			"设备状态", "领用单位", "领用日期" };

	public String createFile() throws UnsupportedEncodingException {
		CreateFileUtil util = CreateFileUtil.getInstance();
		String sortName = request.getParameter("sortName");
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();

		// service.findByfilter(entity1, entity2);
		List res = fingerPrintManagerService.find(sortName, null);

		for (int i = 0; i < res.size(); i++) {
			Object[] cc = (Object[]) res.get(i);
			LinkedList content = new LinkedList();
			content.add(i);
			for (int j = 0; j < cc.length; j++) {
				if (cc[j] != null && cc[j] instanceof Date) {
					cc[j] = sdf.format(cc[j]);
				}
				if (j == 2) {
					content.add("02".equals(cc[j]) ? "未用"
							: "03".equals(cc[j]) ? "在用" : "停用");
					continue;
				}
				content.add(cc[j]);
			}
			contentList.add(content);
		}

		try {
			fileName = util.create(headTable, contentList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public InputStream getTargetFile() {
		CreateFileUtil util = CreateFileUtil.getInstance();
		try {
			return util.getFileInputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

}
