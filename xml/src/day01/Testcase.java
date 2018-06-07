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
		//root.elements();返回全部子元素，list类型<Element>
		//root.element(name)返回指定名字的子元素
		//root.content()返回全部的孩子
		List<Element>list=root.elements();
		Element first=list.get(0);
		//根据名字找子元素
		Element name=first.element("name");
		//name.getText();获取元素的文本
		System.out.println(name.getTextTrim());
		//直接拿到元素的文本
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
		//获取指定的属性
		Attribute attr=book1.attribute("language");
		Attribute attr2=book1.attribute(0);
		//获得属性对象的名字和值
		System.out.println(attr.getName());
		System.out.println(attr.getValue());
		System.out.println(attr2.getName());
		System.out.println(attr2.getValue());
	}
	@Test
	public void testCreateXML() throws Exception{
		//使用流写xml
		PrintWriter out=new PrintWriter(new File("demo.xml"));
		out.println("<?xml version=\"1\" encoding=\"UTF-8\">");
		out.println("<config哈哈哈>");
		out.close();
		//使用流读XML
		InputStreamReader in=new InputStreamReader(new FileInputStream(new File("demo.xml")));
		BufferedReader reader=new BufferedReader(in);
		String s=null;
		while((s=reader.readLine())!=null)
		System.out.println(s);
		reader.close();
	}
	@Test
	//写入元素和属性
	public void testModifyXML() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("contacts.xml"));
		Element root=doc.getRootElement();
		Element contact=root.addElement("contact");
		contact.addAttribute("id", "3");
		contact.addElement("name").addText("nemo");
		contact.addElement("phone").addText("12345");
		contact.addElement("address").addText("北京");
		System.out.println(doc.asXML());
		
		//写文件
		FileOutputStream out=new FileOutputStream("contacts2.xml");
		//设置输出格式：漂亮的格式
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(out, format);
		writer.write(doc);
		out.close();
		
	}
	@Test
	//创建新的Document
	public void textNewDoc() throws Exception{
		//创建新的Document元素
		//添加根元素
		//增加子元素   属性
		//保存文件
		
		Document doc=DocumentHelper.createDocument();
		Element root=doc.addElement("datesource");
		root.addAttribute("type", "oracle");
		root.addElement("user").addText("orcl");
		root.addElement("pwd").addText("123");
		//保存文件
		FileOutputStream out=new FileOutputStream("ds.xml");
		//设置输出格式：漂亮的格式
		OutputFormat format=OutputFormat.createPrettyPrint();
		XMLWriter writer=new XMLWriter(out,format);
		
		writer.write(doc);
		out.close();
		
	}
	@Test
	//检索xpath
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
		xpath="./book";//或者“book”
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
		//有多个但就返回一个用Sing
		Element e2=(Element) doc.selectSingleNode(xpath2);
		System.out.println(e2.asXML());
		//返回book的子元素price的text>30的book
		String xpath3="books/book[price>30]";
		//String xpath3="books/book[.>30]";
		
		//有多个但就返回一个用Sing
		Element e3=(Element) doc.selectSingleNode(xpath3);
		System.out.println(e3.asXML());
	}
	@Test
	public void testXPath5() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		String xpath="node()/book";//(*/book)
		//*=node()=//
		//@*表示匹配任意属性值
		List<Element>list=doc.selectNodes(xpath);
		for(Element e:list){
			System.out.println(e.asXML());
		}
		//@*表示匹配任意属性值
		String xpath2="//@*";
		List<Attribute>list2=doc.selectNodes(xpath2);
		for(Attribute a:list2){
			System.out.println(a.asXML());
		}
		//*只表示任意元素
		String xpath3="/catalogs/catalog[@type='book']/price[price<50]";
		List<Element>list3=doc.selectNodes(xpath3);
		for(Element e:list3){
			System.out.println(e.asXML());
		}
	}
}
