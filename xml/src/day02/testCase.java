package day02;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class testCase {
	
	public static void main(String[] args) {
		for(int i=0;i<9;i++){
			if(--i<5) System.out.println(1);
		}
	}

	@Test
	public void testContents() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("contacts.xml"));
		Element root=doc.getRootElement();
		List<Element> list=root.elements();
		Element first=list.get(0);
		List<Node> children=first.content();//获取第一个元素的全部孩子
		System.out.println("数量"+children.size());
		int i=1;
		for(Node n:children){
			System.out.println(i+++""+n.getNodeTypeName()+"\t"+n.asXML());
		}
	}
	
}
