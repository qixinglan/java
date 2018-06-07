package com.nci.dcs.common.utils;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class EncodingUtil {

	private static Logger logger = LoggerFactory.getLogger(EncodingUtil.class);
	
	public static String encryptKey="CETCcETcCetCcetc";
	public static String ivKey="cetcCetCcETcCETC";

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */

	public static String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	public static byte[] base64Decode(String message) throws IOException {
		return new BASE64Decoder().decodeBuffer(message);
	}

	/**
	 * Description: AES的CBC加密
	 * 
	 * @author Shuzz
	 * @since 2015年7月22日下午4:05:45
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @param ivKey
	 *            向量密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey,
			String ivKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE,
				new SecretKeySpec(encryptKey.getBytes(), "AES"), iv);

		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * Description:AES的CBC加密
	 * 
	 * @author Shuzz
	 * @since 2015年7月22日下午4:28:45
	 * @param encryptBytes
	 *            加密后的内容
	 * @param decryptKey
	 *            解密密钥
	 * @param ivKey
	 *            向量密钥
	 * @return
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes,
			String decryptKey, String ivKey) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.DECRYPT_MODE,
				new SecretKeySpec(decryptKey.getBytes(), "AES"), iv);
		byte[] decryptBytes = cipher.doFinal(encryptBytes);

		return new String(decryptBytes);
	}

	/**
	 * Description:AES加密后base64
	 * 
	 * @author Shuzz
	 * @since 2015年7月22日下午4:43:27
	 * @param content
	 *            待加密内容
	 * @param encryptKey
	 *            本地密钥
	 * @param ivKey
	 *            向量密钥
	 * @return
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey,
			String ivKey) {
		try {
			return base64Encode(aesEncryptToBytes(content, encryptKey, ivKey));
		} catch (Exception e) {
			logger.error("AES混合base64加密" + content + "出错", e);
			return null;
		}
	}

	/**
	 * Description:将base64后的字符串AES解密
	 * 
	 * @author Shuzz
	 * @since 2015年7月22日下午4:42:31
	 * @param encrypt
	 *            加密后的内容
	 * @param decryptKey
	 *            本地密钥
	 * @param ivKey
	 *            向量密钥
	 * @return
	 */
	public static String aesDecrypt(String encrypt, String decryptKey,
			String ivKey) {
		try {
			return aesDecryptByBytes(base64Decode(encrypt), decryptKey, ivKey);
		} catch (Exception e) {
			logger.error("AES混合base64解密" + encrypt + "出错", e);
			return null;
		}
	}

	/**
	 * Description:md5加密
	 * 
	 * @author Shuzz
	 * @since 2015年8月11日上午10:09:43
	 * @param content
	 *            待加密内容
	 * @return
	 */
	public static byte[] md5Encode(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(content.getBytes("utf-8"));
		} catch (Exception e) {
			logger.error("AES混合base64解密" + content + "出错", e);
			return null;
		}
	}

	/**
	 * Description:md5加密后base64
	 * 
	 * @author Shuzz
	 * @since 2015年8月11日上午10:12:01
	 * @param content
	 *            待加密内容
	 * @return
	 */
	public static String md5Base64(String content) {
		try {
			return base64Encode(md5Encode(content));
		} catch (Exception e) {
			logger.error("AES混合base64解密" + content + "出错", e);
			return null;
		}
	}
}
