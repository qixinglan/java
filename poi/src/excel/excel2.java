package excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * ģ�巽ʽ
 * HSSFWorkbook  excel2003     xls��β
 * XSSFWorkbook  excel2007+   xlsx��β
 */
public class excel2 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream ("ģ��.xlsx"));
//		FileOutputStream fos=new FileOutputStream("studemt2.xlsx");
//		wb.write(fos);
//		fos.close();
		XSSFWorkbook wb=new XSSFWorkbook(new FileInputStream ("ģ��.xlsx"));
		FileOutputStream fos=new FileOutputStream("studemt2.xlsx");
		wb.write(fos);
		fos.close();
	}
	
}
