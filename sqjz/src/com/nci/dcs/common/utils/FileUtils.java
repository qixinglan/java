package com.nci.dcs.common.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.nci.dcs.common.exceptions.QueryException;
import com.nci.dcs.common.utils.PathUtils;

/**
 * 文件工具类
 * 生成随机文件名，复制文件，关闭数据流，删除文件功能
 * @author yzq
 * 
 */
public class FileUtils {
	/**
	 * 随机生成文件名 
	 * @return 文件名
	 */
	public static String getFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Random random = new Random();
		
		float rnd = random.nextFloat();
		rnd *= 10000;
		return sdf.format(Calendar.getInstance().getTime()) + (int) rnd;
	}

	/**
	 * 复制文件
	 * @param srcFile	源文件
	 * @param destFile	目标文件
	 * @throws IOException	
	 */
	public static void copyFile(File srcFile, File destFile) throws IOException {
		org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
	}

	/**
	 * 关闭数据输入流
	 * @param is
	 */
	public static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据输出流
	 * @param os
	 */
	public static void close(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据输入流
	 * @param reader
	 */
	public static void close(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据输出流
	 * @param writer
	 */
	public static void close(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取文件扩展名
	 * @param fileName	文件名
	 * @return
	 */
	public static String getExtension(String fileName){
		String ext = null;
		if(StringUtils.isNotBlank(fileName)){
			int pos = fileName.lastIndexOf(".");
			ext = fileName.substring(pos);
		}
		
		return ext;
	}
	
	/**
	 * 根据属性名得到资源属性
	 * 
	 * @param itemIndex
	 * @return
	 */
	public static String getConfigInfomation(String configFile, String itemIndex) {
		try {
			ResourceBundle resource = ResourceBundle.getBundle(configFile);

			String docExt = resource.getString(itemIndex);

			// LOG.info("文档类型为:"+docExt);
			return new String(docExt.getBytes("ISO-8859-1"), "utf-8");
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 删除文件
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String pathName){
		if(StringUtils.isNotBlank(pathName)){
			File file = new File(PathUtils.getRealPath() + pathName);
			if(file.exists()){
				file.delete();				
			}
			return true;
		}
		return false;
	}	
	
	/**
	 * 保存文件
	 * @param file
	 * @param suffix 后缀
	 * @param uploadPath 文件上传路径
	 * @param format 文件要求格式 f1,f2,f3
	 * @param limitSize 文件要求大小
	 * @return
	 * @throws Exception
	 */
	public static String save(File file, String suffix, String uploadPath, String format, double limitSize) throws Exception{
		String fileName = "";
		String filePath = "";
		if(file != null){			
			//格式验证
			if(StringUtils.isNotEmpty(format) && !isSuffixMatched(suffix, format)){
				throw new Exception("文件类型不正确");
			}
			//大小验证
			double fileSize = file.length()/1024.0/1024.0;
			if(fileSize <= 0){
				throw new Exception("不能上传空文件");
			}
			if(fileSize > limitSize){
				throw new Exception("文件超过允许的大小" + limitSize + "M");
			}
			//生成文件
			fileName = getFileName() + suffix;
			filePath = PathUtils.getRealPath() + uploadPath;
			File destFile = new File(filePath + File.separator + fileName);
			//保存文件
			copyFile(file, destFile);			
		}		
		
		return fileName;
	}
	
	/***
	 * 判断格式
	 * @param suffix  后缀    例  "txt"
	 * @param format   格式要求 多个用","分隔   例  "txt,docx,xls"
	 * @return
	 */
	private static boolean isSuffixMatched(String suffix, String format){
		boolean result = false;
		format = "," + format + ",";
		if(format.contains(suffix)){
			result = true;
		}
	
		return result;
	}
	
	/**
	 * 级联删除文件
	 * @param pathName  目录或文件的相对路径   
	 * 
	 */
	public static void deleteCascade(String pathName) throws Exception{
		File file = new File(PathUtils.getRealPath() + pathName);
		if(file.exists()){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for(File f : files){
					deleteCascade(PathUtils.getRealPath() + f.getPath());
				}				
			}
			file.delete();		
		}
	}
	
	/**
	 * 
	 * @param pathName
	 * @return
	 */
	public static File getFile(String pathName){
		File file = new File(PathUtils.getRealPath() + pathName);
		return file;
	}

}
