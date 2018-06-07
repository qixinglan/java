import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;


public class Testcase {
	public static void main(String[] args) {
		
	}
	@Test
	public void testSAXReader() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("books.xml"));
		System.out.println(doc.asXML());
	}
}
