
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * ʹ���ַ���������������ı��ļ���ֻ�ܣ�
 * @author asus
 *
 */
public class CopeFile5 {
	public static void main(String[] args) throws IOException {
		/**
		 * 1����һ���ļ��ֽ������������ڶ�ȡ��ǰ�ļ�
		 * 2���ֽ���������װ���ַ������������ַ�Ϊ��λ��ȡ����
		 * 3����һ���ļ��ֽ������������д�븴�Ƶ��ļ�
		 * 4���ֽ��������װ���ַ�����������ַ�Ϊ��λд����
		 * 5ѭ���ӵ�ǰ�ļ���ȡ�ַ���д��Ҫ���Ƶ��ļ�
		 * 6�ر����������
		 */
		FileInputStream fis=new FileInputStream("src"+File.separator+
				"day02"+File.separator+"CopeFile5.java");
		InputStreamReader reader=new InputStreamReader(fis);
		FileOutputStream fos=new FileOutputStream("CopeFile5.java");
		OutputStreamWriter writer=new OutputStreamWriter(fos);
		int i=0;
		while((i=reader.read())!=-1){
			writer.write(i);
		}
		/**
		 * reader��һ����ȡ�ַ��ķ���
		 * int read(char[] chs)
		 * ���ض�ȡ����ʵ���ַ���
		 * ����ֵΪ-1����Ϊ EOF
		 */
//		char[] chs=new char[5];
//		int len=0;
//		while((len=reader.read(chs))!=-1){
//			writer.write(chs, 0, len);
//		}
		reader.close();
		writer.close();
		
	}
}
