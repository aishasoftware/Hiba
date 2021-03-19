package aisha.util;

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
import aisha.util.bean.Field;

public class getFromXML {

	public static List<Field> getFormFields(String beanName) throws ParserConfigurationException, SAXException, IOException {

		ArrayList<Field> result = new ArrayList<>();

		/*File fXmlFile = new File(
				Thread.currentThread().getContextClassLoader().getResource("resources/appForm_1.xml").getFile());
		*/
		
		File fXmlFile = new File("C:\\Users\\shishi\\Desktop\\Talent.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(fXmlFile);

		doc.getDocumentElement().normalize();

		NodeList ParentTag = doc.getElementsByTagName("fields");

		for (int temp = 0; temp < ParentTag.getLength(); temp++) {

			Node childFieldTag = ParentTag.item(temp);
			Element childFieldElement = (Element) childFieldTag;
			if (childFieldTag.getNodeType() == Node.ELEMENT_NODE) {
				NodeList FieldsNodeList = childFieldElement.getElementsByTagName("field");

				for (int temp1 = 0; temp1 < FieldsNodeList.getLength(); temp1++) {

					Node singleFieldNode = FieldsNodeList.item(temp1);
					//Element FieldElement = (Element) singleFieldNode;
					if (singleFieldNode.getNodeType() == Node.ELEMENT_NODE) {
						//NodeList FieldNodeList = FieldElement.getElementsByTagName("field");

						Field myField = new Field();
						Element fieldElement = (Element) FieldsNodeList.item(temp1);
						if (fieldElement.getElementsByTagName("fID").item(0) != null)
							myField.setfID(fieldElement.getElementsByTagName("fID").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fContentArabic").item(0) != null)
							myField.setfContentArabic(
									fieldElement.getElementsByTagName("fContentArabic").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fContentEnglish").item(0) != null)
							myField.setfContentEnglish(
									fieldElement.getElementsByTagName("fContentEnglish").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("fType").item(0) != null)
							myField.setfType(fieldElement.getElementsByTagName("fType").item(0).getTextContent());
						if (fieldElement.getElementsByTagName("saveInField").item(0) != null)
							myField.setSaveInField(
									fieldElement.getElementsByTagName("saveInField").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fBoxSize").item(0) != null)
							myField.setfBoxSze(fieldElement.getElementsByTagName("fBoxSize").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fMaxLength").item(0) != null)
							myField.setfMaxlength(
									fieldElement.getElementsByTagName("fMaxLength").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fRegExpr").item(0) != null)
							myField.setfRegExpr(fieldElement.getElementsByTagName("fRegExpr").item(0).getTextContent());

						if (fieldElement.getElementsByTagName("fRequired").item(0) != null)
							myField.setfRequired((
									fieldElement.getElementsByTagName("fRequired").item(0).getTextContent()));

						if (fieldElement.getElementsByTagName("fEnabledFor").item(0) != null)
							myField.setfEnabledFor((
									fieldElement.getElementsByTagName("fEnabledFor").item(0).getTextContent()));

						if (fieldElement.getElementsByTagName("fAccessedBy").item(0) != null)
							myField.setfAccessedBy((
									fieldElement.getElementsByTagName("fAccessedBy").item(0).getTextContent()));

						result.add(myField);

					}

				}

			}

		}

		return result;

	}

}
