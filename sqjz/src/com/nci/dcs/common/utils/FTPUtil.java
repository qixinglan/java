package com.nci.dcs.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * FTP服务类
 * 实现文件的上传、下载、创建目录、浏览文件列表、命令接口
 * @author gengqingbin
 * 
 */
public class FTPUtil {
	private String host;	//ftp服务器
	private String username;	//登录用户名
	private String password;	//登录密码
	private int port = -1;		//ftp端口
	private Logger logger = LogManager.getLogger(FTPUtil.class);
	private FTPClient ftpClient = new FTPClient();
	private static boolean isFirst = true;
	private String encoder = "GBK";

	/**
	 * 默认构造函数
	 */
	public FTPUtil() {

	}

	/**
	 * 构造函数
	 * @param host 服务器IP
	 * @param username 登录用户名
	 * @param password 登录密码
	 */
	public FTPUtil(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 * @param serverIp 服务器IP
	 * @param port		端口号
	 * @param username 登录用户名
	 * @param password 登录密码
	 */
	public FTPUtil(String host, int port, String username, String password) {
		this(host, username, password);
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getEncoder() {
		return encoder;
	}

	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}

	/**
	 * 连接FTP服务器
	 * 使用默认参数
	 * @return
	 */
	public boolean connect() {
		return connect(host, port, username, password);
	}
	/**
	 * 连接FTP服务器
	 * @param host ftp服务器
	 * @param port 端口
	 * @param username	用户名
	 * @param password	密码
	 * @return
	 */
	public boolean connect(String host, int port, String username, String password){
		int reply;
		try {
			ftpClient.connect(host, port);
			reply = ftpClient.getReplyCode();
			//返回正数，连接成功
			if(FTPReply.isPositiveCompletion(reply)){
				ftpClient.setControlEncoding(encoder);
				if(ftpClient.login(username, password)){
					return true;
				}
				logger.info("FTP server connection failure, check the username or password.");
				return false;
			}else{
				logger.info("FTP server refused connection.");
				ftpClient.disconnect();
				return false;
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return false;
	}

	/**
	 * 关闭连接
	 * 
	 * @return
	 */
	public void disconnect() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
	}

	/**
	 * 判断远程目录是否存在
	 * 
	 * @param pathname
	 *            ftp服务器上的目录
	 * @return
	 */
	public boolean isExistDir(String pathname) {
		try {			
			String pwd = ftpClient.printWorkingDirectory(); // 获取当前目录			
			// 进入目标目录，返回true，目标目录存在
			if(ftpClient.changeWorkingDirectory(convertCode(pathname))){				
				ftpClient.changeWorkingDirectory(pwd);
			}
			isFirst = false;
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			return false;
		}

	}
	
	/**
	 * 判断远程文件是否存在
	 * 
	 * @param remoteFile
	 *            ftp服务器上的文件
	 * @return True if the file is exist, false if not.
	 */
	public boolean isExistFile(String remoteFile) {
		//检索远程文件是否存在
		FTPFile[] files;
		boolean result = false;
		try {
			files = ftpClient.listFiles(convertCode(remoteFile));
			result = files.length == 1 ? true : false;
			isFirst = false;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}

	/**
	 * 创建远程目录
	 * 
	 * @param pathname
	 *            目录名称
	 * @return
	 */
	public boolean createDir(String dir) {		
		try {
			String directory = dir.substring(0,dir.lastIndexOf("/")+1); 
			if(!directory.equals("/") && !ftpClient.changeWorkingDirectory(convertCode(directory))){		
				isFirst = false;
				int start = 0;				
				if(directory.startsWith("/")){	//绝对路径	
					cd("/");
					start = 1;
				}
				int end = directory.indexOf("/",start);
				
				while(start <= end){
					String subDir = convertCode(directory.substring(start,end));
					//进入子目录，如果失败，则说明子目录不存在
					if(!ftpClient.changeWorkingDirectory(subDir)){
						//创建子目录
						if(ftpClient.makeDirectory(subDir)){
							logger.info("create directory:" + directory.substring(0, end) +" successful.");
							cd(subDir);
						}else{
							logger.info("create directory: "+ directory.substring(0, end) +" failure.");
							return false;
						}
					}
					start = end +1;
					end = directory.indexOf("/",start);
				}
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 上传文件到FTP服务器
	 * 
	 * @param localFile
	 *            本地文件的文件名
	 * @param remoteFile
	 *            远程文件的文件名
	 * @return 
	 */
	public boolean uploadFile(String localFile, String remoteFile) {
		File file = null;
		FileInputStream fis = null;
		OutputStream os = null;
		//设置本地被动数据传输模式
		ftpClient.enterLocalPassiveMode();
		
		try {
			//以二进制流传输数据
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			//处理远程目录,不存在时创建远程目录
			if(remoteFile.contains("/")){
				this.createDir(remoteFile.substring(0, remoteFile.lastIndexOf("/")+1));
			}			
			
			//检查远程文件是否存在			
			if(isExistFile(remoteFile)){	//远程文件存在
				logger.info("remote file has existed.");
				return false;
			}else{
				file = new File(localFile);
				fis = new FileInputStream(file);
				if(file.exists()){
					ftpClient.storeFile(convertCode(remoteFile), fis);
					isFirst = false;
					logger.info("file:"+ localFile +" upload success.");
//					System.out.println("file:"+ localFile +" upload success.");
//					//上传进度
//					long step = file.length() / 100;
//					long process = 0;
//					long localreadbytes = 0;
//					byte[] bytes = new byte[1024];   
//					int c;
//					while((c = fis.read(bytes)) != -1){
//						os.write(bytes, 0, c);
//						localreadbytes += c;
//						if(step > 0 && localreadbytes / step != process){
//							process = localreadbytes / step;
//							process = process > 100 ? 100 : process;
//							logger.info(localFile + " upload process:" + process);
//						}
//					}
//					os.flush();	
				}else{
					logger.info("local file is not exist.");
					return false;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			disconnect();
		}
		return true;
	}
	
	
	/**
	 * 上传文件夹到FTP服务器
	 * @param localDir	本地目录
	 * @param remoteDir	远程目录
	 * @return
	 * @throws IOException 
	 */
	public boolean uploadDir(String localDir, String remoteDir) throws IOException{
		//本地文件
		File localFile = new File(localDir);	
		//切换到根目录
		if(localFile.isDirectory()){
			//创建远程文件夹
			if(createDir(remoteDir)){				
				File[] childs = localFile.listFiles();
				for (File file : childs) {
					String filename = file.getName();
					if (file.isDirectory()) { // 文件夹
						uploadDir(localDir + Constants.SEPARATOR + filename,remoteDir + filename + "/");
					} else {						
						uploadFile(localDir + Constants.SEPARATOR + filename,remoteDir + filename);
						connect();
					}
				}
			}
		}else{
			uploadFile(localDir, remoteDir);
		}
		return true;
	}

	/**
	 * 下载文件到本地
	 * 
	 * @param remoteFile
	 *            ftp服务器上的文件名
	 * @param localFile
	 *            //本地文件名
	 * @return	文件大小
	 */
	public boolean downloadFile(String remoteFile, String localFile) {
		InputStream is = null;
		FileOutputStream fos = null;
		ftpClient.enterLocalPassiveMode();
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			String filename = convertCode(remoteFile);		
			isFirst = false;
			is = ftpClient.retrieveFileStream(filename);
			if(is != null){
				File lFile = new File(localFile);
				if(!lFile.exists()){
					fos = new FileOutputStream(localFile);
					//下载进度
					//long step = files[0].getSize() / 100;
					long process = 0;
					long localreadbytes = 0;
					byte[] bytes = new byte[1024];   
					int c;
					while((c = is.read(bytes)) != -1){
						fos.write(bytes, 0, c);
						localreadbytes += c;
//							if(localreadbytes / step != process){
//								process = localreadbytes / step;
//								logger.info("download process:" + process);
//							}
					}
					fos.flush();	
					return true;
				}else{
					logger.info("The local file has existed.");
					return false;
				}
			}else{
				logger.info("Get the remote file is failure.");
				return false;
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//手动在代码中关闭连接，否则取一个连接就要连一次
			//this.disconnect();
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 转换文件名编码，解决中文文件名问题
	 * @param remoteFile
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String convertCode(String remoteFile)
			throws UnsupportedEncodingException {
		return isFirst == true ? new String(remoteFile.getBytes("GBK"),"iso-8859-1") : remoteFile;
	}
	
	/**
	 * 下载远程文件夹到本地
	 * @param remoteDir
	 * @param localDir
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public long downloadDir(String remoteDir, String localDir) throws UnsupportedEncodingException{
		long result = 0;
		File file = new File(localDir);
		//判断本地文件夹是否存在
		if(!file.isDirectory()){ 
			//不存在，创建文件夹
			file.mkdir();
		}
		
		List<String> list = this.getFileList(convertCode(remoteDir));
		isFirst = false;
		for (String filename : list) {
			if(this.isExistDir(filename)){
				logger.debug("directory : " + filename);
				downloadDir(filename, localDir + Constants.SEPARATOR + filename);
			}else{
				logger.debug("file : " + filename);
				downloadFile(filename,localDir + Constants.SEPARATOR + filename);
			}
		}
		
		return result;
	}
	
	/**
	 * 获取远程文件列表
	 * @param path
	 * @return
	 */
	public List<String> getFileList(String path){
		List<String> list = new ArrayList<String>();		
		FTPFile[] files;
		try {
			files = ftpClient.listFiles(convertCode(path));
			for(FTPFile file : files){
				list.add(file.getName());
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 删除远程文件
	 * @param remoteFile
	 * @return
	 */
	public boolean deleteRemoteFile(String remoteFile){
		try {
			return ftpClient.deleteFile(convertCode(remoteFile));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除远程文件夹(递归删除)
	 * @param pathname
	 * @return
	 */
	public boolean removeDirectory(String pathname){
		try {
			//获取目录下文件列表
			FTPFile[] files = ftpClient.listFiles(convertCode(pathname));
			isFirst = false;
			for (FTPFile file : files) {	//遍历列表
				if(file.isDirectory()){	//文件夹，递归删除
					removeDirectory(file.getName());
				}else{	//文件，直接删除
					deleteRemoteFile(file.getName());
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 文件重命名
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean rename(String from, String to){
		try {
			return ftpClient.rename(convertCode(from), convertCode(to));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
//		FTPUtil ftp = new FTPUtil("10.10.100.207",21,"ftp","ftp");
//		ftp.connect();
//		ftp.pwd();	
////		ftp.uploadDir("C:\\Documents and Settings\\Administrator\\桌面\\测试视频文件", "/Uploads/gengqb/video/");
////		ftp.uploadFile("C:\\Documents and Settings\\Administrator\\桌面\\bf二期项目\\bf二期项目所检部署清单-20120113.xlsx", "/Uploads/geng/video/bf二期项目所检部署清单-20120113.xlsx");
////		ftp.connect();
////		ftp.uploadFile("C:\\Documents and Settings\\Administrator\\桌面\\bf二期项目\\备忘录影响边防业务专用软件进度因素20120112.doc", "/Uploads/geng/video/备忘录影响边防业务专用软件进度因素20120112.doc");
////		ftp.cd("/Uploads");
////		ftp.pwd();
////		ftp.cd("/");
////		ftp.pwd();
////		String file = "/Uploads/geng/runqian_api/stylesheet.css";
////		boolean flag = ftp.downloadFile(file, "D:\\ws\\stylesheet.css");
////		if(flag){//下载成功
////			ftp.deleteRemoteFile(file);
////		}
//		
//		ftp.downloadFile("/Uploads/ff8080810f84270e010f94ba31e50020/未标题-1.jpg","C:\\Documents and Settings\\Administrator\\桌面\\未标题-1.jpg");
//		ftp.disconnect();	
//		System.out.println(1<<10);
	}

	public void pwd() {
		// TODO Auto-generated method stub
		try {
			String pwd = ftpClient.printWorkingDirectory();
			logger.info("current path:" + pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cd(String pathname){
		try {
			boolean result = ftpClient.changeWorkingDirectory(pathname);
			if(result){
				logger.info("切换路径成功,当前路径：" + ftpClient.printWorkingDirectory());
			}else{
				logger.info("切换路径失败：" + ftpClient.printWorkingDirectory());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
