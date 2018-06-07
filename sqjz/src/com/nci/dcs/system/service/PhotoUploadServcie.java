package com.nci.dcs.system.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.nci.dcs.common.utils.CommonUtils;
import com.nci.dcs.common.utils.PathUtils;
import com.nci.dcs.system.model.PhotoUpload;

@Service
public class PhotoUploadServcie {
	private static String SAVE_PATH = "/upload/photos";
	private static String CONTENT = "CONTENT";
	private static String INFO = "INFO";

	public void deleteFile(String id) {
		try {
			String path = getRealPath(id);
			FileUtils.deleteDirectory(new File(path));
		} catch (Exception e) {

		}
	}

	public String saveFile(PhotoUpload entity) throws IOException {
		String id = CommonUtils.uuid();
		String path = getRealPath(id);
		String newFileName = path + File.separator + CONTENT;
		File savedFile = new File(newFileName);
		FileUtils.copyFile(entity.getPhoto(), savedFile);
		String info = path + File.separator + INFO;
		File infoFile = new File(info);
		List<String> lines = new ArrayList<String>();
		lines.add(entity.getPhotoContentType());
		lines.add(entity.getPhotoFileName());
		FileUtils.writeLines(infoFile, lines);
		return id;
	}

	private String getRealPath(String id) {
		String path = PathUtils.getRealPath() + File.separator
				+ FilenameUtils.normalize(SAVE_PATH);
		path = path + File.separator + id.substring(0, 2) + File.separator
				+ id.substring(2, 4) + File.separator + id;
		return path;
	}

	public PhotoUpload get(String id) throws IOException {
		String path = getRealPath(id);
		String file = path + File.separator + CONTENT;
		String info = path + File.separator + INFO;
		File infoFile = new File(info);
		List<String> lines = FileUtils.readLines(infoFile);
		PhotoUpload photo = new PhotoUpload(new File(file), lines.get(0),
				lines.get(1));
		photo.setId(id);
		return photo;
	}

	public String getInfo(String path) {
		return path + File.separator + INFO;
	}

	public File createTempFile(byte[] data, String fileName) {
		String prefix = fileName, suffix = null;
		if (fileName.contains(".")) {
			prefix = fileName.split("\\.")[0];
			suffix = "." + fileName.split("\\.")[1];
		}
		FileOutputStream fo = null;
		File f = null;
		try {
			f = File.createTempFile(prefix, suffix);
			fo = new FileOutputStream(f);
			fo.write(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fo) {
				try {
					fo.close();
				} catch (IOException e) {
				}
			}
		}
		return f;
	}

	public byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public boolean checkFile(String id) {
		try {
			String path = getRealPath(id);
			String file = path + File.separator + CONTENT;
			String info = path + File.separator + INFO;
			File infoFile = new File(info);
			List<String> lines = FileUtils.readLines(infoFile);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void saveFile(PhotoUpload entity, String id) throws IOException {
		String path = getRealPath(id);
		String newFileName = path + File.separator + CONTENT;
		File savedFile = new File(newFileName);
		FileUtils.copyFile(entity.getPhoto(), savedFile);
		String info = path + File.separator + INFO;
		File infoFile = new File(info);
		List<String> lines = new ArrayList<String>();
		lines.add(entity.getPhotoContentType());
		lines.add(entity.getPhotoFileName());
		FileUtils.writeLines(infoFile, lines);
	}
}
