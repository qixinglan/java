package com.nci.dcs.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;


public class CreateFileUtil {

	private static CreateFileUtil cf = new CreateFileUtil();


	private CreateFileUtil() {
	}

	public static CreateFileUtil getInstance() {
		return cf == null ? new CreateFileUtil() : cf;
	}
	
	public String create(LinkedList headTable,LinkedList<LinkedList> contentList) throws Exception{
		String fileName="";
		FileOutputStream fos = null;	//文件输出流
		//创建excel文件	
		HSSFWorkbook wb = new HSSFWorkbook();	//创建空白workbook
		HSSFSheet sheet = wb.createSheet("列表");//创建表
		HSSFRow row = sheet.createRow(0);	//创建行
		HSSFCell cell = row.createCell(0); 	//创建列
		HSSFFont font=wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style=wb.createCellStyle();
		style.setFont(font);
		try {
			//创建表头
			for(int i=0;i<headTable.size();i++){
				cell.setCellValue(headTable.get(i).toString());
				cell.setCellStyle(style);
				cell=row.createCell(i+1);
			}
			//内容
			for(int i=0;i<contentList.size();i++){
				row = sheet.createRow(i+1);
				LinkedList lk=contentList.get(i);
				for(int j=0;j<lk.size();j++){
					cell = row.createCell(j); 	//创建列
					if(lk.get(j)!=null){
						cell.setCellValue(lk.get(j).toString());
					}else{
						cell.setCellValue("");
					}			
				}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		}
		//按当前日期生成文件名
		fileName = Calendar.getInstance().getTimeInMillis() + ".xls";
		//判断文件夹是否存在
		File fil=new File(this.getPath());
		if(!fil.exists()){
			fil.mkdir();
		}
		File file = new File(this.getPath()+fileName);
		if(file.exists()){	
			file.delete();	//删除原文件
		}
		try {
			file.createNewFile();	//创建新文件
			fos = new FileOutputStream(file);	//创建文件输出流
			wb.write(fos);	//将内容写入文件
			fos.flush();
		} catch (IOException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		}finally{
			if(fos != null){
				try {
					fos.close();//关闭流
				} catch (IOException e) {
					throw new RuntimeException("数据导出失败" + e.getMessage());
				}	
			}
		}
		return fileName;
	}
	
	public String createMore(LinkedList headTable,LinkedList<String> contentList) throws Exception {
		String fileName = "";
		FileOutputStream fos = null; // 文件输出流
		// 创建excel文件
		SXSSFWorkbook wb = new SXSSFWorkbook(100); // 创建空白workbook
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		int index = 1; // 列表编号
		// 内容
		try {
			if(contentList==null||contentList.size()==0){
				sheet = wb.createSheet("列表" + index);// 创建表
				row = sheet.createRow(0); // 创建行
				cell = row.createCell(0); // 创建列
				// 创建表头
				for (int j = 0; j < headTable.size(); j++) {
					cell.setCellValue(headTable.get(j).toString());
					cell = row.createCell(j + 1);
				}
			}else{
				for (int i = 0; i < contentList.size(); i++) {
					if (i % 10000 == 0) {
						sheet = wb.createSheet("列表" + index);// 创建表
						row = sheet.createRow(0); // 创建行
						cell = row.createCell(0); // 创建列
						// 创建表头
						for (int j = 0; j < headTable.size(); j++) {
							cell.setCellValue(headTable.get(j).toString());
							cell = row.createCell(j + 1);
						}
						index++;
					}
					row = sheet.createRow(i + 1 - (index - 2) * 10000);
					String[] contents = contentList.get(i).split(",");
					for (int j = 0; j < contents.length; j++) {
						cell = row.createCell(j); // 创建列
						if (contents[j] != null&&!contents[j].equals("null")) {
							cell.setCellValue(contents[j]);
						} else {
							cell.setCellValue("");
						}
					}			
					if((i+1)%100==0){
						((SXSSFSheet)sheet).flushRows();
					}		
		    	}
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		}
		// 按当前日期生成文件名
		fileName = Calendar.getInstance().getTimeInMillis() + ".xlsx";
		// 判断文件夹是否存在
		File fil = new File(this.getPath());
		if (!fil.exists()) {
			fil.mkdir();
		}
		File file = new File(this.getPath() + fileName);
		if (file.exists()) {
			file.delete(); // 删除原文件
		}
		try {
			file.createNewFile(); // 创建新文件
			fos = new FileOutputStream(file); // 创建文件输出流
			wb.write(fos); // 将内容写入文件
			fos.flush();
		} catch (IOException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();// 关闭流
				} catch (IOException e) {
					throw new RuntimeException("数据导出失败" + e.getMessage());
				}
			}
		}
		return fileName;
	}

	private String getPath() {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/files");
		if (path.lastIndexOf("\\") != 0 || path.lastIndexOf("/") != 0) {
			path += System.getProperties().getProperty("file.separator");
		}
		return path;
	}

	public InputStream getFileInputStream(String fileName) throws Exception {
		InputStream is = new FileInputStream(this.getPath() + fileName);
		return is;
	}
	
	public byte[] getFileBytes(String fileName) throws Exception{
		
		return null; 
	}
	
	//导出服刑人员档案信息
	//root 代表每个doc中封装好的数据
	//dabh 代表导出doc的模板
	//frxry_name 服刑人员姓名
	//damc 档案名称
	public String createFxryDoc(Map<String,String> root,String fxry_dabh,String frxry_name,String damc) throws Exception{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		//获取模板所在的文件路径
		URI uri=CreateFileUtil.class.getClassLoader().getResource("/docTempletes").toURI();
		//配置模板路径
		cfg.setDirectoryForTemplateLoading(new File(uri));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		/* Get the template (uses cache internally) */
		//加载模板文件
		Template temp = cfg.getTemplate(fxry_dabh+".xml");
		/* Merge data-model with template */
		String fileName="";
		FileOutputStream fos = null;	//文件输出流
		Writer out =null;
		//按当前日期生成文件名
		fileName = frxry_name +"_" +damc+".doc";
		//必须要转换，否则下载中文名称乱码
		fileName=java.net.URLEncoder.encode(fileName,"utf-8");
		//判断文件夹是否存在
		File fil=new File(this.getPath());
		if(!fil.exists()){
			fil.mkdir();
		}
		File file = new File(this.getPath()+fileName);
		if(file.exists()){	
			file.delete();	//删除原文件
		}
		try {
			file.createNewFile();	//创建新文件
			fos = new FileOutputStream(file);	//创建文件输出流
			out = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			temp.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		}finally{
			if(fos != null){
				try {
					fos.close();//关闭流
				} catch (IOException e) {
					throw new RuntimeException("数据导出失败" + e.getMessage());
				}	
			}
			if(out != null){
				try {
					out.close();//关闭流
				} catch (IOException e) {
					throw new RuntimeException("数据导出失败" + e.getMessage());
				}	
			}
		}
		return fileName;
	}
	/**
	 * 综合查询中，导出查询结果
	 * SXSSF适合大批量的excel生成
	 * List<Object[]>> 封装了分页查询的结果，String 为分页中的某一页，List<Object[]>>为这一页的结果集，Object[]为 查询javabean要求显示的部分字段
	 * titles 列名，对应数据库表entity中的字段的中文名称
	 * titles 列名数组
	 */
	public String createComprehensiceExcel(
			Map<String, List<Object[]>> map, String[] titles
			) throws Exception {
		String fileName = "";
		FileOutputStream fos = null; // 文件输出流
		// 内存缓存记录行数
		int rowaccess = 100;
		SXSSFWorkbook wb = new SXSSFWorkbook(rowaccess);
		try {
			// 生成sheet的个数=分页查询的页数
			Set<String> sheetNum = map.keySet();
			for (String s : sheetNum) {
				Sheet sh = wb.createSheet();
				// 每个分页的服刑人员list
				List<Object[]> pagelist = map.get(s);
				if (pagelist == null || pagelist.size() == 0) {
					// 综合查询结果为空,只导出title
					Row row = sh.createRow(0);
					for (int cellnum = 0; cellnum < titles.length; cellnum++) {
						Cell cell = row.createCell(cellnum);
						cell.setCellValue(titles[cellnum]);
					}
				} else {
					for (int rownum = 0; rownum < pagelist.size()+1; rownum++) {
						Object []fxryContent =null;
						if(rownum>=1){
							fxryContent = pagelist.get(rownum-1);
						}else{
							fxryContent = pagelist.get(rownum);
						}
						Row row = sh.createRow(rownum);
						// 每行有多少个cell
						for (int cellnum = 0; cellnum < titles.length; cellnum++) {
							if (rownum == 0) {
								Cell cell = row.createCell(cellnum);
								cell.setCellValue(titles[cellnum]);
							} else {
								Cell cell = row.createCell(cellnum);
								cell.setCellValue(fxryContent[cellnum]+"");
							}
						}
						// 每行达到指定设置值时，就自动刷新数据到硬盘，清理内存
						if (rownum % rowaccess == 0) {
							((SXSSFSheet) sh).flushRows();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 按当前日期生成文件名
		fileName = Calendar.getInstance().getTimeInMillis() + ".xlsx";
		// 判断文件夹是否存在
		File fil = new File(this.getPath());
		if (!fil.exists()) {
			fil.mkdir();
		}
		File file = new File(this.getPath() + fileName);
		if (file.exists()) {
			file.delete(); // 删除原文件
		}
		try {
			file.createNewFile(); // 创建新文件
			fos = new FileOutputStream(file); // 创建文件输出流
			wb.write(fos); // 将内容写入文件
			fos.flush();
		} catch (IOException e) {
			throw new RuntimeException("数据导出失败" + e.getMessage());
		} finally {
			if (fos != null) {
				try {
					fos.close();// 关闭流
				} catch (IOException e) {
					throw new RuntimeException("数据导出失败" + e.getMessage());
				}
			}
		}
		return fileName;
	}
}
