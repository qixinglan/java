package com.nci.dcs.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Service
@Transactional
public class ImageUtils{
	private static Logger logger = Logger.getLogger(ImageUtils.class);
	/**
	 * 更新图片
	 * @param imageFile 目标图片
	 * @param imageFilename	目标图片名称
	 * @param entityFilename	实体关联图片名称
	 * @return
	 * @throws IOException
	 */
	public static String save(File imageFile,String imageFilename,String entityFilename) throws IOException{
		//图片文件上传路径
		String imagePath = Constants.IMAGEPATH + Constants.SEPARATOR;
		logger.info("imagePath路径："+imagePath);
		if(imageFile!=null && imageFile.length()==0)
			return null;
		//上传图片文件
		if(StringUtils.isNotBlank(imagePath)){
			//获取上传文件的扩展名
			String postfix = FileUtils.getExtension(imageFilename);
			//图片文件名
			String random = FileUtils.getFileName();
			String fileName = PathUtils.getRealPath() + imagePath + random + postfix;
			logger.info("fileName路径："+fileName);
			//如果目标图片不为空
			if(imageFile!=null){
				//如果目标图片名称和实体已保存的图片名称不同，则删除实体关联的图片，并保存目标图片
				if(!imageFilename.equals(entityFilename)){
					String deleteFilename = imagePath+entityFilename;
					FileUtils.deleteFile(deleteFilename);
					//目标图片文件
					File destFile = new File(fileName);
					//复制图片文件
					FileUtils.copyFile(imageFile, destFile);
					
					logger.info("复制文件："+imageFile+ "到：" + destFile);
					//返回重新指定的图片名
					return random + postfix;
				}
				logger.info("复制文件："+imageFile+ "失败。");
			//如果目标图片为空
			}else{
				//如果取消实体的照片则删除实体关联的图片
//				if(entityFilename!=null){
//					FileUtils.deleteFile(imagePath+entityFilename);
//				}
				logger.info("imageFile路径为空"+imageFile);
				return entityFilename;
			}
		}
		return null;
	}
	
	//如果删除对象，则删除与之关联的图片
	public static void deleteImage(String entityFilename){
		if(!CommonUtils.isNull(entityFilename)){
			String imagePath = Constants.IMAGEPATH + Constants.SEPARATOR;
			FileUtils.deleteFile(imagePath+entityFilename);
		}
	}
	
	public static boolean createImage(String imgStr ,String imgFilePath){
		if(imgStr== null){
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try{
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for(int i=0;i<bytes.length;++i){
				if(bytes[i]<0){
					bytes[i]+=256;//调整异常数据
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean createImage(byte[] imgB ,String imgFilePath){
		try{
			byte[] bytes =imgB;
			for(int i=0;i<bytes.length;++i){
				if(bytes[i]<0){
					bytes[i]+=256;//调整异常数据
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//如果删除对象，则删除与之关联的图片
		public static void deleteImageByPath(String path){
			if(!CommonUtils.isNull(path)){
				FileUtils.deleteFile(path);
			}
		}
	
	public static void main(String[] args) {
		String imgStr = "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";
		File file = new File("D:/1/2/");
		if(!file.exists())
			file.mkdirs();
		String imgFilePath = "D:/1/2/1.jpeg";
		ImageUtils.createImage(imgStr, imgFilePath);
	}
	
}
