package day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.print.Doc;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
public class Testcase {
	
	public static void main(String[] args) {
	} 
	@Test
	public  void testSAXReadr() throws Exception{
		InputStream in=new FileInputStream("books.xml");
		SAXReader reader=new SAXReader();
		Document doc=reader.read(in);
		in.close();
		System.out.println(doc.asXML());
		
	}
	@Test
	public void testGetName() throws Exception{
		SAXReader  reader=new SAXReader();
		Document doc=reader.read(new File("contacts.xml"));
		Element root=doc.getRootElement();
		//root.elements();����ȫ����Ԫ�أ�list����<Element>
		//root.element(name)����ָ�����ֵ���Ԫ��
		//root.content()����ȫ���ĺ���
		List<Element>list=root.elements();
		Element first=list.get(0);
		//������������Ԫ��
		Element name=first.element("name");
		//name.getText();��ȡԪ�ص��ı�
		System.out.println(name.getTextTrim());
		//ֱ���õ�Ԫ�ص��ı�
		String s=first.elementTextTrim("name");
		System.out.println(s);
		System.out.println(first.elementText("name"));
	}
	@Test
	public void testAttr() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		Element root=doc.getRootElement();
		List<Element> list=root.elements();
		Element book1=list.get(0);
		System.out.println(book1.asXML());
		//��ȡָ��������
		Attribute attr=book1.attribute("language");
		Attribute attr2=book1.attribute(0);
		//������Զ�������ֺ�ֵ
		System.out.println(attr.getName());
		System.out.println(attr.getValue());
		System.out.println(attr2.getName());
		System.out.println(attr2.getValue());
	}
	@Test
	public void testCreateXML() throws Exception{
		//ʹ����дxml
		PrintWriter out=new PrintWriter(new File("demo.xml"));
		out.println("<?xml version=\"1\" encoding=\"UTF-8\">");
		out.println("<config������>");
		out.close();
		//ʹ������XML
		InputStreamReader in=new InputStreamReader(new FileInputStream(new File("demo.xml")));
		BufferedReader reader=new BufferedReader(in);
		String s=null;
		while((s=reader.readLine())!=null)
		System.out.println(s);
		reader.close();
	}
	@Test
	//д��Ԫ�غ�����
	public void testModifyXML() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("contacts.xml"));
		Element root=doc.getRootElement();
		Element contact=root.addElement("contact");
		contact.addAttribute("id", "3");
		contact.addElement("name").addText("nemo");
		contact.addElement("phone").addText("12345");
		contact.addElement("address").addText("����");
		System.out.println(doc.asXML());
		
		//д�ļ�
		FileOutputStream out=new FileOutputStream("contacts2.xml");
		//���������ʽ��Ư���ĸ�ʽ
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(out, format);
		writer.write(doc);
		out.close();
		
	}
	@Test
	//�����µ�Document
	public void textNewDoc() throws Exception{
		//�����µ�DocumentԪ��
		//��Ӹ�Ԫ��
		//������Ԫ��   ����
		//�����ļ�
		
		Document doc=DocumentHelper.createDocument();
		Element root=doc.addElement("datesource");
		root.addAttribute("type", "oracle");
		root.addElement("user").addText("orcl");
		root.addElement("pwd").addText("123");
		//�����ļ�
		FileOutputStream out=new FileOutputStream("ds.xml");
		//���������ʽ��Ư���ĸ�ʽ
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(out,format);
		
		writer.write(doc);
		out.close();
		
	}
	@Test
	//����xpath
	public void TestXpath() throws Exception {
		System.out.println("ddw");
		String xpath="/contacts/contact[1]/name";
		File file=new File("contacts.xml");
		SAXReader reader=new SAXReader();
		Document doc=reader.read(file);
		System.out.println("ddw");
		Element e=(Element) doc.selectSingleNode(xpath);
		System.out.println("ddw");
		//Element e2=(Element) doc.selectNodes(xpath);
		System.out.println("ddw");
		System.out.println(e.asXML());
		//System.out.println(e2.asXML());
	}
	
	@Test
	public void testXPath1() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="//books";
		List<Element> list=doc.selectNodes(xpath);
		for(Element e:list){
			System.out.println(e.asXML());
		}
		String path="/books";
		Node e2=doc.selectSingleNode(path);
		System.out.println(e2.asXML());
	}
	@Test
	public void testXPath2() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="//book/@language";
		//String xpath="//@language";
		List<Attribute> list=doc.selectNodes(xpath);
		for(Attribute a:list){
			System.out.println(a.asXML());
		}
	}
	@Test
	public void testXPath3() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="books/book";
		List<Element>list=doc.selectNodes(xpath);
		for(Element e:list){
			System.out.println(e.asXML());
		}
		Element root=doc.getRootElement();
		xpath="./book";//���ߡ�book��
		List<Element>list2=root.selectNodes(xpath);
		for(Element e:list2){
			System.out.println(e.asXML());
		}
	}
	@Test
	public void testXPath4() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="books/book[1]/name";
		Element e=(Element) doc.selectSingleNode(xpath);
		System.out.println(e.asXML());
		String xpath2="books/book[@language='ENG']/name";
		//�ж�����ͷ���һ����Sing
		Element e2=(Element) doc.selectSingleNode(xpath2);
		System.out.println(e2.asXML());
		//����book����Ԫ��price��text>30��book
		String xpath3="books/book[price>30]";
		//String xpath3="books/book[.>30]";
		
		//�ж�����ͷ���һ����Sing
		Element e3=(Element) doc.selectSingleNode(xpath3);
		System.out.println(e3.asXML());
	}
	@Test
	public void testXPath5() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="node()/book";//(*/book)
		//*=node()=//
		//@*��ʾƥ����������ֵ
		List<Element>list=doc.selectNodes(xpath);
		for(Element e:list){
			System.out.println(e.asXML());
		}
		//@*��ʾƥ����������ֵ
		String xpath2="//@*";
		List<Attribute>list2=doc.selectNodes(xpath2);
		for(Attribute a:list2){
			System.out.println(a.asXML());
		}
		//*ֻ��ʾ����Ԫ��
		String xpath3="/catalogs/catalog[@type='book']/price[price<50]";
		List<Element>list3=doc.selectNodes(xpath3);
		for(Element e:list3){
			System.out.println(e.asXML());
		}
	}
}
