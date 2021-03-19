package aisha.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class getFromXML {


	public static List<Question>  getFormFields(int appID) throws ParserConfigurationException, SAXException, IOException 
	{
			

		ArrayList<Question> result = new ArrayList<>();
		//File fXmlFile = new File("C:\\Users\\shishi\\Desktop\\appForm_1.xml");
	/*	File fXmlFile = new File(Thread.currentThread().getContextClassLoader()
				.getResource("resources/appForm_1.xml").getFile());*/
		 final File fXmlFile = new File("C://Users//hp//Desktop//DS//" + appID + ".xml");
		//File fXmlFile = new File("innovation\\apache-tomcat-8.5.43\\webapps\\TechInc\\appForm_1.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();
		NodeList ParentTag = doc.getElementsByTagName("questions");
		
		for (int temp = 0; temp < ParentTag.getLength(); temp++) {

			Node childQuestionTag = ParentTag.item(temp);
			Element childQuestionElement = (Element) childQuestionTag;
			if (childQuestionTag.getNodeType() == Node.ELEMENT_NODE) {
				NodeList questionsNodeList = childQuestionElement.getElementsByTagName("question");

				for (int temp1 = 0; temp1 < questionsNodeList.getLength(); temp1++) {
					
					Node singleQuestionNode = questionsNodeList.item(temp1);
					Element questionElement = (Element) singleQuestionNode;
					if (singleQuestionNode.getNodeType() == Node.ELEMENT_NODE) {
						NodeList questionNodeList = questionElement
								.getElementsByTagName("question");
						
						
						Question myField = new Question();
							Element fieldElement = (Element) questionsNodeList.item(temp1);
							if (fieldElement.getElementsByTagName("qID").item(0) != null)
								myField.setqID(fieldElement
										.getElementsByTagName("qID").item(0)
										.getTextContent());
							
							if (fieldElement.getElementsByTagName("qContentArabic").item(0) != null)
								myField.setqContentArabic(fieldElement
										.getElementsByTagName("qContentArabic").item(0)
										.getTextContent());
							
						if (fieldElement.getElementsByTagName("qContent").item(0) != null)
								myField.setqContent(fieldElement
										.getElementsByTagName("qContent").item(0)
										.getTextContent());
							if (fieldElement.getElementsByTagName("qType").item(0) != null)
								myField.setqType(fieldElement
										.getElementsByTagName("qType").item(0)
										.getTextContent());
							if (fieldElement.getElementsByTagName("saveInField").item(0) != null)
								myField.setSaveInField(fieldElement
										.getElementsByTagName("saveInField").item(0)
										.getTextContent());
						
							System.err.println("questions list before: " + result);
							System.err.println("### myField : " + myField);
					
							result.add(myField);
							
							System.err.println("questions list after: " + result);
							}
				
							}
				
				
						}
	
			}
		


return result;


					}

			
	

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("@@@@@@@@ hwa main" );

	
	}
	
	}
