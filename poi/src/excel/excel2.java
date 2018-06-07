package excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * 模板方式
 * HSSFWorkbook  excel2003     xls结尾
 * XSSFWorkbook  excel2007+   xlsx结尾
 */
public class excel2 {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream ("模板.xlsx"));
//		FileOutputStream fos=new FileOutputStream("studemt2.xlsx");
//		wb.write(fos);
//		fos.close();
		XSSFWorkbook wb=new XSSFWorkbook(new FileInputStream ("模板.xlsx"));
		FileOutputStream fos=new FileOutputStream("studemt2.xlsx");
		wb.write(fos);
		fos.close();
	}
	
}
