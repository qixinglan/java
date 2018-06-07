package day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;

public class Code implements Serializable{
	String code="";
	BufferedImage image;
	public boolean verify(String input){
		return code.equalsIgnoreCase(input);
	}
	public void init(){
		initCode();
		image=initImage(code);
	}
	private BufferedImage initImage(String code) {
		// TODO Auto-generated method stub
		BufferedImage img=new BufferedImage(80, 30, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g=img.getGraphics();
		g.setColor(new Color(0xffffff));
		g.fillRect(0, 0, 80, 30);
		g.setColor(new Color(0xEDBE00));
		g.drawString(code, 10, 25);
		for(int i=0;i<5;i++){
			int c=new Random().nextInt(0xffffff);
			g.setColor(new Color(c));
			g.drawLine((int)Math.random()*80,(int)Math.random()*30, (int)Math.random()*80,(int) Math.random()*30);
		}
		return img;
	}
	//产生验证码
	private String initCode() {
		// TODO Auto-generated method stub
		String s="QWERTYUOPLKJHGFDSAXCVBNMqwertyuipasdfghjkzxcvbnm3456789";
		Random r=new Random();
		for(int i=0;i<5;i++){
			code+=s.charAt(r.nextInt(s.length()));
		}
		return code;
	}
	//将当前验证码图片保存在流中
	public void save(OutputStream out) throws IOException{
		ImageIO.write(image, "png", out);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
