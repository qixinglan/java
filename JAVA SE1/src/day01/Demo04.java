package day01;

public class Demo04 {
	public static void main(String[] args) {
		String name="Tom   g \t";
		name=name.trim();
		System.out.println(name );
		String file="photo.png";
		boolean isPng=file.endsWith(".png");
		System.out.println(isPng);
		String cmd="get photo.png";
		if(cmd.startsWith("get ")){
			System.out.println("��������");
		}else if(cmd.startsWith("ls")){
			System.out.println("��Ŀ¼�б�");
		}
		String s="123";
		System.out.println(s.length());
	}
}
