package md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD {
	public String getMD5(String str1) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("md5");
		byte[] buf=md.digest(str1.getBytes());
		BASE64Encoder encoder=new BASE64Encoder();
		return encoder.encode(buf);
	}
}
