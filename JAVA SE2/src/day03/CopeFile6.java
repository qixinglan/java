package day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * �����ֽ����������
 * @author asus
 *
 */
public class CopeFile6 {
	public static void main(String[] args) throws IOException {
		/**
		 * 
		 * ���Ƹ��ļ�Ŀ¼�µ�CopeFile5��Java
		 */
		FileInputStream fis=new FileInputStream("CopeFile5.java");
		InputStreamReader irs=new InputStreamReader(fis);
		BufferedReader br=new BufferedReader(irs);//���ڵ���Ҫ�����ַ�Ϊ��λ�������ֽ���Ҫ�����ַ�Ϊ��λ����
		/**
		 * 
		 */
		
		FileOutputStream fos=new FileOutputStream("copy_CopyFile5.java");
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osw);//���ڵ���Ҫ�����ַ�Ϊ��λ
		
		String str=null;
		br.readLine();//һ�ζ�һ�У�ֱ�����з�Ϊֹ��Ȼ�󽫻��з�֮ǰ���ַ�������ַ������أ�ע�ⷵ��ֵ�в��������з�������nullʱ��ʾEOF
		while((str=br.readLine())!=null){
			System.out.println(str);
			bw.write(str);//ֱ�ӽ�һ���ַ���д��
			bw.newLine();//������з�
		}
	
		
		br.close();
		bw.close();
	}
}
