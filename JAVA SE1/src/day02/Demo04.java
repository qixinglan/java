package day02;
//StringBuilder
public class Demo04 {
	public static void main(String[] args) {
		StringBuilder buf=new StringBuilder();
		buf.append("大师李敖").append("哈哈哈").append("胡一萌").append("很漂亮");
		buf.delete(0, 2);
		buf.insert(2, "先生");
		String s=buf.toString();
		System.out.println(buf);
		System.out.println(s);
	}
}
