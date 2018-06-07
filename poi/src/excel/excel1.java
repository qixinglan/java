package excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class excel1 {
	public static void main(String[] args) throws IOException {
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("学生表");
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//设置表头局中格式
		HSSFCell cell=row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("姓名");
		cell=row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("年龄");
		
		
		List<student> list=new ArrayList();
		student s1=new student();
		s1.setName("Tom");
		s1.setAge(18);
		student s2=new student();
		s2.setName("Andy");
		s2.setAge(17);
		list.add(s1);
		list.add(s2);
		for(int i=0;i<list.size();i++){
			 row=sheet.createRow(i+1);
			 cell=row.createCell(0);
			 cell.setCellValue(list.get(i).getName());
			 cell=row.createCell(1);
			 cell.setCellValue(list.get(i).getAge());
		}
		
		
		FileOutputStream fos=new FileOutputStream("./studen.xls");//输出excel
		wb.write(fos);
		fos.close();
	}
}
