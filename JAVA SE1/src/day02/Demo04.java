package day02;
//StringBuilder
public class Demo04 {
	public static void main(String[] args) {
		StringBuilder buf=new StringBuilder();
		buf.append("��ʦ�").append("������").append("��һ��").append("��Ư��");
		buf.delete(0, 2);
		buf.insert(2, "����");
		String s=buf.toString();
		System.out.println(buf);
		System.out.println(s);
	}
}
