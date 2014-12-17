package tools;

import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLParser
{
	private String file=null;
	
	public XMLParser(String file)
	{
		this.file=file;
	}
	
	public  String getData(String elementName){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element root = doc.getRootElement();
            
            List<Element> param = root.elements();
            
            for (Element element : param) 
            {
            	if(element.getName().equals(elementName))
            		return String.valueOf(element.getData());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
	

	public static void main(String[] args)
	{
		XMLParser xmlParser=new XMLParser("D:\\workspace\\xixi\\src\\conf\\dbconfig.xml");
		System.out.println(xmlParser.getData("DBPort"));
	}

}
