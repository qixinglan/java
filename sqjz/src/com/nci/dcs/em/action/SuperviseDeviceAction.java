package com.nci.dcs.em.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.nci.dcs.base.action.BaseAction;
import com.nci.dcs.base.action.FileUploadAction;
import com.nci.dcs.common.Constants;
import com.nci.dcs.common.form.AjaxFormResult;
import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.CreateFileUtil;
import com.nci.dcs.common.web.jquery.jqgrid.search.SearchRule;
import com.nci.dcs.em.model.CcDevicePair;
import com.nci.dcs.em.model.CcSuperviseDevice;
import com.nci.dcs.em.service.SuperviseDeviceService;
import com.nci.dcs.jzgl.dagl.model.FXRYBaseinfo;
import com.opensymphony.xwork2.ActionContext;

import edu.emory.mathcs.backport.java.util.Arrays;

public class SuperviseDeviceAction extends BaseAction<CcSuperviseDevice> {
	private AjaxFormResult ajaxFormResult;
	@Autowired
	private SuperviseDeviceService superviseDeviceService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Excel excel;

	class Excel {
		private InputStream inputStream;
		private String name;

		public Excel(InputStream inputStream, String name) {
			super();
			this.inputStream = inputStream;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
	}

	@Override
	public String list() throws Throwable {
		return SUCCESS;
	}

	public String search() {
		try {
			List<SearchRule> searchs = this
					.parseJQGridRequest(getDefaultRule());
			superviseDeviceService.findEmSupervisePaged(
					this.fillPageWithJQGridRequest(), searchs);
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return SUCCESS;
	}

	protected List<SearchRule> getDefaultRule() {
		// 可以加入本级单位过滤
		List<SearchRule> rules = new ArrayList<SearchRule>();
		String orgId = getCurOrgId();
		if (!rootOrgId.equals(orgId)) {
			SearchRule sr = new SearchRule();
			sr.setField("procureUnit");
			sr.setOp("eq");
			sr.setData(orgId);
			rules.add(sr);
		}
		SearchRule sr = new SearchRule();
		sr.setField("deviceType");
		sr.setOp("eq");
		sr.setData(Constants.TYPE_MACHINE);
		rules.add(sr);
		return rules;
	}

	@Override
	public String view() throws Throwable {
		replaceModel();
		if ("03".equals(entity.getDeviceStatus())
				&& "1".equals(entity.getDeviceType())) {
			FXRYBaseinfo fxry = superviseDeviceService.getFxryByCode(entity
					.getDeviceNumber());
			if (fxry != null) {
				request.setAttribute("fxry", fxry.getName());
				request.setAttribute("date",
						superviseDeviceService.findAdornDate(fxry.getId()));
			}
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		if (StringUtils.isEmpty(entity.getDeviceId())) {
			return "add";
		} else {
			CcDevicePair pair = superviseDeviceService.getDevicePair(entity
					.getDeviceId());
			request.setAttribute("pair", pair);
			// replaceModel();
			return "edit";
		}
	}

	protected final CcDevicePair handRow(HSSFRow row) throws ParseException {
		int i = 0;
		Date date = new Date();
		CcSuperviseDevice watch = new CcSuperviseDevice();
		CcSuperviseDevice machine = new CcSuperviseDevice();
		CcDevicePair pair = new CcDevicePair();
		pair.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		pair.setWatch(watch);
		pair.setMachine(machine);
		pair.setBeginDate(date);
		pair.setStatus(Constants.STATUS_UNUSED);
		pair.setCrtperson(getUserId());
		pair.setCrtorg(getCurOrgId());

		watch.setDevicePair(pair);
		watch.setProcureUnit(getCurOrgId());
		watch.setOrg(null);
		watch.setDeviceType(Constants.TYPE_WATCH);
		watch.setDeviceStatus(Constants.STATUS_UNUSED);
		machine.setDevicePair(pair);
		machine.setProcureUnit(getCurOrgId());
		machine.setOrg(null);
		machine.setDeviceType(Constants.TYPE_MACHINE);
		machine.setDeviceStatus(Constants.STATUS_UNUSED);

		HSSFCell cell = null;
		cell = row.getCell(i++);
		pair.setNo(getInteger(cell));
		// 设备配对编号
		cell = row.getCell(i++);
		pair.setPairDeviceNumber(cell.getStringCellValue());
		// 定位主机设备编号
		cell = row.getCell(i++);
		machine.setDeviceNumber(cell.getStringCellValue());
		// 主机电话号码
		cell = row.getCell(i++);
		machine.setConnectPhone(cell.getStringCellValue());
		// 腕表编号
		cell = row.getCell(i++);
		watch.setDeviceNumber(cell.getStringCellValue().toUpperCase());
		// 购买日期
		cell = row.getCell(i++);
		Date buyTime = sdf.parse(cell.getStringCellValue());
		watch.setProcureDate(buyTime);
		machine.setProcureDate(buyTime);

		return pair;
	}

	protected final Integer getInteger(HSSFCell cell) {
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN)
			return cell.getBooleanCellValue() ? 1 : 0;
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
			return null;
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_ERROR)
			return null;
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) // TODO 计算
			return null;
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			return (int) cell.getNumericCellValue();
		else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			try {
				return Integer.parseInt(cell.getStringCellValue());
			} catch (NumberFormatException e) {
				return null;
			}
		return null;
	}

	private String importFile = null;

	public String importExcel() throws IOException {
		FileInputStream fis = null;
		HSSFWorkbook wb = null;
		List<CcDevicePair> pairs = null;
		try {
			File file = new File(FileUploadAction.getRealPath("jgsb/import",
					importFile) + File.separator + "CONTENT");
			fis = new FileInputStream(file);
			// 创建excel文件
			wb = new HSSFWorkbook(fis);
			int sheetSize = 1;// wb.getNumberOfSheets();
			pairs = new LinkedList<CcDevicePair>();

			for (int i = 0; i < sheetSize; i++) {
				HSSFSheet sheet = wb.getSheetAt(i);
				int lastRow = sheet.getLastRowNum();
				for (int j = 1; j <= lastRow; j++) {
					HSSFRow row = sheet.getRow(j);
					pairs.add(handRow(row));
				}
			}
			wb = null;
		} catch (Exception e) {
			ajaxFormResult = new AjaxFormResult(false, "导入文件错误，请导入正确格式的文件！");
			return SUCCESS;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
				fis = null;
			}
		}
		if (pairs.size() > 0) {
			List<String> failed = superviseDeviceService.imprt(pairs);
			if (failed.size() == 0)
				ajaxFormResult = new AjaxFormResult(true, "");
			else
				ajaxFormResult = new AjaxFormResult(false,
						StringUtils.join(failed.toArray()));
		} else
			ajaxFormResult = new AjaxFormResult(false, "文件中没有记录。");
		return SUCCESS;
	}

	private void replaceModel() {
		ActionContext ac = ActionContext.getContext();
		ac.getValueStack().getRoot().remove(entity);
		entity = superviseDeviceService.get(entity.getDeviceId());
		ac.getValueStack().getRoot().push(entity);
	}

	@Override
	public String delete() throws Throwable {
		String ids = (String) request.getParameter("id");
		String[] id = ids.split(",");
		superviseDeviceService.delete(id);
		ajaxFormResult = AjaxFormResult.success(null);
		return SUCCESS;
	}

	private static final String[] headers = new String[] { "配对设备编号",
			"定位主机设备编号", "主机电话号码", "腕表设备编号", "设备状态", "购买单位", "购买日期", "当前使用单位" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String exportExcel() throws UnsupportedEncodingException {
		CreateFileUtil util = CreateFileUtil.getInstance();
		String[] head = jqgrid.getCols().split(",");
		LinkedList<String> headTable = new LinkedList(Arrays.asList(headers));
		headTable.add(0, "序号");

		LinkedList<LinkedList> contentList = new LinkedList<LinkedList>();
		// service.findByfilter(entity1, entity2);
		try {

			List res = superviseDeviceService.findPaged(
					fillPageWithJQGridRequest(),
					parseJQGridRequest(getDefaultRule()), Arrays.asList(head));
			for (int i = 0; i < res.size(); i++) {
				Object[] cc = (Object[]) res.get(i);
				LinkedList content = new LinkedList();
				content.add(i);
				for (int j = 0; j < cc.length; j++) {
					if (j == 4) {
						content.add("02".equals(cc[j]) ? "未用" : "03"
								.equals(cc[j]) ? "在用" : "停用");
						continue;
					}
					if (j == 5 || j == 6) {
						content.add(getOrgName((String) cc[j]));
						continue;
					}
					if (cc[j] != null && cc[j] instanceof Date) {
						cc[j] = sdf.format(cc[j]);
					}
					content.add(cc[j]);
				}
				contentList.add(content);
			}
			String fileName = util.create(headTable, contentList);
			excel = new Excel(util.getFileInputStream(fileName), fileName);
		} catch (Exception e) {
			logger.error(e);
		}
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

	@Override
	public String add() throws Throwable {
		String pairDeviceNumber = request.getParameter("pairDeviceNumber");
		String machineDeviceNumber = request
				.getParameter("machineDeviceNumber");
		String watchDeviceNumber = request.getParameter("watchDeviceNumber");
		watchDeviceNumber = watchDeviceNumber.toUpperCase();
		Date date = new Date();

		CcSuperviseDevice watch = new CcSuperviseDevice();
		CcSuperviseDevice machine = new CcSuperviseDevice();
		CcDevicePair pair = new CcDevicePair();
		pair.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		pair.setWatch(watch);
		pair.setMachine(machine);
		pair.setBeginDate(date);
		pair.setCrtperson(getUserId());
		pair.setCrtorg(getCurOrgId());

		watch.setDevicePair(pair);
		watch.setProcureUnit(getCurOrgId());
		watch.setDeviceType(Constants.TYPE_WATCH);

		machine.setDevicePair(pair);
		machine.setProcureUnit(getCurOrgId());
		machine.setDeviceType(Constants.TYPE_MACHINE);

		pair.setPairDeviceNumber(pairDeviceNumber);
		pair.setStatus(Constants.STATUS_UNUSED);

		watch.setDeviceNumber(watchDeviceNumber);
		watch.setDeviceStatus(Constants.STATUS_UNUSED);
		machine.setDeviceNumber(machineDeviceNumber);
		machine.setDeviceStatus(Constants.STATUS_UNUSED);
		watch.setProcureDate(entity.getProcureDate());
		machine.setProcureDate(entity.getProcureDate());

		machine.setConnectPhone(entity.getConnectPhone());

		superviseDeviceService.add(pair, watch, machine, null);

		ajaxFormResult = AjaxFormResult.success(null);
		return SUCCESS;
	}

	@Override
	public String update() throws Throwable {
		CcDevicePair newPair = null;
		CcDevicePair pair = superviseDeviceService.getDevicePair(entity
				.getDeviceId());
		CcSuperviseDevice machine = pair.getMachine();
		CcSuperviseDevice watch = pair.getWatch();

		machine.setDeviceStatus(entity.getDeviceStatus());
		machine.setProcureDate(entity.getProcureDate());
		if (!CommonUtils.isNull(entity.getConnectPhone())) {
			machine.setConnectPhone(entity.getConnectPhone());
		}
		watch.setDeviceStatus(entity.getDeviceStatus());
		watch.setProcureDate(entity.getProcureDate());
		// pair.setStatus(entity.getDeviceStatus());

		if (!entity.getDeviceStatus().equals(pair.getStatus())) {
			newPair = superviseDeviceService.pairStatusChanged(pair,
					entity.getDeviceStatus());
			newPair.setCrtorg(getCurOrgId());
			newPair.setCrtperson(getUserId());
			newPair.setBeginDate(new Date());
			pair.setEndDate(newPair.getBeginDate());
		}

		superviseDeviceService.add(pair, machine, watch, newPair);
		ajaxFormResult = AjaxFormResult.success(null);
		return null;
	}

	public String checkDevicePair() {
		String pairId = request.getParameter("pairId");
		String machineId = request.getParameter("machineId");
		String watchId = request.getParameter("watchId");
		watchId = watchId.toUpperCase();
		String res = superviseDeviceService.checkDevicePair(pairId, machineId,
				watchId);
		ajaxFormResult = new AjaxFormResult(res == null ? true : false, res);
		return SUCCESS;
	}

	public String queryEquipableMachine() {
		//different from online
		/*
		boolean flag = superviseDeviceService.queryMachineEquipable();
		if (flag) {
		*/
			dto = superviseDeviceService.queryEquipableMachine(getCurOrgId());
		//different from online
		/*
		} else {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deviceNumber", "在用主机数量达到性能上限");
			list.add(map);
			dto = list;
		}
		*/
		return SUCCESS;
	}

	private List<Map<String, Object>> dto;

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

	public SuperviseDeviceService getSuperviseDeviceService() {
		return superviseDeviceService;
	}

	public void setSuperviseDeviceService(
			SuperviseDeviceService superviseDeviceService) {
		this.superviseDeviceService = superviseDeviceService;
	}

	public AjaxFormResult getAjaxFormResult() {
		return ajaxFormResult;
	}

	public void setAjaxFormResult(AjaxFormResult ajaxFormResult) {
		this.ajaxFormResult = ajaxFormResult;
	}

	public List<Map<String, Object>> getDto() {
		return dto;
	}

	public void setDto(List<Map<String, Object>> dto) {
		this.dto = dto;
	}

	public String getImportFile() {
		return importFile;
	}

	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

	public Excel getExcel() {
		return excel;
	}

	public void setExcel(Excel excel) {
		this.excel = excel;
	}
}
